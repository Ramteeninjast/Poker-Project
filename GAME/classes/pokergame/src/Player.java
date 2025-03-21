import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private int chips;
    private List<Card> hand;

    public Player(String name, int chips) {
        this.name = name;
        this.chips = chips;
        this.hand = new ArrayList<>();
    }
    public void receiveCard(Card card) {
        hand.add(card);
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
}
