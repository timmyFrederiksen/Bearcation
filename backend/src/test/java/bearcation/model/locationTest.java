package bearcation.model;

import bearcation.model.Location;
import bearcation.repository.LocationRepository;
import bearcation.service.LocationService;
import org.junit.Before;
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
        location.setLongitude(88.8);
        location.setLongitude(99.9);
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
