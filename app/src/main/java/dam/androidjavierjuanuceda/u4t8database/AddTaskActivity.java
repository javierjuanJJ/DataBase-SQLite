package dam.androidjavierjuanuceda.u4t8database;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import dam.androidjavierjuanuceda.u4t8database.data.TodoListDBManager;

public class AddTaskActivity extends AppCompatActivity {
    private EditText etTodo;
    private EditText etToAccomplish;
    private EditText etDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        setUI();
    }

    private void setUI() {
        etTodo = findViewById(R.id.etTodo);
        etToAccomplish = findViewById(R.id.etToAccomplish);
        etDescription = findViewById(R.id.etDescription);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.buttonOK) {
            if (etTodo.getText().toString().length() > 0) {
                TodoListDBManager todoListDBManager = new TodoListDBManager(this);
                todoListDBManager.insert(etTodo.getText().toString(), etToAccomplish.getText().toString(), etDescription.getText().toString());
            } else {
                Toast.makeText(this, getString(R.string.task_name_is_Empty), Toast.LENGTH_LONG).show();
            }
            finish();
        }
    }
}
