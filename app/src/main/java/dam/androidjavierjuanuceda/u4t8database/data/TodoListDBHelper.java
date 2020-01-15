package dam.androidjavierjuanuceda.u4t8database.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class TodoListDBHelper extends SQLiteOpenHelper {
    private static TodoListDBHelper instanceDBHelper;

    public TodoListDBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, TodoListDBContract.DB_NAME, factory, TodoListDBContract.DB_VERSION);
    }

    private TodoListDBHelper(Context context) {

        super(context, TodoListDBContract.DB_NAME, null, TodoListDBContract.DB_VERSION);
    }

    public static TodoListDBHelper getInstance(Context context) {

        return null;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TodoListDBContract.Tasks.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(TodoListDBContract.Tasks.DELETE);
        onCreate(db);
    }
}
