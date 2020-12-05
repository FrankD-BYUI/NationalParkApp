package team2.cs246.nationalparkapp.model;

import java.util.List;

public class Park {
    private String name;
    private String fullName;
    private String parkCode;
    private String description;
    private String latLong;
    private List<ParkImage> images;
    private ParkContacts contacts;
    private List<ParkAddress> addresses;

    private Boolean favorite = false; //indicates if park has been set as a favorite
    private Boolean visited = false; //indicates if park has been marked as visited

    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
    }

    public void setVisited(Boolean visited) {
        this.visited = visited;
    }

    public List<ParkImage> getImages() {
        return images;
    }

    public Boolean getFavorite() {
        return favorite;
    }

    public Boolean getVisited() {
        return visited;
    }

    @Override
    public String toString() {
        return "Park{" +
                "name='" + name + '\'' +
                ", fullName='" + fullName + '\'' +
                ", parkCode='" + parkCode + '\'' +
                ", description='" + description.substring(0, Math.min(description.length(), 25)) +
                    "..." + '\'' +
                ", latLong='" + latLong + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public String getFullName() {
        return fullName;
    }

    public String getParkCode() {
        return parkCode;
    }

    public String getDescription() {
        return description;
    }

    public String getLatLong() {
        return latLong;
    }

    public ParkContacts getContacts() {
        return contacts;
    }

    public List<ParkAddress> getAddresses() {
        return addresses;
    }
}
