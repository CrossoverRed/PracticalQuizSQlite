package com.example.practicalquizsqlite;

import androidx.annotation.NonNull;

public class School {
    private int id;
    private int numOfStudent;
    private String schoolName;

    public School(int id, int numOfStudent, String schoolName) {
        this.id = id;
        this.numOfStudent = numOfStudent;
        this.schoolName = schoolName;
    }

    @Override
    public String toString() {
        return schoolName+":"+numOfStudent;
    }
}
