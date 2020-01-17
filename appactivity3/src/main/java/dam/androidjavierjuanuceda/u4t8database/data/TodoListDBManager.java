package dam.androidjavierjuanuceda.u4t8database.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import dam.androidjavierjuanuceda.u4t8database.model.Task;

public class TodoListDBManager {

    private TodoListDBHelper todoListDBHelper;


    public TodoListDBManager(Context context) {
        this.todoListDBHelper = TodoListDBHelper.getInstance(context);
    }

    public void insert(String todo, String when, String description, int status, int progress) {

        SQLiteDatabase sqLiteDatabase = todoListDBHelper.getWritableDatabase();

        if (sqLiteDatabase != null) {
            ContentValues contentValue = new ContentValues();
            contentValue.put(TodoListDBContract.Tasks.TODO, todo);
            contentValue.put(TodoListDBContract.Tasks.TO_ACCOMPLISH, when);
            contentValue.put(TodoListDBContract.Tasks.DESCRIPTION, description);
            contentValue.put(TodoListDBContract.Tasks.STATUS, status);
            contentValue.put(TodoListDBContract.Tasks.PROGRESS, progress);
            sqLiteDatabase.insert(TodoListDBContract.Tasks.TABLE_NAME, null, contentValue);
        }
    }

    public void update(int id, String todo, String when, String description, int status, int progress) {

        SQLiteDatabase sqLiteDatabase = todoListDBHelper.getWritableDatabase();

        if (sqLiteDatabase != null) {

            ContentValues contentValue = new ContentValues();
            contentValue.put(TodoListDBContract.Tasks.TODO, todo);
            contentValue.put(TodoListDBContract.Tasks.TO_ACCOMPLISH, when);
            contentValue.put(TodoListDBContract.Tasks.DESCRIPTION, description);
            contentValue.put(TodoListDBContract.Tasks.STATUS, status);
            contentValue.put(TodoListDBContract.Tasks.PROGRESS, progress);
            sqLiteDatabase.update(TodoListDBContract.Tasks.TABLE_NAME, contentValue, TodoListDBContract.Tasks._ID + "=" + id, null);
        }
    }

    public void delete(int id) {

        SQLiteDatabase sqLiteDatabase = todoListDBHelper.getWritableDatabase();

        if (sqLiteDatabase != null) {
            sqLiteDatabase.delete(TodoListDBContract.Tasks.TABLE_NAME, TodoListDBContract.Tasks._ID + "=" + id, null);
        }
    }

    public void delete_by_status(int status) {

        SQLiteDatabase sqLiteDatabase = todoListDBHelper.getWritableDatabase();
        if (sqLiteDatabase != null) {
            if (status == -1) {
                sqLiteDatabase.delete(TodoListDBContract.Tasks.TABLE_NAME, null, null);
            } else {
                sqLiteDatabase.delete(TodoListDBContract.Tasks.TABLE_NAME, TodoListDBContract.Tasks.PROGRESS + "=" + status, null);
            }
        }
    }

    public ArrayList<Task> getTasks(int option_status) {
        ArrayList<Task> tasks = new ArrayList();
        SQLiteDatabase sqLiteDatabase = todoListDBHelper.getReadableDatabase();
        Cursor cursorTodoList;
        if (sqLiteDatabase != null) {
            String[] projection = {TodoListDBContract.Tasks._ID, TodoListDBContract.Tasks.TODO, TodoListDBContract.Tasks.TO_ACCOMPLISH, TodoListDBContract.Tasks.STATUS, TodoListDBContract.Tasks.PROGRESS, TodoListDBContract.Tasks.DESCRIPTION};

            if (option_status == -1) {
                cursorTodoList = sqLiteDatabase.query(TodoListDBContract.Tasks.TABLE_NAME, projection, null, null, null, null, null);
            } else {
                String selection = TodoListDBContract.Tasks.PROGRESS + " =?";
                String[] selectionArgs = {String.valueOf(option_status)};
                String sortOrder = TodoListDBContract.Tasks._ID + " ASC";
                cursorTodoList = sqLiteDatabase.query(TodoListDBContract.Tasks.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
            }

            if (cursorTodoList != null) {
                while (cursorTodoList.moveToNext()) {
                    int _idIndex = cursorTodoList.getColumnIndexOrThrow(TodoListDBContract.Tasks._ID);
                    int todoIndex = cursorTodoList.getColumnIndexOrThrow(TodoListDBContract.Tasks.TODO);
                    int _toAccomplishIndex = cursorTodoList.getColumnIndexOrThrow(TodoListDBContract.Tasks.TO_ACCOMPLISH);
                    int descriptionIndex = cursorTodoList.getColumnIndexOrThrow(TodoListDBContract.Tasks.DESCRIPTION);
                    int status = cursorTodoList.getColumnIndexOrThrow(TodoListDBContract.Tasks.STATUS);
                    int progress = cursorTodoList.getColumnIndexOrThrow(TodoListDBContract.Tasks.PROGRESS);
                    Task task = new Task(cursorTodoList.getInt(_idIndex), cursorTodoList.getString(todoIndex), cursorTodoList.getString(_toAccomplishIndex), cursorTodoList.getString(descriptionIndex), cursorTodoList.getInt(status), cursorTodoList.getInt(progress));
                    tasks.add(task);
                }
            }
            cursorTodoList.close();
        }


        return tasks;
    }

    public void close() {
        todoListDBHelper.close();
    }
}
