package dam.androidjavierjuanuceda.u4t8database.model;

import java.io.Serializable;

public class Task implements Serializable {

    private int _id;
    private String todo;
    private String toAccomplish;
    private String description;
    private int status;
    private int progress;

    public Task(int _id, String todo, String toAccomplish, String description, int status, int progress) {
        this._id = _id;
        this.todo = todo;
        this.toAccomplish = toAccomplish;
        this.description = description;
        this.status = status;
        this.progress = progress;
    }

    public int getStatus() {
        return status;
    }

    public int getProgress() {
        return progress;
    }

    public int get_id() {
        return _id;
    }

    public String getTodo() {
        return todo;
    }

    public String getToAccomplish() {
        return toAccomplish;
    }

    public String getDescription() {
        return description;
    }
}
