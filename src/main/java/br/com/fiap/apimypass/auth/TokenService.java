package br.com.fiap.apimypass.auth;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.fiap.apimypass.user.User;
import br.com.fiap.apimypass.user.UserRepository;

@Service
public class TokenService {

    private final UserRepository userRepository;
    Algorithm algorithm = Algorithm.HMAC256("assinatura");

    public TokenService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Token create(Credentials credentials) {
        var expiresAt = LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.ofHours(-3));
        var token = JWT.create()
                .withSubject(credentials.username())
                .withClaim("role", "ADMIN")
                .withExpiresAt(expiresAt)
                .sign(algorithm);
        return new Token(token,"ADMIN" ,credentials.username());
    }

    public User getUserFromToken(String token) {
        var username = JWT.require(algorithm)
                .build()
                .verify(token)
                .getSubject();

        return userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User not found")
        );
    }
}
