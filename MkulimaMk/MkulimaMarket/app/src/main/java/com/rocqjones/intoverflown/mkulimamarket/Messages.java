package com.rocqjones.intoverflown.mkulimamarket;

public class Messages
{
    public Messages()
    {
    }

    public String date,from,message,receivername,sendername,to;

    public Messages(String date, String from, String message, String receivername, String sendername, String to) {
        this.date = date;
        this.from = from;
        this.message = message;
        this.receivername = receivername;
        this.sendername = sendername;
        this.to = to;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReceivername() {
        return receivername;
    }

    public void setReceivername(String receivername) {
        this.receivername = receivername;
    }

    public String getSendername() {
        return sendername;
    }

    public void setSendername(String sendername) {
        this.sendername = sendername;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
