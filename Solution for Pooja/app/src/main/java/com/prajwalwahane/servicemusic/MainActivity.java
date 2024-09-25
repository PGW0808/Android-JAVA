package com.prajwalwahane.servicemusic;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewCourses;
    private List<String> courseNames;
    private List<String[]> courseSyllabi;
    private List<String> teacherNames;
    private List<String> courseFees;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewCourses = findViewById(R.id.recyclerViewCourses);

        // Get course names, syllabi, teacher names, and course fees from resources
        courseNames = Arrays.asList(getResources().getStringArray(R.array.course_names));
        courseSyllabi = new ArrayList<>();
        courseSyllabi.add(getResources().getStringArray(R.array.java_syllabus));
        courseSyllabi.add(getResources().getStringArray(R.array.android_syllabus));
        courseSyllabi.add(getResources().getStringArray(R.array.data_science_syllabus));

        teacherNames = Arrays.asList(getResources().getStringArray(R.array.teacher_names));
        courseFees = Arrays.asList(getResources().getStringArray(R.array.course_fees));

        // Set up RecyclerView
        recyclerViewCourses.setLayoutManager(new LinearLayoutManager(this));
        CourseAdapter adapter = new CourseAdapter(this, courseNames, courseSyllabi, teacherNames, courseFees);
        recyclerViewCourses.setAdapter(adapter);
    }
}
