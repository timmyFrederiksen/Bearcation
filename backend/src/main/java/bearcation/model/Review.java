package bearcation.model;

import java.util.List;

public class Review {
    private Double rating;
    private String description;
    private Survey survey;

    public Review(Double rating, String description, List<String> answerList) {
        this.rating = rating;
        this.description = description;
        this.survey = new Survey(answerList);
    }
}
