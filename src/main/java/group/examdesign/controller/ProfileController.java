package group.examdesign.controller;

import group.examdesign.model.Profile;
import group.examdesign.model.User;
import group.examdesign.service.IProfileService;
import group.examdesign.service.IUserService;
import group.examdesign.service.ProfileService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@AllArgsConstructor
@RestController
@RequestMapping("/user/api/profile")
public class ProfileController {
    private IProfileService profileService;
    private IUserService userService;

    @PostMapping("/save")
    public ResponseEntity<String> saveProfile (@RequestBody Profile profile) {
        Optional<User> user_ = userService.findById(profile.getUser().getUsername());
        if (user_.isPresent()) {
            profileService.save(profile);
            return new ResponseEntity<>("Profile created: " + profile.getFullName(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Profile NOT created: " + profile.getFullName(), HttpStatus.BAD_REQUEST);
        }

    }
    @GetMapping("/find")
    public ResponseEntity<Profile> findProfile(@RequestBody Profile profile) {
        if (profileService.findById(profile.getUsername()).isPresent()) {
            Profile profileToFind = profileService.findById(profile.getUsername()).get();
            return new ResponseEntity<>(profileToFind, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/update")
    public ResponseEntity<String> updateProfile(@RequestBody Profile profile){
        Optional<Profile> profiletoCheck = profileService.findById(profile.getUsername());
        if(profiletoCheck.isPresent()){
            profileService.save(profile);
            return new ResponseEntity<>("Profile updated", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Profile not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Profile>> findAllProfiles(){
        return new ResponseEntity<>(profileService.findAll(), HttpStatus.OK);
    }
}
