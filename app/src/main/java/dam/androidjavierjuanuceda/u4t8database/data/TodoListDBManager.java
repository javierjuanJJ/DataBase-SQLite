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

    public void insert(String todo, String when, String description) {

        SQLiteDatabase sqLiteDatabase = todoListDBHelper.getWritableDatabase();

        if (sqLiteDatabase == null) {
            ContentValues contentValue = new ContentValues();
            contentValue.put(TodoListDBContract.Tasks.TODO, todo);
            contentValue.put(TodoListDBContract.Tasks.TO_ACCOMPLISH, when);
            contentValue.put(TodoListDBContract.Tasks.DESCRIPTION, description);
            sqLiteDatabase.insert(TodoListDBContract.Tasks.TABLE_NAME, null, contentValue);
        }
    }

    public ArrayList<Task> getTasks() {
        ArrayList<Task> tasks = new ArrayList();

        SQLiteDatabase sqLiteDatabase = todoListDBHelper.getReadableDatabase();

        if (sqLiteDatabase != null) {
            String[] projection = {TodoListDBContract.Tasks._ID, TodoListDBContract.Tasks.TODO, TodoListDBContract.Tasks.TO_ACCOMPLISH, TodoListDBContract.Tasks.DESCRIPTION};
            Cursor cursorTodoList = sqLiteDatabase.query(TodoListDBContract.Tasks.TABLE_NAME, projection, null, null, null, null, null);
            if (cursorTodoList != null) {
                int _idIndex = cursorTodoList.getColumnIndexOrThrow(TodoListDBContract.Tasks._ID);

                int todoIndex = cursorTodoList.getColumnIndexOrThrow(TodoListDBContract.Tasks.TODO);
                int _toAccomplishIndex = cursorTodoList.getColumnIndexOrThrow(TodoListDBContract.Tasks.TO_ACCOMPLISH);
                int descriptionIndex = cursorTodoList.getColumnIndexOrThrow(TodoListDBContract.Tasks.DESCRIPTION);


                Task task = new Task(cursorTodoList.getInt(_idIndex), cursorTodoList.getString(todoIndex), cursorTodoList.getString(_toAccomplishIndex), cursorTodoList.getString(descriptionIndex));
                tasks.add(task);
            }
            cursorTodoList.close();
        }


        return tasks;
    }

    public void close() {
        todoListDBHelper.close();
    }
}
