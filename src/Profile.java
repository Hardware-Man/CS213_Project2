/**
 * This class defines the profile of an account holder.
 *
 * @author Kaivalya Mishra, Ridwanur Sarder
 */
public class Profile {
    private final String fName;
    private final String lName;

    public Profile(String fName, String lName) {
        this.fName = fName;
        this.lName = lName;
    }

    public String toString() {
        return fName + " " + lName;
    }

    public boolean equals(Profile holder) {
        return holder.fName.equals(this.fName) && holder.lName.equals(this.lName);
    }
}
