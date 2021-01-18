package com.example.smproject.ui.Facts;

public class Fact {
    String text;

    public Fact(String i, String text) {
        super();
        this.text = text;
    }
    public Fact(String text) {
        super();
        this.text = text;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
}
