package group.examdesign.controller;
import group.examdesign.model.User;
import group.examdesign.service.IProfileService;
import group.examdesign.service.IUserService;
import group.examdesign.service.ProfileService;
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

    private IUserService userService;
    private IProfileService profileService;
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

    @GetMapping("/find")
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

    //Skal vi ikke lige gennemgå denne sammen torsdag?
    @DeleteMapping("/delete")
    public ResponseEntity<User> delete(@RequestBody User user) {
        Optional<User> user1 = userService.findById(user.getUsername());
        if (user1.isPresent()) {
            if(profileService.findByUser_Username(user1.get().getUsername()).isPresent()){
                profileService.deleteByUser_Username(user.getUsername());
            }
                user1.get().setEnabled(false);
                //userService.deleteById(user.getUsername());
                return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<User> update(@RequestBody User user) {
        if (userService.findById(user.getUsername()).isPresent()) {
            userService.save(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}