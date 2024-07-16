import Entity.*;
import Service.evaluateImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

public class HandRankingTest {
    @Test
    @DisplayName("카드덱 생성")
    public void handranking() {
        for(int k = 0; k<10; k++) {
            Player player = new Player("Stive");
            Player player2 = new Player("KIM");
            evaluateImpl e = new evaluateImpl();

            CardDeck cardDeck = CardDeck();

            Random random = new Random();

            for (int i = 0; i < 7; i++) {
                int r = random.nextInt(cardDeck.CardDeck().size());
                player.add(cardDeck.getCard(r));
                cardDeck.CardDeck().remove(r);

                r = random.nextInt(cardDeck.CardDeck().size());
                player2.add(cardDeck.getCard(r));
                cardDeck.CardDeck().remove(r);
            }
            System.out.println();
            System.out.println((k+1)+" ROUND");
            player.sortDeck(sortCard(player.getCarddeck()));
            player = e.evaluateHand(player);

            player2.sortDeck(sortCard(player2.getCarddeck()));
            player2 = e.evaluateHand(player2);

            e.result(player, player2);

        }
    }

    @Test
    @DisplayName("테스트")
    public void testhandranking() {
        Player player = new Player("Stive");
        Player player2 = new Player("KIM");
        evaluateImpl e = new evaluateImpl();

//        CardDeck cardDeck = CardDeck();
//        Stive의 카드 : [HEART[K], DIAMOND[J], HEART[4], SPADE[3], DIAMOND[3], CLOVER[2], DIAMOND[2]]
//--------------------------------
//KIM의 카드 : [DIAMOND[Q], CLOVER[Q], HEART[J], CLOVER[J], HEART[10], CLOVER[8], CLOVER[3]]
        player.add(new Card(CardNumber.KING, CardSuit.HEART, false));
        player.add(new Card(CardNumber.JACK, CardSuit.DIAMOND, false));
        player.add(new Card(CardNumber.FOUR, CardSuit.HEART, false));
        player.add(new Card(CardNumber.THREE, CardSuit.SPADE, false));
        player.add(new Card(CardNumber.THREE, CardSuit.DIAMOND, false));
        player.add(new Card(CardNumber.TWO, CardSuit.CLOVER, false));
        player.add(new Card(CardNumber.TWO, CardSuit.DIAMOND, false));

        player2.add(new Card(CardNumber.QUEEN, CardSuit.DIAMOND, false));
        player2.add(new Card(CardNumber.QUEEN, CardSuit.CLOVER, false));
        player2.add(new Card(CardNumber.JACK, CardSuit.HEART, false));
        player2.add(new Card(CardNumber.JACK, CardSuit.CLOVER, false));
        player2.add(new Card(CardNumber.TEN, CardSuit.HEART, false));
        player2.add(new Card(CardNumber.EIGHT, CardSuit.CLOVER, false));
        player2.add(new Card(CardNumber.THREE, CardSuit.CLOVER, false));

        System.out.println();
        player.sortDeck(sortCard(player.getCarddeck()));
        player = e.evaluateHand(player);
        System.out.println(player.getName() + "의 카드 : " + player.getCarddeck());
        System.out.println(player.getHandRanking() + " : " + player.getResultDeck());
        System.out.println("--------------------------------");

        player2.sortDeck(sortCard(player2.getCarddeck()));
        player2 = e.evaluateHand(player2);
        System.out.println(player2.getName() + "의 카드 : " + player2.getCarddeck());
        System.out.println(player2.getHandRanking() + " : " + player2.getResultDeck());

        System.out.println(player.getNumber() +"/"+ player2.getNumber());
        System.out.println(player.getSuit()+" vs "+player2.getSuit());
        if (player.getHandRanking() == player2.getHandRanking() && player.getNumber() == player2.getNumber()) {
            if (player.getSuit() < player2.getSuit()) {
                System.out.println(player.getName() + " WIN");
            } else if (player.getSuit() > player2.getSuit()) {
                System.out.println(player2.getName() + " WIN");
            } else {
                System.out.println("무승부");
            }
        } else {
            if (player.getHandRanking().getValue() < player2.getHandRanking().getValue()) {
                System.out.println(player.getName() + " WIN");
            } else {
                System.out.println(player2.getName() + " WIN");
            }
        }
    }

    @Test
    @DisplayName("테스트 카드덱")
    public void testDeck() {
        Player player = new Player("Stive");
        evaluateImpl e = new evaluateImpl();

        CardDeck cardDeck = CardDeck();

//      SPADE[A], HEART[J], DIAMOND[10], SPADE[9], DIAMOND[8], CLOVER[7], DIAMOND[6]
        player.add(new Card(CardNumber.ONE, CardSuit.SPADE, false));
        player.add(new Card(CardNumber.JACK, CardSuit.HEART, false));
        player.add(new Card(CardNumber.TEN, CardSuit.DIAMOND, false));
        player.add(new Card(CardNumber.NINE, CardSuit.SPADE, false));
        player.add(new Card(CardNumber.EIGHT, CardSuit.DIAMOND, false));
        player.add(new Card(CardNumber.SEVEN, CardSuit.CLOVER, false));
        player.add(new Card(CardNumber.SIX, CardSuit.DIAMOND, false));

        player.sortDeck(sortCard(player.getCarddeck()));
        player = e.evaluateHand(player);
        System.out.println(player.getName() + "의 카드 : " + player.getCarddeck());
        System.out.println(player.getHandRanking() + " : " + player.getResultDeck());
    }

    public List<Card> sortCard(List<Card> player) {
        List<Card> playerList = new ArrayList<>(player);
        Comparator<Card> suitThenValue = new CardValueSort();
        //카드 무늬로 정렬후 카드번호로 정렬
        Collections.sort(playerList, suitThenValue);
        return playerList;
    }

    public CardDeck CardDeck() {
        CardDeck cardDeck = new CardDeck();
        cardDeck.Shuffle();
        return cardDeck;
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
}