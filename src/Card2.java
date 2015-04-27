/**
 * Created by gordon on 4/21/2015.
 */
public enum Card2 {

    SPADE('S'),
    CLUB('C'),
    HEART('H'),
    DIAMOND('D');

    private final char suit;

    private Card2(char suit) {
        this.suit = suit;
    }

    public int getValue() {
        return this.suit;
    }
}
