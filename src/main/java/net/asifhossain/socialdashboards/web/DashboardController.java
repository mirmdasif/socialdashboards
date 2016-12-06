package net.asifhossain.socialdashboards.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author asif.hossain
 * @since 12/5/16.
 */
@Controller
public class DashboardController {

    private final String FACEBOOK_VIEW_PAGE = "dashboard/facebook";

    @Autowired
    private Facebook facebook;

    @Autowired
    private ConnectionRepository connectionRepository;

    @RequestMapping(value = {"/facebook", "/"})
    public String showFacebookDashBoard(Model model) {
        if (connectionRepository.findPrimaryConnection(Facebook.class) ==  null) {
            return "redirect:/connect/facebook";
        }

        User user = facebook.fetchObject("me", User.class, "name");
        System.out.println(user);
        model.addAttribute("profile", user);
        model.addAttribute("feed", facebook.feedOperations().getFeed());

        return FACEBOOK_VIEW_PAGE;
    }
}
