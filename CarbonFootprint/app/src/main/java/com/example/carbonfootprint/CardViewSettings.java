package com.example.carbonfootprint;

public class CardViewSettings {
    private int cardImage;
    private String cardTopText;
    private String cardBottomText;

    public CardViewSettings(int cardImage, String cardTopText) {
        this.cardImage = cardImage;
        this.cardTopText = cardTopText;
    }

    public int getCardImage() {
        return cardImage;
    }

    public String getCardTopText() {
        return cardTopText;
    }

    public String getCardBottomText() {
        return cardBottomText;
    }
}
