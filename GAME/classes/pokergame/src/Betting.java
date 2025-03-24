import java.util.List;
import java.util.Scanner;

public class Betting {
    private List<Player> players;
    private int pot;
    private int highestbet;
    private boolean bettingFinished; // 🔹 Flag to track when betting is done
    private Scanner scanner;

    public Betting(List<Player> players) {
        this.players = players;
        this.pot = 0;
        this.highestbet = 0;
        this.bettingFinished = false;
        this.scanner = new Scanner(System.in);
    }

    public void startround() {
        bettingFinished = false; // Reset the flag at the start of each betting round

        while (!bettingFinished) { // 🔹 Loop until the round is finished
            bettingFinished = true; // Assume betting will be finished, unless a raise occurs

            for (Player player : players) {
                if (!player.isActive()) continue; // Skip folded players

                System.out.println("\n" + player.getName() + "'s turn to play");
                System.out.println("Current pot: " + pot);
                System.out.println("Your current chips: " + player.getChips());
                System.out.println("Highest bet so far: " + highestbet);
                System.out.println("Choose your action:" +
                        "\n1) Bet" + "\n2) Call" + "\n3) Raise" + "\n4) Check" + "\n5) Fold");

                int choice = scanner.nextInt();
                switch (choice) {
                    case 1 -> Bet(player);
                    case 2 -> Call(player);
                    case 3 -> {
                        Raise(player);
                        bettingFinished = false; // 🔹 Betting is NOT finished if someone raises
                    }
                    case 4 -> Check(player);
                    case 5 -> Fold(player);
                    default -> System.out.println("Invalid choice");
                }
            }

            // 🔹 Check if all players are either folded or have called the highest bet
            if (allPlayersHaveCalledOrFolded()) {
                bettingFinished = true;
            }
        }
    }

    private boolean allPlayersHaveCalledOrFolded() {
        for (Player player : players) {
            if (player.isActive() && player.getCurrentBet() < highestbet) {
                return false; // A player still needs to match the bet
            }
        }
        return true; // Betting round is done
    }

    private void Check(Player player) {
        if (highestbet == 0) {
            System.out.println(player.getName() + " checks.");
        } else {
            System.out.println("You cannot check, you must call, raise, or fold.");
        }
    }

    private void Bet(Player player) {
        System.out.println("Enter your bet amount:");
        int betAmount = scanner.nextInt();

        if (betAmount <= highestbet) {
            System.out.println("Your bet must be higher than the current highest bet.");
            return;
        }
        if (player.placeBet(betAmount)) {
            highestbet = betAmount;
            pot += betAmount;
            System.out.println(player.getName() + " bets " + betAmount);
        }
    }

    private void Call(Player player) {
        int amountToCall = highestbet - player.getCurrentBet(); // Get the difference
        if (amountToCall <= 0) {
            System.out.println(player.getName() + " has already matched the bet.");
            return;
        }
        if (player.placeBet(amountToCall)) {
            pot += amountToCall;
            System.out.println(player.getName() + " calls " + amountToCall);
        }
    }

    private void Raise(Player player) {
        System.out.println("Enter your raise amount:");
        int raiseAmount = scanner.nextInt();

        if (raiseAmount <= highestbet) {
            System.out.println("Your raise must be higher than the current highest bet.");
            return;
        }
        int amountToRaise = raiseAmount - player.getCurrentBet();
        if (player.placeBet(amountToRaise)) {
            highestbet = raiseAmount;
            pot += amountToRaise;
            System.out.println(player.getName() + " raises to " + raiseAmount);
        }
    }

    private void Fold(Player player) {
        player.fold();
        System.out.println(player.getName() + " folds.");
    }

    public int getPot() {
        return pot;
    }

    public void resetPot() {
        pot = 0;
        highestbet = 0;
    }
}
