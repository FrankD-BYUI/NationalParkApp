package team2.cs246.nationalparkapp.model;

import java.util.List;

/**
 * This class primarily exists for serializing contact data from the JSON
 * string returned by the NPS API.
 */
public class ParkContacts {
    private List<ParkPhone> phoneNumbers;
    private List<ParkEmail> emailAddresses;

    public List<ParkPhone> getPhoneNumbers() {
        return phoneNumbers;
    }

    public List<ParkEmail> getEmailAddresses() {
        return emailAddresses;
    }
}
