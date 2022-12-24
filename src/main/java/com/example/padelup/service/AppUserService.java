package com.example.padelup.service;

import com.example.padelup.model.entity.AppUser;
import com.example.padelup.repo.AppUserRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

   private final static String USER_NOT_FOUND_MSG = "user with email %s not found";
   private final AppUserRepo appUserRepo;
   private final BCryptPasswordEncoder bCryptPasswordEncoder;

   @Override
   public UserDetails loadUserByUsername(String email)
           throws UsernameNotFoundException {
      return appUserRepo.findByEmail(email)
              .orElseThrow(() ->
                      new UsernameNotFoundException(
                              String.format(USER_NOT_FOUND_MSG, email)));
   }

   public String signUpUser(AppUser appUser) {
      boolean userExists = appUserRepo
              .findByEmail(appUser.getEmail())
              .isPresent();

      if (userExists) {
         // TODO check of attributes are the same and

         throw new IllegalStateException("email already taken");
      }

      String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());
      appUser.setPassword(encodedPassword);
      appUserRepo.save(appUser);
      String token = UUID.randomUUID().toString();
      return token;
   }

   public int enableAppUser(String email) {
      return appUserRepo.enableAppUser(email);
   }
}