package com.example.room;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.room.Data.AppDatabase;

public class AddTaskViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    AppDatabase aDb;
    int  TaskID;

    public AddTaskViewModelFactory(AppDatabase aDb, int taskID) {
        this.aDb = aDb;
        TaskID = taskID;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new AddTaskViewModel(aDb,TaskID);
    }
}
