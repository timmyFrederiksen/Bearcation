package bearcation.service;

import bearcation.model.User;
import bearcation.model.Location;
import bearcation.model.Review;
import bearcation.repository.UserRepository;
import bearcation.repository.LocationRepository;
import bearcation.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;
import java.util.Optional;


@Service
public class LocationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    public List<Location> getLocations() {
        return this.locationRepository.findAll();
    }

    public Location createLocation(Location location){
        userRepository.save(location.getOwner());
        return locationRepository.save(location);
    }

    public Location getLocationById(long id) {
        Optional<Location> optionalLocation= this.locationRepository.findById(id);
        if (optionalLocation.isPresent()) {
            return optionalLocation.get();
        }
        return null;
    }

    public void deleteLocation(String name) {
        locationRepository.deleteByName(name);
    }

    public void updateLocation(String name, Location location) {
        locationRepository.save(location);
    }
}