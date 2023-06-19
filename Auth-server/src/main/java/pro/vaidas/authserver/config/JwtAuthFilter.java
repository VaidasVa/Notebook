package pro.vaidas.authserver.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Configuration
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtValidationService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        // when we make a call we need to pass JWT token in header
        // gets bearer token
        final String authHeader = request.getHeader("Authorization");
        final String jwtToken;
        final String userEmail;

        if (authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }
        jwtToken = authHeader.substring(7);

        // call UserDetailsService to extract userName and check if we have a user in DB
        userEmail = jwtService.getUserName(jwtToken);
                if (userEmail != null &&
                // checking if user is not authenticated yet
                SecurityContextHolder.getContext().getAuthentication() == null)
        {
            // if not authenticated - then checking with database -
            // load.. is implemented and overriden in AppConfig
            UserDetails userDetails = this
                    .userDetailsService.loadUserByUsername(userEmail);

            // if user is valid, update securityContext and send request to DispatchServlet
            if (jwtService.isTokenValid(jwtToken, userDetails)){
                // update security context
                UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                //update Security Context Holder
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
                filterChain.doFilter(request, response);
    }
}
