package SpringLatestSecurity.TryingLatestSecurity.Service;

import SpringLatestSecurity.TryingLatestSecurity.Model.User;
import SpringLatestSecurity.TryingLatestSecurity.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepo userRepo;
    @Override

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user=userRepo.findByEmail(email);
        if(user==null){
            throw new UsernameNotFoundException("User not found");
        }
        else
return  user;
    }

    public User signup(User user) {
        return userRepo.save(user);

    }

}
