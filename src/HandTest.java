import junit.framework.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;


public class HandTest {

    //sample hands
    Hand randomHand = new Hand ("AS TS 3H 4S 2H");
    Hand highHand = new Hand("7D 2S 5D 3S AC");
    Hand onePairHand = new Hand("2H 2D 4S 9C 7H");
    Hand twoPairHand = new Hand("7D 7S 5D 5S AC");
    Hand tripsHand = new Hand("2S AH 7D 7S 7C");
    Hand fullHouseHand = new Hand("2S 2H 3H 3D 3C");
    Hand straightHand = new Hand("2D 3S 6D 4S 5C");
    Hand flushHand = new Hand("3S 5S 9S 4S KS");
    Hand quadsHand = new Hand("3S 3C 3H 4S 3D");
    Hand royalFlushHand = new Hand("TC JC KC AC QC");
    Hand straightFlushHand = new Hand("4C 7C 5C 6C 8C");
    @Before
    public void setUp() {
    }

    @Test
    public void testSortHand() throws Exception {
        assertEquals(randomHand.toString(), "2H 3H 4S TS AS"); //Why do I need the space?
        //too lazy to override toEquals and Hashcode
    }

    @Test
    public void testCountMatches() throws Exception {
        assertEquals(onePairHand.countMatches(), 1);
        assertEquals(tripsHand.countMatches(), 2);
        assertEquals(twoPairHand.countMatches(), 2);
        assertEquals(highHand.countMatches(), 0);
        assertEquals(flushHand.countMatches(), 0);
        assertEquals(fullHouseHand.countMatches(), 3);
        assertEquals(straightHand.countMatches(), 0);
        assertEquals(flushHand.countMatches(), 0);
        assertEquals(quadsHand.countMatches(), 3);
        assertEquals(straightFlushHand.countMatches(), 0);
    }

    @Test
    public void testRankHand() throws Exception {
        assertEquals(straightHand.findRank(), Rank.STRAIGHT);
        assertEquals(tripsHand.findRank(), Rank.TRIPS);
        assertEquals(twoPairHand.findRank(), Rank.TWO_PAIR);
        assertEquals(quadsHand.findRank(), Rank.FOUR_OF_A_KIND);
        assertEquals(flushHand.findRank(), Rank.FLUSH);
        assertEquals(straightHand.findRank(), Rank.STRAIGHT);
        assertEquals(highHand.findRank(), Rank.HIGH_CARD);
        assertEquals(tripsHand.findRank(), Rank.TRIPS);
        assertEquals(fullHouseHand.findRank(), Rank.FULL_HOUSE);
        assertEquals(straightFlushHand.findRank(), Rank.STRAIGHT_FLUSH);
        assertEquals(royalFlushHand.findRank(), Rank.ROYAL_FLUSH);
    }

    @Test
    public void testBestHand() throws Exception {
        assertEquals(Hand.bestHand(flushHand, tripsHand), true);
        assertEquals(Hand.bestHand(royalFlushHand, tripsHand), true);
        assertEquals(Hand.bestHand(flushHand, royalFlushHand), false);
        assertEquals(Hand.bestHand(twoPairHand, onePairHand), true);
        assertEquals(Hand.bestHand(quadsHand, onePairHand), true);
        assertEquals(Hand.bestHand(fullHouseHand, onePairHand), true);
        assertEquals(Hand.bestHand(fullHouseHand, highHand), true);
        assertEquals(Hand.bestHand(fullHouseHand, twoPairHand), true);
        assertEquals(Hand.bestHand(fullHouseHand, tripsHand), true);
        assertEquals(Hand.bestHand(fullHouseHand, straightHand), true);
        assertEquals(Hand.bestHand(fullHouseHand, flushHand), true);
        assertEquals(Hand.bestHand(fullHouseHand, quadsHand), false);
        assertEquals(Hand.bestHand(fullHouseHand, fullHouseHand), false);
        assertEquals(Hand.bestHand(fullHouseHand, straightFlushHand), false);
        assertEquals(Hand.bestHand(fullHouseHand, royalFlushHand), false);

    }


}