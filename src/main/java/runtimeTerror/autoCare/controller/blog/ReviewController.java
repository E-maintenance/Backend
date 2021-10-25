package runtimeTerror.autoCare.controller.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import runtimeTerror.autoCare.model.User;
import runtimeTerror.autoCare.model.blog.Review;
import runtimeTerror.autoCare.repository.UserRepository;
import runtimeTerror.autoCare.repository.blog.ReviewRepository;

import java.security.Principal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Controller
public class ReviewController {

    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/reviews")
    public String getMyProfilePage(Principal principal, Model model) {
        User user = userRepository.findUserByUsername(principal.getName());
        model.addAttribute("user",user);
        model.addAttribute("principal", principal.getName());
        return "reviews";
    }

    @PostMapping("/reviews")
    public RedirectView createPost(Principal principal, Model model, String body) throws ParseException {
        User user = userRepository.findUserByUsername(principal.getName());
        if(user != null){
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
            sdf.format(timestamp);
            Review review = new Review(body, timestamp, user);
            reviewRepository.save(review);
        }

        model.addAttribute("principal", principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("reviews" , user.getReview());
        return new RedirectView("/reviews");
    }

    @GetMapping("/addReview")
    public String getReviewPage(Model model,Principal principal) {
        User user =  userRepository.findUserByUsername(principal.getName());
        model.addAttribute("user",user);

        return "addReview";
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
