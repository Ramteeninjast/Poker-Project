import java.util.*;

public class Main {
    public static void main(String[] args) {
        Deck deck = new Deck();
        Player player1 = new Player("Ramteen", 1000);
        Player player2 = new Player("alice",1000);
        deck.shuffle();

        List<Card> player1Cards = deck.dealCards();
        List<Card> player2Cards = deck.dealCards();

        player1.receiveCard(player1Cards.get(0));
        player1.receiveCard(player1Cards.get(1));
        player2.receiveCard(player2Cards.get(0));
        player2.receiveCard(player2Cards.get(1));


        System.out.println("The cards in Player1's hand are: " + player1Cards);
        System.out.println("The cards in Player2's hand are: " + player2Cards);

        List<Card> flop = deck.community_cards();
        System.out.println("\nCommunity cards on the flop: " + flop);

        // Create player list
        List<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);

        // Preflop Betting Round
        Betting preflop = new Betting(players);
        preflop.startround();

        // Flop Betting Round (Only if both players are active)
        if (player1.isActive() && player2.isActive()) {
            System.out.println("\nThe Flop:");
            System.out.println(flop);

            Betting postFlop = new Betting(players);
            postFlop.startround(); // Corrected method name
        }
    }
}
