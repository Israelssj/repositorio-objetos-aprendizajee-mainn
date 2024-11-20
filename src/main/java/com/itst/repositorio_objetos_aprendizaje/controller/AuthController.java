package com.itst.repositorio_objetos_aprendizaje.controller;

import com.itst.repositorio_objetos_aprendizaje.model.Usuario;
import com.itst.repositorio_objetos_aprendizaje.repository.UsuarioRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final String jwtSecret = "xmoyC6eFOYqPptxAXCBNn3FyWQ0vXzzl1wqQfgNEnB9RJWmjPlRQoY4P2EDfcnRjrWTLMubQa3AczJuWUDnbEsHQJQLTw6l8JMSuRnWhqDYeRH6U0VYpV4TaS6k6lVgQ";
    private final long jwtExpirationMs = 86400000; // 1 día

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario user) {
        Usuario usuario = usuarioRepository.findByEmail(user.getEmail()).orElse(null);

        if (usuario != null && passwordEncoder.matches(user.getPassword(), usuario.getPassword())) {
            byte[] keyBytes = Base64.getDecoder().decode(jwtSecret);
            SecretKey key = new SecretKeySpec(keyBytes, SignatureAlgorithm.HS512.getJcaName());

            String token = Jwts.builder()
                    .setSubject(usuario.getEmail())
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                    .signWith(key, SignatureAlgorithm.HS512)
                    .compact();

            Map<String, Object> response = new HashMap<>();
            response.put("token", token);

            Map<String, Object> userData = new HashMap<>();
            userData.put("id", usuario.getIdUsuario());
            userData.put("email", usuario.getEmail());
            userData.put("nombre", usuario.getNombreUsuario());
            userData.put("rol", usuario.getRol().getNombreRol());

            response.put("user", userData);

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }
    }
}
