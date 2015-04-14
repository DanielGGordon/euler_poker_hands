import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class HandTest {

    //sample hands
    String hand1 = "AS TS 3H 4S 2H";
    String hand2 = "7D 2S 5D 3S AC";
    String onePairHand = "2H 2D 4S 9C 7H";
    String twoPairHand = "7D 7S 5D 5S AC";
    String tripsHand = "2S AH 7D 7S 7C";
    Hand fullHouseHand = new Hand("2S 2H 3H 3D 3C");
    //two pair
    String hand4 = "2D 3S 6D 4S 5C";
    //straight
    String hand5 = "7D 2S 5D 3S AC";
    String hand6 = "7D 2S 5D 3S AC";
    String hand7 = "7D 2S 5D 3S AC";
    @Before
    public void setUp() {

    }

    @Test
    public void testSetCards() throws Exception {
        Hand hand = new Hand(hand1);
        System.out.println(hand1);
        assertEquals(hand1.toString(), hand1);

    }

    @Test
    public void testSortHand() throws Exception {
        Hand hand = new Hand(hand1);
        Hand hand2 = new Hand("2H 3H 4S TS AS");
        assertEquals(hand.toString(), hand2.toString());
        //too lazy to override toEquals and Hashcode
    }


    @Test
    public void testHasPair() throws Exception {
        Hand hand = new Hand(onePairHand);
        assertEquals(true, hand.hasPair());
        assertEquals(1, hand.hasPairIndexed()); //returns index of 2nd pair element
    }

    @Test
    public void testHasTwoPair() throws Exception {
        Hand hand = new Hand(twoPairHand);
        Hand hand2 = new Hand(tripsHand);
        Hand hand3 = new Hand("3C 5D 7H 9S 9D");
        assertEquals(true, hand.hasTwoPair());
        assertEquals(false, hand2.hasTwoPair());
        int index = hand3.hasPairIndexed();
        int twoPairResult = hand3.hasTwoPair(index);
        assertEquals(twoPairResult,-1);
    }

    @Test
    public void testHasTripes() throws Exception {
        Hand hand = new Hand("2S 5C 5H 5D 9H");
        Hand hand1 = new Hand(twoPairHand);
        assertEquals(hand.hasTrips(), true);
        assertEquals(hand1.hasTrips(), false);
        assertEquals(fullHouseHand.hasTrips(), true);
    }
    @Test
    public void testHasStraight() throws Exception {

    }

    @Test
    public void testHasFlush() throws Exception {

    }

    @Test
    public void testRankHand() throws Exception {

    }

    @Test
    public void testBestHand() throws Exception {

    }


}