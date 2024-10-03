package com.prajwalwahane.solutionofstringarray;


import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class CourseDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);

        // Retrieve views
        TextView courseTextView = findViewById(R.id.courseTextView);
        TextView syllabusTextView = findViewById(R.id.syllabusTextView);
        TextView instructorTextView = findViewById(R.id.instructorTextView);
        TextView professionTextView = findViewById(R.id.professionTextView);

        // Retrieve data from the intent
        String course = getIntent().getStringExtra("course");
        String[] syllabus = getIntent().getStringArrayExtra("syllabus"); // Get the syllabus array
        String instructor = getIntent().getStringExtra("instructor");
        String profession = getIntent().getStringExtra("profession");

        // Set course name
        courseTextView.setText(course);

        // Format and set syllabus
        if (syllabus != null) {
            StringBuilder syllabusBuilder = new StringBuilder();
            for (String item : syllabus) {
                syllabusBuilder.append(item).append("\n");  // Append each syllabus item with a newline
            }
            syllabusTextView.setText(syllabusBuilder.toString().trim()); // Set the formatted syllabus
        } else {
            syllabusTextView.setText("No syllabus available"); // Handle case when syllabus is null
        }

        // Set instructor and profession
        instructorTextView.setText(instructor);
        professionTextView.setText(profession);
    }
}
