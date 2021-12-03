package com.olx.core;

public interface ApiTask<T> {

    T perform(T var);
}
