package team2.cs246.nationalparkapp;

public class Park {
    private String name;
    private String fullName;
    private String parkCode;
    private String description;
    private String latLong;

    @Override
    public String toString() {
        return "Park{" +
                "name='" + name + '\'' +
                ", fullName='" + fullName + '\'' +
                ", parkCode='" + parkCode + '\'' +
                ", description='" + description + '\'' +
                ", latLong='" + latLong + '\'' +
                '}';
    }
}
