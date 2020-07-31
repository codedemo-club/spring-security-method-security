package club.codedemo.springsecuritymethodsecurity.repository;

import club.codedemo.springsecuritymethodsecurity.entity.CustomUser;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class UserRoleRepositoryImpl implements UserRoleRepository {
    @Override
    public boolean isValidUsername(String username) {
        return true;
    }

    @Override
    public CustomUser loadUserByUserName(String username) {
        CustomUser customUser = new CustomUser(username, "12345", Arrays.asList());
        customUser.setNickName(username);
        return customUser;
    }

    @Override
    public List<String> getAllUsernames() {
        return Arrays.asList();
    }

    @Override
    public CustomUser loadUserByNickname(String nickname) {
        CustomUser customUser = new CustomUser(nickname, "12345", Arrays.asList());
        customUser.setNickName(nickname);
        return customUser;
    }
}
