public class Card {
    private String suit;
    private String value;
    private int blackjackValue;  

    public Card (String suit, String value, int blackjackValue) {
        this.suit = suit;
        this.value = value;
        this.blackjackValue = blackjackValue;
    }

    public String toString () {
        return value + " - " + suit;
    }

    public String getSuit () {
        return suit;
    }

    public String getValue () {
        return value;
    }

    public int getBJvalue () {
        return blackjackValue;
    }
}
