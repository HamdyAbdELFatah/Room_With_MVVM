package com.example.room;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.room.Data.AppDatabase;
import com.example.room.Data.TaskEntry;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private LiveData<List<TaskEntry>> Tasks;

    public MainViewModel(@NonNull Application application) {
        super(application);
        AppDatabase appDatabase=AppDatabase.getInstance(application);
        Tasks=appDatabase.TaskDao().LoadAllTask();
    }
     LiveData<List<TaskEntry>> getAllTasks() {
        return Tasks;
    }


}
