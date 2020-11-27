import java.util.Comparator;

public class BSDistCompate implements Comparator<BusStop> {
    double lat;
    double lon;

    BSDistCompate(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    @Override
    public int compare(BusStop b1, BusStop b2) {
        if (b1.dist1(lat,lon) > b2.dist1(lat,lon)) {
            return 1;
        } else if (b1.dist1(lat,lon) < b2.dist1(lat,lon)) {
            return -1;
        }
        return 0;
    }
}