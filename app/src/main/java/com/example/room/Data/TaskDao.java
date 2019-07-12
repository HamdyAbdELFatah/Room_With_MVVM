package com.example.room.Data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;
@Dao
public interface TaskDao {
    @Query("select * from task order by priority")
    LiveData<List<TaskEntry>> LoadAllTask();
    @Query("SELECT * FROM task WHERE id = :id")
    LiveData<TaskEntry> getTaskById(int id);
    @Insert
    void insertTask(TaskEntry taskEntry);
    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateTask(TaskEntry taskEntry);
    @Delete
    void deleteTask(TaskEntry taskEntry);
    @Query("DELETE FROM task")
    void deleteAll();


}
