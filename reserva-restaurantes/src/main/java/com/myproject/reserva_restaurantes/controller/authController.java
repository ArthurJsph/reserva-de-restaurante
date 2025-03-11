package com.myproject.reserva_restaurantes.controller;


import com.myproject.reserva_restaurantes.service.authService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/auth")
public class authController {
    @Autowired
    private authService AuthService;

    

}
