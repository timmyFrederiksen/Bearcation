package bearcation.controller;


import bearcation.model.User;
import bearcation.service.LocationService;
import bearcation.model.Location;
import bearcation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/location")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @GetMapping("/locations")
    public List<Location> getLocations() {
        return locationService.getLocations();
    }

    @PostMapping("/addLocation")
    public Location saveLocation(@RequestBody Location location){
        return locationService.createLocation(location);
    }
}
