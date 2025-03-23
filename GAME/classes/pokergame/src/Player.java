import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private int chips;
    private List<Card> hand;
    private boolean isActive;
    private int currentbet;


    public Player(String name, int chips) {
        this.name = name;
        this.chips = chips;
        this.hand = new ArrayList<>();
        this.isActive = true;
        this.currentbet = 0;
    }
    public void receiveCard(Card card) {
        hand.add(card);
    }
    public boolean placeBet(int amount) {
        if (amount > chips){
            System.out.println("You do not have enough chips to place bet");
            return false;
        }
        currentbet += amount;
        chips -= amount;
        System.out.println( name +" placed " + amount + " bet");
        return true;
    }
    public void fold(){
        isActive = false;
        System.out.println( name +" folds");
    }
    public int getCurrentBet() {
        return currentbet;
    }
    public String getName() {
        return name;
    }
    public int getChips() {
        return chips;
    }
    public void showhand() {
        System.out.println("player has" +hand);
    }

    public boolean isActive() {
        return isActive;
    }
    public List<Card> getHand() {
        return hand; // Assuming 'hand' is a List<Card> storing the player's cards
    }

}
