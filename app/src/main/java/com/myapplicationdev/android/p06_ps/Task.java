package com.myapplicationdev.android.p06_ps;

import java.io.Serializable;

public class Task implements Serializable {
    private int id;
    private String name;
    private String desc;
    private int time;

    public Task(int id, String name, String desc, int time) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public int getTime() {
        return time;
    }
}
