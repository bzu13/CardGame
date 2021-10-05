package cardGame;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CardGameTest {

	Player[] players;
	int numberOfPlayers;
	Card[] deck;
	int[] scores;
	
	@BeforeEach
	void setUp() throws Exception {
		numberOfPlayers = 4;
		players = new Player[numberOfPlayers];
		String[] playerNames = CardGame.getPlayerNames(numberOfPlayers);
		scores = new int[numberOfPlayers];
		deck = CardGame.createDeck();
		for (int i = 0; i < numberOfPlayers; i++) {
			
			players[i] = new Player();
			players[i].setName(playerNames[i]);
			players[i].setHand(deck[i]);
			deck[i].setPoints();
			players[i].setScore(0);
			scores[i] = players[i].getScore();
		}
	}

	@Test
	void testCreateDeck() {
		Card[] testDeck = CardGame.createDeck();
		assertEquals(testDeck.length, 56);
		//fail("Not yet implemented");
	}
	
	@Test
	void testShuffleDeck() {
		Card[] testDeck = CardGame.createDeck();
		testDeck = CardGame.shuffleDeck(testDeck);
		assertEquals(testDeck.length, 56);
		//fail("Not yet implemented");
	}
	
	@Test
	void testScoringUnique() {
		
		for (int i = 0; i < numberOfPlayers; i++) {
			players[i] = new Player();
			players[i].setHand(deck[i]);
			deck[i].setPoints();
		}
		
		players = CardGame.scoring(players);
		assertEquals(players[numberOfPlayers-1].getScore(), 2);
		
		
	}
	
	@Test
	void testScoringPenalty() {
		
		players[numberOfPlayers - 1].setHand(deck[55]);
		deck[55].setPoints();//penalty card
		players = CardGame.scoring(players);
		assertEquals(players[numberOfPlayers-1].getScore(), 0);
		
	}
	
	@Test
	void testScoringPenaltyLosePoints() {
		
		
		players[numberOfPlayers - 1].setScore(2);
		players[numberOfPlayers - 1].setHand(deck[55]);
		deck[55].setPoints();//penalty card
		players = CardGame.scoring(players);
		assertEquals(players[numberOfPlayers-1].getScore(), 1);
		
	}
	
	
	@Test
	void testRepeatFace() {
		Card[] testDeck = new Card[4];
		testDeck[0] = deck[1]; // Three of spades
		testDeck[1] = deck[10]; //Queen of Spades
		testDeck[2] = deck[24]; //Queen of Hearts
		testDeck[3] = deck[38];// Queen of Clubs
		
		for(int i =0; i < numberOfPlayers; i ++) {
			players[i] = new Player();
			players[i].setHand(testDeck[i]);
			testDeck[i].setPoints();
		}
		
		players = CardGame.scoring(players);
		assertEquals(players[1].getScore(), 2);
		
	}
	
	@Test
	void testPlayGame() {
		CardGame.playGame(players, scores, deck);
		int winningScore = CardGame.findMax(scores)[0];
		int winningPlayer = CardGame.findMax(scores)[1];
		
		assertEquals(players[winningPlayer].getScore(), winningScore);
		
	}
	
	
	
	

}
