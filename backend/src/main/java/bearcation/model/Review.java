package bearcation.model;

import java.util.List;
import java.util.Objects;

public class Review {
    private Double rating;
    private String description;
    private Survey survey;

    public Review(Double rating, String description, List<String> answerList) {
        this.rating = rating;
        this.description = description;
        this.survey = new Survey(answerList);
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        Objects.requireNonNull(rating);

        if (rating > 5.0 || rating < 0.0) {
            throw new IllegalArgumentException("Illegal rating value was given");
        }

        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        Objects.requireNonNull(description);

        this.description = description;
    }

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        Objects.requireNonNull(survey);

        this.survey = survey;
    }
}
