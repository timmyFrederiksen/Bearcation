package bearcation.controller;


import bearcation.model.dto.LocationDTO;
import bearcation.model.requests.Activity;
import bearcation.model.requests.CreateLocationRequest;
import bearcation.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/location")
public class LocationController {
    private final LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService){
        this.locationService = locationService;
    }
    @PostMapping("/createLocation")
    public LocationDTO createLocation(@RequestBody CreateLocationRequest createLocationRequest) {
        return locationService.createLocation(createLocationRequest);
    }

    @GetMapping("/search/{id}")
    public LocationDTO getLocationById(@PathVariable Long id){
        return locationService.findLocationById(id);
    }

    @GetMapping("/locations")
    public List<LocationDTO> findAllLocations() {
        return locationService.findAllLocations();
    }

    @GetMapping("/search/{latitude}/{longitude}/{price}/{activities}")
    public List<LocationDTO> getLocationsByAlgorithm(@PathVariable Double latitude, @PathVariable Double longitude,
                                                  @PathVariable Double price, @PathVariable Activity[] activities){
        return locationService.getRecommendedLocations(latitude, longitude, price, activities);
    }
}