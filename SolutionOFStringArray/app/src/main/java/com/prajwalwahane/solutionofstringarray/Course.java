package com.prajwalwahane.solutionofstringarray;

public class Course {
    private final String text;
    private final int drawableResId;

    public Course(String text, int drawableResId) {
        this.text = text;
        this.drawableResId = drawableResId;
    }

    public String getText() {
        return text;
    }

    public int getDrawableResId() {
        return drawableResId;
    }
}
