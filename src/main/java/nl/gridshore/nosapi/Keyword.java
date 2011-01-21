package nl.gridshore.nosapi;

/**
 * @author Jettro Coenradie
 */
public class Keyword {
    private String tag;
    private int count;

    public Keyword(String tag, int count) {
        this.tag = tag;
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public String getTag() {
        return tag;
    }

}
