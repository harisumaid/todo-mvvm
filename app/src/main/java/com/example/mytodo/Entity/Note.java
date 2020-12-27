package com.example.mytodo.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note_table")
public class Note {
    private String title;

    private String discription;

    private int priority;

    @PrimaryKey(autoGenerate = true)
    private int id;


    public Note(String title, String discription, int priority) {
        this.title = title;
        this.discription = discription;
        this.priority = priority;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public String getDiscription() {
        return discription;
    }

    public int getPriority() {
        return priority;
    }

    public int getId() {
        return id;
    }
}
