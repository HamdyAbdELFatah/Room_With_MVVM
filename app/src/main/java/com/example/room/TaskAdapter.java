package com.example.room;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.room.Data.TaskEntry;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    final private ItemClickListener mItemClickListener;
    private static final String DATE_FORMAT = "dd/MM/yyy";
    private List<TaskEntry> mTaskEntries;
    private Context mContext;
    private SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());
    public TaskAdapter(Context context,ItemClickListener itemClickListener) {
        mContext = context;
        mItemClickListener = itemClickListener;
    }
    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.task_layout, parent, false);
        return new TaskViewHolder(view);
    }
    @Override
    public void onBindViewHolder(TaskViewHolder holder, int position) {
        TaskEntry taskEntry = mTaskEntries.get(position);
        String description = taskEntry.description;
        int priority = taskEntry.priority;
        String updatedAt = dateFormat.format(taskEntry.updateAt);
        holder.taskDescriptionView.setText(description);
        holder.updatedAtView.setText(updatedAt);
        holder.priorityView.setText("" + priority);
        GradientDrawable priorityCircle = (GradientDrawable) holder.priorityView.getBackground();
        int priorityColor = getPriorityColor(priority);
        priorityCircle.setColor(priorityColor);
    }
    private int getPriorityColor(int priority) {
        int priorityColor = 0;
        switch (priority) {
            case 1:
                priorityColor = ContextCompat.getColor(mContext, R.color.materialRed);
                break;
            case 2:
                priorityColor = ContextCompat.getColor(mContext, R.color.materialOrange);
                break;
            case 3:
                priorityColor = ContextCompat.getColor(mContext, R.color.materialYellow);
                break;
            default:
                break;
        }
        return priorityColor;
    }
    @Override
    public int getItemCount() {
        if (mTaskEntries == null) {
            return 0;
        }
        return mTaskEntries.size();
    }
     List<TaskEntry> getTasks() {
return mTaskEntries;
    }
     void setTasks(List<TaskEntry> taskEntries) {
        mTaskEntries = taskEntries;
        notifyDataSetChanged();
    }

    public interface ItemClickListener {
        void onItemClickListener(int itemId);
    }
    class TaskViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {
        TextView taskDescriptionView;
        TextView updatedAtView;
        TextView priorityView;
        public TaskViewHolder(View itemView) {
            super(itemView);
            taskDescriptionView = itemView.findViewById(R.id.taskDescription);
            updatedAtView = itemView.findViewById(R.id.taskUpdatedAt);
            priorityView = itemView.findViewById(R.id.priorityTextView);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            int elementId = mTaskEntries.get(getAdapterPosition()).id;
            mItemClickListener.onItemClickListener(elementId);
        }
    }
}