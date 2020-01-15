package dam.androidjavierjuanuceda.u4t8database.data;

public class TodoListDBContract {

    public static final String DB_NAME = "TODOLIST.DB";
    public static final int DB_VERSION = 1;

    public static class Tasks {
        public static final String TABLE_NAME = "TASKS";
        public static final String _ID = "_id";
        public static final String TODO = "todo";
        public static final String TO_ACCOMPLISH = "to_accomplish";
        public static final String DESCRIPTION = "description";
        public static final String CREATE_TABLE =
                "CREATE TABLE " +
                        Tasks.TABLE_NAME + " (" + Tasks._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        Tasks.TODO + " TEXT NOT NULL, " +
                        Tasks.TO_ACCOMPLISH + " TEXT, " +
                        Tasks.DESCRIPTION + " TEXT";

        public static final String DELETE = "DROP TABLE IF EXISTS  " + Tasks.TABLE_NAME;
    }

}
