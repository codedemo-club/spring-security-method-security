package club.codedemo.springsecuritymethodsecurity.service;

import club.codedemo.springsecuritymethodsecurity.entity.CustomUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service("userDetailService")
public class UserService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CustomUser customUser = new CustomUser(username, "123", Arrays.asList());
        customUser.setNickName("jane");
        return customUser;
    }
}
