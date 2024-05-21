package com.ssafy.pistachio.model.dto.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 유저
 */
public class User implements UserDetails, Serializable {
    private Long id;
    private Long membershipId;
    private String email;
    private String password;
    private String name;
    private Long pista;
    private String userProfile;
    private List<String> roles;
    private boolean isAdmin;
    private boolean isActivate;

    public User(Long id,
                Long membershipId,
                String email,
                String password,
                String name,
                Long pista,
                String userProfile,
                boolean isAdmin,
                boolean isActivate) {
        this.id = id;
        this.membershipId = membershipId;
        this.email = email;
        this.password = password;
        this.name = name;
        this.pista = pista;
        this.userProfile = userProfile;
        this.roles = new ArrayList<>();
        this.isAdmin = isAdmin;
        this.isActivate = isActivate;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (roles != null) {
            for (String role : roles) {
                if (role != null && !role.trim().isEmpty()) {
                    authorities.add(new SimpleGrantedAuthority(role));
                } else {
                    throw new IllegalArgumentException("Role cannot be null or empty");
                }
            }
        }
        return authorities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(Long membershipId) {
        this.membershipId = membershipId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActivate;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPista() {
        return pista;
    }

    public void setPista(Long pista) {
        this.pista = pista;
    }

    public String getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(String userProfile) {
        this.userProfile = userProfile;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public boolean isActivate() {
        return isActivate;
    }

    public void setActivate(boolean activate) {
        isActivate = activate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", membershipId=" + membershipId +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", pista=" + pista +
                ", userProfile='" + userProfile + '\'' +
                ", roles=" + roles +
                ", isAdmin=" + isAdmin +
                ", isActivate=" + isActivate +
                '}';
    }
}
