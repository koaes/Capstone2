package com.example.windows7.myapplication.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Date;

@Entity(tableName = "stats")
public class Stats {

    @NonNull
    @PrimaryKey (autoGenerate = true)
    private int sessionID;
    private Date studyDate;
    private String studyType;
    private int numberCorrect;

    public Stats(@NonNull int sessionID, Date studyDate, String studyType, int numberCorrect) {
        this.sessionID = sessionID;
        this.studyDate = studyDate;
        this.studyType = studyType;
        this.numberCorrect = numberCorrect;
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

    public int getNumberCorrect() {
        return numberCorrect;
    }

    public void setNumberCorrect(int numberCorrect) {
        this.numberCorrect = numberCorrect;
    }
}
