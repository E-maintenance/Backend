package runtimeTerror.autoCare.webSecurityConfig;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import runtimeTerror.autoCare.model.UserM;
import runtimeTerror.autoCare.repository.UserRepostry;

public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepostry studentRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserM user = UserRepostry.findStudentByUsername(username);

        if (user == null) {
            System.out.print("Username not found");
            throw new UsernameNotFoundException((username + " not found"));
        }

        return user;
    }

    }

