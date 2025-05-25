package api.authentication.service;

import api.authentication.model.UserModel;
import api.authentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


         UserModel user = userRepository.findUserByUsername(username).orElseThrow(()->{
           return new UsernameNotFoundException("usuario no encontrado");
        });

         return new User(user.getUsername(),user.getPassword(),user.getAuthorities());


    }
}
