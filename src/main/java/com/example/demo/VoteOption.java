package com.example.demo;

public class VoteOption {

    String caption;
    int presentationOrder;
    int votes;

    public VoteOption() { }

    //Getters
    public String getCaption() {
        return caption;
    }
    public int getPresentationOrder(){
        return presentationOrder;
    }
    public int getVotes(){return votes;}

    //Setters
    public void setCaption(String caption) {
        this.caption = caption;
    }
    public void setPresentationOrder(int presentationOrder)
    {
        this.presentationOrder = presentationOrder;
    }
    public void setVotes(int votes) {this.votes = votes;}
}