package pro.vaidas.authserver.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtValidationService {

    private static final String SECRET = "fe623bd733fc57bad3fe78db92b1016488e323912553695449d0d39cb833b13a";

    /* */
    public String getUserName(String jwtToken){
        return getClaim(jwtToken, Claims::getSubject); // subject - email=username
    }

    /** method to extract all and then one single claim
     * to decode a token we need a signing key;
     signing key - is a secret to sign jwt (signature of jwt);
     signature verifies that sender is who claims to be and msg was not changed
     signing key is used with signing algorithm specified in jwt header to create a signature
     specific signing alg and key size depends on configuration
     * */

    public <T> T getClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey( getSigningKey() )
                .build()
                .parseClaimsJws(token)
                .getBody(); // gets all claims in the token
    }

    private Key getSigningKey() {
        byte[] secretBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(secretBytes);
    }

    /* GENERATE TOKEN withOUT additional details*/
    public String generateToken (UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }

    /* GENERATE TOKEN with additional details
    * Check dependencies in build.gradle for further implementations
    * */
    public String generateToken(
            Map<String, Object> extraClaims, // to pass authorities, roles, etc
            UserDetails userDetails){
        return Jwts.builder()
                .setClaims(extraClaims) // passing additional info
                .setSubject(userDetails.getUsername()) // username
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact(); // generates token
    }

    // we need UserDetails to validate if this token belongs to userDetails
    public boolean isTokenValid (String token, UserDetails userDetails){
        final String userName = getUserName(token);
        return (userName.equals(userDetails.getUsername()) &&
                !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return getExpirationFromToken(token).before(new Date());
    }

    private Date getExpirationFromToken(String token) {
        return getClaim(token, Claims::getExpiration);
    }
}
