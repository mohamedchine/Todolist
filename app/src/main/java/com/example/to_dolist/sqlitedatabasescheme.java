package com.example.to_dolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class sqlitedatabasescheme extends SQLiteOpenHelper{
    private Context c;
    private static sqlitedatabasescheme db;
    public static final String DATABASE_NAME = "todolist";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME_TASKS = "tasks";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TASK = "task";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_TERMINATED = "terminated";

    public sqlitedatabasescheme(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME_TASKS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TASK + " TEXT, " +
                COLUMN_DESCRIPTION + " TEXT, " +
                COLUMN_DATE + " TEXT, " +
                COLUMN_TERMINATED + " TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void insertTask(task newTask) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, newTask.getId());
        values.put(COLUMN_TASK, newTask.getTasktitle());
        values.put(COLUMN_DESCRIPTION,newTask.getDescription());
        values.put(COLUMN_DATE, newTask.getDeadline());
        values.put(COLUMN_TERMINATED, newTask.getTerminated());
        db.insert(TABLE_NAME_TASKS, null, values);
    }

    public void repopulate() {
        SQLiteDatabase db = this.getWritableDatabase();
            Cursor result = db.rawQuery("SELECT * FROM " + TABLE_NAME_TASKS, null);

            if (result.getCount() != 0) {
                while (result.moveToNext()) {
                    int id = result.getInt(0);
                    String title = result.getString(1);
                    String description = result.getString(2);
                    String date = result.getString(3);
                    String ter = result.getString(4);
                    task x = new task(id, title, date, ter, description);
                    task.tasklist.add(x);
                }
            }
        }
    public void updatetask(task updatedTask) {
        SQLiteDatabase db = this.getWritableDatabase();

        String updateQuery = "UPDATE " + TABLE_NAME_TASKS +
                " SET " +
                COLUMN_TASK + "='" + updatedTask.getTasktitle() + "', " +
                COLUMN_DESCRIPTION + "='" + updatedTask.getDescription() + "', " +
                COLUMN_DATE + "='" + updatedTask.getDeadline() + "', " +
                COLUMN_TERMINATED + "='" + updatedTask.getTerminated() + "' " +
                "WHERE " + COLUMN_ID + "=" + updatedTask.getId();

        db.execSQL(updateQuery);
    }
    public void deleteTask(int taskId) {
        SQLiteDatabase db = this.getWritableDatabase();
        String deleteQuery = "DELETE FROM " + TABLE_NAME_TASKS +
                " WHERE " + COLUMN_ID + "=" + taskId;
        db.execSQL(deleteQuery);
    }
}

