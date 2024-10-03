package com.prajwalwahane.solutionofstringarray;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Get the string arrays from strings.xml
        String[] courses = getResources().getStringArray(R.array.course_array);
        String[] duration = getResources().getStringArray(R.array.duration_array);
        String[] instructors = getResources().getStringArray(R.array.instructor_array);
        String[] professions = getResources().getStringArray(R.array.profession_array);

        // Syllabi as a 2D array (each course has multiple syllabi)
        String[][] syllabi = {
            getResources().getStringArray(R.array.syllabus_android_development),
            getResources().getStringArray(R.array.syllabus_web_development),
            getResources().getStringArray(R.array.syllabus_data_science)
        };

        // Set the adapter
        MyAdapter adapter = new MyAdapter(courses, duration, instructors, professions, syllabi);
        recyclerView.setAdapter(adapter);
    }
}
