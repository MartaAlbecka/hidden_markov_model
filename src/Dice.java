public enum Dice {
    FAIR(0),
    LOADED(1);

    public int getIndex() {
        return index;
    }

    private int index;

    Dice(int i) {
        this.index =i;
    }
}
