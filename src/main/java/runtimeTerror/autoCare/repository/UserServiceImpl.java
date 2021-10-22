package runtimeTerror.autoCare.repository;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import runtimeTerror.autoCare.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("---------------------------------------------------------------------s;djaslkfjlkashjflkashflkhasf-");
        System.out.println(username);
//        System.out.println(userRepository.findUserByUsername(username)+"ffdgfdgfdgfdgfdgfdg");
        User user = userRepository.findUserByUsername(username);
        System.out.println("----------------------------------------------------------------------");


        if (user == null) {
            System.out.print("Username not found");
            throw new UsernameNotFoundException((username + " not found"));
        }

        return user;
    }
}
