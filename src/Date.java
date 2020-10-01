/**
 * This class implements the Java Interface Comparable for Date.
 *
 * @author Kaivalya Mishra, Ridwanur Sarder
 */
public class Date implements Comparable<Date>{
    private int year;
    private int month;
    private int day;

    public int compareTo(Date date) {
        return 0;
    }
    public String toString() {
        return "";
    }
    public boolean isValid() {
        return true;
    }
}
