package runtimeTerror.autoCare.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import runtimeTerror.autoCare.model.User;

import java.util.ArrayList;
import java.util.List;


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
//        if (!user.isVerified()) {
//            System.out.print("Please verified your email");
//            throw new UsernameNotFoundException((username + "Please verified your email"));
//        }
        List<GrantedAuthority> grantedAuthority= new ArrayList<>();
        grantedAuthority.add(new SimpleGrantedAuthority(user.getRole().getName()));

        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return  new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),grantedAuthority);
    }
}
