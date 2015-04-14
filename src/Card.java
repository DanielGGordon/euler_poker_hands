/**
 * Created by gordon on 1/28/2015.
 */
public class Card implements Comparable {
    

    private char value; //J = 11
    private char suit; //'D' = Diamond, 'C' = club


    public Card (char[] card) {
        this.value = card[0];
        this.suit = card[1];
    }

    public char getValue() {
        return value;
    }

    //This method is used to convert a Card 'value' into an int.
    //Converts char to int.
    //How we value the 'Ace'
    public int getIntValue() {
        if (value > 64) {
            if (value == 84) { return 10; }
            if (value == 74) { return  11; } //Jack - 'J'
            if (value == 81) { return  12; } //Queen - 'Q'
            if (value == 75) { return  13; } //King - 'K'
            if (value == 65) { return  14; } //Ace - 'A'
        }
        return value - 48;
    }
    public void setValue(char value) {
        this.value = value;
    }
    public char getSuit() {
        return suit;
    }
    public void setSuit(char suit) {
        this.suit = suit;
    }

    @Override
    public int compareTo(Object o) {
        Card another_card = (Card) o; //compare TO this card
        /**
         * if another_card < this.card, then return 1
         * if equal, return 0
         * if another_card > this.card, then return -1
         */
        int other_value = another_card.getIntValue();
        int this_value = this.getIntValue();
        if (other_value == this_value) {
            return 0;
        }
        return (other_value < this_value) ?  1: -1;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(this.value);
        str.append(this.suit);
        return str.toString();
    }
}


