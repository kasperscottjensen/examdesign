package group.examdesign.controller;

import group.examdesign.model.Newsfeed;
import group.examdesign.service.NewsfeedService;
import group.examdesign.service.ProfileService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/user/api/newsfeed")
public class NewsfeedController {
    private NewsfeedService newsfeedService;
    private ProfileService profileService;

    @PostMapping("/save")
    public ResponseEntity<Newsfeed> save(@RequestBody Newsfeed newsfeed) {
        if (profileService.findById(newsfeed.getProfile().getUsername()).isPresent()) {
            newsfeedService.save(newsfeed);
            return new ResponseEntity<>(newsfeed, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
    @GetMapping("/find/{id}")
    public ResponseEntity<Newsfeed> find(@PathVariable("id") Long id) {
        if (newsfeedService.findById(id).isPresent()) {
            Newsfeed newsfeedToFind = newsfeedService.findById(id).get();
            return new ResponseEntity<>(newsfeedToFind, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Newsfeed> delete(@PathVariable("id") Long id) {
        Optional<Newsfeed> newsfeed1 = newsfeedService.findById(id);
        if (newsfeed1.isPresent()) {
            newsfeedService.deleteById(id);
            return new ResponseEntity<>(newsfeed1.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/deleteAllByUser/{username}")
    public ResponseEntity<Newsfeed> deleteAllByUser(@PathVariable String username) {
        List<Newsfeed> newsfeed1 = newsfeedService.findAllNewsfeedByProfile_Username(username);
        if (newsfeed1 != null) {
            for (Newsfeed newsfeed2 : newsfeed1) {
                newsfeedService.deleteById(newsfeed2.getId());
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/findAllByUser/{username}")
    public ResponseEntity<List<Newsfeed>> findAllByUser(@PathVariable String username){
        return new ResponseEntity<>(newsfeedService.findAllNewsfeedByProfile_Username(username), HttpStatus.OK);
    }
}
