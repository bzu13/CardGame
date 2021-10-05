package cardGame;

public class Card {
    private String suit;
    private String facevalue;
    private int points;

    public void setSuit(String suit) {
            this.suit = suit;
    }
    
    public String getSuit() {
            return this.suit;
    }
    
    public void setFaceValue(String faceValue) {
            this.facevalue = faceValue;
    }
    
    public String getFaceValue() {
            return this.facevalue;
    }
    
    public void setPoints() {
            
            if (this.getFaceValue().equals("Two")) {
                    this.points = 2;
            }
            else if (this.getFaceValue().equals("Three")) {
                    this.points = 3;
            }
            else if (this.getFaceValue().equals("Four")) {
                    this.points = 4;
            }
            else if (this.getFaceValue().equals("Five")) {
                    this.points = 5;
            }
            else if (this.getFaceValue().equals("Six")) {
                    this.points = 6;
            }
            else if (this.getFaceValue().equals("Seven")) {
                    this.points = 7;
            }
            else if (this.getFaceValue().equals("Eight")) {
                    this.points = 8;
            }
            else if (this.getFaceValue().equals("Nine")) {
                    this.points = 9;
            }
            else if (this.getFaceValue().equals("Ten")) {
                    this.points = 10;
            }
            else if (this.getFaceValue().equals("Jack")) {
                    this.points = 11;
            }
            else if (this.getFaceValue().equals("Queen")) {
                    this.points = 12;
            }
            else if (this.getFaceValue().equals("King")) {
                    this.points = 13;
            }
            else if (this.getFaceValue().equals("Ace")) {
                    this.points = 14;
            }
            else {
                    this.points = -1;
            }
    }
    
    
    public int getPoints() {
            return this.points;
    }
    
    
    public int getSuitRank() {
            int suitRank = 0;
            if (this.getSuit().equals("Spades")) {
                    suitRank = 4;
            }
            else if (this.getSuit().equals("Diamonds")) {
                    suitRank = 3;
            }
            else if (this.getSuit().equals("Hearts")) {
                    suitRank = 2;
            }
            else if (this.getSuit().equals("Clubs")) {
                    suitRank = 1;
            }
            else {
                    suitRank = 0;
            }
            
            return suitRank;
    }
    
    public String getCardName() {
            String cardName = "";
            cardName = this.facevalue + " of " + this.suit;
            return cardName;
    }
    
    
}


