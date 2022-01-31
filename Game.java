import java.util.List;
import java.util.Scanner;


public class Game {
    public void gameLoop() {
        Deck deck = new Deck();
        Player player = new Player();
        Player dealer = new Player();
        int roundCounter = 0;
        Scanner userInput = new Scanner(System.in);
        int playerBet = 0;

        //RULES
        System.out.println("RULES:\n");
        

        while (true) {
            boolean roundContinue = true;
            boolean busted = false;
            boolean dealerBusted = false;
            roundCounter++;
            System.out.println("\n" + deck.toString() + "\n");
            System.out.println("\n------------------------------------------------\nYour balance is : " + player.getBalance() + "          Round : " + roundCounter);
            System.out.print("Place your bet : ");
            do {
                while (!userInput.hasNextInt()) {
                    System.out.println("Input is not a number.");
                    System.out.print("Place your bet : ");
                    userInput.nextLine();
                }
                playerBet = userInput.nextInt();
                if (player.getBalance() - playerBet < 0) {
                    System.out.println("Insufficient balance for bet.               Your balance : " + player.getBalance());
                    System.out.print("Place your bet : ");
                }
            } while (player.getBalance() - playerBet < 0);
            System.out.println("\nBET PLACED = " + playerBet + " Credits");
           
            //deal 2 card to player and 2 to the dealer
            player.addCardsPlayerHand(2, deck);
            dealer.addCardsPlayerHand(2, deck);

            //setHand used for debugging
            // player.setHand();
            // dealer.setHand();

            System.out.println("\nYour hand : [" + player.getSingleCardFromHand(0) + ", " + player.getSingleCardFromHand(1) + "]");
            System.out.println("\nDealer's hand : [" + dealer.getSingleCardFromHand(0) + ", *Face down card*]");
    

            // IM NOT GOING TO INLCUDE THESE RULSE FOR NOW BECAUSE IT COMPLICATES THINGS

            /**
             * If the dealer's face-up card is a ten-card or an ace, they look at their 
             * face-down card to see if the two cards make a natural. If the face-up 
             * card is not a ten-card or an ace, they do not look at the face-down card
             *  until it is the dealer's turn to play.
             */


            // if (dealer.checkFirstCard(dealer.getHand())) { //If Ace, Jack, Queen, King
            //     System.out.println("\nThe dealer is checking for a natural...");
            //     System.out.println("Dealer's hand : " + dealer.getHand());
            //     if (dealer.checkIfNatural(dealer.getHand())) { //Check dealer has a natural
            //         System.out.println("The dealer has a natrual !");
            //         if (player.checkIfNatural(player.getHand())) { //Check if player  has a natural
            //             System.out.println("You also have a natural, it's a tie. Take your bet back ");
            //             roundContinue = false;
            //         } else {
            //             System.out.println("Dealer collects the bets of all players who do not have naturals");
            //             player.changeBalance(-playerBet);
            //             System.out.println("-" + playerBet + " credits..     ~~ Balance = "  + player.getBalance());
            //             roundContinue = false;
            //         }
            //     } else {
            //         System.out.println("Dealer doesn't have a natural");
            //     }
            // }


            // /**
            //  * It's established that the dealer does NOT have a natural
            //  * now you must check if the player has a natural.
            //  */

            // if (player.checkIfNatural(player.getHand())) {
            //     System.out.println("\nYou have a natural and the dealer does not, you WIN!");
            //     System.out.println("Win amount = " + playerBet*1.5);
            //     roundContinue = false;
            // }


            //*******************
            roundContinue = true; //this piece of code is from the commented code previsously 
            


            if (roundContinue = true) { // only continue playing is boolean is TRUE
                /**
                 * If no natural's are delt to anyone the game continues as normal
                 * An ace is worth 11 until the total of the hand is higher than 21, then the ace is worth just 1
                 */
                int playerHandTotal = 0;
                int playerTempHandTotal = 0;
                int dealerHandTotal = 0;
                int dealerTempHandTotal = 0;
                int countedPlayerCardsCounter = 0;
                int countedDealerCardsCounter = 0;
                int playCounter = 0;
                boolean firstRound = true;
                boolean playerDecisionRound = false;
                boolean playerBusted = false;
                boolean playerBlackJack = false;
                while (true) {


                //First round the player has been delt 2 cards and only 1 card is visible from the dealer
                


                /**
                 * 1. Calculate player's hand value and do checks based on value
                 */ 

                int playerHandValue = player.calcHandValue(player.getHand()); //calc player hand value

                // Check if the player has a hand equal to 21 (Natural)
                if (playerHandValue == 21) {
                    //Check if dealer's hand is equal to 21 (Natural)
                    int dealerHandValue = dealer.calcHandValue(dealer.getHand());
                    if (dealerHandValue == 21) {
                        System.out.println("Hand has tied.. (Push)\nBets are returned");
                    } else {
                        System.out.println("You win!"); 
                        player.changeBalance(playerBet*1.5); //Payout player 1.5x their bet
                        player.clearHand(); //Clear player hand
                        dealer.clearHand(); //Clear dealer hand
                    }
                }

                /**
                 * 2. Ask player to Hit, Stand (Will add more player decisions later)
                 */
 
                System.out.print("\n~~~~~~~~~~\n(H) Hit   or   (S) Stay    : ");
                userInput.nextLine();
                String userResponse = userInput.nextLine().toUpperCase();
                while (!userResponse.equals("H") && !userResponse.equals("S")) {
                    System.out.println("Input is not valid");
                    System.out.print("\n(H) Hit   or   (S) Stay    : ");
                    userResponse = userInput.nextLine().toUpperCase(); 
                }
                
                while (userResponse.equals("H")) {
                    System.out.println("\nYou chose HIT.");
                    player.addCardsPlayerHand(1, deck); //***TODO: this really does not work properly
                    System.out.println("Your hand : " + player.getHand());
                    playerHandValue = player.calcHandValue(player.getHand());
                    System.out.println("Your hand value : " + playerHandValue); 
                    if (playerHandValue > 21) {
                        playerBusted = true;
                        break;
                    }

                    if (playerHandValue == 21) {
                        playerBlackJack = true;
                        break;
                    }

                    System.out.print("\n~~~~~~~~~~\n(H) Hit   or   (S) Stay    : ");
                    userResponse = userInput.nextLine().toUpperCase();
                    while (!userResponse.equals("H") && !userResponse.equals("S")) {
                        System.out.println("Input is not valid");
                        System.out.print("\n(H) Hit   or   (S) Stay    : ");
                        userResponse = userInput.nextLine().toUpperCase(); 
                    }                                      
                } 
                
                //If the player busted
                if (playerBusted) {
                    System.out.println("\nBUST");
                    player.changeBalance(-playerBet);
                }

                //If player got a black jack
                if (playerBlackJack) {
                    player.changeBalance(playerBet*1.5);
                    System.out.println("\nBlackJack, You win " + playerBet*1.5 + " chips!");
                }
                
                //User selected to STAY and DIDNT bust or get a blackjack (natural)
                System.out.println("Dealer's hand : " + dealer.getHand());
                while (!playerBusted && !playerBlackJack) {
                    int dealerHandValue = dealer.calcHandValue(dealer.getHand());
                    if (dealerHandValue > 21) {
                        player.changeBalance(playerBet*1.5);
                        System.out.println("You win " + playerBet*1.5 + " chips!");
                        break;
                    }

                    if (dealerHandValue > playerHandValue) {
                        System.out.println("Dealer wins!?");
                        player.changeBalance(-playerBet);
                        break;
                    }

                    if (dealerHandValue >= 17) { //stand >= 17??

                        if (dealerHandValue > playerHandValue) {
                            System.out.println("Dealer wins!?");
                            player.changeBalance(-playerBet);
                            break;
                        } else if (dealerHandValue == playerHandValue) { 
                            System.out.println("Hand has tied.. (Push)\nBets are returned");
                        } else {
                            player.changeBalance(playerBet*1.5);
                            System.out.println("You win " + playerBet*1.5 + " chips!");
                        }

                        
                    } else {
                        dealer.addCardsPlayerHand(1, deck);
                        System.out.println("Dealer's hand : " + dealer.getHand());
                    }
                }           
                player.clearHand(); //Clear player hand
                dealer.clearHand(); //Clear dealer hand
                break;
            }

        }















































































                    // playCounter++;
                    // playerTempHandTotal = playerHandTotal;









                    // //if the player has already decided to hit/stay 
                    // if (playCounter > 1) {
                    //     //show the rest of the dealer's hand
                    //     System.out.println("Dealer's hand: " + dealer.getHand());
                    //     //calculate value of dealer's hand
                    //     int dealerHandValue = dealer.calcHandValue(dealer.getHand());
                    //     System.out.println("Dealer's hand value = " + dealerHandValue);

                    //     if (dealerHandValue > 21) {
                    //         System.out.println("Dealer bust..");
                    //         player.changeBalance(playerBet); 
                    //         dealerBusted = true;
                    //     } else if (dealerHandValue == 21) {
                    //         System.out.println("Dealer wins..");
                    //         dealerBusted = false;
                    //     } else {
                    //         //calculate value of player's hand
                    //         int playerHandValue = player.calcHandValue(player.getHand());
                    //         System.out.println("Player's hand value = " + playerHandValue);
                    //         if (playerHandValue > 21) {
                    //             System.out.println("You lose..");
                    //             player.changeBalance(-playerBet); 
                    //             busted = true;
                    //         } else if (playerHandValue == 21) {
                    //             System.out.println("You win!!");
                    //             player.changeBalance(playerBet*2);
                    //         } else {
                            
                    //             System.out.print("\n~~~~~~~~~~\n(H) Hit   or   (S) Stay    : ");
                    //             userInput.nextLine();
                    //             String response = userInput.nextLine().toUpperCase();
                    //             while (!response.equals("H") && !response.equals("S")) {
                    //                 System.out.println("Input is not valid");
                    //                 System.out.print("\n(H) Hit   or   (S) Stay    : ");
                    //                 userInput.nextLine();
                    //                 response = userInput.nextLine().toUpperCase(); //TODO: bug here where it scans an extra line 
                    //             }
                                
                    //             if (response.equals("H")) {
                    //                 System.out.println("\nYou chose HIT.");
        
                    //                 player.addCardsPlayerHand(1, deck);
                    //                 System.out.println("Your hand : " + player.printHand());
                    //             } 
                                
                    //             if (response.equals("S")) {
                    //                 System.out.println("\nYou chose STAY.");
                    //                 System.out.println("Your hand : " + player.printHand());
                    //             } 
                    //         }
                    //     }
                    //

                        
                       















                    //     //iterate through dealer's hand to calculate value of cards
                    //     for (int i = countedDealerCardsCounter; i < dealer.getHand().size(); i++) {
                    //         //check if card is an ace
                    //         if(dealer.checkIfCurrentCardAce(dealer.getHand().get(i))) {
                    //             if (playerTempHandTotal > 21) { //check if total is greater than 21
                    //                 playerTempHandTotal -= 10;
                    //             }
    
                    //             //check AGAIN if total is > 21
                    //             // if it is then you have busted...
                    //             if (dealerTempHandTotal > 21) { 
                    //                 System.out.println("\n*** DEALER BUST ***\nTheir hand value is greater 21...");
                    //                 dealerBusted = true;
                    //                 break;
                    //             }
                    //         } else {
                    //             //if current card is NOT an Ace
                    //             dealerTempHandTotal += dealer.getHand().get(i).getBJvalue();
                    //         }

                    //         if (dealerTempHandTotal > 21) { //check if total is greater than 21
                    //             System.out.println("\n*** DEALER BUST ***\nTheir hand value is greater 21...");
                    //             dealerBusted = true;
                    //             break;
                    //         }
    
                    //         countedDealerCardsCounter++;
                    //     }
                    // }

                    // for (int i = countedPlayerCardsCounter; i < player.getHand().size(); i++) {

                    //     if (player.checkIfCurrentCardAce(player.getHand().get(i))) { //if current card is an ace
                    //         playerTempHandTotal += player.getHand().get(i).getBJvalue() + 10; //get card value of current card in hand

                    //         if (playerTempHandTotal > 21) { //check if total is greater than 21
                    //             playerTempHandTotal -= 10;
                    //         }

                    //         //check AGAIN if total is > 21
                    //         // if it is then you have busted...
                    //         if (playerTempHandTotal > 21) { 
                    //             System.out.println("\n*** BUST ***\nYour hand value is greater 21...");
                    //             player.changeBalance(-playerBet); 
                    //             busted = true;
                    //             break;
                    //         }
                            
                    //     } else {
                    //         //if current card is NOT an Ace
                    //         playerTempHandTotal += player.getHand().get(i).getBJvalue();
                    //     }

                    //     if (playerTempHandTotal > 21) { //check if total is greater than 21
                    //         System.out.println("\n*** BUST ***\nYour hand value is greater 21...");
                    //         player.changeBalance(-playerBet); 
                    //         busted = true;
                    //         break;
                    //     }

                    //     countedPlayerCardsCounter++;
                    // }

                    // if (busted != true) { //only continue if the player has NOT busted
                    //     playerHandTotal = playerTempHandTotal; 

                    //     if (playCounter > 1) {
                    //         System.out.println("Dealer's hand : " + dealer.getHand());
                    //         //check if dealer's hand is blackjack
                            
                    //     } else {

                    //     }

                    //     System.out.println("\nCard total value = " + playerHandTotal);
    
                    //     System.out.print("\n~~~~~~~~~~\n(H) Hit   or   (S) Stay    : ");
                    //     userInput.nextLine();
                    //     String response = userInput.nextLine().toUpperCase();
                    //     while (!response.equals("H") && !response.equals("S")) {
                    //         System.out.println("Input is not valid");
                    //         System.out.print("\n(H) Hit   or   (S) Stay    : ");
                    //         userInput.nextLine();
                    //         response = userInput.nextLine().toUpperCase(); //TODO: bug here where it scans an extra line 
                    //     }
                        
                    //     if (response.equals("H")) {
                    //         System.out.println("\nYou chose HIT.");

                    //         player.addCardsPlayerHand(1, deck);
                    //         System.out.println("Your hand : " + player.printHand());
                    //     } 
                        
                    //     if (response.equals("S")) {
                    //         System.out.println("\nYou chose STAY.");
                    //         System.out.println("Your hand : " + player.printHand());
                    //     }

                    // } else { //if the player has busted then break out of while loop
                    //     dealer.getHand().toString();
                    //     break;
                    // }
                    
                    
                    
                    // if (player.isCardInHand("Ace")) { //Check if the player has an Ace
                    //     int numOfAcesInHand = player.countAcesInHand();
                    //     playerTempHandTotal = 0;
                    //     for (Card c : player.getHand()) { //Add up all the value of cards in hand IF there is 1 or more ace
                    //         playerTempHandTotal = c.getBJvalue();
                    //     }
                    //     playerTempHandTotal += 11*numOfAcesInHand; //Add
                    //     // playerTempHandTotal = player.getSingleCardFromHand(0).getBJvalue() + player.getSingleCardFromHand(1).getBJvalue() + 10;
                    //     while (playerTempHandTotal > 21) {
                    //         playerTempHandTotal -= 10; 
                    //         playerHandTotal = playerTempHandTotal;
                    //     }
                    //     playerHandTotal = playerTempHandTotal; 
                    //     System.out.println("\nCard total value = " + playerHandTotal);
                    // } else {
                    //     for (Card c : player.getHand()) { //Add up all the value of cards in hand IF there is 1 or more ace
                    //         playerHandTotal = c.getBJvalue();
                    //     }
                    //     playerHandTotal = player.getSingleCardFromHand(0).getBJvalue() + player.getSingleCardFromHand(1).getBJvalue(); 
                    //     System.out.println("\nCard total value = " + playerHandTotal);
                    // } 

                    // if (playerHandTotal == 21) {
                    //     System.out.println("You win!");
                    //     player.changeBalance((int) Math.round(playerBet*1.5));
                    //     break;
                    // } 

                    // if (playerHandTotal > 21) {
                    //     System.out.println("Bust..");
                    //     player.changeBalance(-playerBet);
                    //     break;
                    // }

      
            
                
                

            
        } //outer while loop
    }
}





   


            // if (dealer.getSingleCardFromHand(0).getBJvalue() == 10) { //If Jack, Queen, King
            //     System.out.println("\nThe dealer is checking for a natural...");
            //     System.out.println("Dealer's hand : " + dealer.getHand());
            //     if (dealer.getSingleCardFromHand(1).getValue().equals("Ace")) { //If Ace
            //         System.out.println("The dealer has a natrual !");
            //         if (player.getSingleCardFromHand(0).getBJvalue() == 10 && player.getSingleCardFromHand(1).getValue().equals("Ace")) {
            //             System.out.println("You also have a natural, it's a tie. Take your bet back ");
            //         } else if (player.getSingleCardFromHand(0).getValue().equals("Ace") && player.getSingleCardFromHand(1).getBJvalue() == 10) {
            //             System.out.println("You also have a natural, it's a tie. Take your bet back ");
            //         } else {
            //             System.out.println("Dealer collects the bets of all players who do not have naturals");
            //             player.changeBalance(-playerBet);
            //             System.out.println("-" + playerBet + " credits..     ~~ Balance = "  + player.getBalance());
                       
            //         }
            //     } else {
            //         System.out.println("Dealer doesn't have a natural");
            //     }
            // } else if (dealer.getSingleCardFromHand(0).getValue().equals("Ace")) { //If Ace
            //     System.out.println("\nThe dealer is checking for a natural...");
            //     System.out.println("Dealer's hand : " + dealer.getHand());
            //     if (dealer.getSingleCardFromHand(1).getBJvalue() == 10) { //If Jack, Queen, King
            //         System.out.println("The dealer has a natrual !");
            //         if (player.getSingleCardFromHand(0).getBJvalue() == 10 && player.getSingleCardFromHand(1).getValue().equals("Ace")) {
            //             System.out.println("You also have a natural, it's a tie. Take your bet back ");
            //         } else if (player.getSingleCardFromHand(0).getValue().equals("Ace") && player.getSingleCardFromHand(1).getBJvalue() == 10) {
            //             System.out.println("You also have a natural, it's a tie. Take your bet back ");
            //         } else {
            //             System.out.println("Dealer collects the bets of all players who do not have naturals");
            //             player.changeBalance(-playerBet);
            //             System.out.println("-" + playerBet + " credits..     ~~ Balance = "  + player.getBalance());
            //         }   
            //     } else {
            //         System.out.println("Dealer doesn't have a natural");
            //     }
            // } else { // If the dealer does NOT have a natural, proceed
            //     //Check if the player's hand is a natural
            //     checkIfNatural(player.getHand());
                
            // }