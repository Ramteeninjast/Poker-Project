import java.util.List;
import java.util.Scanner;

public class Betting {
    private List<Player> players;
    private int pot;
    private int highestbet;
    private Scanner scanner;

    public Betting(List<Player> players) {
        this.players = players;
        this.pot = 0;
        this.highestbet = 0;
        this.scanner = new Scanner(System.in);
    }
    public void startround(){
        for (Player player : players){
            if(!player.isActive()) continue;
            System.out.println( "\n" + player.getName() + "'s turn to play");
            System.out.println("current pot is" + pot);
            System.out.println("your current chips are :" + player.getChips());

            System.out.println("choose your action:" + "\n" + "1) Bet" + "\n" + "2)Call" + "\n" + "3)Raise" + "\n" + "4)Check" + "\n" + "5)Fold");
            int choice = scanner.nextInt();
            switch(choice){
                case 1 -> Bet(player);
                case 2 -> Call(player);
                case 3 -> Raise(player);
                case 4 -> Check(player);
                case 5 -> Fold(player);
                default -> System.out.println("Invalid choice");
            }

        }

    }
    private void Check(Player player){
        if (highestbet == 0) {
            System.out.println(player.getName() +"checks");
        } else {
            System.out.println("you cannot check, you must raise or fold");
        }
    }
    private void Bet(Player player){
        System.out.println("Enter your bet amount:");
        int betAmount = scanner.nextInt();
        if (betAmount <= highestbet) {
            System.out.println("Your bet must be higher than the current highest bet.");
            return;
        }
        if (player.placeBet(betAmount)){
            highestbet = betAmount;
            pot += betAmount;
            System.out.println(player.getName() + " bets " + betAmount);
        }
    }
    private void Call(Player player){
        int AmountToCall = highestbet - player.getCurrentBet();
        if (AmountToCall <= 0){
            System.out.println("player has nothing to call");
            return;
        }
        if(player.placeBet(AmountToCall)) {
            pot += AmountToCall;
            System.out.println(player.getName() + " calls " + AmountToCall);
        }
    }

    private void Raise(Player player){
        System.out.println("Enter your bet amount:");
        int raiseAmount = scanner.nextInt();
        if (raiseAmount <= highestbet) {
            System.out.println("Your raise must be higher than the current highest bet.");
            return;
        }
        if (player.placeBet(raiseAmount)){
            highestbet = raiseAmount;
            pot += raiseAmount;
            System.out.println(player.getName() + " raises to " + raiseAmount);
        }
    }
    private void Fold(Player player){
        player.fold();
        System.out.println(player.getName() + " folds.");
    }

}
