package club.codedemo.springsecuritymethodsecurity;

import club.codedemo.springsecuritymethodsecurity.entity.CustomUser;
import club.codedemo.springsecuritymethodsecurity.service.UserRoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@WithMockUser(username = "john", roles = { "VIEWER" })
public class TestMethodSecurity {
    @Autowired
    UserRoleService userRoleService;

    @Test
    public void givenRoleViewer_whenCallGetUsername_thenReturnUsername() {
        String userName = userRoleService.getUsername();
        assertEquals("john", userName);
    }

    @Test
    @WithMockUser(username = "JOHN", authorities = { "SYS_ADMIN" })
    public void givenAuthoritySysAdmin_whenCallGetUsernameLC_thenReturnUsername() {
        String username = userRoleService.getUsernameInLowerCase();

        assertEquals("john", username);
    }

    @Test
    @WithAnonymousUser
    public void givenAnomynousUser_whenCallGetUsername_thenAccessDenied() {
        assertThrows(AccessDeniedException.class, () -> userRoleService.getUsername());
    }

    @Test
    @WithUserDetails(
            value = "john",
            userDetailsServiceBeanName = "userDetailService")
    public void whenJohn_callLoadUserDetail_thenOK() {
        CustomUser user = userRoleService.loadUserDetail("jane");
        assertEquals("jane", user.getNickName());
    }

    @Test
    @WithMockJohnViewer
    public void givenMockedJohnViewer_whenCallGetUsername_thenReturnUsername() {
        String userName = userRoleService.getUsername4();

        assertEquals("john", userName);
    }
}
