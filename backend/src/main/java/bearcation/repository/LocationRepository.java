package bearcation.repository;

import bearcation.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    Optional<Location> findLocationsByOwnerId(long user_id);
    Optional<Location> findByName(String name);


}