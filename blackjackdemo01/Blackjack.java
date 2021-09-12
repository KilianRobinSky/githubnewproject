package blackjackdemo01;

import java.util.Scanner;

public class Blackjack {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

			//Welcome Message
		System.out.println("Welkom bij Blackjack.");
		
		Deck playingDeck = new Deck();
		playingDeck.createFullDeck();
		playingDeck.shuffle();
		//Playing Deck dealer en speler maken
		Deck playerDeck = new Deck ();
		
		Deck dealerDeck = new Deck ();
			
		System.out.println(playingDeck);
		
		double playerMoney = 100.00;
		
		Scanner userInput = new Scanner(System.in);
		
		//Loop maken
		while(playerMoney >0) {
			// Speel door
			// Bet aannemen
			System.out.println("Je hebt $" + playerMoney +", hoeveel wil je inzetten?");
			double playerBet = userInput.nextDouble();
			if (playerBet > playerMoney) {
				System.out.println("Je hebt geen geld meer, verlaat het casino");
				break;
			}
			
			boolean endRound = false;
			
			// Start met uitdelen
			// Speler krijgt twee kaarten
			playerDeck.draw(playingDeck);
			playerDeck.draw(playingDeck);
			
			//Dealer krijgt twee kaarten
			dealerDeck.draw(playingDeck);
			dealerDeck.draw(playingDeck);
			
			while (true) {
				System.out.println("Je hand:");
				System.out.println(playerDeck.toString());
				System.out.println("Je hand is: " + playerDeck.cardsValue());
				
				//Dealer Hand
				System.out.println("Dealer hand: " + dealerDeck.getCard(0).toString() +" en verborgen");
				
				// Wilde speler doorgaan?
				System.out.println("Wil je 1(Hit) of 2 (Passen)");
				int response = userInput.nextInt();
				
				// Hit
				if (response == 1) {
					playerDeck.draw(playingDeck);
					System.out.println("Je hebt een: " + playerDeck.getCard(playerDeck.deckSize() -1).toString());
				// Bust over 21
					if (playerDeck.cardsValue() >21) {
						System.out.println("Bust. Waarde van kaarten is: " + playerDeck.cardsValue());
						playerMoney -=playerBet;
						endRound = true;
						break;
					}
				}
				if (response ==2) {
					break;
	
				}
			}
			// Laat dealer kaart zien
			System.out.println("Dealer kaarten: "+ dealerDeck.toString());
			// Waarde dealer bepalen
			if((dealerDeck.cardsValue() > playerDeck.cardsValue())&& endRound == false) {
				System.out.println("Dealer wint");
				playerMoney -= playerBet;
				endRound = true;
				
			}
			
			// Dealer Draws tot 16, past vanaf 17
			while((dealerDeck.cardsValue() < 17) && endRound == false) {
				dealerDeck.draw(playingDeck);;
				System.out.println("Dealer Draws: " + dealerDeck.getCard(dealerDeck.deckSize()-1).toString());
				
			}
			// Laat waarde dealer zien
			System.out.println("Dealers hand is gewaardeerd op: " + dealerDeck.cardsValue());
			// Dealer busted?
			if((dealerDeck.cardsValue() > 21)&& endRound == false){
				System.out.println("Dealer busted, jij wint!");
				playerMoney +=playerBet;
				endRound = true;
			}
			// Gelijkspel?
			if ((playerDeck.cardsValue() == dealerDeck.cardsValue()) && endRound == false){
				System.out.println("Gelijkspel");
				endRound = true;
				
			}
			if((playerDeck.cardsValue() > dealerDeck.cardsValue()) && endRound == false) {
				System.out.print("Jij wint de hand!");
				playerMoney += playerBet;
				endRound = true;
			}
			else if(endRound == false) {
				System.out.println("Je verliest de hand.");
				endRound = true;
			}
			playerDeck.moveALLtoDeck(playingDeck);
			dealerDeck.moveALLtoDeck(playingDeck);
			System.out.println("Einde hand");
		}
		System.out.println("Game Over! Geen geld meer. :(");
		
	}

}
