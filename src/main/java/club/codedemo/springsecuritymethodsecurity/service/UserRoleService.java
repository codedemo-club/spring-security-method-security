package club.codedemo.springsecuritymethodsecurity.service;

import club.codedemo.springsecuritymethodsecurity.annonation.IsViewer;
import club.codedemo.springsecuritymethodsecurity.entity.CustomUser;
import club.codedemo.springsecuritymethodsecurity.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserRoleService {
    @Autowired
    UserRoleRepository userRoleRepository;

    @Secured("ROLE_VIEWER")
    public String getUsername() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return securityContext.getAuthentication().getName();
    }

    @Secured({"ROLE_VIEWER", "ROLE_EDITOR"})
    public boolean isValidUsername(String username) {
        return userRoleRepository.isValidUsername(username);
    }

    @RolesAllowed("ROLE_VIEWER")
    public String getUsername2() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return securityContext.getAuthentication().getName();
    }

    @RolesAllowed({"ROLE_VIEWER", "ROLE_EDITOR"})
    public boolean isValidUsername2(String username) {
        return userRoleRepository.isValidUsername(username);
    }

    @PreAuthorize("hasRole('ROLE_VIEWER')")
    public String getUsernameInUpperCase() {
        return this.getUsername().toUpperCase();
    }

    @PreAuthorize("hasRole('ROLE_VIEWER') or hasRole('ROLE_EDITOR')")
    public boolean isValidUsername3(String username) {
        return userRoleRepository.isValidUsername(username);
    }

    @PreAuthorize("#username == authentication.principal.username")
    public String getMyRoles(String username) {
        return "MY_ROLES";
    }

    @PostAuthorize("#username == authentication.principal.username")
    public String getMyRoles2(String username) {
        return "MY_ROLES2";
    }

    @PostAuthorize("returnObject.username == authentication.principal.nickName")
    public CustomUser loadUserDetail(String nickname) {
        CustomUser customUser = userRoleRepository.loadUserByNickname(nickname);
        return customUser;
    }

    @PreFilter("filterObject != authentication.principal.username")
    public String joinUsernames(List<String> usernames) {
        return usernames.stream().collect(Collectors.joining(";"));
    }

    @PreFilter(value = "filterObject != authentication.principal.username",
            filterTarget = "usernames")
    public String joinUsernamesAndRoles(
            List<String> usernames, List<String> roles) {

        return usernames.stream().collect(Collectors.joining(";"))
                + ":" + roles.stream().collect(Collectors.joining(";"));
    }

    @PostFilter("filterObject != authentication.principal.username")
    public List<String> getAllUsernamesExceptCurrent() {
        return userRoleRepository.getAllUsernames();
    }

    @IsViewer
    public String getUsername4() {
        return this.getUsername();
    }

    @PreAuthorize("#username == authentication.principal.username")
    @PostAuthorize("returnObject.username == authentication.principal.nickName")
    public CustomUser securedLoadUserDetail(String username) {
        return userRoleRepository.loadUserByUserName(username);
    }

    public boolean isValidUsername4(String username) {
        // 以下的方法将会跳过安全认证
        this.getUsername();
        return true;
    }

    @PreAuthorize("hasAuthority('SYS_ADMIN')")
    public String getUsernameInLowerCase() {
        return getUsername().toLowerCase();
    }
}
