package com.example.carbonfootprint;

public class CardViewPastResults {
    private int cardImage;
    private String cardTopText;
    private String cardBottomText;

    public CardViewPastResults(int cardImage, String cardTopText, String cardBottomText) {
        this.cardImage = cardImage;
        this.cardTopText = cardTopText;
        this.cardBottomText = cardBottomText;
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
