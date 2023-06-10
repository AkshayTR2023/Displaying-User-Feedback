package com.ecommerce;

import java.io.IOException;
import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class FeedbackController {

    private final FeedbackRepository feedbackRepository;


    public FeedbackController(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    @PostMapping("/feedback")
    public void saveFeedback(@RequestParam("username") String username,
                                     @RequestParam("email") String email,
                                     @RequestParam("comment") String comment,
                                     HttpServletResponse response) throws IOException {
        Feedback feedback = new Feedback();
        feedback.setUsername(username);
        feedback.setEmail(email);
        feedback.setComment(comment);
        feedbackRepository.save(feedback);
        response.sendRedirect("/success.html");
    }



    @GetMapping("/feedbacks")
    public List<Feedback> getAllFeedbacks() {
        List<Feedback> feedbacks = feedbackRepository.findAll();
        return feedbacks;
    }
    
}
