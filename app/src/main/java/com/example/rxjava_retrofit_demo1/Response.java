package com.example.rxjava_retrofit_demo1;

import java.io.Serializable;

public class Response<T> implements Serializable {
    private T object;// 返回一个对象的对象

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    @Override
    public String toString() {
        return "EntityResponse{" +
                "object=" + object +
                '}';
    }

}
