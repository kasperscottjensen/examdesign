package group.examdesign.controller;
import group.examdesign.model.User;
import group.examdesign.model.Wishes;
import group.examdesign.service.UserService;
import group.examdesign.service.WishesService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/user/api/wishes")
public class WishesController {

    private WishesService wishesService;
    private UserService userService;

    @PostMapping("/save")
    public ResponseEntity<Wishes> save(@RequestBody Wishes wishes) {
        if (userService.findById(wishes.getProfile().getUsername()).isPresent()) {
            if(wishesService.findByDateAndProfile_Username(wishes.getDate(), wishes.getProfile().getUsername()).isPresent()){
                Wishes wishtoCheck = wishesService.findByDateAndProfile_Username(wishes.getDate(), wishes.getProfile().getUsername()).get();
                wishes.setId(wishtoCheck.getId());
                wishesService.save(wishes);
                return new ResponseEntity<>(wishes, HttpStatus.OK);
            }else{
                wishesService.save(wishes);
                return new ResponseEntity<>(wishes, HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Wishes> find(@PathVariable("id") Long id) {
        if (wishesService.findById(id).isPresent()) {
            Wishes wishToFind = wishesService.findById(id).get();
            return new ResponseEntity<>(wishToFind, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Wishes> update(@RequestBody Wishes wishes){
        if(wishesService.findById(wishes.getId()).isPresent()){
            wishesService.save(wishes);
            return new ResponseEntity<>(wishes, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Wishes>> findAll(){
        return new ResponseEntity<>(wishesService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/findAllByUser/{username}")
    public ResponseEntity<List<Wishes>> findAllByUser(@PathVariable String username){
        return new ResponseEntity<>(wishesService.findAllWishByProfile_Username(username), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Wishes> delete(@PathVariable Long id) {
        Optional<Wishes> wishes1 = wishesService.findById(id);
        if (wishes1.isPresent()) {
            wishesService.deleteById(id);
            return new ResponseEntity<>(wishes1.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteAllByUser/{username}")
    public ResponseEntity<Wishes> deleteAllByUser(@PathVariable("username") String username) {
        List<Wishes> wishes1 = wishesService.findAllWishByProfile_Username(username);
        if (wishes1 != null) {
            for (Wishes wishes2 : wishes1) {
                wishesService.deleteById(wishes2.getId());
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/findByUserAndDate/{date}/{username}")
    public ResponseEntity<Wishes> findByDateAndProfile_Username(@PathVariable("date")Date date, @PathVariable("username") String username) {
        if (wishesService.findByDateAndProfile_Username(date, username).isPresent()) {
            Wishes wishToFind = wishesService.findByDateAndProfile_Username(date, username).get();
            return new ResponseEntity<>(wishToFind, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
