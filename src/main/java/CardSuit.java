public enum CardSuit {
    SPADE("SPADE", 1),
    DIAMOND("DIAMOND", 2),
    HEART("HEART", 3),
    CLOVER("CLOVER", 4);

    private final String value;
    private final int rank;

    CardSuit(String value, int rank) {
        this.value = value;
        this.rank = rank;
    }

    public String getValue() {
        return value;
    }
    public int getRank() {
        return rank;
    }

}
