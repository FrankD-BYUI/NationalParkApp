package team2.cs246.nationalparkapp.model;

public class ParkAddress {
    private String postalCode;
    private String city;
    private String stateCode;
    private String line1;
    private String line2;
    private String line3;
    private String type;

    public String getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
    }

    public String getStateCode() {
        return stateCode;
    }

    public String getLine1() {
        return line1;
    }

    public String getLine2() {
        return line2;
    }

    public String getLine3() {
        return line3;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        String output;
        output = line1;
        if (!line2.isEmpty()){
            output += "\n" + line2;
        }
        if (!line3.isEmpty()){
            output += "\n" + line3;
        }
        output += "\n" + city + ", " + stateCode + " " + postalCode;
        return output;
    }
}
