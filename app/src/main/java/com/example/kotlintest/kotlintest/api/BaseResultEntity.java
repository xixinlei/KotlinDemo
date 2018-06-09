package com.example.kotlintest.kotlintest.api;

import com.example.kotlintest.kotlintest.entity.MovieDetailEntity;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 回调信息统一封装类
 */
public class BaseResultEntity<T> {
    //    显示数据（用户需要关心的数据）
    private T subjects;
    private int count;
    private int start;
    private int total;
    private String title;

    @Override
    public String toString() {
        return "BaseResultEntity{" +
                "subjects=" + subjects +
                ", count=" + count +
                ", start=" + start +
                ", total=" + total +
                ", title='" + title + '\'' +
                '}';
    }

    public T getSubjects() {
        return subjects;
    }

    public void setSubjects(T subjects) {
        this.subjects = subjects;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
