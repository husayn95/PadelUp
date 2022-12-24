package com.example.padelup.service;

import com.example.padelup.model.dto.RegisterUserDto;
import com.example.padelup.model.entity.AppUser;
import com.example.padelup.model.entity.AppUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
//@AllArgsConstructor
public class  RegisterUserService {

   private final AppUserService appUserService;
   private final EmailValidator emailValidator;
   @Autowired
   public RegisterUserService(AppUserService appUserService, EmailValidator emailValidator) {
      this.appUserService = appUserService;
      this.emailValidator = emailValidator;
   }

   public String register(RegisterUserDto request) {
      boolean isValidEmail = emailValidator.test(request.getEmail());

      if (!isValidEmail) {
         throw new IllegalStateException("email not valid");
      }

      String token = appUserService.signUpUser(
              new AppUser(
                      request.getEmail(),
                      request.getPassword(),
                      AppUserRole.USER
              )
      );
      return token;
   }
}
