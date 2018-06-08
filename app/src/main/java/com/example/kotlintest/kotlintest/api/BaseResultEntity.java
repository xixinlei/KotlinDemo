package com.example.kotlintest.kotlintest.api;

/**
 * 回调信息统一封装类
 */
public class BaseResultEntity<T> {
    //显示数据（用户需要关心的数据）
    private T subjects;

    public T getSubjects() {
        return subjects;
    }

    public void setSubjects(T subjects) {
        this.subjects = subjects;
    }

    @Override
    public String toString() {
        return "BaseResultEntity{" +
                "subjects=" + subjects +
                '}';
    }
}
