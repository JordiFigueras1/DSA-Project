package edu.upc.dsa.models;

public class MessageRequest {
    String receiver;
    String message;

    public MessageRequest(){}
    public MessageRequest(String receiver, String msg){

        this.message = msg;
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
}
