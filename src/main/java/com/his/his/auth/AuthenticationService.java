package com.his.his.auth;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.his.his.user.UserRepository;
import com.his.his.user.Role;
import com.his.his.user.User;
import com.his.his.PublicPrivateMapping.PublicPrivateService;
import com.his.his.config.JwtService;
import com.his.his.token.Token;
import com.his.his.token.TokenRepository;
import com.his.his.token.TokenType;

import java.util.UUID;

import org.springframework.security.authentication.AuthenticationManager;

@Service
@RequiredArgsConstructor
public class AuthenticationService 
{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TokenRepository tokenRepository;
    private final PublicPrivateService publicPrivateService;

    public AuthenticationResponse register(RegisterRequest request)
    {
        var user = User.builder().name(request.getUsername()).dateOfBirth(request.getDateOfBirth()).employeeStatus(request.getEmployeeStatus()).lastCheckIn(request.getLastCheckIn()).role(request.getRole()).password(passwordEncoder.encode(request.getPassword())).build();
        var savedUser=userRepository.save(user);
        publicPrivateService.savePublicPrivateId(savedUser.getEmployeeId(),savedUser.getEmployeeType().toString());
        System.out.println(user.getEmployeeId().toString());
        var jwtToken = jwtService.generateToken(user);
        saveUserToken(savedUser,jwtToken);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
    public AuthenticationResponse authenticate(AuthenticationRequest request)
    {
        
        String x=request.getUuid();
        String y=request.getPassword();
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                x,
                y
            )
        );
        
        var user = userRepository.findByEmployeeId(UUID.fromString(request.getUuid()))
            .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        saveUserToken(user,jwtToken);
        return AuthenticationResponse.builder()
            .token(jwtToken)
            .build();
    }
    private void saveUserToken(User user,String jwtToken)
    {
        var token=Token.builder().user(user).token(jwtToken).tokenType(TokenType.BEARER).expired(false).revoked(false).build();
        tokenRepository.save(token);
    }
}
