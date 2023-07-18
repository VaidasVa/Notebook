package pro.vaidas.notebookclient.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtValidationService {

    private static final String SECRET = "fe623bd733fc57bad3fe78db92b1016488e323912553695449d0d39cb833b13a";

    public String getUserName(String jwtToken){
        return getClaim(jwtToken, Claims::getSubject);
    }

    public <T> T getClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSigningKey() {
        byte[] secretBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(secretBytes);
    }

    public Jws validate(String jwtToken){
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(jwtToken);
    }

    public boolean isTokenValid (String token, UserDetails userDetails){
        final String userName = getUserName(token);
        return (userName.equals(userDetails.getUsername()) &&
                !isTokenExpired(token));
    }

    public boolean isTokenExpired(String token) {
        return getExpirationFromToken(token).before(new Date());
    }

    public Date getExpirationFromToken(String token) {
        return getClaim(token, Claims::getExpiration);
    }
}
