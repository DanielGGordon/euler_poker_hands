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


    public Rank findRank() {
        int numberMatches = this.countMatches();
        if (numberMatches == 0) { //straight, flush, straight flush, royal flush or high card possible
            boolean straight = this.hasStraight();
            boolean flush = this.hasFlush();
            if (straight && !flush) {
                return Rank.STRAIGHT;
            }
            if (!straight && flush) {
                return Rank.FLUSH;
            }
            if (straight && flush) { //royal or SF
                return  (this.cards[4].getIntValue() == 14) ? Rank.ROYAL_FLUSH : Rank.STRAIGHT_FLUSH;
            }
            else {
                return Rank.HIGH_CARD;
            }

        }
        if (numberMatches == 1) { //can only be a pair, nothing else
            return Rank.PAIR;
        }
        if (numberMatches == 2) { //Can be trips or two pair only
            return (this.hasTrips()) ? Rank.TRIPS : Rank.TWO_PAIR;
        }
        if (numberMatches == 3) { //must be full house or quads
            return (this.hasQuads()) ? Rank.FOUR_OF_A_KIND : Rank.FULL_HOUSE;
        }
        else {
            throw new IllegalArgumentException("countMatches() did not find correct number of matches");
        }

    }

    /**
     * All ranking methods assume a sorted state
     */


    /**
     *
     * @return the number of equal matches when comparing each card
     * to the card next to it.
     */
    public int countMatches () {
        int matchCounter = 0;
        for (int i = 0 ; i < 4; i++) {
            if (cards[i].getValue() == cards[i+1].getValue()) {
                matchCounter++;
            }
        }
        return matchCounter;
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

    public boolean hasStraight() {
        for (int i = 0; i < cards.length - 1; i++) {
            if (cards[i].getIntValue() + 1 != cards[i + 1].getIntValue()) {
                return false;
            }
        }
        return true;
    }

    public boolean hasFlush() {
        for (int i = 0; i < cards.length - 1; i++) {
            if (cards[i].getSuit() != cards[i + 1].getSuit()) {
                return false;
            }
        }
        return true;
    }

    public boolean hasQuads () {
        //assume that pair returned true and is sorted.
        for (int i = 0; i < cards.length - 3; i++) {
            if (cards[i].getValue() == cards[i + 3].getValue()) {
                return true;
            }
        }
        return false;
    }

    public static boolean bestHand (Hand hand1, Hand hand2) {
        //a push will return false for now
        hand1.setRank();
        hand2.setRank();
        return (hand1.rank.getValue() > hand2.rank.getValue());
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

    public void setRank() {
        this.rank = this.findRank();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < cards.length; i++) {
            str.append(cards[i].getValue());
            str.append(cards[i].getSuit());
            if (i < 4) {
                str.append(" ");
            }
        }
        return str.toString();
    }

}
