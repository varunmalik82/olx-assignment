package com.olx.core;

import lombok.Getter;
import lombok.Setter;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class ApiTaskManager<T> {

    private List<ApiTask<T>> tasks;

    public ApiTaskManager(){
        if (this.tasks == null) {
            tasks = new LinkedList<>();
        }
    }

    public void addTask(ApiTask<T> task) {
        this.tasks.add(task);
    }

    public void execute(T object) {
        Iterator i = this.getTasks().listIterator();

        while(i.hasNext()){
            ApiTask<T> task = (ApiTask<T>) i.next();
            task.perform(object);
        }

    }
}