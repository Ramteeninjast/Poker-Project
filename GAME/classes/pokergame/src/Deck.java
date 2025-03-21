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

    public List<Card> commiunity_cards(){
        List<Card> flop = new ArrayList<>();
        for (int i=1; i<4; i++){
            if (cards.size()!=0) {
                flop.add(cards.remove(0));
            }
        }
        return flop;
    }
    public int remainingsize(){
        return cards.size();
    }
}

