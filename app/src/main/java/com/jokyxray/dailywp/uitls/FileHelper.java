package com.jokyxray.dailywp.uitls;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class FileHelper {
	public static void saveDailyImage(Context context,String destPath, String srcPath) {
		File srcFile = new File(srcPath);
		if (!srcFile.exists()) {
			Toast.makeText(context, "保存失败，图片文件不存在", Toast.LENGTH_LONG).show();
			return;
		}
		File destFile = new File(destPath);
		File parent = destFile.getParentFile();
		if (!parent.exists()) {
			parent.mkdirs();
		}
		asynCopyFileTo(context,srcFile, destFile);
	}

	private static void asynCopyFileTo(Context context,File srcFile, File destFile) {
		Observable.just(srcFile).subscribeOn(Schedulers.io()).subscribe(file -> {
			boolean success = copyFileTo(file, destFile);
			if(success){
				sendScanFileBoardcast(context, destFile);
				new Handler(Looper.getMainLooper()).post(() -> Toast.makeText(context, "保存成功", Toast.LENGTH_LONG).show());
			}
		});
	}

	public static boolean copyFileTo(File srcFile, File destFile) {
		boolean result = true;
		FileInputStream fis = null;
		FileOutputStream fos = null;
		File parent = destFile.getParentFile();
		if (!parent.exists()) {
			parent.mkdirs();
		}
		try {
			fis = new FileInputStream(srcFile);
			fos = new FileOutputStream(destFile, false);
			int readLen;
			byte[] buf = new byte[1024];
			while ((readLen = fis.read(buf)) != -1) {
				fos.write(buf, 0, readLen);
			}
		} catch (Exception e) {
			result = false;
			if (fos != null) {
				try {
					fos.close();
					fos = null;
				} catch (IOException e1) {
				}
			}
			destFile.delete();
		} finally {
			try {
				if (fos != null) {
					fos.close();
				}
				if (fis != null) {
					fis.close();
				}
			} catch (IOException e) {
			}
		}
		return result;
	}

	public static void sendScanFileBoardcast(Context context, File file) {
		Intent scanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
		scanIntent.setData(Uri.fromFile(file));
		context.sendBroadcast(scanIntent);
	}

	public static boolean writeResponseBodyToDisk(ResponseBody body, String path) {
		File temp = null;
		InputStream inputStream = null;
		OutputStream outputStream = null;
		try {
			temp = new File(path+System.currentTimeMillis());
			File parent = temp.getParentFile();
			if(!parent.exists()){
				parent.mkdirs();
			}else if(temp.exists()){
				temp.delete();
			}

			byte[] fileReader = new byte[8192];
			inputStream = body.byteStream();
			outputStream = new FileOutputStream(temp);
			while (true) {
				int read = inputStream.read(fileReader);
				if (read == -1) {
					break;
				}
				outputStream.write(fileReader, 0, read);
			}
			outputStream.flush();
			File file = new File(path);

			if(file.exists()){
				file.delete();
			}
			temp.renameTo(file);
			return true;
		} catch (IOException e) {
			if(temp != null){
				temp.delete();
			}
			return false;
		}finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
				}
			}
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
				}
			}
		}
	}
}
