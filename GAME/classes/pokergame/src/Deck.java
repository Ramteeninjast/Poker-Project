import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



public class Deck{

    private List<Card> cards;
    public Deck(){
        cards = new ArrayList<>();
        String [] ranks = {"2","3","4","5","6","7","8","9","10","Jack","Queen","King","Ace"};
        String [] suits = {"Clubs", "Diamonds", "Hearts", "Spades"};

        for (String suit : suits){
            for (String rank : ranks){
                cards.add(new Card(rank, suit));
            }
        }
    }

    public void shuffle(){
        Collections.shuffle(cards);
    }
    public List<Card> dealCards(){
        List<Card> hand = new ArrayList<>();
        if (cards.size()>2){
            hand.add(cards.remove(0));
            hand.add(cards.remove(0));

        }
        return hand;
    }

    private List<Card> communityCards = new ArrayList<>(); // Store community cards

    public List<Card> community_cards(){
        if (communityCards.isEmpty()) { // Only deal flop once
            for (int i = 0; i < 3; i++) {
                communityCards.add(cards.remove(0));
            }
        }
        return new ArrayList<>(communityCards);
    }

    public Card turncard(){
        if (communityCards.size() == 3) { // Ensure turn is dealt only after flop
            Card turn = cards.remove(0);
            communityCards.add(turn);
            return turn;
        }
        return null;
    }

    public Card rivercard(){
        if (communityCards.size() == 4) { // Ensure river is dealt only after turn
            Card river = cards.remove(0);
            communityCards.add(river);
            return river;
        }
        return null;
    }

    public int remainingsize(){
        return cards.size();
    }
}

