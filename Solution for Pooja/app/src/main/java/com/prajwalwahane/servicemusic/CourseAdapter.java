package com.prajwalwahane.servicemusic;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    private List<String> courseNames;
    private List<String[]> courseSyllabi;
    private List<String> teacherNames;
    private List<String> courseFees;
    private Context context;

    public CourseAdapter(Context context, List<String> courseNames, List<String[]> courseSyllabi, List<String> teacherNames, List<String> courseFees) {
        this.context = context;
        this.courseNames = courseNames;
        this.courseSyllabi = courseSyllabi;
        this.teacherNames = teacherNames;
        this.courseFees = courseFees;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        String courseName = courseNames.get(position);
        holder.courseNameTextView.setText(courseName);

        holder.itemView.setOnClickListener(v -> {
            // Get corresponding data
            String[] syllabus = courseSyllabi.get(position);
            String teacherName = teacherNames.get(position);
            String courseFee = courseFees.get(position);

            // Create Intent to navigate to SyllabusActivity
            Intent intent = new Intent(context, CourseList.class);
            intent.putExtra("course_name", courseName);
            intent.putExtra("syllabus", syllabus);
            intent.putExtra("teacher_name", teacherName);
            intent.putExtra("course_fee", courseFee);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return courseNames.size();
    }

    public class CourseViewHolder extends RecyclerView.ViewHolder {
        TextView courseNameTextView;

        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            courseNameTextView = itemView.findViewById(android.R.id.text1);
        }
    }
}
