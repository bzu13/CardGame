package cardGame;

import java.util.ArrayList;
import java.util.List;

public class Player {
        
        private String name;
        private List<Card> hand = new ArrayList<Card>();
        private int score = 0;
        
        public void setName(String name) {
                this.name = name;
        }
        
        public String getName() {
                return this.name;
        }
        
        public void setScore(int score) {
                this.score = score;
        }
        
        public int getScore() {
                return this.score;
        }
        
        public void setHand(Card card) {
                this.hand.add(0,card);
        }
        
        public List<Card> getHand() {
                return this.hand;
        }
        
        @Override
        public String toString() {
                String returnStatement = "";
                returnStatement =  this.name + " drew card: " + this.hand.get(0).getFaceValue() + " of " + this.hand.get(0).getSuit();
                return returnStatement;
        }
        

        

}
