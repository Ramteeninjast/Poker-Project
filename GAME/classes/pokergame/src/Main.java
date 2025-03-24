import java.util.*;

public class Main {
    public static void main(String[] args) {
        while (true) {  // Start game loop
            Deck deck = new Deck();
            Player player1 = new Player("Ramteen", 1000);
            Player player2 = new Player("Alice", 1000);
            List<Player> players = Arrays.asList(player1, player2);
            Betting betting = new Betting(players);

            deck.shuffle();

            // ✅ Deal two hole cards to each player
            player1.receiveCard(deck.dealCards().get(0));
            player1.receiveCard(deck.dealCards().get(1));
            player2.receiveCard(deck.dealCards().get(0));
            player2.receiveCard(deck.dealCards().get(1));

            System.out.println("\nThe cards in Player1's hand are: " + player1.getHand());
            System.out.println("The cards in Player2's hand are: " + player2.getHand());

            // ✅ Preflop betting
            betting.startround();

            if (player1.isActive() && player2.isActive()) {
                List<Card> flop = deck.community_cards();
                System.out.println("\nCommunity cards on the flop: " + flop);
                betting.startround(); // ✅ Postflop betting

                if (player1.isActive() && player2.isActive()) {
                    Card turn = deck.turncard();
                    System.out.println("\nThe Turn: " + turn);
                    System.out.println("Community cards now: " + deck.community_cards());
                    betting.startround(); // ✅ Turn betting

                    if (player1.isActive() && player2.isActive()) {
                        Card river = deck.rivercard();
                        System.out.println("\nThe River: " + river);
                        System.out.println("Community cards now: " + deck.community_cards());

                        betting.startround(); // ✅ River betting

                        // ✅ Evaluate hands and determine the winner
                        determineWinner(player1, player2, deck, betting);
                        break;  // 🚨 Exit the loop so the game does NOT restart
                    }
                }
            }
        }
    }

    public static void determineWinner(Player player1, Player player2, Deck deck, Betting betting) {
        System.out.println("\n-- FINAL SHOWDOWN --");
        System.out.println(player1.getName() + "'s hand: " + player1.getHand());
        System.out.println(player2.getName() + "'s hand: " + player2.getHand());
        System.out.println("Community Cards: " + deck.community_cards());

        // ✅ Compute hand rankings
        int player1Rank = Evaluation.evaluateHand(player1.getHand(), deck.community_cards());
        int player2Rank = Evaluation.evaluateHand(player2.getHand(), deck.community_cards());

        if (player1Rank > player2Rank) {
            System.out.println(player1.getName() + " wins the pot of " + betting.getPot() + " chips!");
            player1.addChips(betting.getPot());
        } else if (player1Rank < player2Rank) {
            System.out.println(player2.getName() + " wins the pot of " + betting.getPot() + " chips!");
            player2.addChips(betting.getPot());
        } else {
            System.out.println("It's a tie! Pot is split.");
            int splitAmount = betting.getPot() / 2;
            player1.addChips(splitAmount);
            player2.addChips(splitAmount);
        }

        // ✅ Reset the pot for the next round
        betting.resetPot();
    }
}
