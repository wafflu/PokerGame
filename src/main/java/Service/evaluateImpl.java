package Service;

import Entity.Card;
import Entity.CardSuit;
import Entity.HandRanking;
import Entity.Player;

import java.util.*;

public class evaluateImpl implements evaluate{
    private evaluateImpl (){}

    public static evaluateImpl createEvaluate(){
        return new evaluateImpl();
    }

    @Override
    public Player evaluateHand(Player player) {
        int max = 0;
        int suit = 0;
        int highnum = 0;
        List<Card> cards = player.getCarddeck();
        //5장의 카드조합
        List<List<Card>> combinations = new ArrayList<>();
        generateCombinations(cards, 5, 0, new ArrayList<>(), combinations);

        for(List<Card> combination : combinations){
            Map<Integer, Integer> cardNumCounts = new HashMap<>();
            Map<Integer, Integer> cardSuitCounts = new HashMap<>();
            for(Card card : combination){
                int cardvalue = card.getCardValue();
                int suitvalue = card.getSuitRank();
                cardNumCounts.put(cardvalue, cardNumCounts.getOrDefault(cardvalue, 0) + 1);
                cardSuitCounts.put(suitvalue, cardSuitCounts.getOrDefault(suitvalue, 0) + 1);
            }
            HandRanking hr = handrank(combination, cardNumCounts, cardSuitCounts);
            if(max == 0 || max > hr.getValue()){
                max = hr.getValue();
                player.setSuit(findSuit(cardSuitCounts));
                player.setNumber(findNumber(cardNumCounts, highnum));
                player.setResultDeck(combination);
                player.setHandRanking(hr);
            }
        }
        return player;
    }

    private void generateCombinations(List<Card> cards, int r, int start, List<Card> currentCombination, List<List<Card>> combinations){
        if (currentCombination.size() == r) {
            //5개가 되었을떄 들어감
            combinations.add(new ArrayList<>(currentCombination));
            return;
        }
        for (int i = start; i < cards.size(); i++) {
            currentCombination.add(cards.get(i));
            generateCombinations(cards, r, i + 1, currentCombination, combinations);
            currentCombination.remove(currentCombination.size() - 1); // 마지막 요소 제거
        }
    }

    @Override
    public HandRanking handrank(List<Card> combination, Map<Integer, Integer> cardNums, Map<Integer, Integer> cardSuits) {
        boolean suitFive = cardSuits.values().contains(5);
        if(suitFive && isRoyalFlush(cardNums)){
            return HandRanking.ROYAL_FLUSH;
        }
        if(suitFive && isStraightFlush(cardNums)){
            return HandRanking.STRAIGHT_FLUSH;
        }

        if(isFourOfKind(cardNums)){
            return HandRanking.FOUR_OF_A_KIND;
        }

        if(isFullHouse(cardNums)){
            return HandRanking.FULL_HOUSE;
        }

        if(cardSuits.containsValue(5)){
            return HandRanking.FLUSH;
        }

        if(isStraight(combination)){
            return HandRanking.STRAIGHT;
        }

        if (isThreeOfKind(cardNums)) {
            return HandRanking.THREE_OF_A_KIND;
        }

        if (isTwoPair(cardNums)) {
            return HandRanking.TWO_PAIR;
        }

        if (isOnePair(cardNums)) {
            return HandRanking.ONE_PAIR;
        }

        return HandRanking.HIGH_CARD;
    }

    private boolean isRoyalFlush(Map<Integer, Integer> cardNum) {
        //A, K, Q, J, 10으로 이루어진 동일한 무늬의 카드 조합.
        if (cardNum.keySet().containsAll(Arrays.asList(10, 11, 12, 13, 14))) {
            return true;
        }
        return false;
    }

    private boolean isStraightFlush(Map<Integer, Integer> cardNumCounts) {
        //연속된 숫자로 이루어진 동일한 무늬의 카드 조합.
        List<Integer> keyList = new ArrayList<>(cardNumCounts.keySet());
//        System.out.println(cardNumCounts.keySet());
        for(int a = 0; a<keyList.size()-1; a++){
            if((keyList.get(a) == 14 ? 1 : keyList.get(a)) != keyList.get(a+1)){
                return false;
            }
        }
        return true;
    }

    private boolean isFourOfKind(Map<Integer, Integer> cardCounts){
        return cardCounts.containsValue(4);
    }

    private boolean isFullHouse(Map<Integer, Integer> cardCounts){
        return cardCounts.containsValue(3) && cardCounts.containsValue(2);
    }

    private boolean isStraight(List<Card> combination) {
        //연속된 숫자로 이루어진 다른 무늬의 카드 조합.
        int ac = 0;
        int res = 0;
//        System.out.println(combination);
        for(int a = 0; a<combination.size()-1; a++){
            ac = combination.get(a).getCardValue() == 14 ? 1 : combination.get(a+1).getCardValue();
            res = ac - combination.get(a).getCardValue() < 0 ? (ac - combination.get(a).getCardValue()) * -1 : ac - combination.get(a).getCardValue();
//            System.out.println(ac+" - "+combination.get(a).getCardValue()+" = "+res+"/"+(ac-combination.get(a).getCardValue()));
            if(res != 1){
                return false;
            }
        }
//        System.out.println();
//        System.out.println(combination);
        return true;
    }

    private boolean isThreeOfKind(Map<Integer, Integer> cardCounts) {
        return cardCounts.containsValue(3);
    }

    private boolean isTwoPair(Map<Integer, Integer> cardCounts) {
        int twopair = 0;
        for(Integer cardNum : cardCounts.values()){
            if(cardNum == 2){
                twopair++;
            }
        }
        return twopair>= 2;
    }

    private boolean isOnePair(Map<Integer, Integer> cardCounts) {
        return cardCounts.containsValue(2);
    }

    private int findSuit(Map<Integer, Integer> cardSuitCounts) {
        int suitmin = -1;
        for(int suit : cardSuitCounts.keySet()) {
            if (suitmin == -1 || suitmin > suit) {
                suitmin = suit;
            }
        }
        return suitmin;
    }

    private int findNumber(Map<Integer, Integer> cardCounts, int highnum) {
        for (Map.Entry<Integer, Integer> entry : cardCounts.entrySet()) {
            if (highnum < entry.getValue() && entry.getValue() >= 2) {
//                System.out.println(highnum+"/"+entry.getKey()+" : "+entry.getValue());
                highnum = entry.getKey();
            }
        }
        return highnum;
    }

    public void result(Player player, Player player2){
        System.out.println(player.getName() + "의 카드 : " + player.getCarddeck());
        System.out.println(player.getHandRanking() + " : " + player.getResultDeck());
        System.out.println("--------------------------------");
        System.out.println(player2.getName() + "의 카드 : " + player2.getCarddeck());
        System.out.println(player2.getHandRanking() + " : " + player2.getResultDeck());
        System.out.println();
        System.out.println(player.getNumber() +"/"+ player2.getNumber());
        System.out.println(player.getSuit()+" vs "+player2.getSuit());

        if (player.getHandRanking() == player2.getHandRanking() && player.getNumber() == player2.getNumber()) {
            System.out.println("비교용");
            if (player.getSuit() < player2.getSuit()) {
                System.out.println(player.getName() + " WIN");
            } else if (player.getSuit() > player2.getSuit()) {
                System.out.println(player2.getName() + " WIN");
            } else {
                System.out.println("무승부");
            }
        } else if(player.getHandRanking() == player2.getHandRanking()) {
            if(player.getNumber() > player2.getNumber()){
                System.out.println(player.getName() + " WIN");
            } else if(player.getNumber() < player2.getNumber()){
                System.out.println(player2.getName() + " WIN");
            } else {
                System.out.println("무승부");
            }
        } else {
            if (player.getHandRanking().getValue() < player2.getHandRanking().getValue()) {
                System.out.println(player.getName() + " WIN");
            } else if (player.getHandRanking().getValue() > player2.getHandRanking().getValue()) {
                System.out.println(player2.getName() + " WIN");
            } else {
                System.out.println("무승부");
            }
        }
    }
}