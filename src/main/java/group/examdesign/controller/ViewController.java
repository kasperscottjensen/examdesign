package group.examdesign.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/")
    public String index() {
        return "/auth/login";
    }

    @GetMapping("/auth/login")
    public String login() {
        return "/auth/login";
    }

    @GetMapping("/auth/accessdenied")
    public String accessdenied() {
        return "/auth/accessdenied";
    }

    @GetMapping("/auth/logout")
    public String logout() {
        return "/auth/logout";
    }

    @GetMapping("/auth/logoutsuccess")
    public String logoutsuccess() {
        return "/auth/logoutsuccess";
    }

    @GetMapping("/auth/authdenied")
    public String authdenied() {
        return "/auth/authdenied";
    }

    @GetMapping("/admin/adminindex")
    public String adminindex() {
        return "/admin/adminindex";
    }

    @GetMapping("/admin/allusers")
    public String allusers() {
        return "/admin/allusers";
    }

    @GetMapping("/admin/createuser")
    public String createuser() {
        return "/admin/createuser";
    }

    @GetMapping("/user/userindex")
    public String userindex() {
        return "/user/userindex";
    }
    @GetMapping("/user/calenderforwishesuser")
    public String userWishesCalender() {
        return "/user/calenderforwishesuser";
    }

    @GetMapping("/admin/shiftadministration")
    public String shiftadministration() {
        return "admin/shiftadministration";
    }

    @GetMapping("/user/shiftoverview")
    public String shiftOverview() {
        return "user/shiftoverview";
    }

}