package dam.androidjavierjuanuceda.u4t8database;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

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
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AddTaskActivity.class));
            }
        });
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
        myAdapter.getData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

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
