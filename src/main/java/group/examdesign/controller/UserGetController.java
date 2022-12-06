package group.examdesign.controller;
import group.examdesign.model.User;
import group.examdesign.service.ProfileService;
import group.examdesign.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/user/api/user")
public class UserGetController {

    private UserService userService;
    private ProfileService profileService;

    @GetMapping("/findall")
    public ResponseEntity<List<User>> findAll() {
        List<User> list = userService.findAll();
        if (!(list == null)) {
            return new ResponseEntity<>(list, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/find")
    public ResponseEntity<User> find(@RequestBody User user) {
        Optional<User> optUser = userService.findById(user.getUsername());
        if (optUser.isPresent()) {
            return new ResponseEntity<>(optUser.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}