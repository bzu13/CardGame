
package cardGame;
import java.util.*;
import java.io.*;

public class CardGame {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		
		
		Scanner scan = new Scanner(System.in);
		int numberOfPlayers;
		System.out.println("Enter number of players as an integer between 2 and 4:");
		
		boolean intEntered = false;
		//start of game
		do {
			try {
				numberOfPlayers = scan.nextInt();
				if (numberOfPlayers == 2 || numberOfPlayers == 3 || numberOfPlayers == 4) {
					
					Player[] players = new Player[numberOfPlayers];
					System.out.println("Beginning Game...");
					Card[] deck = createDeck();
					deck = shuffleDeck(deck);
					int[] scores = new int[numberOfPlayers];
					
					playGame(players, scores, deck);
					
					intEntered = true;
				} 
				
				else {
					intEntered = false;
				}
				
			} catch (InputMismatchException e) {
				// TODO Auto-generated catch block
				System.out.println("Please run again and enter an integer for number of players between 2 and 4.");
				break;
				//e.printStackTrace();
			}
		} while (!intEntered);
		
		
	}
	
	public static Card[] createDeck() {
		Card [] deck = new Card[56];
		String [] suits = new String[] {"Spades", "Hearts", "Clubs", "Diamonds"};
		String [] faces = new String[] {"Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King", "Ace", "Penalty"};
		int current = 0;
		
		for (int i = 0; i < 4; i ++)
		{
			for (int j = 0; j < 14; j++) {
				deck[current] = new Card();
				deck[current].setSuit(suits[i]);
				deck[current].setFaceValue(faces[j]);
				current += 1; 
			}
		}
		return deck;
	}
	
	public static Card[] shuffleDeck(Card[] deck) {
		Set<Integer> indexes = new HashSet<Integer> ();
		Card[] shuffled = new Card[56];
		Random rand = new Random();
		int current = 0; 
		for (int i = 0; i < shuffled.length; i++) {
			current = rand.nextInt(56);
			if (indexes.add(current)) { //checks if index is duplicated in list
				shuffled[i] = new Card();
				shuffled[i].setSuit(deck[current].getSuit()); 
				shuffled[i].setFaceValue(deck[current].getFaceValue()); 
			}
			else {
				i = i - 1;  //get new index for current iteration 
				current = rand.nextInt(56);
			}
			
		}
		return shuffled;
	}
	
	
	public static Player[] scoring(Player[] players) {
		int numberOfPlayers = players.length;
		int max = 0;
		int[] points = new int[numberOfPlayers];
		List<Integer> highestFace = new ArrayList<Integer>();
		
		for(int i = 0; i < numberOfPlayers; i ++) {			
			points[i] = players[i].getHand().get(0).getPoints();
		}
		
		max = findMax(points)[0];
		int maxLocation = findMax(points)[1];
		
		//int indexOfRepeatFace = 0;
		
		for (int k = 0; k < numberOfPlayers; k++) {
			if(k == maxLocation) {
				//players[k].setScore(players[k].getScore()+2);
				highestFace.add(0, players[k].getHand().get(0).getSuitRank());
				points[k] += highestFace.get(0);
 			}
			else {
				if (points[k] == -1) {
					if(players[k].getScore() > 0) {
						players[k].setScore(players[k].getScore()-1);
					}
				}
				else {
					players[k].setScore(players[k].getScore());
				}
				
			}
			
		}
		
		if(highestFace.size() > 1) {
			max = findMax(points)[0];
			maxLocation = findMax(points)[1];
			players[maxLocation].setScore(players[maxLocation].getScore()+2);
		}
		else {
			players[maxLocation].setScore(players[maxLocation].getScore()+2);
		}
		
		return players;
		
	}

	public static int[] findMax(int[] values) {
		int max = values[0];
		int position = 0;
		
		for (int i = 1; i < values.length; i ++) {
			if (values[i] > max) {
				max = values[i];
				position = i;
			}
		}
		int[] result = {max, position};
		return result;
	}
	
	
	
	public static String scoreboard(Player[] players, int round) {
		String scoreboard = "";
		scoreboard = "\n" + "--------------------------- \n" + "Score Board after round " + round + "\n";
		for (int i = 0; i < players.length; i ++) {
			scoreboard += players[i].getName() + ": " + players[i].getScore() + "\n";
		}
		scoreboard += "--------------------------- \n";
		return scoreboard;
	}
	
	
	public static String[] getPlayerNames(int numberOfPlayers) {
		String[] playerNames = new String[numberOfPlayers];
		for (int i = 0; i < numberOfPlayers; i ++) {
			playerNames[i] = "player" + (i+1);
		}
		return playerNames;
	}
	
	public static void playGame(Player[] players, int[] scores, Card[] deck) {
		Scanner scan = new Scanner(System.in);
		int numberOfPlayers = players.length;
		String[] playerNames = getPlayerNames(numberOfPlayers);
	
		
		int currentTurn = 0;
		int playerIndex = 0;
		int winningScore = 21;
		int round = 1;
		//initial conditions
		for (int i = 0; i < numberOfPlayers; i++) {
			
			players[i] = new Player();
			players[i].setName(playerNames[i]);
			players[i].setHand(deck[i]);
			deck[i].setPoints();
			players[i].setScore(0);
			scores[i] = players[i].getScore();
		}
		
		while(currentTurn < numberOfPlayers) {
			System.out.print(players[playerIndex].getName() + " Press any key and then enter to take turn:");
				scan.next();
				System.out.println();
				System.out.println(players[playerIndex].toString());
				//System.out.println();
				playerIndex += 1;
				currentTurn += 1;
		}
		
		players = scoring(players);
		
		
		System.out.println(scoreboard(players, round));
		
		int winningPlayer = 0;
		int currentHighestScore = findMax(scores)[0];
		
		while(currentHighestScore < winningScore) {
			round += 1; //increase round count
			deck = shuffleDeck(deck); //shuffle deck
			playerIndex = 0; //start at first player
			currentTurn = 0; //reset turn count
			while(currentTurn < numberOfPlayers) { 
				System.out.print(players[playerIndex].getName() + " Press any key and then enter to take turn:");
					scan.next();
					System.out.println();
					deck[playerIndex].setPoints();
					players[playerIndex].setHand(deck[playerIndex]);
					System.out.println(players[playerIndex].toString());
					//System.out.println();
					playerIndex += 1;
					currentTurn += 1;
			}//take turn, deal card, output each players hand
			players = scoring(players); //get new scores based on round
			System.out.println(scoreboard(players, round)); //display updated scoreboard
			for(int j = 0; j<numberOfPlayers; j++) {
				scores[j] = players[j].getScore();
			}//update score array
			currentHighestScore = findMax(scores)[0]; //update the current high score
			
			if(currentHighestScore >= winningScore) { //check to see if winning player leads by more than two points or updates winning score necessary if no lead
				for(int k = 0; k < numberOfPlayers; k++) {
					if(k != findMax(scores)[1] && scores[findMax(scores)[1]]-scores[k] < 2) {
						winningScore += 1;
						break;
					}
					else {
						winningPlayer = findMax(scores)[1];
					}
				}
			System.out.println("Player " + players[winningPlayer].getName() + " wins! \n" + "Winning Score: " + players[winningPlayer].getScore());	
			}
		}
		
	}
}
