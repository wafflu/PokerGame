import Entity.HandRanking;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PokerHandTest {

    @Test
    public void handrank(){
        List<Integer> cards = Arrays.asList(14, 14, 14, 6, 6, 9);
        HandRanking hand = evaluateHand(cards);
        System.out.println("The poker hand is: " + hand);
    }

    public static HandRanking evaluateHand(List<Integer> cards) {
        Map<Integer, Integer> cardCounts = new HashMap<>();
        int a = 0;
        for (int card : cards) {
            cardCounts.put(card, cardCounts.getOrDefault(card, 0) + 1);
            System.out.println(cards.get(a)+" : "+cardCounts.getOrDefault(card, 0)+"/"+cardCounts.get(a));
            a++;
        }

        if (isOnePair(cardCounts)) {
            return HandRanking.ONE_PAIR;
        }
        if (isThreeOfAKind(cardCounts)) {
            return HandRanking.THREE_OF_A_KIND;
        }
        // 추가적인 핸드 판별 메서드를 순서대로 호출합니다.

        return HandRanking.HIGH_CARD; // 기본적으로 HIGH_CARD로 반환
    }

    private static boolean isOnePair(Map<Integer, Integer> cardCounts) {
        return cardCounts.containsValue(2);
    }

    // 추가적인 핸드 판별 메서드들...
    private static boolean isTwoPair(Map<Integer, Integer> cardCounts) {
        int pairCount = 0;
        for (int count : cardCounts.values()) {
            if (count == 2) pairCount++;
        }
        return pairCount == 2;
    }

    private static boolean isThreeOfAKind(Map<Integer, Integer> cardCounts) {
        return cardCounts.containsValue(3);
    }
}
