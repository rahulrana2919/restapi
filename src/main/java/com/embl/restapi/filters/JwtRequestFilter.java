/*
 * 2020.
 * Author: Rahul Rana
 */

package com.embl.restapi.filters;

import com.embl.restapi.services.impl.MyUserDetailsService;
import com.embl.restapi.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter
{
    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER_ = "Bearer ";

    @Autowired private MyUserDetailsService userDetailsService;

    @Autowired private JwtUtil jwtUtil;

    @Override protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException
    {
        final String authorizationHeader = request.getHeader(AUTHORIZATION);

        String username = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader
                .startsWith(BEARER_))
        {
            jwt = authorizationHeader.substring(7);
            username = jwtUtil.extractUsername(jwt);
        }

        if (username != null
                && SecurityContextHolder.getContext().getAuthentication()
                == null)
        {
            UserDetails userDetails = this.userDetailsService
                    .loadUserByUsername(username);

            if (jwtUtil.validateToken(jwt, userDetails))
            {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                        = new UsernamePasswordAuthenticationToken(userDetails,
                        null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(
                        new WebAuthenticationDetailsSource()
                                .buildDetails(request));
                SecurityContextHolder.getContext()
                        .setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        // Causes the next filter in the chain to be invoked, or if the calling
        // filter is the last filter in the chain, causes the resource at the
        // end of the chain to be invoked.
        filterChain.doFilter(request, response);
    }
}
