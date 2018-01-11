package model.modelImpl;

import java.io.Serializable;

import model.modelImpl.enums.Actions;

public class RequestBody<T> implements Serializable {
    private Actions action;
    private T data;

    public Actions getAction() {
        return action;
    }

    public void setAction(Actions action) {
        this.action = action;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
