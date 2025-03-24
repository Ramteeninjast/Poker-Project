public class Pot {
    private int total;
    public Pot(){
        this.total = 0;
    }
    public void addToPot(int amount){
        total += amount;
    }
    public int gettotal(){
        return total;
    }
    public void awardPot(Player winner) {
        winner.addChips(total);
        System.out.println(winner.getName() + " wins the pot of " + total + " chips!");
        total = 0;  // Reset the pot for the next round
    }

}
