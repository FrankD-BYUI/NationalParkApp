package team2.cs246.nationalparkapp.model;

import java.util.List;

/**
 * The repository is the one stop interaction with the model.
 * All requests for data are sent to the repository which then
 * retrieves the data from either local storage or the web API.
 */
public class ParkRepository {
    public static Park loadParkByParkCode(String parkCode) {
        return API.getParkByParkCode(parkCode);
    }

    public static List<Park> loadParksByQuery(String query) {
        return API.getParksByQuery(query);
    }

    public static List<Park> loadParksByName(String name) {
        return API.getParksByName(name);
    }

    public static List<Park> loadParksByState(String state) {
        return API.getParksByState(state);
    }

    //TODO: Implement favorites
    public static List<Park> loadFavorites() {
        return null;
    }

    //TODO: Implement favorites
    public static boolean saveFavorites(List<Park> parkList) {
        return false;
    }
}
