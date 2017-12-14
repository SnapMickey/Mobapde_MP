package com.example.jared.addingactivity;

/**
 * Created by Jared on 12/8/2017.
 */

public class Task {

    public final static String TABLE_NAME = "taskTable";
    public final static String COL_TASK_ID = "_id";
    public final static String COL_LIST_ID = "_id";
    public final static String COL_DESC = "description";
    public final static String COL_DONE = "active";
    public final static String COL_SEQ = "sequence";
    public final static String COL_LAT = "latitude";
    public final static String COL_LNG = "longtitude";

    int taskId, listId, seq;
    boolean done;
    String description;
    double latitude, longtitude;

    public Task() {

    }

    public Task(int taskId, int listId, int seq, boolean done, String description, double latitude, double longtitude){
        this();
        this.taskId = taskId;
        this.listId = listId;
        this.seq = seq;
        this.done = done;
        this.description = description;
        this.latitude = latitude;
        this.longtitude = longtitude;
    }

    public Task(int listId, int seq, boolean done, String description, double latitude, double longtitude){
        this();
        this.listId = listId;
        this.seq = seq;
        this.done = done;
        this.description = description;
        this.latitude = latitude;
        this.longtitude = longtitude;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getListId() {
        return listId;
    }

    public void setListId(int listId) {
        this.listId = listId;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }
}
