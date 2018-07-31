package com.jokyxray.dailywp.uitls;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.ResponseBody;

public class FileHelper {

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
