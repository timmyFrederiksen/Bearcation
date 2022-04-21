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

    public double calculateDistance(Location that) {

        // Convert to radian angle measures
        double lon1 = Math.toRadians(this.getLongitude());
        double lon2 = Math.toRadians(that.getLongitude());
        double lat1 = Math.toRadians(this.getLatitude());
        double lat2 = Math.toRadians(that.getLatitude());

        // Find difference
        double diff_longitudes = lon2 - lon1;
        double diff_latitudes = lat2 - lat1;
        double a = Math.pow(Math.sin(diff_latitudes / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.pow(Math.sin(diff_longitudes / 2),2);
        double c = 2 * Math.asin(Math.sqrt(a));

        // Earth's radius in km
        double r = 6371;

        // Return the calculated result
        return c * r;
    }

}
