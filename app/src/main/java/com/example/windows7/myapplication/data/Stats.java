package com.example.windows7.myapplication.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Date;

@Entity(tableName = "stats")
public class Stats {

    @NonNull
    @PrimaryKey (autoGenerate = true)
    private int sessionID;
    private Date studyDate;
    private String studyType; //addition or subtraction
    private int first;
    private int second;
    private boolean correct = true;

    @Ignore
    public Stats(Date studyDate, String studyType, int first, int second, boolean correct) {
        this.studyDate = studyDate;
        this.studyType = studyType;
        this.first = first;
        this.second = second;
        this.correct = correct;
    }

    public Stats(@NonNull int sessionID, Date studyDate, String studyType, int first, int second, boolean correct) {
        this.sessionID = sessionID;
        this.studyDate = studyDate;
        this.studyType = studyType;
        this.first = first;
        this.second = second;
        this.correct = correct;
    }

    @NonNull
    public int getSessionID() {
        return sessionID;
    }

    public void setSessionID(@NonNull int sessionID) {
        this.sessionID = sessionID;
    }

    public Date getStudyDate() {
        return studyDate;
    }

    public void setStudyDate(Date studyDate) {
        this.studyDate = studyDate;
    }

    public String getStudyType() {
        return studyType;
    }

    public void setStudyType(String studyType) {
        this.studyType = studyType;
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public boolean getCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }
}
