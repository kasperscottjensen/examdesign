package group.examdesign.security;
import group.examdesign.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@AllArgsConstructor
@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse res, Authentication auth) throws IOException {
        String userAuth = userService.findById(auth.getName()).get().getAuthority().getAuthority();
        String redirect;
        if (userAuth.equalsIgnoreCase("ROLE_ADMIN")) {
            redirect = "/admin/adminindex";
        } else if (userAuth.equalsIgnoreCase("ROLE_USER")) {
            redirect = "/user/userindex";
        } else {
            redirect = "/";
        }
        res.sendRedirect(redirect);
    }

}