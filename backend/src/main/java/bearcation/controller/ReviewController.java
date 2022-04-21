package bearcation.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/review")
public class ReviewController {

}

//    @Autowired
//    private ReviewService reviewService;
//
//    @GetMapping("/reviews")
//    public List<Review> getReviews() {
//        return reviewService.getReviews();
//    }
//
//    @PostMapping("/addReview")
//    public Review saveReview(@RequestBody Review review){
//        return reviewService.createReview(review);
//    }