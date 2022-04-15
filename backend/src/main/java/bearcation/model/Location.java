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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Set<Review> getOwnedLocations() {
        return ownedLocations;
    }

    public void setOwnedLocations(Set<Review> ownedLocations) {
        this.ownedLocations = ownedLocations;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
