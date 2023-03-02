package com.team5.household.expense.Security.provider;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import com.team5.household.expense.Security.VO.TokenVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.team5.household.expense.Security.VO.TokenVO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;

@Component
public class JwtTokenProvider {
    private final Key key;
    private final Integer tokenExpireMinutes = 10;
    private final Integer refreshExpireMinutes = 60;
        
    public JwtTokenProvider(@Value("${jwt.secretKey}") String secretKey){
        //secret값을 base64 decode(복호화)하여 key 변수에 할당하기 위함
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes); 
    }

    public TokenVO generateToken(Authentication authentication){
        // .collect(Collectors.joining(",") 참고자료:(https://bactoria.tistory.com/entry/%EC%9E%90%EB%B0%948-collectCollectorsjoining-%EB%9E%8C%EB%8B%A4)
        String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));
        Date expires = new Date((new Date()).getTime()+tokenExpireMinutes*60*1000);
        Date refreshExpires = new Date((new Date()).getTime()+refreshExpireMinutes*60*1000);
        String accessToken = Jwts.builder()
                                // JWT payload 에 저장되는 정보단위
                                .setSubject(authentication.getName())
                                .claim("auth", authorities)
                                .setExpiration(expires)
                                // JWT를 서명하기위해 SecretKey나 PrivateKey를 지정한다. 압축하고 서명하기 위해 compact()를 호출하고, accessToken 생성
                                .signWith(key, SignatureAlgorithm.HS256).compact();
        String refreshToken = Jwts.builder()
                                    .setExpiration(refreshExpires)
                                    .signWith(key, SignatureAlgorithm.HS256).compact();

        return TokenVO.builder().grantType("Bearer").accessToken(accessToken).refreshToken(refreshToken).build();
    }   

    // accessToken에 담겨있는 권한 정보들을 이용하여 Authentication 객체를 리턴
    public Authentication getAuthentication(String accessToken){
        Claims claims = parseClaims(accessToken);
        if(claims.get("auth") == null){
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }
        // 클레임에서 권한 정보 가져오기
        Collection<? extends GrantedAuthority> authorities = 
                                    Arrays.stream(claims.get("auth").toString().split(","))
                                    .map(SimpleGrantedAuthority::new)
                                    .collect(Collectors.toList());
        // UserDetails 객체를 만들어서 Authentication 리턴 위에서 setSubject를 authentication.getName()로 설정함
        UserDetails principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    public Claims parseClaims(String accessToken) {
        try{
            // JWS (JSON Web Signature) : 서버에서 인증을 증거로 인증 정보를 서버의 private key 로 서명한 것을 Token 화 한것.
            // 토큰을 생성할 때 사용했던 key를 set해주고 parseClaimsJws를 이용해서 토큰을 Jws로 파싱
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
        } catch(ExpiredJwtException e){
            return e.getClaims();
        }
    }

    public boolean validateToken(String token){
        try{
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        }catch(SecurityException | MalformedJwtException e) {
            System.out.println("Invaild JWT Token"+e);
        }catch(ExpiredJwtException e){
            System.out.println("Expired JWT Token"+e);
        }catch(UnsupportedJwtException e){
            System.out.println("Unsupported JWT Token"+e);
        }catch(IllegalArgumentException e){
            System.out.println("JWT claims string empty"+e);
        }
        return false;
    }
}
