import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;

// import java.util.Arrays;

// public class Deck {
//     public static String[] makeUnshuffledDeck(){
//         // All this code is based on james lecture, so slightly plaigraised 
//         String[] cards = new String[52]; 
//         // Not sure what the final keyword does?
//         final String[] cardSuit = {"CLUB","DIAMOND","HEART","SPADE"};
//         final String[] cardValue = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
//         int deckIndex = 0;
//         for (String SuitElement : cardSuit){
//             for(String ValueElement : cardValue){
//                 cards[deckIndex] = ValueElement + "-" + SuitElement;
//                 deckIndex++;
//             }
//         }
//         return cards; 
//     }
    
//     public static void printDeck(String [] cards){
//         for (int i = 0; i < 52; i++) {
//             System.out.print(cards[i] + "  ");
//         }
//     }

//     // Temporary main method since I don't know OOP
//     public static void main(String[] args) {
//         printDeck(makeUnshuffledDeck()); 
//     }




//     // More tesing class things.s
//     // card object
//     // String[] emptyDeckTest;
//     // Card testCard = new Card();... 

    
// }
    
public class Deck {
    private List<Card> deckList = new ArrayList<Card>();
    // private String[] deckArray = new String[52];
    private String[] suitArray = {"Heart", "Diamond", "Spade", "Club"};
    private String[] valueArray = {"Ace","2","3","4","5","6","7","8","9","10","Jack","Queen","King"};
    private int[] blackjackValue = {1,2,3,4,5,6,7,8,9,10,10,10}; //Ace is set to 1 you can plus 10 if it needs to be 11

    public Deck () {
        this.deckList = makeDeck();
    }

    private List<Card> makeDeck() {
        // /**
        //  * Copy strings from suit array into suit list
        //  */
        // List<String> suitList = new ArrayList<String>(); //suits
        // for (String suit : suitArray) {
        //     suitList.add(suit);
        // }
        // /**
        //  * Copy strings from value array into value list
        //  */
        // List<String> valueList = new ArrayList<String>(); //values
        // for (String value : valueArray) {
        //     valueList.add(value);
        // }

        /**
         * Create array of unshuffled deck of cards
         */
        for (String suit : suitArray) {
            for (String value : valueArray) {
                int bjValue =  0;
                
                if (value.equals("Jack") || value.equals("Queen") || value.equals("King")) {
                    bjValue = 10;
                } else {
                    bjValue = Arrays.asList(valueArray).indexOf(value) + 1; //Set the value of the card to the corresponding blackjack card value
                } 
                Card card = new Card(suit, value, bjValue);
                deckList.add(card);
            }
        }

        // int deckArrayCounter = 0;
        // for (String suit : suitArray) {
        //     for (String value : valueArray) {
        //         deckArray[deckArrayCounter++] = value + "-" + suit;
        //     }
        // }

        /**
         * Shuffle array of cards
         */
        Random rand = new Random();
        for (int i = 0; i < deckList.size(); i++) {
            Card currentCard = deckList.get(i); //current card
            int randomPos = rand.nextInt(52); //generates a random number from 1 - 52
            Card cardAtRandomPos = deckList.get(randomPos); //card at random position

            //iterate through deck and switch current card with a random card in the deck
            deckList.set(randomPos, currentCard); 
            deckList.set(i, cardAtRandomPos); 

        }
    
        // Random rand = new Random();
        // for (int i = 0; i < deckList.length; i++) {
        //     deckArray[i] = deckArray[rand.nextInt(52)];
        // }

        /**
         * Copy card in array to list
         */
        // for (String card : deckArray) {
        //     deckList.add(card);
        // }

        // System.out.println(Arrays.toString(deckArray));
        return this.deckList;
    }

    public List<Card> getDeck () {
        return this.deckList;
    }

    // public String getCardFromDeck () {
    //     return this.deckList.get(0); //get first element from deck list
    // }

    public Card removeAndReturnCard () {
        return this.deckList.remove(0); //remove first element in deck list then return it
    }
    
    public String toString () {
        return "" + deckList;
    }
}