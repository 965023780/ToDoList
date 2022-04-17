package com.example.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class AppInfo {
    @Id(autoincrement = true)
    private long id;
    private String theme;
    private String chartColor;
    @Generated(hash = 1823839841)
    public AppInfo(long id, String theme, String chartColor) {
        this.id = id;
        this.theme = theme;
        this.chartColor = chartColor;
    }
    @Generated(hash = 1656151854)
    public AppInfo() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getTheme() {
        return this.theme;
    }
    public void setTheme(String theme) {
        this.theme = theme;
    }
    public String getChartColor() {
        return this.chartColor;
    }
    public void setChartColor(String chartColor) {
        this.chartColor = chartColor;
    }
}
