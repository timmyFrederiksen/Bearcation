package bearcation.service;

import bearcation.model.dto.LocationDTO;
import bearcation.model.entities.Location;
import bearcation.model.entities.User;
import bearcation.model.requests.Activity;
import bearcation.model.requests.CreateLocationRequest;
import bearcation.repository.LocationRepository;
import bearcation.repository.UserRepository;
import bearcation.utils.MathUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class LocationService {
    private final UserRepository userRepository;
    private final LocationRepository locationRepository;

    @Autowired
    public LocationService(UserRepository userRepository, LocationRepository locationRepository){
        this.userRepository = userRepository;
        this.locationRepository = locationRepository;
    }

    public LocationDTO createLocation(CreateLocationRequest createLocationRequest) {
        User owner = userRepository.getById(createLocationRequest.getOwnerId());
        Location location = new Location( owner,
                createLocationRequest.getName(),
                createLocationRequest.getAddress(),
                createLocationRequest.getDescription(),
                createLocationRequest.getPrice(),
                createLocationRequest.getLatitude(),
                createLocationRequest.getLongitude());
        return new LocationDTO(locationRepository.save(location));
    }

    public LocationDTO findLocationById(Long id) {
        return this.locationRepository.findById(id).map(LocationDTO::new).orElse(null);
    }

    public List<LocationDTO> findAllLocations() {
        return this.locationRepository.findAll().stream().map(LocationDTO::new).collect(Collectors.toList());
    }

    public List<LocationDTO> getRecommendedLocations(Double latitude, Double longitude, Double price, Activity[] activities) {
        List<Location> locations = locationRepository.findAll();
        locations.sort(Comparator.comparingInt(a -> calculateScore(a, latitude, longitude)));
        return locations.stream().map(LocationDTO::new).limit(10).collect(Collectors.toList());
    }

    Integer calculateScore(Location location, Double latitude, Double longitude){
        double distance = MathUtils.calculateDistance(latitude, longitude, location.getLatitude(), location.getLongitude());
        Double cost = location.getPrice();

        int score = 0;
        if(distance <= 50) {
            score += 100;
        }else if (distance <= 100) {
            score += 50;
        } else if (distance <= 250) {
            score += 25;
        } else if (distance <= 750) {
            score += 10;
        }

        if (cost <= location.getPrice()) {
            score += 50;
        } else if (cost <= location.getPrice() * 2) {
            score += 25;
        } else if (cost <= location.getPrice() * 3) {
            score += 5;
        }

        return score;
    }
}