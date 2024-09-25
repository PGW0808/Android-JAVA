package com.prajwalwahane.servicemusic;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CourseList extends AppCompatActivity {

    private TextView syllabusTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);

        syllabusTextView = findViewById(R.id.syllabusTextView);

        // Get data from Intent
        String courseName = getIntent().getStringExtra("course_name");
        String[] syllabus = getIntent().getStringArrayExtra("syllabus");
        String teacherName = getIntent().getStringExtra("teacher_name");
        String courseFee = getIntent().getStringExtra("course_fee");

        // Format and display data
        StringBuilder syllabusText = new StringBuilder("Course: " + courseName + "\n");
        syllabusText.append("Teacher: ").append(teacherName).append("\n");
        syllabusText.append("Course Fee: ").append(courseFee).append("\n\n");
        syllabusText.append("Syllabus:\n");
        for (String point : syllabus) {
            syllabusText.append("• ").append(point).append("\n");
        }
        syllabusTextView.setText(syllabusText.toString());
    }
}