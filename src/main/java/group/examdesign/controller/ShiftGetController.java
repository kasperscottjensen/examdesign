package group.examdesign.controller;

import group.examdesign.model.Shift;
import group.examdesign.service.IShiftService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/user/api/shift")
public class ShiftGetController {


    private IShiftService shiftService;

    @GetMapping("/findall")
    public ResponseEntity<List<ShiftWithUser>> findAll(){
        List<Shift> list = shiftService.findAll();
        if (list != null) {
            List<ShiftWithUser> responseList = new ArrayList<>();
            for (Shift shift : list) {
                ShiftWithUser shiftwithuser = new ShiftWithUser(shift);
                responseList.add(shiftwithuser);
            }
            return new ResponseEntity<>(responseList, HttpStatus.OK);
        }else{
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

    @GetMapping("/findbydate/{date}")
    public ResponseEntity<List<Shift>> findShiftsByDate(@PathVariable("date") Date date){
        List<Shift> list = shiftService.findShiftsByDate(date);
        if (list != null) {
            return new ResponseEntity<>(list, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}







