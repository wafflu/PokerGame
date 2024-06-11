import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

public class HandRankingTest {

    @Test
    @DisplayName("카드 랭킹")
    public void handranking(){
        CardDeck cardDeck = new CardDeck();
        cardDeck.Shuffle();

        ArrayList<Card> player = new ArrayList<>();
        Random random = new Random();

        for(int i = 0; i<5; i++){
            int r = random.nextInt(cardDeck.CardDeck().size());
            player.add(cardDeck.getCard(i));
        }

        Comparator<Card> suitThenValue = new SuitSort().thenComparing(new CardValueSort());
        //카드 무늬로 정렬후 카드번호로 정렬
        Collections.sort(player, suitThenValue);

        Iterator it = player.iterator();

        while (it.hasNext()){
            Card card = (Card) it.next();
            System.out.println(card.getSuit()+" | "+card.getNumber());
        }
    }

}

class SuitSort implements Comparator<Card> {
    @Override
    public int compare(Card card1, Card card2) {
        return Integer.compare(card1.getSuitRank(), card2.getSuitRank());
    }
}

class CardValueSort implements Comparator<Card> {
    @Override
    public int compare(Card card1, Card card2) {
        return Integer.compare(card2.getCardValue(), card1.getCardValue());
    }
}
