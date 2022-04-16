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
    @GetMapping("/search/{id}")
    public Location getLocationById(@PathVariable long id){
        return locationService.getLocationById(id);
    }
    @DeleteMapping("/delete/{name}")
    public void deleteLocation(@PathVariable String name){
        locationService.deleteLocation(name);
    }

    @PutMapping("/update/{name}")
    public void updateLocation(@PathVariable String name, @RequestBody Location location){
        locationService.updateLocation(name, location);
    }


    @PostMapping("/addLocation")
    public Location saveLocation(@RequestBody Location location){
        return locationService.createLocation(location);
    }
}
