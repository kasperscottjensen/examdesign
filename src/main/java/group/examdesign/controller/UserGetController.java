package group.examdesign.controller;
import group.examdesign.model.User;
import group.examdesign.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/user/api/user")
public class UserGetController {

    private UserService userService;

    @GetMapping("/findall")
    public ResponseEntity<List<User>> findAll() {
        List<User> list = userService.findAll();
        if (!(list == null)) {
            return new ResponseEntity<>(list, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/find/{username}")
    public ResponseEntity<User> find(@PathVariable String username) {
        Optional<User> optUser = userService.findById(username);
        if (optUser.isPresent()) {
            return new ResponseEntity<>(optUser.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
