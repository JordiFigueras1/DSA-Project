package edu.upc.dsa.models;

public class Message {
    int receiver;
    String message;

    public Message(){}
    public Message(int receiver, String msg){

        this.message = msg;
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getReceiver() {
        return receiver;
    }

    public void setReceiver(Integer receiver) {
        this.receiver = receiver;
    }
}
