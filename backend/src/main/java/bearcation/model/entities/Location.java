package bearcation.model.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "locations")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = { "owner", "reviews" })
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="location_id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;
    private String name;
    private String address;
    private String description;
    private Double price;
    private Double latitude;
    private Double longitude;

    @OneToMany(mappedBy="location", cascade=CascadeType.ALL)
    private Set<Review> reviews;

    @ElementCollection
    private Set<String> activities;

    public Location(User owner, String name, String address, String description, Double price, Double latitude, Double longitude) {
        this.owner = owner;
        this.name = name;
        this.address = address;
        this.description = description;
        this.price = price;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
