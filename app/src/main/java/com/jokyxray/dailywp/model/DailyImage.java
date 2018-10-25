package com.jokyxray.dailywp.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.List;

@Entity(indices = { @Index(value = {"sort"})})
public class DailyImage {

    /**
     * startdate : 20180726
     * fullstartdate : 201807261600
     * enddate : 20180727
     * url : /az/hprichbg/rb/SuperBlueBloodMoon_ZH-CN11881086623_1920x1080.jpg
     * urlbase : /az/hprichbg/rb/SuperBlueBloodMoon_ZH-CN11881086623
     * copyright : 2018年1月31日的月食的合成图像，日本东京 (© Kazuhiro Nogi/Getty Images)
     * copyrightlink : http://www.bing.com/search?q=%E6%9C%88%E9%A3%9F&form=hpcapt&mkt=zh-cn
     * quiz : /search?q=Bing+homepage+quiz&filters=WQOskey:%22HPQuiz_20180726_SuperBlueBloodMoon%22&FORM=HPQUIZ
     * wp : false
     * hsh : 19820cf2ded5e4b10e15a746f2df0d19
     * drk : 1
     * top : 1
     * bot : 1
     * hs : []
     */
    @NonNull
    @PrimaryKey
    private String hsh;
    private String title;
    private String startdate;
    private String fullstartdate;
    private String enddate;
    private String url;
    private String urlbase;
    private String copyright;
    private String copyrightlink;
    private String quiz;
    private int sort;
    @Ignore
    private boolean wp;
    @Ignore
    private int drk;
    @Ignore
    private int top;
    @Ignore
    private int bot;
    @Ignore
    private List<?> hs;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartdate() { return startdate;}

    public void setStartdate(String startdate) { this.startdate = startdate;}

    public String getFullstartdate() { return fullstartdate;}

    public void setFullstartdate(String fullstartdate) { this.fullstartdate = fullstartdate;}

    public String getEnddate() { return enddate;}

    public void setEnddate(String enddate) { this.enddate = enddate;}

    public String getUrl() { return url;}

    public void setUrl(String url) { this.url = url;}

    public String getUrlbase() { return urlbase;}

    public void setUrlbase(String urlbase) { this.urlbase = urlbase;}

    public String getCopyright() { return copyright;}

    public void setCopyright(String copyright) { this.copyright = copyright;}

    public String getCopyrightlink() { return copyrightlink;}

    public void setCopyrightlink(String copyrightlink) { this.copyrightlink = copyrightlink;}

    public String getQuiz() { return quiz;}

    public void setQuiz(String quiz) { this.quiz = quiz;}

    public boolean isWp() { return wp;}

    public void setWp(boolean wp) { this.wp = wp;}

    public String getHsh() { return hsh;}

    public void setHsh(String hsh) { this.hsh = hsh;}

    public int getDrk() { return drk;}

    public void setDrk(int drk) { this.drk = drk;}

    public int getTop() { return top;}

    public void setTop(int top) { this.top = top;}

    public int getBot() { return bot;}

    public void setBot(int bot) { this.bot = bot;}

    public List<?> getHs() { return hs;}

    public void setHs(List<?> hs) { this.hs = hs;}

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}
