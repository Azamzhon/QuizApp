package com.azamzhon.data.core;

public interface IBaseCallBack<T> {
    void onSuccess(T result);

    void onFailure(Exception e);
}
