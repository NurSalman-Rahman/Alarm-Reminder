package com.example.alarm.model;

import android.support.v4.app.INotificationSideChannel;

public class InformationAlarm {
    String mName;
    String mwhen;
    String mtime;

    public InformationAlarm(String mName, String mwhen, String mtime)
    {
        this.mName = mName;
        this.mwhen = mwhen;
        this.mtime = mtime;
    }




    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getMwhen() {
        return mwhen;
    }

    public void setMwhen(String mwhen) {
        this.mwhen = mwhen;
    }

    public String getMtime() {
        return mtime;
    }

    public void setMtime(String mtime) {
        this.mtime = mtime;
    }
}
