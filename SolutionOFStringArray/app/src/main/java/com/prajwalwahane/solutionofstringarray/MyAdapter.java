package com.prajwalwahane.solutionofstringarray;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private final String[] courses;
    private final String[] duration;
    private final String[] instructors;
    private final String[] professions;
    private final String[][] syllabuses;  // Updated to String[][] to handle multiple syllabi per course

    public MyAdapter(String[] courses, String[] duration, String[] instructors, String[] professions, String[][] syllabuses) {
        this.courses = courses;
        this.duration = duration;
        this.instructors = instructors;
        this.professions = professions;
        this.syllabuses = syllabuses;  // Updated
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView courseTextView;
        private final TextView durationTextView;

        public ViewHolder(View view) {
            super(view);
            courseTextView = view.findViewById(R.id.courcename);
            durationTextView = view.findViewById(R.id.duration);
        }

        public TextView getCourseTextView() {
            return courseTextView;
        }
        public TextView getDurationTextView() {
            return durationTextView;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.getCourseTextView().setText(courses[position]);
        viewHolder.getDurationTextView().setText(duration[position]);

        // Handle item click to open CourseDetailsActivity
        viewHolder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), CourseDetailsActivity.class);
            intent.putExtra("course", courses[position]);
            intent.putExtra("instructor", instructors[position]);
            intent.putExtra("profession", professions[position]);

            // Passing the syllabi for the specific course as an array to the next activity
            intent.putExtra("syllabus", syllabuses[position]);

            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return courses.length;
    }
}
