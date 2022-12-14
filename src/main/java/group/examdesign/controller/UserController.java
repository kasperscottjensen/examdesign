package group.examdesign.controller;
import group.examdesign.model.Profile;
import group.examdesign.model.User;
import group.examdesign.service.ProfileService;
import group.examdesign.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/admin/api/user")
public class UserController {

    private UserService userService;
    private ProfileService profileService;
    private ProfileController profileController;

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

    @PostMapping("/save")
    public ResponseEntity<User> save(@RequestBody User user) {
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            userService.save(user);
            Date sqlDate = java.sql.Date.valueOf(java.time.LocalDate.now());
            profileController.save(user.getUsername(),sqlDate);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<User> delete(@RequestBody User user) {
        Optional<User> optUser = userService.findById(user.getUsername());
        if (optUser.isPresent()) {
            optUser.get().setEnabled(false);
            userService.save(optUser.get());
            if (profileService.findById(optUser.get().getUsername()).isPresent()) {
                profileService.deleteById(user.getUsername());
            }
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