package com.searchEngine.searchEngine.service;

import java.util.Optional;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import com.searchEngine.searchEngine.entity.User;
import com.searchEngine.searchEngine.model.UserModel;
import com.searchEngine.searchEngine.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    public boolean validateUser(UserModel userModel, User user) throws Exception {
      
        boolean passwordMatches = passwordEncoder.matches(userModel.getPassword(), user.getPassword());
        return user.getEmail().equals(userModel.getEmail()) && passwordMatches;

    }

    public void authenticate(UserModel userModel, HttpSession session) throws Exception {
        Optional<User> optionalUser = userRepository.findByEmail(userModel.getEmail());
        if (optionalUser.isEmpty())
            throw new AuthenticationException("User doesn't exist.");

        if (validateUser(userModel, optionalUser.get())) {
            createSession(optionalUser.get(), session);
            return;
        }
        throw new AuthenticationException("Nie prawidłowe dane logowania");
    }

    public void createSession(User user, HttpSession session) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(
            user,
            null,
            user.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);


        session.setAttribute("USER_SESSION", user);
        session.setMaxInactiveInterval(3600);
    }

    public void logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null)
            new SecurityContextLogoutHandler().logout(request, response, auth);
    }
}
