package runtimeTerror.autoCare.controller.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import runtimeTerror.autoCare.model.User;
import runtimeTerror.autoCare.model.WorkShopFeeds;
import runtimeTerror.autoCare.model.blog.Review;
import runtimeTerror.autoCare.repository.UserRepository;
import runtimeTerror.autoCare.repository.blog.ReviewRepository;

import java.security.Principal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Controller
public class ReviewController {

    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/reviews")
    public String getReviewsPage(Principal principal, Model model) {
        User user = userRepository.findUserByUsername(principal.getName());
        List<Review> reviews = reviewRepository.findAll();
        model.addAttribute("user",user);
        model.addAttribute("reviews",reviews);
        model.addAttribute("principal", principal.getName());
        return "/blog/reviews";
    }

    @PostMapping("/reviews")

    public String createReview (@ModelAttribute Review reviews){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findUserByUsername(userDetails.getUsername());
        reviews.setUser(user);
        reviewRepository.save(reviews);
        userRepository.save(user);
        user.setReview(Collections.singletonList(reviews));
        return ("redirect:/reviews");
    }
    @GetMapping("/addReview")
    public String getReviewPage(Model model,Principal principal) {
        User user =  userRepository.findUserByUsername(principal.getName());
        model.addAttribute("user",user);

        return "/blog/addReview";
    }


    @GetMapping("/deleteReview/{id}")
    public String deleteReview(@PathVariable("id") Long id, Model model){
          Review review = reviewRepository.findReviewById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid review Id:" + id));
        reviewRepository.deleteById(id);
        return "redirect:/reviews";
    }

//    @GetMapping("/blog/edit/{id}")
//    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
//        Review review = reviewRepository.findPostById(id).orElseThrow(() -> new IllegalArgumentException("Invalid post Id:" + id));
//
//        model.addAttribute("post", review);
//        return "update-post";
//    }
//
//    @PostMapping("/blog/update/{id}")
//    public String updateUser(@PathVariable("id") long id, Review review,
//                             BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            review.setId(id);
//            return "update-post";
//        }
//
//        reviewRepository.save(review);
//        return "redirect:/blog";
//    }
//
//    @GetMapping("/blog/delete/{id}")
//    public String deleteUser(@PathVariable("id") long id, Model model) {
//        Review review = reviewRepository.findPostById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid post Id:" + id));
//        reviewRepository.delete(review);
//        return "redirect:/blog";
//    }
}
