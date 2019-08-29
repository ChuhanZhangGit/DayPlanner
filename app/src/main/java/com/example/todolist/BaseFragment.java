package com.example.todolist;

public interface BaseFragment<T> {

    void setPresenter(T presenter);
}
