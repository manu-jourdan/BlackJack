import java.util.ArrayList;
import java.util.List;



public class Player {
    private List<Card> playerHand;
    private double playerBalance;
    // private List<Card> tempPlayerHand;

    public Player () {
        this.playerHand = new ArrayList<Card>();
        this.playerBalance = 500;
        // this.tempPlayerHand = this.playerHand;
    }

    public void addCardsPlayerHand (int amount, Deck d) {
        for (int i = 0; i < amount; i++) {
            // playerHand.add(d.getDeck().get(0)); //Get the first element of deck list then add it to player hand
            // d.getDeck().remove(0); //Remove same element from deck list
            // playerHand.add(d.getCardFromDeck());
            // d.removeCardDeck();
            playerHand.add(d.removeAndReturnCard()); //take 1 card away from deck and add it to hand
            
        }
    }

    /**
     * Method for debugging 
     */
    public void setHand () { 
        playerHand.clear();
        // Card card = new Card ("Heart", "Ace", 1);
        // playerHand.add(card);
        // card = new Card ("Heart", "Jack", 10);
        // playerHand.add(card);

        Card card = new Card ("Heart", "Ace", 1);
        playerHand.add(card);
        card = new Card ("Diamond", "Ace", 1);
        playerHand.add(card);



    } // setHand

    public List<Card> getHand () {
        return playerHand;
    }

    public Card getSingleCardFromHand (int pos) {
        return playerHand.get(pos);
    }

    public boolean checkFirstCard (List<Card> hand) {
        if (hand.get(0).getValue().equals("Ace")) {
            return true;
        } else if (hand.get(0).getBJvalue() == 10) {
            return true;
        } else {
            return false;
        }
        
    }

    public boolean checkIfCurrentCardAce (Card c) {
        if (c.getValue().equals("Ace")) {
            return true;
        }
        return false;

    }

    public boolean isCardInHand (String value) {
        for (Card c : playerHand) {
            if (c.getValue().equals(value)) {
                return true;
            }       
        }
        return false;
    }

    public int countAcesInHand () {
        int counter= 0;
        for (Card c : playerHand) {
            if (c.getValue().equals("Ace")) {
                counter++;
            }       
        }
        return counter;
    }

    public boolean checkIfNatural (List<Card> hand) {
        if (hand.get(0).getValue().equals("Ace") && hand.get(1).getBJvalue() == 10) {
            return true;
        } else if (hand.get(0).getBJvalue() == 10 && hand.get(1).getValue().equals("Ace")) {
            return true;
        } else {
            return false;
        }
        
    }

    public int getAcePos () {
        for (Card c : playerHand) {
            if (c.getValue().equals("Ace")) {
                return playerHand.indexOf(c);
            }       
        }
        return -1;
    }

    /**
     * Method to count value of card in hand of player/dealer
     */

     public int calcHandValue (List<Card> hand) {
        int handValue = 0;
        int aceCount = 0;
        for (int i = 0; i < hand.size(); i++) {

            //check if the current card is an ace
            if (hand.get(i).getValue().equals("Ace")) {
                //if it is an ace then just set the value to 1 initally
                handValue += 1;      
                aceCount ++;
            } else {
                 //if current card is NOT an Ace
                handValue += hand.get(i).getBJvalue();
            }         
        }

        for (int i = 0; i < aceCount; i++) {
            if (handValue + 10 <= 21) {
                handValue += 10;
            }
        }

        //return final value of hand
        return handValue;
    }

    /**
     * Method to clear the hand of player/dealer
     */
    public void clearHand () {
        playerHand.clear();
    }


    public void changeBalance (double amount) {
        this.playerBalance += amount;
    }

    public double getBalance () {
        return this.playerBalance;
    }

    public String printHand () {
        String stringHand = "[";
        for (Card c : playerHand) {
            if (playerHand.indexOf(c) != playerHand.size()-1) {
                stringHand += c + ",  ";
            } else {
                stringHand += c;
            }
            
        }
        return stringHand + "]";
    }

    // public List<Card>  getTempHand () {
    //     return tempPlayerHand;
    // }

    // public void removeCurrentCardTempHand (Card c) {
    //     this.playerHand.remove(c);
    // }

    // public Card getSingleCardFromTempHand (int pos) {
    //     return tempPlayerHand.get(pos);
    // }
}
