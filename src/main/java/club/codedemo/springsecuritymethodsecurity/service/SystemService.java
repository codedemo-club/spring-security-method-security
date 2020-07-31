package club.codedemo.springsecuritymethodsecurity.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class SystemService {
    public String getSystemYear(){
        return null;
    }

    public String getSystemDate(){
        return null;
    }
}
