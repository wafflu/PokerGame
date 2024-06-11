public enum CardNumber {
    ONE("A", 14),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("10", 10),
    JACK("J", 11),
    QUEEN("Q", 12),
    KING("K", 13);

    private final String value;
    private final int cardvalue;

    CardNumber(String value, int cardvalue) {
        this.value = value;
        this.cardvalue = cardvalue;
    }

    public String getValue() {
        return value;
    }

    public int getCardvalue() {
        return cardvalue;
    }
}