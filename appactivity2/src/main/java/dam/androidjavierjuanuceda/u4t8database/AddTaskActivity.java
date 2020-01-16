package dam.androidjavierjuanuceda.u4t8database;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import dam.androidjavierjuanuceda.u4t8database.data.TodoListDBManager;
import dam.androidjavierjuanuceda.u4t8database.model.Task;

public class AddTaskActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private EditText etTodo;
    private EditText etToAccomplish;
    private EditText etDescription;
    private int option_selected_priorities, option_selected_progress;
    private Spinner spinnerprority, spinnerstatus;
    private Task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        setUI();
    }

    private void setUI() {
        spinnerprority = findViewById(R.id.spinnerprority);
        spinnerstatus = findViewById(R.id.spinnerstatus);
        etTodo = findViewById(R.id.etTodo);
        etToAccomplish = findViewById(R.id.etToAccomplish);
        etDescription = findViewById(R.id.etDescription);

        ArrayAdapter<CharSequence> spinnerAdapterprority = ArrayAdapter.createFromResource(this, R.array.priorities, android.R.layout.simple_spinner_item);
        spinnerAdapterprority.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerprority.setAdapter(spinnerAdapterprority);
        spinnerprority.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> spinnerAdapterLevel = ArrayAdapter.createFromResource(this, R.array.progresses, android.R.layout.simple_spinner_item);
        spinnerAdapterLevel.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerstatus.setAdapter(spinnerAdapterLevel);
        spinnerstatus.setOnItemSelectedListener(this);
        option_selected_priorities = 0;
        option_selected_progress = 0;

        if (getIntent().getBooleanExtra("have_Task", false)) {
            task = (Task) getIntent().getSerializableExtra("Task");
            spinnerprority.setSelection(task.getProgress());
            spinnerstatus.setSelection(task.getStatus());
            etTodo.setText(task.getTodo());
            etToAccomplish.setText(task.getToAccomplish());
            etDescription.setText(task.getDescription());
        }

    }

    public void onClick(View view) {
        if (view.getId() == R.id.buttonOK) {
            if (etTodo.getText().toString().length() > 0) {
                TodoListDBManager todoListDBManager = new TodoListDBManager(this);
                todoListDBManager.insert(
                        etTodo.getText().toString(),
                        etToAccomplish.getText().toString(),
                        etDescription.getText().toString(),
                        option_selected_priorities,
                        option_selected_progress);
            } else {
                Toast.makeText(this, getString(R.string.task_name_is_Empty), Toast.LENGTH_LONG).show();
            }
            finish();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {

            case R.id.spinnerprority:
                option_selected_priorities = position;
                break;
            case R.id.spinnerstatus:
                option_selected_progress = position;
                break;
            default:
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
