package com.example.padelup.controller;

import com.example.padelup.model.dto.RegisterUserDto;
import com.example.padelup.service.RegisterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1")
//@AllArgsConstructor
public class RegisterUserCtrl {

   private final RegisterUserService registerUserService;
   @Autowired
   public RegisterUserCtrl(RegisterUserService registerUserService) {
      this.registerUserService = registerUserService;
   }

   @PostMapping(path ="/registeruser")
   public String register(@RequestBody RegisterUserDto registerUserDto) {
      return registerUserService.register(registerUserDto);
   }
//   @GetMapping("/login")
//   public String home(){
//      return "You are loged in";
//   }
//   @GetMapping(path = "confirm")
//   public String confirm(@RequestParam("token") String token) {
//      return registerUserService.confirmToken(token);
//   }

}