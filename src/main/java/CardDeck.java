import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class CardDeck {
    private final ArrayList<Card> carddeck;


    //카드덱 생성
    public CardDeck(){
        carddeck = new ArrayList<>();
        for (CardNumber number : CardNumber.values()) {
            for (CardSuit suit : CardSuit.values()) {
                carddeck.add(new Card(number, suit));
            }
        }
    }

    public Card getCard(int index){
        return carddeck.get(index);
    }
    public String getCardnumber(int index){
        return carddeck.get(index).getNumber();
    }

    public String getCardsuit(int index){
        return carddeck.get(index).getSuit();
    }

    public ArrayList<Card> CardDeck(){
        return carddeck;
    }

    public void Shuffle(){
        Collections.shuffle(carddeck);
    }
}
