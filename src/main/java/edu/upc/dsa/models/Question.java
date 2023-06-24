package edu.upc.dsa.models;

public class Question {
    private int sender;
    private String date;
    private String title;
    private String message;
    private String response;

    public Question() {
        this.response = "we will answer you as soon as possible";
    }

    public Question(String date, String title, String message, int sender) {
        this.date = date;
        this.title = title;
        this.message = message;
        this.sender = sender;
        this.response = "we will answer you as soon as possible";
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public Integer getSender() {
        return sender;
    }
    public void setSender(Integer sender) {
        this.sender = sender;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

}
