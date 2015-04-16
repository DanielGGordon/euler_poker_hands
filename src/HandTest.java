import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class HandTest {

    //sample hands
    String hand1 = "AS TS 3H 4S 2H";
    Hand highHand = new Hand("7D 2S 5D 3S AC");
    Hand onePairHand = new Hand("2H 2D 4S 9C 7H");
    Hand twoPairHand = new Hand("7D 7S 5D 5S AC");
    Hand tripsHand = new Hand("2S AH 7D 7S 7C");
    Hand fullHouseHand = new Hand("2S 2H 3H 3D 3C");
    Hand straightHand = new Hand("2D 3S 6D 4S 5C");
    Hand flushHand = new Hand("3S 5S 9S 4S KS");
    @Before
    public void setUp() {

    }

    @Test
    public void testSetCards() throws Exception {
        System.out.println(highHand);
        assertEquals(highHand.toString(), highHand);

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
        assertEquals(true, onePairHand.hasPair());
    }

    @Test
    public void testHasPairIndexed() throws Exception {
        assertEquals(1, onePairHand.hasPairIndexed()); //returns index of 2nd pair element
    }

    @Test
    public void testHasTwoPair() throws Exception {
        assertEquals(true, twoPairHand.hasTwoPair());
        assertEquals(false, tripsHand.hasTwoPair());
    }

    @Test
    public void testHasTwoPairIndexed() throws Exception {
        int index = twoPairHand.hasPairIndexed(); //will return index of first pair
        int resultIndex = twoPairHand.hasTwoPair(index);
        assertEquals(resultIndex,3); //last pair index at [3]
        assertEquals(index,1);
    }

    @Test
    public void testHasTrips() throws Exception {
        assertEquals(twoPairHand.hasTrips(), true);
        assertEquals(twoPairHand.hasTrips(), false);
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