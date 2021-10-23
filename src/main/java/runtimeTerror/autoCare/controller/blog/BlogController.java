package runtimeTerror.autoCare.controller.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import runtimeTerror.autoCare.model.blog.Post;
import runtimeTerror.autoCare.repository.blog.PostRepository;

import java.security.Principal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Controller
public class BlogController {

    @Autowired
    PostRepository postRepository;


    @GetMapping("/blog")
    public String getMyProfilePage(Principal principal, Model model) {
//        ApplicationUser appUser = appUserRepository.findUserByUsername(principal.getName());
//        model.addAttribute("appUser",appUser);
        model.addAttribute("principal", principal.getName());
        return "blog";
    }

    @PostMapping("/posts")
    public RedirectView createPost(Principal principal, Model model, String body) throws ParseException {
//        ApplicationUser user = appUserRepository.findUserByUsername(principal.getName());

//        if(user != null){
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
            sdf.format(timestamp);
//            Post post = new Post(body, timestamp, user);
            Post post = new Post (body,timestamp);
            postRepository.save(post);
//        }

        model.addAttribute("principal", principal.getName());
//        model.addAttribute("appUser", user);
//        model.addAttribute("posts" , user.getPosts());
        return new RedirectView("/blog");
    }

    @GetMapping("/addPost")
    public String getPostPage(Model model,Principal principal) {
//        ApplicationUser appUser =  appUserRepository.findUserByUsername(principal.getName());
//        model.addAttribute("appUser",appUser);

        return "addPost";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Post post = postRepository.findPostById(id).orElseThrow(() -> new IllegalArgumentException("Invalid post Id:" + id));

        model.addAttribute("post", post);
        return "update-post";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id, Post post,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            post.setId(id);
            return "update-post";
        }

        postRepository.save(post);
        return "redirect:/blog";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        Post post = postRepository.findPostById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid post Id:" + id));
        postRepository.delete(post);
        return "redirect:/blog";
    }
}
