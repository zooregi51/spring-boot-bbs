package ex.springbbs.service;

import ex.springbbs.dto.JwtDto;
import ex.springbbs.entity.Member;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Date;

@Service
public class JwtServiceImpl implements IJwtService {

    @Override
    public JwtDto issue (Member member) { // 발행
        Date now = new Date();
        String jwt = Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE) // 헤더를 jwt 타입으로 설정
                .setIssuer("admin") // 클레임의 토큰 발행자 설정
                .setIssuedAt(now) // 토큰 발행시간 설정, Date 타입만 가능
                .setExpiration(new Date(now.getTime() + Duration.ofMinutes(30).toMillis())) // 토큰 만료시간 설정
                .claim("email", member.getEmail())
                .signWith(SignatureAlgorithm.HS256, "secret")
                .compact();
        return JwtDto.builder()
                .jwt(jwt)
                .email(member.getEmail())
                .build();
    }

    @Override
    public JwtDto authentication(String jwtToken) {
        try {
            jwtToken = jwtToken.replaceAll("^Bearer( )*", "");
            JwtDto jwtDto = JwtDto.builder()
                    .jwt(jwtToken)
                    .build();
            Claims claim = Jwts.parser() // 작동하면 해당 토큰은 정상 토큰
                    .setSigningKey("secret")
                    .parseClaimsJws(jwtDto.getJwt())
                    .getBody();
            String email = (String) claim.get("email");
            return JwtDto.builder()
                    .email(email)
                    .build();
        } catch (JwtException exception) {
            throw new IllegalStateException("토큰이 만료되었거나 변조되었습니다.");
        }
    }
}