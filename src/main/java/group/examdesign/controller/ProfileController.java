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

@AllArgsConstructor
@RestController
@RequestMapping("/admin/api/user")
public class ProfileController {
    private IProfileService profileService;
    private IUserService userService;

    @PostMapping("/save/profile")
    public ResponseEntity<String> saveProfile (@RequestParam String username, @RequestBody Profile profile) {
        Optional<User> user_ = userService.findById(username);
        if (user_.isPresent()) {
            User user = user_.get();
            profile.setUser(user);
            profileService.save(profile);

            return new ResponseEntity<>("Event created: " + profile.getFullName(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Event NOT created: " + profile.getFullName(), HttpStatus.BAD_REQUEST);
        }

    }
    @GetMapping("/get/profile")
    public ResponseEntity<Map> getProfile(@RequestParam Long id) {
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
}
