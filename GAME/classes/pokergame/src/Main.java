public class Main {
    public static void main(String[] args) {
        Deck deck = new Deck();
        Player player2 = new Player("alice",1000);
        deck.shuffle();

        for (Card card : deck.dealCards()) {
            player2.receiveCard(card);
        }

        System.out.println("The cards in your hand are: " + deck.dealCards());
        player2.showhand();
        System.out.println("The cards on the flop are: " + deck.commiunity_cards());

    }
}
