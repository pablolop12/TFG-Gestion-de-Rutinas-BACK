package com.pablolopezlujan.tfggimnasio.security.service;

import com.pablolopezlujan.tfggimnasio.entity.Role;
import com.pablolopezlujan.tfggimnasio.entity.User;
import com.pablolopezlujan.tfggimnasio.repository.UserRepository;
import com.pablolopezlujan.tfggimnasio.security.dto.LoginCredential;
import com.pablolopezlujan.tfggimnasio.security.dto.RegisterRequest;
import com.pablolopezlujan.tfggimnasio.security.dto.TokenDto;
import com.pablolopezlujan.tfggimnasio.security.dto.TokenPayloadDTO;
import com.pablolopezlujan.tfggimnasio.security.jwt.TokenUtils;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AuthService {
    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenUtils jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JavaMailSender mailSender;

    public TokenDto login(LoginCredential request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        logger.debug("BUSCANDO USUARIO: " + request.getEmail());
        UserDetails user = userRepository.findByEmail(request.getEmail()).orElseThrow();

        User userData = userRepository.findByEmail(request.getEmail()).orElseThrow();
        String roles = userData.getRole().name();

        Map<String, Object> payload = new HashMap<>();
        payload.put("id", userData.getId());
        payload.put("name", userData.getName());
        payload.put("lastname", userData.getLastname());
        payload.put("email", userData.getEmail());
        payload.put("role", roles);
        logger.debug(payload.toString());

        return TokenDto.builder().token(jwtService.generateToken(payload)).build();
    }

    public TokenPayloadDTO getPayloadFromToken(String token) {
        try {
            Claims payloadClaims = jwtService.getAllClaimsFromToken(token.substring(7));
            return modelMapper.map(payloadClaims, TokenPayloadDTO.class);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public RegisterRequest register(RegisterRequest request) {
        User user = modelMapper.map(request, User.class);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);
        userRepository.save(user);

        User userData = userRepository.findByEmail(request.getEmail()).orElseThrow();
        String roles = userData.getRole().name();

        return RegisterRequest.builder()
                .id(userData.getId())
                .name(userData.getName())
                .lastname(userData.getLastname())
                .email(userData.getEmail())
                .roles(roles)
                .build();
    }

    public RegisterRequest register(User request) {
        User user = request;
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);
        userRepository.save(user);

        User userData = userRepository.findByEmail(request.getEmail()).orElseThrow();
        String roles = userData.getRole().name();

        return RegisterRequest.builder()
                .id(userData.getId())
                .name(userData.getName())
                .lastname(userData.getLastname())
                .email(userData.getEmail())
                .roles(roles)
                .build();
    }

    public RegisterRequest registerAdmin(RegisterRequest request) {
        User user = modelMapper.map(request, User.class);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.ADMIN);
        userRepository.save(user);

        User userData = userRepository.findByEmail(request.getEmail()).orElseThrow();
        String roles = userData.getRole().name();

        return RegisterRequest.builder()
                .id(userData.getId())
                .name(userData.getName())
                .lastname(userData.getLastname())
                .email(userData.getEmail())
                .roles(roles)
                .build();
    }

    public RegisterRequest registerAdmin(User request) {
        User user = request;
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.ADMIN);
        userRepository.save(user);

        User userData = userRepository.findByEmail(request.getEmail()).orElseThrow();
        String roles = userData.getRole().name();

        return RegisterRequest.builder()
                .id(userData.getId())
                .name(userData.getName())
                .lastname(userData.getLastname())
                .email(userData.getEmail())
                .roles(roles)
                .build();
    }

 // Password reset methods
    public void sendPasswordResetEmail(String email) {
        logger.debug("Iniciando el proceso de recuperación de contraseña para el email: {}", email);
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (!userOptional.isPresent()) {
            logger.error("No se encontró un usuario con el email: {}", email);
            throw new RuntimeException("No user found with email " + email);
        }

        User user = userOptional.get();
        String token = UUID.randomUUID().toString();
        user.setResetPasswordToken(token);
        user.setResetPasswordExpires(new Date(System.currentTimeMillis() + 3600000)); // 1 hour expiry
        userRepository.save(user);

        logger.debug("Usuario encontrado: {}. Token de restablecimiento de contraseña generado: {}", user.getEmail(), token);

        SimpleMailMessage emailMessage = new SimpleMailMessage();
        emailMessage.setTo(user.getEmail());
        emailMessage.setSubject("Password Reset Request");
        emailMessage.setText("To reset your password, click the link below:\n" +
                "http://localhost:4200/reset-password?token=" + token);

        logger.debug("Enviando correo a: {}", user.getEmail());
        mailSender.send(emailMessage);
        
        logger.debug("correo enviado a: {}", user.getEmail());
    }

    public void resetPassword(String token, String newPassword) {
        Optional<User> userOptional = userRepository.findByResetPasswordToken(token);
        if (!userOptional.isPresent()) {
            throw new RuntimeException("Invalid token");
        }

        User user = userOptional.get();
        if (user.getResetPasswordExpires().before(new Date())) {
            throw new RuntimeException("Token expired");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        user.setResetPasswordToken(null);
        user.setResetPasswordExpires(null);
        userRepository.save(user);
    }
}
