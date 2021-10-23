package runtimeTerror.autoCare.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import runtimeTerror.autoCare.model.User;


@Service
public class UserServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            System.out.print("Username not found");
            throw new UsernameNotFoundException((username + " not found"));
        }
        if (!user.isVerified()) {
            System.out.print("Please verified your email");
            throw new UsernameNotFoundException((username + "Please verified your email"));
        }
        return user;
    }
}
