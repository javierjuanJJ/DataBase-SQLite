package dam.androidjavierjuanuceda.u4t8database;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import dam.androidjavierjuanuceda.u4t8database.data.TodoListDBManager;
import dam.androidjavierjuanuceda.u4t8database.model.Task;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private TodoListDBManager todoListDBManager;
    private ArrayList<Task> myTaskList;
    private OnItemClickListener listener;

    public MyAdapter(TodoListDBManager todoListDBManager, OnItemClickListener listener) {
        this.todoListDBManager = todoListDBManager;
        this.listener = listener;
    }

    public void getData() {
        this.myTaskList = todoListDBManager.getTasks();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_todo_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(myTaskList.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return myTaskList.size();
    }

    public interface OnItemClickListener {
        void OnItemClick(Task task);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvId;
        TextView tvTodo;
        TextView tvToAccomplish;
        TextView tvDescription;
        TextView tvStatus;
        TextView priority;
        Resources resources;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            this.tvId = itemView.findViewById(R.id.tvId);
            this.tvTodo = itemView.findViewById(R.id.tvTodo);
            this.tvToAccomplish = itemView.findViewById(R.id.tvtoAccomplish);
            this.tvDescription = itemView.findViewById(R.id.tvDescription);
            this.tvStatus = itemView.findViewById(R.id.tvStatus);
            this.priority = itemView.findViewById(R.id.priority);
            resources = itemView.getResources();

        }

        public void bind(Task task, OnItemClickListener listener) {
            this.tvId.setText(String.valueOf(task.get_id()));
            this.tvTodo.setText(task.getTodo());
            this.tvToAccomplish.setText(task.getToAccomplish());
            this.tvDescription.setText(task.getDescription());
            this.tvStatus.setText(resources.getStringArray(R.array.priorities)[task.getStatus()]);
            this.priority.setText(resources.getStringArray(R.array.progresses)[task.getProgress()]);

            this.tvId.setOnClickListener(v -> listener.OnItemClick(task));
            this.tvTodo.setOnClickListener(v -> listener.OnItemClick(task));
            this.tvToAccomplish.setOnClickListener(v -> listener.OnItemClick(task));
            this.tvDescription.setOnClickListener(v -> listener.OnItemClick(task));
            this.tvStatus.setOnClickListener(v -> listener.OnItemClick(task));
            this.priority.setOnClickListener(v -> listener.OnItemClick(task));


        }
    }


}
