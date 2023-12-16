package com.greenGekko.servicies.user_service;

import static com.greenGekko.database.tables.Users.USERS;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
@Slf4j
@AllArgsConstructor
public class AuthSuccessHandler implements AuthenticationSuccessHandler {

    private final RedirectStrategy redirectStrategy;
    private final DSLContext dslContext;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        handle(request, response, authentication);
        clearAuthenticationAttributes(request);
    }

    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        String targetUrl = determineTargetUrl(authentication);

        if (response.isCommitted()) {
            log.debug(
                    "Response has already been committed. Unable to redirect to "
                            + targetUrl);
            return;
        }
         redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    protected String determineTargetUrl(final Authentication authentication) {
        authentication.getDetails();
        log.info("authentication.getDetails() is: {}", authentication.getDetails());
        authentication.getPrincipal();
        log.info("authentication.getPrincipal()) is: {}", authentication.getPrincipal());
        Object o = authentication.getPrincipal();
        User user = (User) o;
        String userEmail = user.getUsername();

        return "lk/" + dslContext.selectFrom(USERS)
                .where(USERS.EMAIL.equalIgnoreCase(userEmail))
                .fetchOptional(USERS.UUID)
                .orElse("");
    }

    protected void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }

}
