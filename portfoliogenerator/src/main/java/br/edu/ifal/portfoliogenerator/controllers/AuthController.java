package br.edu.ifal.portfoliogenerator.controllers;

import br.edu.ifal.portfoliogenerator.models.AuthResponse;
import br.edu.ifal.portfoliogenerator.services.AuthService;
import jakarta.validation.Valid;
import br.edu.ifal.portfoliogenerator.dtos.AuthDTO;
import br.edu.ifal.portfoliogenerator.dtos.RegisterDTO;
import br.edu.ifal.portfoliogenerator.models.User;
import br.edu.ifal.portfoliogenerator.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final AuthService authService;

    @Autowired
    private UserRepository repository;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody @Valid RegisterDTO registerDTO){

        if(this.repository.findByUsername(registerDTO.username()).isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }

        return ResponseEntity.ok(authService.register(new User(registerDTO.username(), registerDTO.password())));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthDTO authDTO){

        return ResponseEntity.ok(authService.authenticate(new User(authDTO.username(), authDTO.password())));
    }

}
