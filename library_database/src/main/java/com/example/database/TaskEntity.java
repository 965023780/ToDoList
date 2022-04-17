package com.example.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class TaskEntity {
    @Id(autoincrement = true)
    private Long time;
    private String content;
    private boolean isCompleted;

    @Keep
    public TaskEntity(Long time, String content, boolean isCompleted){
        this.time = time;
        this.content = content;
        this.isCompleted = isCompleted;
    }


    @Generated(hash = 397975341)
    public TaskEntity() {
    }


    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }


    public boolean getIsCompleted() {
        return this.isCompleted;
    }


    public void setIsCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }
}
