package bearcation.model;

import bearcation.model.Location;
import bearcation.model.entities.Location;
import bearcation.model.entities.Review;
import bearcation.model.entities.User;
import bearcation.repository.LocationRepository;
import bearcation.service.LocationService;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mockito;
import org.mockito.Mockito.*;
import org.junit.Test;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class locationTest {
    Location location = new Location();
    LocationService server = new LocationService();
    LocationRepository repository = mock(LocationRepository.class);

    @Before
    public void setLocation(){
        location.setId((long)123);
        location.setName("park");
        location.setDescription("very good");
        location.setLongitude(99.8);
        location.setLatitude(88.9);
        location.setPrice(100.0);
    }

    @Test
    public void testSearchById(){
        server.setLocationRepository(repository);
        when(repository.findById((long)123)).thenReturn(Optional.ofNullable((location)));
        assertEquals(Optional.ofNullable((location)), repository.findById(123));
        assertEquals(location, server.getLocationById(123));
    }

    @Test
    @DisplayName("Test average rating")
    public void testAvgRating() {

        User u = new User();
        Review r1 = new Review(5.0, "place 1", u, location);
        Review r2 = new Review(4.0, "place 2", u, location);
        Review r3 = new Review(3.0, "place 3", u, location);
        Review r4 = new Review(2.0, "place 4", u, location);
        Review r5 = new Review(1.0, "place 5", u, location);

        assertEquals(location.findAvgRating(), 3.0, 0.01);
    }

    @Test
    @DisplayName("Test Default avg rating")
    public void testDefaultAvgRating() {

        assertEquals(location.findAvgRating(), 2.5, 0.01);
    }

    @Test
    @DisplayName("Test Price points")
    public void testPricePoints() {
        location.setPrice(0.0);

        assertEquals(location.givePricePoints(0.0), 50);

        location.setPrice(100.0);

        assertEquals(location.givePricePoints(0.0), 0);
        assertEquals(location.givePricePoints(40.0), 5);
        assertEquals(location.givePricePoints(60.0), 25);
        assertEquals(location.givePricePoints(200.0), 50);
    }

    @Test
    @DisplayName("Test Raw Activity")
    public void testRawActivityScore() {
        Set<String> s = new HashSet<String>();
        s.add("1");
        s.add("2");
        s.add("3");
        s.add("4");
        s.add("5");
        s.add("6");
        location.setActivities(s);

        assertEquals(12, location.giveRawActivityPoints());
    }
}
