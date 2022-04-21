package bearcation.model.entities;

import bearcation.utils.MathUtils;
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

    public int calcRecommendationScore(Location targetLocation, double targetPrice, Set<String> targetActivites) {
        int score = 0;

        // Location Component (100 points)
        score += giveDistancePoints(MathUtils.calculateDistance(
                this.getLatitude(), this.getLatitude(), targetLocation.getLatitude(),
                targetLocation.getLongitude()));

        // Availability Component (50 points)
        score += 50;

        // Rating Component (50 points)
        score += (int)(this.findAvgRating() / 5.0) * 50;

        // Pricing Component (50 points)
        score += givePricePoints(targetPrice);

        // Raw Activity Component (50 points)
        score += giveRawActivityPoints();

        // Target Activity Component (10 points / activity match, no max)
        for (String activity : targetActivites) {
            if (this.hasActivity(activity)) {
                score += 10;
            }
        }

        return score;
    }

    public double findAvgRating() {
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

    public int giveRawActivityPoints() {
        // Scoring scheme:
        // If <25 activities, activities * 2
        // Else 50 ( max score )
        return (this.getActivities().size() > 25) ? 50 : (this.getActivities().size() * 2);
    }

}
