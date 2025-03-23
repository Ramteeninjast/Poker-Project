import java.util.*;

public class Evaluation {
    public static String evaluateHand (List<Card> playerCards , List<Card> communityCards){
        List<Card> allCards = new ArrayList<> (playerCards);
        allCards.addAll(communityCards);
        allCards.sort(Comparator.comparingInt(Evaluation :: getCardValue ));

        if (isRoyalFlush(allCards)) return "Royal Flush";
        if (isStraightFlush(allCards)) return "Straight Flush";
        if (isFourOfAKind(allCards)) return "Four Of A Kind";
        if (isFullHouse(allCards)) return "Full House";
        if (isFlush(allCards)) return "Flush";
        if (isStraight(allCards))  return "Straight";
        if (isThreeOfAKind(allCards)) return "Three Of A Kind";
        if (isTwoPair(allCards)) return "Two Pair";
        if(isPair(allCards)) return "Pair";

        return "High Card " + allCards.get(allCards.size() - 1);

    }

    public static boolean isRoyalFlush(List<Card> cards) {
        Set<Integer> cardValues = new HashSet<>();

        // Extract card values
        for (Card card : cards) {
            cardValues.add(getCardValue(card));
        }

        // Check if it's a straight flush and contains {10, J, Q, K, A}
        return isStraightFlush(cards) &&
                cardValues.containsAll(Arrays.asList(10, 11, 12, 13, 14));
    }

    public static boolean isStraightFlush(List<Card> cards){
        return isFlush(cards) && isStraight(cards);
    }
    public static boolean isFourOfAKind(List<Card> cards){
        return hasSameRank(cards, 4);
    }
    public static boolean isFullHouse(List<Card> cards){
        Map<String, Integer> rankCount = new HashMap<>();
        for (Card card : cards) {
            rankCount.put(card.getrank(), rankCount.getOrDefault(card.getrank(), 0) + 1);
        }
        boolean threeOfAKind = false;
        boolean pair = false;
        for (int count : rankCount.values()) {
            if (count == 3) threeOfAKind = true;
            if (count == 2) pair = true;
        }
        return threeOfAKind && pair;

    }
    public static boolean isFlush(List<Card> cards){
        Map<String, Integer> suitCount = new HashMap<>();
        for (Card card : cards) {
            suitCount.put(card.getsuit(), suitCount.getOrDefault(card.getsuit(), 0) + 1);
            if (suitCount.get(card.getsuit()) >= 5) return true;
        }
        return false;
    }
    public static boolean isStraight(List<Card> cards) {
        Set<Integer> uniqueValues = new HashSet<>();

        // Convert each card rank to an integer value and store in a Set to remove duplicates
        for (Card card : cards) {
            uniqueValues.add(getCardValue(card));
        }

        // Convert the Set to a List and sort it
        List<Integer> sortedValues = new ArrayList<>(uniqueValues);
        Collections.sort(sortedValues);

        // Check for a sequence of 5 consecutive numbers
        for (int i = 0; i <= sortedValues.size() - 5; i++) {
            if (sortedValues.get(i) + 1 == sortedValues.get(i + 1) &&
                    sortedValues.get(i + 1) + 1 == sortedValues.get(i + 2) &&
                    sortedValues.get(i + 2) + 1 == sortedValues.get(i + 3) &&
                    sortedValues.get(i + 3) + 1 == sortedValues.get(i + 4)) {
                return true;
            }
        }

        // Special case: Ace-low straight (A-2-3-4-5)
        return uniqueValues.contains(14) && uniqueValues.contains(2) &&
                uniqueValues.contains(3) && uniqueValues.contains(4) &&
                uniqueValues.contains(5);
    }

    private static boolean isThreeOfAKind(List<Card> cards) {
        return hasSameRank(cards, 3);
    }

    private static boolean isTwoPair(List<Card> cards) {
        int pairCount = 0;
        Map<String, Integer> rankCount = new HashMap<>();
        for (Card card : cards) {
            rankCount.put(card.getrank(), rankCount.getOrDefault(card.getrank(), 0) + 1);
        }
        for (int count : rankCount.values()) {
            if (count == 2) pairCount++;
        }
        return pairCount >= 2;
    }

    private static boolean isPair(List<Card> cards) {
        return hasSameRank(cards, 2);
    }

    private static boolean hasSameRank(List<Card> cards, int countNeeded) {
        Map<String, Integer> rankCount = new HashMap<>();
        for (Card card : cards) {
            rankCount.put(card.getrank(), rankCount.getOrDefault(card.getrank(), 0) + 1);
        }
        for (int count : rankCount.values()) {
            if (count == countNeeded) return true;
        }
        return false;
    }

    private static int getCardValue(Card card) {
        Map<String, Integer> valueMap = new HashMap<>();
        valueMap.put("2", 2);
        valueMap.put("3", 3);
        valueMap.put("4", 4);
        valueMap.put("5", 5);
        valueMap.put("6", 6);
        valueMap.put("7", 7);
        valueMap.put("8", 8);
        valueMap.put("9", 9);
        valueMap.put("10", 10);
        valueMap.put("Jack", 11);
        valueMap.put("Queen", 12);
        valueMap.put("King", 13);
        valueMap.put("Ace", 14);
        return valueMap.get(card.getrank());
    }
}
