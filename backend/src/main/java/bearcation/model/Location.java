package bearcation.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "locations")
@Data
@NoArgsConstructor
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="location_id")
    private long id;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private @NonNull User owner;

    @OneToMany(mappedBy="location", cascade=CascadeType.ALL)
    private @NonNull Set<Review> ownedLocations;


    private @NonNull String name;
    private @NonNull String address;
    private @NonNull String details;

    public Location(@NonNull User owner, @NonNull String name, @NonNull String address, @NonNull String details) {
        this.owner = owner;
        this.name = name;
        this.address = address;
        this.details = details;
    }
}
