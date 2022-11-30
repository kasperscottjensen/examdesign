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

import java.util.HashMap;
import java.util.Optional;
import java.util.Map;
import java.util.Set;

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
            User user = user_.get();
            profile.setUser(user);
            profileService.save(profile);

            return new ResponseEntity<>("Profile created: " + profile.getFullName(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Profile NOT created: " + profile.getFullName(), HttpStatus.BAD_REQUEST);
        }

    }
    @GetMapping("/find")
    public ResponseEntity<Map> findProfile(@RequestParam Long id) {
        if (profileService.findById(id).isPresent()) {
            Profile profile = profileService.findById(id).get();
            Map<String, String> map = new HashMap<>();
            map.put("profile found", profile.getFullName());
            return ResponseEntity.ok(map);
        }else {
            Map<String, String> map = new HashMap<>();
            map.put("profile not found", "try another");
            return ResponseEntity.ok(map);
        }
    }
    @PutMapping("/update")
    public ResponseEntity<String> updateProfile(@RequestBody Profile profile){
        Optional<Profile> profiletoCheck = profileService.findById(profile.getId());
        if(profiletoCheck.isPresent()){
            Profile profile1 = profiletoCheck.get();
            profile.setUser(profile1.getUser());
            profileService.save(profile);
            return new ResponseEntity<>("Profile updated", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Profile not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<Set<Profile>> findAllProfiles(){
        return new ResponseEntity<>(profileService.findAll(), HttpStatus.OK);
    }
}
