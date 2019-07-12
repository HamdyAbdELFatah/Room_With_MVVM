package com.example.room;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.room.Data.AppDatabase;
import com.example.room.Data.TaskEntry;

public class AddTaskViewModel extends ViewModel {
    private LiveData<TaskEntry> Task;

    public AddTaskViewModel(AppDatabase appDatabase, int id) {
        Task=appDatabase.TaskDao().getTaskById(id);
    }
    public LiveData<TaskEntry> getTask() {
        return Task;
    }

}
