package com.example.jared.addingactivity;

import java.util.ArrayList;

/**
 * Created by Jared on 12/8/2017.
 */

public class List {

    public final static String TABLE_NAME = "listTable";
    public final static String COL_LIST_ID = "list_id";
    public final static String COL_TITLE = "title";
    public final static String COL_TYPE = "type";
    public final static String COL_DONE = "done";


    int listId;
    boolean done;
    String title, type;
    ArrayList<Task> tasks;

    public List() {

    }

    public List(int listId, boolean done, String title, String type){
        this();
        this.listId = listId;
        this.done = done;
        this.title = title;
        this.type = type;
    }

    public List(boolean done, String title, String type){
        this();
        this.done = done;
        this.title = title;
        this.type = type;
    }

    public int getListId() {
        return listId;
    }

    public void setListId(int listId) {
        this.listId = listId;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTasks(Task t){
        tasks.add(t);
    }

    public Task getTask(int taskId){
        for(Task t :tasks){
            if(t.taskId == taskId)
                return t;
        }
        return null;
    }

    public void removeTasks(int taskId){
        tasks.remove(taskId);
    }
}
