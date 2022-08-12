package com.github.abhishekroy666.chatbot.resource;

import com.github.abhishekroy666.chatbot.config.security.JwtTokenizer;
import com.github.abhishekroy666.chatbot.model.JwtRequest;
import com.github.abhishekroy666.chatbot.model.JwtResponse;
import com.github.abhishekroy666.chatbot.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Abhishek Roy
 */
@RestController
@CrossOrigin
@RequestMapping("/api/authenticate")
public class ApiAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenizer jwtTokenizer;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @PostMapping(value = "")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        this.authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
        final UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());
        final String token = this.jwtTokenizer.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(jwtRequest.getUsername(), token));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException exception) {
            throw new Exception("USER_DISABLED", exception);
        } catch (BadCredentialsException exception) {
            throw new Exception("INVALID_CREDENTIALS", exception);
        }
    }
}
