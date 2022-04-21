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

    // This is the default value for rating score (no ratings), change if you have an opinion
    // Change must be reflected in Location Unit tests -> default avg rating
    private final static double RATING_DEFAULT = 2.5;

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

    public double findAvgScore() {
        if (this.getReviews().size() == 0) {
            return RATING_DEFAULT;
        }

        double avgScore = 0.0;

        // Sum
        for (Review r : this.getReviews()) {
            avgScore += r.getRating();
        }

        // Divide by N (avg)
        avgScore /= this.getReviews().size();

        return avgScore;
    }

    public boolean hasActivity(String activity) {
        return this.activities.contains(activity);
    }

    public int givePricePoints(double targetPrice) {
        //          Scheme
        // Default
        // Less than 	$1 -> 50 points
        //              $2-$20 -> 25 points
        //              $21-$50 -> 5 points

        // User enters non-zero preferred_cost:
        //		Within  preferred_cost -> 50 points
        //		Within  preferred_cost * 2 -> 25 points
        //		Within  preferred_cost * 3 -> 5 points
        if (targetPrice == 0.0) {
            if (this.getPrice() <= 1.0) {
                return 50;
            } else if (this.getPrice() <= 20.0) {
                return 25;
            } else if (this.getPrice() <= 50.0) {
                return 5;
            }
        } else {
            if (this.getPrice() <= targetPrice) {
                return 50;
            } else if (this.getPrice() <= (2.0 * targetPrice)) {
                return 25;
            } else if (this.getPrice() <= (3.0 * targetPrice)) {
                return 5;
            }
        }

        return 0;
    }

    public int giveDistancePoints(double distance) {
        // Scoring scheme:
        // 0-50 miles -> 100 points
        // 50-250 miles -> 50 points
        // 250-750 -> 25 points
        // 750-1500 -> 10 points
        // 1500+ -> 0 points

        if (distance < 50.0) {
            return 100;
        } else if (distance < 250.0) {
            return 50;
        } else if (distance < 750) {
            return 25;
        } else if (distance < 1500) {
            return 10;
        } else {
            return 0;
        }
    }

}
