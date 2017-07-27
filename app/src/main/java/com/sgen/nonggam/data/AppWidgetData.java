package com.sgen.nonggam.data;

import java.io.Serializable;

/**
 * Created by SeRan on 2016-07-03.
 */
public class AppWidgetData implements Serializable {

    public String area;
    public String degree;
    public String summary;
    public String compareYesterday;
    public String comment;
    public String warningComment;
    public String backgroundImg;
    public String announceTime;

    @Override
    public String toString() {
        return "AppWidgetData{" +
                "area='" + area + '\'' +
                ", degree='" + degree + '\'' +
                ", summary='" + summary + '\'' +
                ", compareYesterday='" + compareYesterday + '\'' +
                ", comment='" + comment + '\'' +
                ", warningComment='" + warningComment + '\'' +
                ", backgroundImg='" + backgroundImg + '\'' +
                ", announceTime='" + announceTime + '\'' +
                '}';
    }
}
