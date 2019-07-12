package com.example.room.Data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;
@Entity(tableName = "task")
public class TaskEntry {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "description")
    public String description;
    @ColumnInfo(name = "priority")
    public int priority;
    @ColumnInfo(name = "UpdateAt")
    public Date updateAt;
    //todo ملاحظه هااااااااااااااااااااااااااامه
    //لازم تكون نفس اسماء الباراميتر في الكونستركتور نفس اسماء المتغيرات بتاعه الجدول
    @Ignore
    public TaskEntry(String description, int priority, Date updateAt) {
        this.description = description;
        this.priority = priority;
        this.updateAt = updateAt;
    }
    //انا بعمل ignore علي الكونستركتور الاولاني علشان ده ال انا هستخدمه
    // انما التاني ده ال انا بيتعمل بيه الجدول فانا بقوله تجاهل الاول وانت بتعمل الجدول و انا استدعي برحتي لما اعوز استخدمه
    public TaskEntry(int id, String description, int priority, Date updateAt) {
        this.id = id;
        this.description = description;
        this.priority = priority;
        this.updateAt = updateAt;
    }


}
