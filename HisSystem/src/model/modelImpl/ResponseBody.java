package model.modelImpl;

import java.io.Serializable;

import model.modelImpl.enums.Actions;

public class ResponseBody<T> implements Serializable {
    private int code;
    private String msg;
    private T data;
    private Actions action;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
    
    public Actions getAction() {
    	return action;
    }
    
    public void setAction(Actions action) {
    	this.action = action;
    }

    @Override
    public String toString() {
        return "ResponseBody{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
