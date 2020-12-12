package team2.cs246.nationalparkapp.model;

/**
 * This class primarily exists for serializing image data from the JSON
 * string returned by the NPS API.
 */
public class ParkImage {
    private String credit;
    private String title;
    private String altText;
    private String caption;
    private String url;

    public String getCredit() {
        return credit;
    }

    public String getTitle() {
        return title;
    }

    public String getAltText() {
        return altText;
    }

    public String getCaption() {
        return caption;
    }

    public String getUrl() {
        return url;
    }
}
