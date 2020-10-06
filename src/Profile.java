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

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Profile) {
            Profile aProfile = (Profile) obj;
            return fName.equals(aProfile.fName) && lName.equals(aProfile.lName);
        }
        return false;
    }

    public String getLastNameFirstName() {
        return lName+fName;
    }
}
