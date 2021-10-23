package runtimeTerror.autoCare.webSecurityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import runtimeTerror.autoCare.model.ApplicationUser;
import runtimeTerror.autoCare.repository.ApplicationUserRepo;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    ApplicationUserRepo applicationUserRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUser user = applicationUserRepo.findByUsername(username);

        if (user == null) {
            System.out.print("Username not found");
            throw new UsernameNotFoundException((username + " not found"));
        }

        return user;
    }

}
