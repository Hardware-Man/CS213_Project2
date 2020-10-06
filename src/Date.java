/**
 * This class is for maintaining the Date values and implements the Java Interface Comparable.
 *
 * @author Kaivalya Mishra, Ridwanur Sarder
 */
public class Date implements Comparable<Date>{
    private final int year;
    private final int month;
    private final int day;

    public Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int compareTo(Date date) {
        if(year > date.year) {
            return 1;
        }
        if(year < date.year) {
            return -1;
        }
        if(month > date.month) {
            return 1;
        }
        if(month < date.month) {
            return -1;
        }
        if(day > date.month) {
            return 1;
        }
        if(day < date.day) {
            return -1;
        }
        return 0;
    }

    public String toString() {
        return day + "/" + month + "/" + year;
    }

    public boolean isValid() {
        if(month < 1 || month > 12) {
            return false;
        }
        else if(month == 2) {
            if (year%4 == 0 && (year%100 != 0 || year%400 == 0)) {
                return day <= 29;
            }
            else {
                return day <= 28;
            }
        }
        else if((month == 1 ||month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)) {
            return day <= 31;
        }
        else {
            return day <= 30;
        }
    }
}
