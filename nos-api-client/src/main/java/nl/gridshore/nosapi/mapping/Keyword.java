package nl.gridshore.nosapi.mapping;

/**
 * @author Jettro Coenradie
 */
public class Keyword {
    private String tag;
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
