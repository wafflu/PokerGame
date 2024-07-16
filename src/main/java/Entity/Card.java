package Entity;

public class Card {
    private final CardNumber number;
    private final CardSuit suit;
    public boolean show;

    public Card(CardNumber number, CardSuit suit, boolean show) {
        this.number = number;
        this.suit = suit;
        this.show = show;
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

    public void ChangeShow(){
        if(show){
            this.show = false;
        } else {
            this.show = true;
        }
    }

    public boolean getShow(){
        return show;
    }

    @Override
    public String toString(){
        return suit.getValue()+"["+number.getValue()+"]";
    }
}
