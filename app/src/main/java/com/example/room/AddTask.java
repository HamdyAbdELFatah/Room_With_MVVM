package com.example.room;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import com.example.room.Data.AppDatabase;
import com.example.room.Data.TaskEntry;
import java.util.Date;
public class AddTask extends AppCompatActivity {
EditText description;
AppDatabase mDB;
RadioGroup radioGroup;
Button mButton;
public static final String EXTRA_TASK_ID = "id";
private static final int DEFAULT_TASK_ID = -1;
private int mTaskId = DEFAULT_TASK_ID;
    private AppDatabase mDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        description=findViewById(R.id.description);
         radioGroup =findViewById(R.id.radioGroup);
        mButton =findViewById(R.id.add);
        mDb = AppDatabase.getInstance(getApplicationContext());
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        Priority();
    }
});
        mDB=AppDatabase.getInstance(this);
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(EXTRA_TASK_ID)) {
            mButton.setText("Update");
            if (mTaskId == DEFAULT_TASK_ID) {
                mTaskId = intent.getIntExtra(EXTRA_TASK_ID, DEFAULT_TASK_ID);
AddTaskViewModelFactory addTaskViewModelFactory=new AddTaskViewModelFactory(mDB,mTaskId);
final AddTaskViewModel addTaskViewModel= ViewModelProviders.of(this,addTaskViewModelFactory).get(AddTaskViewModel.class);
                addTaskViewModel.getTask().observe(this, new Observer<TaskEntry>() {
                    @Override
                    public void onChanged(@Nullable TaskEntry taskEntry) {
                        addTaskViewModel.getTask().removeObserver(this);
                        populateUI(taskEntry);
                    }
                });
                    }
            }
        }
    private void populateUI(TaskEntry task) {
        if (task == null) {
            return;
        }
        description.setText(task.description);
        setPriorityInViews(task.priority);
    }
   public void OnAdd(View v){
final String Description=description.getText().toString();
final int Priority=Priority();
final Date date=new Date();
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                TaskEntry taskEntry=new TaskEntry(Description,Priority,date);
                if (mTaskId == DEFAULT_TASK_ID) {
                    mDB.TaskDao().insertTask(taskEntry);
                }else{
                    taskEntry.id=mTaskId;
                    mDb.TaskDao().updateTask(taskEntry);
                }
                finish();
            }
        });
    }
    int Priority(){
        int id = radioGroup.getCheckedRadioButtonId();
        switch(id){
            case R.id.high:return 1;
            case R.id.medium:return 2;
            case R.id.low:return 3;
        }
        return 0;
    }
    public void setPriorityInViews(int priority) {
        switch (priority) {
            case 1:
                ((RadioGroup) findViewById(R.id.radioGroup)).check(R.id.high);
                break;
            case 2:
                ((RadioGroup) findViewById(R.id.radioGroup)).check(R.id.medium);
                break;
            case 3:
                ((RadioGroup) findViewById(R.id.radioGroup)).check(R.id.low);
        }
    }
}
