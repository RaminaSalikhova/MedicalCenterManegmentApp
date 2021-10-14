package models;

import enums.HANDLER_TYPE;

import java.io.Serializable;

public class ClientRequest<T> implements Serializable {
    private HANDLER_TYPE type;
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public HANDLER_TYPE getType() {
        return type;
    }

    public void setType(HANDLER_TYPE type) {
        this.type = type;
    }
}
