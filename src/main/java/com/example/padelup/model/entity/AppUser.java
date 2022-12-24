package com.example.padelup.model.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "appuser")
public class AppUser implements UserDetails {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "user_id")
   private Long Id;
   @Column(name = "user_email", nullable = false, unique = true, length = 77)
   private String email;
   @Column(name = "user_pw")
   private String password;
   @Enumerated(EnumType.STRING)
   private AppUserRole appUserRole;
   private Boolean locked = false;
   private Boolean enabled = false;

   public AppUser(String email, String password, AppUserRole appUserRole) {
      this.email = email;
      this.password = password;
      this.appUserRole = appUserRole;
   }

   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      SimpleGrantedAuthority authority =
              new SimpleGrantedAuthority(appUserRole.name());
      return Collections.singletonList(authority);   }

   @Override
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
//      return !locked;
      return  true;
   }

   @Override
   public boolean isCredentialsNonExpired() {
      return true;
   }

   @Override
   public boolean isEnabled() {
//      return enabled;
      return  true;
   }
}
