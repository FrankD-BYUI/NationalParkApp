package team2.cs246.nationalparkapp.model;

/**
 * This class primarily exists for serializing email data from the JSON
 * string returned by the NPS API.
 */
public class ParkEmail {
    private String emailAddress;

    public String getEmailAddress() {
        return emailAddress;
    }
}
