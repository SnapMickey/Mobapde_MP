package com.example.jared.addingactivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Jared on 12/8/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String SCHEMA = "permitask";
    public static final int VERSION = 1;

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DatabaseHelper(Context context){
        super(context, SCHEMA, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql1 = "CREATE TABLE " + Task.TABLE_NAME + " (" +
                Task.COL_TASK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Task.COL_LIST_ID + " INTEGER, " +
                Task.COL_DONE + " INTEGER, " +
                Task.COL_SEQ + " INTEGER, " +
                Task.COL_DESC + " TEXT, " +
                Task.COL_LAT + " REAL, " +
                Task.COL_LNG + " REAL" +
                ");";

        db.execSQL(sql1);

        String sql2 = "CREATE TABLE " + List.TABLE_NAME + " (" +
                List.COL_LIST_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                List.COL_DONE + " INTEGER, " +
                List.COL_TITLE + " TEXT, " +
                List.COL_TYPE + " TEXT" +
                ");";

        db.execSQL(sql2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertTask(Task t){
        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor1 = db.query(Task.TABLE_NAME, null,
                Task.COL_DESC + " =? ", new String[]{String.valueOf(t.getDescription())}, null, null, null);

        Cursor cursor2 = db.query(Task.TABLE_NAME, null,
                Task.COL_LIST_ID + " =? ", new String[]{String.valueOf(t.getListId())}, null, null, null);

        Cursor cursor3 = db.query(Task.TABLE_NAME, null,
                Task.COL_LAT + " =? ", new String[]{String.valueOf(t.getLatitude())}, null, null, null);

        Cursor cursor4 = db.query(Task.TABLE_NAME, null,
                Task.COL_LNG + " =? ", new String[]{String.valueOf(t.getLongtitude())}, null, null, null);


        if(cursor1.getCount() == 0 || cursor2.getCount() == 0 || cursor3.getCount() == 0 || cursor4.getCount() == 0){
            ContentValues cv = new ContentValues();
            cv.put(Task.COL_DESC, t.getDescription());

            int done = 0;
            if(t.isDone())
                done = 1;

            cv.put(Task.COL_DONE, done);
            cv.put(Task.COL_SEQ, t.getSeq());
            cv.put(Task.COL_LIST_ID, t.getListId());
            cv.put(Task.COL_LAT, t.getLatitude());
            cv.put(Task.COL_LNG, t.getLongtitude());

            db.insert(Task.TABLE_NAME, null, cv);
        }

        db.close();
    }

    public long insertList(List l){
        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor1 = db.query(List.TABLE_NAME, null,
                List.COL_TITLE + " =? ", new String[]{String.valueOf(l.getTitle())}, null, null, null);

        Cursor cursor2 = db.query(List.TABLE_NAME, null,
                List.COL_TYPE + " =? ", new String[]{String.valueOf(l.getType())}, null, null, null);

        long listId = -1;
        if(cursor1.getCount() == 0 || cursor2.getCount() == 0){
            ContentValues cv = new ContentValues();
            cv.put(List.COL_TITLE, l.getTitle());

            int done = 0;
            if(l.isDone())
                done = 1;
            cv.put(List.COL_DONE, done);
            cv.put(List.COL_TYPE, l.getType());

            listId = db.insert(List.TABLE_NAME, null, cv);
        }

        db.close();
        return listId;
    }

    public Task queryTask(int taskId){
        SQLiteDatabase db = getReadableDatabase();
        Task task = new Task();

        Cursor cursor = db.query(Task.TABLE_NAME, null,
                Task.COL_TASK_ID + " =? ", new String[]{String.valueOf(taskId)}, null, null, null);
        if(cursor.moveToFirst()){
            task.setTaskId(cursor.getInt(cursor.getColumnIndex(Task.COL_TASK_ID)));
            task.setListId(cursor.getInt(cursor.getColumnIndex(Task.COL_LIST_ID)));
            task.setDone(1 == cursor.getInt(cursor.getColumnIndex(Task.COL_DONE)));
            task.setSeq(cursor.getInt(cursor.getColumnIndex(Task.COL_SEQ)));
            task.setDescription(cursor.getString(cursor.getColumnIndex(Task.COL_DESC)));
            task.setLatitude(cursor.getDouble(cursor.getColumnIndex(Task.COL_LAT)));
            task.setLongtitude(cursor.getDouble(cursor.getColumnIndex(Task.COL_LNG)));
        }

        cursor.close();
        db.close();

        return task;
    }

    public List queryList(int listId){
        SQLiteDatabase db = getReadableDatabase();
        List list = new List();

        Cursor cursor = db.query(Task.TABLE_NAME, null,
                Task.COL_LIST_ID + " =? ", new String[]{String.valueOf(listId)}, null, null, null);
        if(cursor.moveToFirst()){
            list.setListId(cursor.getInt(cursor.getColumnIndex(List.COL_LIST_ID)));
            list.setDone(1 == cursor.getInt(cursor.getColumnIndex(List.COL_DONE)));
            list.setTitle(cursor.getString(cursor.getColumnIndex(List.COL_TITLE)));
            list.setType(cursor.getString(cursor.getColumnIndex(List.COL_TYPE)));

            Cursor cursor1 = db.query(Task.TABLE_NAME, null,
                    Task.COL_LIST_ID + " =? ", new String[]{String.valueOf(list.getListId())}, null, null, null, null);

            ArrayList<Task> tasks = new ArrayList<>();

            if(cursor.moveToFirst()){
                do{
                    Task task = new Task();
                    task.setTaskId(cursor.getInt(cursor.getColumnIndex(Task.COL_TASK_ID)));
                    task.setListId(cursor.getInt(cursor.getColumnIndex(Task.COL_LIST_ID)));
                    task.setDone(1 == cursor.getInt(cursor.getColumnIndex(Task.COL_DONE)));
                    task.setSeq(cursor.getInt(cursor.getColumnIndex(Task.COL_SEQ)));
                    task.setDescription(cursor.getString(cursor.getColumnIndex(Task.COL_DESC)));
                    task.setLatitude(cursor.getDouble(cursor.getColumnIndex(Task.COL_LAT)));
                    task.setLongtitude(cursor.getDouble(cursor.getColumnIndex(Task.COL_LNG)));
                    tasks.add(task);

                }while(cursor.moveToNext());
            }

            list.setTasks(tasks);

            cursor1.close();
        }
        cursor.close();
        db.close();

        return list;
    }

    public void updateTask(Task t, int taskId){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Task.COL_DESC, t.getDescription());

        int done = 0;
        if(t.isDone())
            done = 1;

        cv.put(Task.COL_DONE, done);
        cv.put(Task.COL_SEQ, t.getSeq());
        cv.put(Task.COL_LIST_ID, t.getListId());
        cv.put(Task.COL_LAT, t.getLatitude());
        cv.put(Task.COL_LNG, t.getLongtitude());

        db.update(Task.TABLE_NAME, cv, Task.COL_TASK_ID + " =? ", new String[]{String.valueOf(taskId)});
        db.close();
    }

    public void updateList(List l, int listId){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(List.COL_TITLE, l.getTitle());

        int done = 0;
        if(l.isDone())
            done = 1;
        cv.put(List.COL_DONE, done);
        cv.put(List.COL_TYPE, l.getType());

        db.update(List.TABLE_NAME, cv, List.COL_LIST_ID + " =? ", new String[]{String.valueOf(listId)});
        db.close();
    }

    public void deleteTask(int taskId){
        SQLiteDatabase db = getWritableDatabase();

        db.delete(Task.TABLE_NAME, Task.COL_TASK_ID + " =? ", new String[]{String.valueOf(taskId)});
        db.close();
    }

    public void deleteList(int listId){
        SQLiteDatabase db = getWritableDatabase();

        db.delete(List.TABLE_NAME, List.COL_LIST_ID + " =? ", new String[]{String.valueOf(listId)});
        db.close();
    }

    public Cursor queryAllTasksAsCursor(){
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(Task.TABLE_NAME, null, null, null, null, null, null, null);

        return cursor;
    }

    public ArrayList<Task> queryAllTasks(){
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(Task.TABLE_NAME, null, null, null, null, null, null, null);

        ArrayList<Task> tasks = new ArrayList<>();

        if(cursor.moveToFirst()){
            do{
                Task task = new Task();
                task.setTaskId(cursor.getInt(cursor.getColumnIndex(Task.COL_TASK_ID)));
                task.setListId(cursor.getInt(cursor.getColumnIndex(Task.COL_LIST_ID)));
                task.setDone(1 == cursor.getInt(cursor.getColumnIndex(Task.COL_DONE)));
                task.setSeq(cursor.getInt(cursor.getColumnIndex(Task.COL_SEQ)));
                task.setDescription(cursor.getString(cursor.getColumnIndex(Task.COL_DESC)));
                task.setLatitude(cursor.getDouble(cursor.getColumnIndex(Task.COL_LAT)));
                task.setLongtitude(cursor.getDouble(cursor.getColumnIndex(Task.COL_LNG)));
                tasks.add(task);

            }while(cursor.moveToNext());
        }

        cursor.close();

        db.close();

        return tasks;
    }

    public Cursor queryAllListsAsCursor(){
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(List.TABLE_NAME, null, null, null, null, null, null, null);

        return cursor;
    }

    public ArrayList<List> queryAllLists(){
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(List.TABLE_NAME, null, null, null, null, null, null, null);

        ArrayList<List> lists = new ArrayList<>();

        if(cursor.moveToFirst()){
            do{
                List list = new List();
                list.setListId(cursor.getInt(cursor.getColumnIndex(List.COL_LIST_ID)));
                list.setDone(1 == cursor.getInt(cursor.getColumnIndex(List.COL_DONE)));
                list.setTitle(cursor.getString(cursor.getColumnIndex(List.COL_TITLE)));
                list.setType(cursor.getString(cursor.getColumnIndex(List.COL_TYPE)));
                lists.add(list);

                Cursor cursor1 = db.query(Task.TABLE_NAME, null,
                        Task.COL_LIST_ID + " =? ", new String[]{String.valueOf(list.getListId())}, null, null, null, null);

                ArrayList<Task> tasks = new ArrayList<>();

                if(cursor1.moveToFirst()){
                    do{
                        Task task = new Task();
                        task.setTaskId(cursor1.getInt(cursor1.getColumnIndex(Task.COL_TASK_ID)));
                        task.setListId(cursor1.getInt(cursor1.getColumnIndex(Task.COL_LIST_ID)));
                        task.setDone(1 == cursor1.getInt(cursor1.getColumnIndex(Task.COL_DONE)));
                        task.setSeq(cursor1.getInt(cursor1.getColumnIndex(Task.COL_SEQ)));
                        task.setDescription(cursor1.getString(cursor1.getColumnIndex(Task.COL_DESC)));
                        task.setLatitude(cursor1.getDouble(cursor1.getColumnIndex(Task.COL_LAT)));
                        task.setLongtitude(cursor1.getDouble(cursor1.getColumnIndex(Task.COL_LNG)));
                        tasks.add(task);

                    }while(cursor1.moveToNext());
                }

                list.setTasks(tasks);

                cursor1.close();
            }while(cursor.moveToNext());
        }

        cursor.close();

        db.close();

        return lists;
    }

    public ArrayList<Task> queryAllTasksOfList(int listId){
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(Task.TABLE_NAME, null, Task.COL_LIST_ID + " =? ", new String[]{String.valueOf(listId)}, null, null, null, null);

        ArrayList<Task> tasks = new ArrayList<>();

        if(cursor.moveToFirst()){
            do{
                Task task = new Task();
                task.setTaskId(cursor.getInt(cursor.getColumnIndex(Task.COL_TASK_ID)));
                task.setListId(cursor.getInt(cursor.getColumnIndex(Task.COL_LIST_ID)));
                task.setDone(1 == cursor.getInt(cursor.getColumnIndex(Task.COL_DONE)));
                task.setSeq(cursor.getInt(cursor.getColumnIndex(Task.COL_SEQ)));
                task.setDescription(cursor.getString(cursor.getColumnIndex(Task.COL_DESC)));
                task.setLatitude(cursor.getDouble(cursor.getColumnIndex(Task.COL_LAT)));
                task.setLongtitude(cursor.getDouble(cursor.getColumnIndex(Task.COL_LNG)));
                tasks.add(task);

            }while(cursor.moveToNext());
        }

        cursor.close();

        db.close();

        Collections.sort(tasks, new Comparator<Task>() {
            @Override
            public int compare(Task task, Task t1) {
                return t1.getSeq() - task.getSeq();
            }
        });

        return tasks;
    }

    public Cursor queryAllTasksOfListAsCursor(int listId){
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(Task.TABLE_NAME, null, Task.COL_LIST_ID + " =? ", new String[]{String.valueOf(listId)}, null, null, null, null);

        return cursor;
    }
}