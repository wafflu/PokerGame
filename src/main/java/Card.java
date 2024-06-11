public class Card {
    private final CardNumber number;
    private final CardSuit suit;

    public Card(CardNumber number, CardSuit suit) {
        this.number = number;
        this.suit = suit;
    }

    public String getNumber() {
        return number.getValue();
    }
    public int getCardValue() {
        return number.getCardvalue();
    }

    public String getSuit() {
        return suit.getValue();
    }
    public int getSuitRank() {
        return suit.getRank();
    }

    @Override
    public String toString(){
        return suit.getValue()+"["+number.getValue()+"]";
    }
}
