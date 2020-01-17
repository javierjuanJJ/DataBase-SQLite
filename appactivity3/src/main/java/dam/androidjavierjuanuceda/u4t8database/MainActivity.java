package dam.androidjavierjuanuceda.u4t8database;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Arrays;

import dam.androidjavierjuanuceda.u4t8database.data.TodoListDBManager;
import dam.androidjavierjuanuceda.u4t8database.model.Task;

public class MainActivity extends AppCompatActivity implements MyAdapter.OnItemClickListener {

    private RecyclerView rvTodoList;
    private TodoListDBManager todoListDBManager;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        todoListDBManager = new TodoListDBManager(this);
        myAdapter = new MyAdapter(todoListDBManager, ((activityName) -> OnItemClick(activityName)));
        setUI();
    }

    private void setUI() {

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), AddTaskActivity.class)));
        rvTodoList = findViewById(R.id.rvTodoList);
        rvTodoList.setHasFixedSize(true);
        rvTodoList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rvTodoList.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        rvTodoList.setAdapter(myAdapter);
    }

    @Override
    protected void onDestroy() {
        todoListDBManager.close();
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        myAdapter.getData(-1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String[] progresses = getResources().getStringArray(R.array.progresses);
        int progress;
        String separator;
        switch (item.getItemId()) {
            case R.id.option_not_stared:
            case R.id.option_in_progress:
            case R.id.option_completed:
            case R.id.option_all_tasks:
                separator = item.getTitle().toString().split(getString(R.string.show) + " ")[1];
                progress = Arrays.asList(progresses).indexOf(separator);
                myAdapter.getData(progress);
                break;
            case R.id.option_delete_all:
            case R.id.option_delete_completed:
                separator = item.getTitle().toString().split(getString(R.string.delete) + " ")[1];
                progress = Arrays.asList(progresses).indexOf(separator);
                todoListDBManager.delete_by_status(progress);
                myAdapter.getData(-1);
                break;
            default:
                break;

        }
        rvTodoList.setAdapter(myAdapter);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void OnItemClick(Task task) {
        Intent intent = new Intent(getApplicationContext(), AddTaskActivity.class);
        intent.putExtra("have_Task", true);
        intent.putExtra("Task", task);
        startActivity(intent);
    }
}
