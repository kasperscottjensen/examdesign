package group.examdesign.controller;
import group.examdesign.model.User;
import group.examdesign.model.Wishes;
import group.examdesign.service.UserService;
import group.examdesign.service.WishesService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
        if (userService.findById(wishes.getUser().getUsername()).isPresent()) {
            wishesService.save(wishes);
            return new ResponseEntity<>(wishes, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/find")
    public ResponseEntity<Wishes> find(@RequestBody Wishes wishes) {
        if (wishesService.findById(wishes.getId()).isPresent()) {
            Wishes wishToFind = wishesService.findById(wishes.getId()).get();
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
        return new ResponseEntity<>(wishesService.findAllWishByUser_Username(username), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Wishes> delete(@RequestBody Wishes wishes) {
        Optional<Wishes> wishes1 = wishesService.findById(wishes.getId());
        if (wishes1.isPresent()) {
            wishesService.deleteById(wishes.getId());
            return new ResponseEntity<>(wishes1.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteAllByUser")
    public ResponseEntity<Wishes> deleteAllByUser(@RequestBody Wishes wishes) {
        List<Wishes> wishes1 = wishesService.findAllWishByUser_Username(wishes.getUser().getUsername());
        if (wishes1 != null) {
            for (Wishes wishes2 : wishes1) {
                wishesService.deleteById(wishes2.getId());
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
