package Service;

import Entity.Card;
import Entity.HandRanking;
import Entity.Player;

import java.util.List;
import java.util.Map;

public interface evaluate {
    public Player evaluateHand(Player player);
    public HandRanking handrank(List<Card> combination, Map<Integer, Integer> cardNum, Map<Integer, Integer> cardNumCounts);
}
