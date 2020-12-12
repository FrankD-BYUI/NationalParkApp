package team2.cs246.nationalparkapp.model;

import android.util.Log;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * This class represents a Park, as returned by the NPS API
 */
public class Park implements Parcelable {
    private String name;
    private String fullName;
    private String parkCode;
    private String description;
    private String latLong;
    private String designation;
    private String states;
    private List<ParkImage> images;
    private ParkContacts contacts;
    private List<ParkAddress> addresses;


    public Park(String name, String latLong) {
        this.name = name;
        this.latLong = latLong;
    }

    private Boolean favorite = false; //indicates if park has been set as a favorite
    private Boolean visited = false; //indicates if park has been marked as visited

    protected Park(Parcel in) {
        name = in.readString();
        fullName = in.readString();
        parkCode = in.readString();
        description = in.readString();
        latLong = in.readString();
        designation = in.readString();
        byte tmpFavorite = in.readByte();
        favorite = tmpFavorite == 0 ? null : tmpFavorite == 1;
        byte tmpVisited = in.readByte();
        visited = tmpVisited == 0 ? null : tmpVisited == 1;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(fullName);
        dest.writeString(parkCode);
        dest.writeString(description);
        dest.writeString(latLong);
        dest.writeString(designation);
        dest.writeByte((byte) (favorite == null ? 0 : favorite ? 1 : 2));
        dest.writeByte((byte) (visited == null ? 0 : visited ? 1 : 2));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Park> CREATOR = new Creator<Park>() {
        @Override
        public Park createFromParcel(Parcel in) {
            return new Park(in);
        }

        @Override
        public Park[] newArray(int size) {
            return new Park[size];
        }
    };

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

    /**
     * basic toString functionality for testing purposes
     * @return  a basic string representation of the Park
     */
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

    public String getDesignation() {
        return designation;
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

    /**
     * @return  the city and state of the park in the format [city], [state code]
     */
    public String getCityState() {
        List<ParkAddress> addresses = getAddresses();

        for (ParkAddress address : addresses)
        {
            if (address.getType().equals("Physical")) {
                return address.getCity() + ", " + address.getStateCode();
            }
        }
        return null;
    }

    public String getStates() {
        return states;
    }
}
