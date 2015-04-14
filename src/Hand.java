import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Created by gordon on 1/28/2015.
 */
public class Hand {

    private Card[] cards;
    private Rank rank;


    public Hand (String str) {
        cards = new Card[5];
        char[] cardPair;
        int i = 0;
        StringTokenizer token = new StringTokenizer(str);
        while (token.hasMoreTokens()) {
            cardPair = token.nextToken().toCharArray();
            cards[i] = new Card(cardPair);
            i++;
        }
        sortHand(); //always sort hand upon creation
    }

    public void sortHand () {
        Arrays.sort(cards);
    }

    /**
     * All ranking methods assume a sorted state
     * There are overloaded methods to include an index
     * The index is used on the following call
     * For example, if a pair is found at the 2nd index,
     * then when looking for a 2nd pair (or trips), we start
     * at that index, instead of from the beginning.
     */

    public boolean hasPair() {
        for (int i = 0; i < cards.length - 1; i++) {
            if (cards[i].getValue() == cards[i+1].getValue()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns an index in which pair is found
     * indexed methods will increase performance
     * -1 will represent 'false'
     */
    public int hasPairIndexed() {
        for (int i = 0; i < cards.length - 1; i++) {
            if (cards[i].getValue() == cards[i+1].getValue()) {
                return i + 1;
            }
        }
        return -1;
    }

    /**
     * This implementation of hasTwoPair will return true for a
     * four of a kind. Three of a kind will not return true
     * @return
     */
    public boolean hasTwoPair() {
        //assumed one pair already found (if that info helps)
        int pairCounter = 0;
        for (int i = 0; i < cards.length - 1; i++) {
            if (cards[i].getValue() == cards[i+1].getValue()) {
                pairCounter++;
                if (pairCounter == 2) {
                    return true;
                }
                i++;
            }
        }
        return false;
    }

    /**
     * This method begins on last matching index
     * if next element matches, then trips, and cannot be two pair
     * will NOT be classifying a 4-of-a-kind as a two-pair
     * @param index
     * @return
     * -1 on no two pair found
     * -index when trips found
     * index when two pair found
     */
    public int hasTwoPair(int index) {
        try {
            if (cards[index].getValue() == cards[index + 1].getValue()) {
                return -index; //this means hand is trips, full house, or 4ofaKind
            }
        }
        catch (IndexOutOfBoundsException e) {
            return -1;
        }
        for (int i = index + 1; i < cards.length - 1; i++) {
            if (cards[i].getValue() == cards[i+1].getValue()) {
                return i + 1; //return matched card
                //this means 2nd pair found
            }
        }
        return -1; //no pair found
        }

    public boolean hasTrips() {
        //assume hasPair already returned true
        for (int i = 0; i < cards.length - 1; i++) {
            if (cards[i].getValue() == cards[i+1].getValue()) {
                if (cards[i+1].getValue() == cards[i + 2].getValue()) {
                    return true;
                }
                i++;
            }
        }
        return false;
    }

    public int hasTrips(int index) {
        //assume hasPair already returned true
        int uniqueCount = 0;
        for (int i = 0; i < cards.length - 1; i++) {
            if (cards[i].getValue() != cards[i+1].getValue()) {
                uniqueCount++;
            }
            if (uniqueCount >= 2) {
                return 1;
            }
        }

        return 1;
    }

    public boolean hasStraight(Hand hand) {
        for (int i = 0; i < cards.length - 1; i++) {
            if (cards[i].getValue() + 1 != cards[i + 1].getValue()) {
                return false;
            }
        }
        return true;
    }

    public boolean hasFlush(Hand hand) {
        for (int i = 0; i < cards.length - 1; i++) {
            if (cards[i].getSuit() != cards[i + 1].getSuit()) {
                return false;
            }
        }
        return true;
    }

    public boolean hasFullHouse (Hand hand) {
        //assume that pair returned true and is sorted.
        return true;
    }

    public static void rankHand(Hand hand) {
        hand.sortHand();
        boolean haspair = hand.hasPair();
    }

    public static boolean bestHand (Hand hand1, Hand hand2) {
        //is hand1 better than hand2?
        //a push will return false for now
        //hands should be ranked first.

        return false;
    }

    public Card[] getCards() {
        return cards;
    }

    public void setCards(Card[] cards) {
        this.cards = cards;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < cards.length; i++) {
            str.append(cards[i].getValue());
            str.append(cards[i].getSuit());
            str.append(" ");
        }

        return str.toString();
    }

}
