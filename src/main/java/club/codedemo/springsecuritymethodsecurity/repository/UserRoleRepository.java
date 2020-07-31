package club.codedemo.springsecuritymethodsecurity.repository;

import club.codedemo.springsecuritymethodsecurity.entity.CustomUser;

import java.util.List;

public interface UserRoleRepository {
    boolean isValidUsername(String username);

    CustomUser loadUserByUserName(String username);

    List<String> getAllUsernames();

    CustomUser loadUserByNickname(String nickname);
}
