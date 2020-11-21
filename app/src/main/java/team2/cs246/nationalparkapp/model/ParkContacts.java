package team2.cs246.nationalparkapp.model;

import java.util.List;

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
