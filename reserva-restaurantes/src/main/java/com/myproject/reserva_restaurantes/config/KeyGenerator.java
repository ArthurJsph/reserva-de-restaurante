package com.myproject.reserva_restaurantes.config;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.security.SecureRandom;
import java.util.Base64;

public class KeyGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String senha = args.length > 0 ? args[0] : "administrador_senha_secret0102@";
        String hash = encoder.encode(senha);
        System.out.println("Chave segura gerada: " + hash);
    }
}