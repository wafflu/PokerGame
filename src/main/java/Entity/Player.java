package Entity;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<Card> Carddeck = new ArrayList<>();
    private String name;
    private Long money;
    private List<Card> resultDeck = new ArrayList<>();
    private HandRanking handRanking;
    private int suit = 0;
    private int number = 0;
    private Player(){}

    public Player(String name){
        this.name = name;
    }

    public List<Card> getCarddeck() {
        return Carddeck;
    }

    public void add(Card card) {
        Carddeck.add(card);
    }

    public void sortDeck(List<Card> deck){
        this.Carddeck = deck;
    }
    public String getName() {
        return name;
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    public List<Card> getResultDeck() {
        return resultDeck;
    }

    public void setResultDeck(List<Card> resultDeck) {
        this.resultDeck = resultDeck;
    }

    public HandRanking getHandRanking() {
        return handRanking;
    }

    public void setHandRanking(HandRanking handRanking) {
        this.handRanking = handRanking;
    }

    public int getSuit() {
        return suit;
    }

    public void setSuit(int suit) {
        this.suit = suit;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
