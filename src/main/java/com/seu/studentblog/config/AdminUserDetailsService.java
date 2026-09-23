package com.seu.studentblog.config;

import com.seu.studentblog.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminUserDetailsService implements UserDetailsService {

    private final AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var admin = adminRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("No admin with email: " + email));

        return User.builder()
                .username(admin.getEmail())
                .password(admin.getPasswordHash())
                .roles(admin.getRole() != null ? admin.getRole() : "ADMIN")
                .build();
    }
}
