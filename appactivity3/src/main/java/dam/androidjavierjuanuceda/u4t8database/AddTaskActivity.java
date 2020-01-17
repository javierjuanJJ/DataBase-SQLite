package dam.androidjavierjuanuceda.u4t8database;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import dam.androidjavierjuanuceda.u4t8database.data.TodoListDBManager;
import dam.androidjavierjuanuceda.u4t8database.model.Task;

public class AddTaskActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    private EditText etTodo, etToAccomplish, etDescription;
    private Button btOk, btSave, btDelete;
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

        btOk = findViewById(R.id.buttonOK);
        btSave = findViewById(R.id.btSave);
        btDelete = findViewById(R.id.btDelete);
        btOk.setOnClickListener(this);
        btSave.setOnClickListener(this);
        btDelete.setOnClickListener(this);

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
            spinnerprority.setSelection(task.getStatus());
            spinnerstatus.setSelection(task.getProgress());
            etTodo.setText(task.getTodo());
            etToAccomplish.setText(task.getToAccomplish());
            etDescription.setText(task.getDescription());
            this.setTitle(getString(R.string.edit_task_activity_name));
            btSave.setVisibility(View.VISIBLE);
            btDelete.setVisibility(View.VISIBLE);
            btOk.setVisibility(View.INVISIBLE);
        } else {
            this.setTitle(getString(R.string.add_task_activity_name));
            btSave.setVisibility(View.INVISIBLE);
            btDelete.setVisibility(View.INVISIBLE);
        }


    }

    public void onClick(View view) {
        TodoListDBManager todoListDBManager = new TodoListDBManager(this);
        switch (view.getId()) {
            case R.id.btDelete:
                todoListDBManager.delete(task.get_id());
                break;
            case R.id.btSave:
                todoListDBManager.update(
                        task.get_id(),
                        etTodo.getText().toString(),
                        etToAccomplish.getText().toString(),
                        etDescription.getText().toString(),
                        option_selected_priorities,
                        option_selected_progress
                );
                break;
            case R.id.buttonOK:

                if (etTodo.getText().toString().length() > 0) {

                    todoListDBManager.insert(
                            etTodo.getText().toString(),
                            etToAccomplish.getText().toString(),
                            etDescription.getText().toString(),
                            option_selected_priorities,
                            option_selected_progress);
                } else {
                    Toast.makeText(this, getString(R.string.task_name_is_Empty), Toast.LENGTH_LONG).show();
                }
                break;
            default:
                break;
        }
        finish();
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
