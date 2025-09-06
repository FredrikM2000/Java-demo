package com.example.demo;

public class VoteOption {

    String caption;
    int presentationOrder;

    public VoteOption() { }

    //Getters
    public String getCaption() {
        return caption;
    }
    public int getPresentationOrder(){
        return presentationOrder;
    }

    //Setters
    public void setCaption(String caption) {
        this.caption = caption;
    }
    public void setPresentationOrder(int presentationOrder)
    {
        this.presentationOrder = presentationOrder;
    }
}