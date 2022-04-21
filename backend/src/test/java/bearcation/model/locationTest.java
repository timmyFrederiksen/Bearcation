package bearcation.model;

import bearcation.model.Location;
import bearcation.model.entities.Location;
import bearcation.repository.LocationRepository;
import bearcation.service.LocationService;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mockito;
import org.mockito.Mockito.*;
import org.junit.Test;

import java.util.Optional;

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


}

class LocationMethodTests {
    @Test
    @DisplayName("Test Calculate Distance Method 1")
    public void testCalcDistance1() {
        // Set up
        Location loc1 = new Location();
        loc1.setId((long)456);
        loc1.setName("here");
        loc1.setDescription("there");
        loc1.setLongitude(-12.1);
        loc1.setLatitude(0.0);
        loc1.setPrice(0.0);
        Location loc2 = new Location();
        loc2.setId((long)123);
        loc2.setName("park");
        loc2.setDescription("very good");
        loc2.setLongitude(102.2222);
        loc2.setLatitude(-12.1);
        loc2.setPrice(100.0);

        // Test calculate
        double testVal = loc1.calculateDistance(loc2);
        double expectedValue = 12640;

        // Make sure we are right within 1% of the expected value
        assertEquals(testVal, expectedValue, expectedValue / 100);
    }

    @Test
    @DisplayName("Test Calculate Distance Method 2")
    public void testCalcDistance2() {
        // Set up
        Location loc1 = new Location();
        loc1.setId((long)456);
        loc1.setName("here");
        loc1.setDescription("there");
        loc1.setLongitude(0.0);
        loc1.setLatitude(0.0);
        loc1.setPrice(0.0);
        Location loc2 = new Location();
        loc2.setId((long)123);
        loc2.setName("park");
        loc2.setDescription("very good");
        loc2.setLongitude(0.0);
        loc2.setLatitude(0.0);
        loc2.setPrice(100.0);

        // Test calculate
        double testVal = loc1.calculateDistance(loc2);
        double expectedValue = 0.0;

        // Make sure we are right within 1% of the expected value
        assertEquals(testVal, expectedValue, expectedValue / 100);
    }
}
