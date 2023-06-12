package pro.vaidas.notebookserver.web.controller.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class CsfrHeaderFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
        System.out.println(token.getToken() + " - FILTER");
        System.out.println("JSESSIONID=" + (request.getSession().getId() + " - SESSION ID"));
        response.setHeader("CSRF-TOKEN-VALUE", token.getToken());

        filterChain.doFilter(request, response);
    }
}
