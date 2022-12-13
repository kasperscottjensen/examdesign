package group.examdesign.controller;
import group.examdesign.model.Profile;
import group.examdesign.model.User;
import group.examdesign.service.ProfileService;
import group.examdesign.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@AllArgsConstructor
@RestController
@RequestMapping("/user/api/profiles")
public class ProfileController {

    private ProfileService profileService;
    private UserService userService;

    @PostMapping("/save")
    public ResponseEntity<Profile> save(@RequestBody Profile profile) {
        Optional<User> optUser = userService.findById(profile.getUsername());
        if (optUser.isPresent()) {
            profileService.save(profile);
            return new ResponseEntity<>(profile, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
    @GetMapping("/find/{username}")
    public ResponseEntity<Profile> find(@PathVariable("username") String username) {
        if (profileService.findById(username).isPresent()) {
            Profile profileToFind = profileService.findById(username).get();
            return new ResponseEntity<>(profileToFind, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/update")
    public ResponseEntity<Profile> update(@RequestBody Profile profile) {
        Optional<Profile> optProfile = profileService.findById(profile.getUsername());
        if(optProfile.isPresent()){
            profileService.save(profile);
            return new ResponseEntity<>(profile, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Profile>> findAll() {
        return new ResponseEntity<>(profileService.findAll(), HttpStatus.OK);
    }

}
