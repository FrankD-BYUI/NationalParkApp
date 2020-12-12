package team2.cs246.nationalparkapp.model;

/**
 * This class primarily exists for serializing phone data from the JSON
 * string returned by the NPS API.
 */
public class ParkPhone {
    public String phoneNumber;
    public String type;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getType() {
        return type;
    }
}
