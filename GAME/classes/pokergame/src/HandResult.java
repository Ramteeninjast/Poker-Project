public class HandResult {
    private final int rank;
    private final String handtype;

    public HandResult(int rank, String handtype) {
        this.rank = rank;
        this.handtype = handtype;
    }

    public int getRank(){
        return rank;
    }
    public String getHandType(){
        return handtype;
    }
}
