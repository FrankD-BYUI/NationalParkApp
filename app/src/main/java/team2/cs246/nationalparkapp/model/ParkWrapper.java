package team2.cs246.nationalparkapp.model;

import java.util.List;

/**
 * This class represents the data that is returned
 */
public class ParkWrapper {
    private int total;
    private int limit;
    private int start;
    private List<Park> data;

    public List<Park> getParks() {
        return data;
    }

    public int getTotal() {
        return total;
    }

    public int getLimit() {
        return limit;
    }

    public int getStart() {
        return start;
    }
}
