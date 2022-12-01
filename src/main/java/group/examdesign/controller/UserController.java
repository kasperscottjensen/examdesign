package group.examdesign.controller;
import group.examdesign.model.User;
import group.examdesign.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@RestController
@RequestMapping("/admin/api/user")
public class UserController {

    private UserService userService;
    private PasswordEncoder encoder;

    @GetMapping("/findall")
    public ResponseEntity<Set<User>> findAll() {
        Set<User> set = userService.findAll();
        if (!(set == null)) {
            return new ResponseEntity<>(set, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/find")
    public ResponseEntity<User> find(@RequestBody User user) {
        Optional<User> optUser = userService.findById(user.getUsername());
        if (optUser.isPresent()) {
            return new ResponseEntity<>(optUser.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<User> save(@RequestBody User user) {
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            user.setPassword(encoder.encode(user.getPassword()));
            userService.save(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<User> delete(@RequestBody User user) {
        if (userService.findById(user.getUsername()).isPresent()) {
            userService.deleteById(user.getUsername());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<User> update(@RequestBody User user) {
        System.out.println("HELLO FROM UPDATE");
        if (userService.findById(user.getUsername()).isPresent()) {
            System.out.println("HELLO");
            userService.save(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/findById/{username}")
    public ResponseEntity<User> findById(@PathVariable("username") String username) {
        if(userService.findById(username).isPresent()) {
            return new ResponseEntity<>(userService.findById(username).get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

}