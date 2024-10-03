package com.prajwalwahane.solutionofstringarray;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class SyllabusAdapter extends RecyclerView.Adapter<SyllabusAdapter.ViewHolder> {
    private final List<String> syllabusList;

    public SyllabusAdapter(List<String> syllabusList) {
        this.syllabusList = syllabusList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView syllabusTextView;

        public ViewHolder(View view) {
            super(view);
            syllabusTextView = view.findViewById(R.id.syllabusTextView);
        }

        public TextView getSyllabusTextView() {
            return syllabusTextView;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.syllabus_item_layout, parent, false); // Create a new layout for each item
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.getSyllabusTextView().setText(syllabusList.get(position));
    }

    @Override
    public int getItemCount() {
        return syllabusList.size();
    }
}
