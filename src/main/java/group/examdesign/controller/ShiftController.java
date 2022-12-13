package group.examdesign.controller;

import group.examdesign.model.Shift;
import group.examdesign.model.User;
import group.examdesign.service.IShiftService;
import group.examdesign.service.IUserService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/admin/api/shift")
public class ShiftController {

    private IShiftService shiftService;
    private IUserService userService;

    @GetMapping("/findall")
    public ResponseEntity<List<Shift>> findAll(){
        List<Shift> list = shiftService.findAll();
        if (!(list == null)) {
            return new ResponseEntity<>(list, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/find")
    public ResponseEntity<Shift> find(@RequestBody Shift shift) {
        Optional<Shift> optShift = shiftService.findById(shift.getId());
        if (optShift.isPresent()) {
            return new ResponseEntity<>(optShift.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<Shift> save(@RequestBody Shift shift) {
        if (shift == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            shiftService.save(shift);
            return new ResponseEntity<>(shift, HttpStatus.CREATED);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Shift> delete(@RequestBody Shift shift) {
        Optional<Shift> optShift = shiftService.findById(shift.getId());
        if (optShift.isPresent()) {
            shiftService.deleteById(shift.getId());
            return new ResponseEntity<>(optShift.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Shift> update(@RequestBody Shift shift) {
        if (shiftService.findById(shift.getId()).isPresent()) {
            shiftService.save(shift);
            return new ResponseEntity<>(shift, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/findbydate/{date}")
    public ResponseEntity<List<Shift>> findShiftsByDate(@PathVariable("date") Date date){
        List<Shift> list = shiftService.findShiftsByDate(date);
        if (list != null) {
            return new ResponseEntity<>(list, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/findbydateinterval/{dateStart}/{dateEnd}")
    public ResponseEntity<List<ShiftWithUser>> findShiftsByDateInterval(@PathVariable("dateStart") Date dateStart, @PathVariable("dateEnd") Date dateEnd){
        List<Shift> list = shiftService.findShiftsByDateInterval(dateStart, dateEnd);
        if (list != null) {
            List<ShiftWithUser> responseList = new ArrayList<>();
            for(Shift shift : list){
                ShiftWithUser shiftwithuser = new ShiftWithUser(shift);
                responseList.add(shiftwithuser);
            }
            return new ResponseEntity<>(responseList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

@NoArgsConstructor
@Getter
@Setter
class ShiftWithUser {
    private Shift shift;
    private String username;

    ShiftWithUser(Shift shift){
        this.shift = shift;
        this.username = shift.getUser().getUsername();
    }
}
