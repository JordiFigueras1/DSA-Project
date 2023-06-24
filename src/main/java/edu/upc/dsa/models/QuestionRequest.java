package edu.upc.dsa.models;

public class QuestionRequest {
    private String sender;
    private String date;
    private String title;
    private String message;
    private String response;

    public QuestionRequest() {
        this.response = "we will answer you as soon as possible";
    }

    public QuestionRequest(String date, String title, String message, String sender) {
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


    public String getSender() {
        return sender;
    }
    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

}
