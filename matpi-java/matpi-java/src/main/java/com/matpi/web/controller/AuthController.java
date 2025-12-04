package com.matpi.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/auth")
public class AuthController {

    // El login es manejado por Spring Security filters, pero podemos exponer
    // endpoints personalizados si usamos JWT.
    // Con formLogin, Spring maneja /auth/login.
    // Este controlador puede usarse para info de sesión o lógica adicional.

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/check")
    public ResponseEntity<String> checkSession() {
        return ResponseEntity.ok("Sesión activa");
    }
}
