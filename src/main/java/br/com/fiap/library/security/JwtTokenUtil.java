package br.com.fiap.library.security;

import com.sun.scenario.effect.Offset;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.HashMap;

@Component
public class JwtTokenUtil {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expire}")
    private int expire;

    public String generateToken(String username) {
        Date dataCriacao = getDateFromLocalDateTime(LocalDateTime.now());
        Date dataExpiracao = getDateFromLocalDateTime(LocalDateTime.now().plusMinutes(expire));

        return Jwts.builder()
                .addClaims(new HashMap<>())
                .setSubject(username)
                .setIssuedAt(dataCriacao)
                .setExpiration(dataExpiracao)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public String getUsername(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token.replace("Bearer ", ""))
                .getBody();

        String subject = claims.getSubject();
        return subject;
    }

    private Date getDateFromLocalDateTime(LocalDateTime localDateTime) {
        return Date.from(localDateTime.toInstant(OffsetDateTime.now().getOffset()));
    }
}
