package club.codedemo.springsecuritymethodsecurity.service;

import club.codedemo.springsecuritymethodsecurity.entity.CustomUser;
import club.codedemo.springsecuritymethodsecurity.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserRoleService {
    @Autowired
    UserRoleRepository userRoleRepository;

    public String getUsername() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return securityContext.getAuthentication().getName();
    }

    public boolean isValidUsername(String username) {
        return userRoleRepository.isValidUsername(username);
    }

    public String getUsername2() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return securityContext.getAuthentication().getName();
    }


    public boolean isValidUsername2(String username) {
        return userRoleRepository.isValidUsername(username);
    }


    public String getUsernameInUpperCase() {
        return this.getUsername().toUpperCase();
    }

    public boolean isValidUsername3(String username) {
        return userRoleRepository.isValidUsername(username);
    }

    public String getMyRoles(String username) {
        return "MY_ROLES";
    }

    public String getMyRoles2(String username) {
        return "MY_ROLES2";
    }

    public CustomUser loadUserDetail(String nickname) {
        return userRoleRepository.loadUserByNickname(nickname);
    }

    public String joinUsernames(List<String> usernames) {
        return usernames.stream().collect(Collectors.joining(";"));
    }


    public String joinUsernamesAndRoles(
            List<String> usernames, List<String> roles) {

        return usernames.stream().collect(Collectors.joining(";"))
                + ":" + roles.stream().collect(Collectors.joining(";"));
    }

    public List<String> getAllUsernamesExceptCurrent() {
        return userRoleRepository.getAllUsernames();
    }

    public String getUsername4() {
        return this.getUsername();
    }

    public CustomUser securedLoadUserDetail(String username) {
        return userRoleRepository.loadUserByUserName(username);
    }

    public boolean isValidUsername4(String username) {
        // 以下的方法将会跳过安全认证
        this.getUsername();
        return true;
    }

    public String getUsernameInLowerCase(){
        return getUsername().toLowerCase();
    }
}
