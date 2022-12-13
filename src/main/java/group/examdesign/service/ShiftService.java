package group.examdesign.service;

import group.examdesign.model.Shift;
import group.examdesign.model.User;
import group.examdesign.repository.IShiftRepo;
import group.examdesign.repository.IUserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.*;

@AllArgsConstructor
@Service
public class ShiftService implements IShiftService{

    private IShiftRepo shiftRepo;
    private IUserRepo userRepo;

    @Override
    public List<Shift> findAll() {
        return new ArrayList<>(shiftRepo.findAll());
    }

    @Override
    public void save(Shift shift) {
        //Hvis feltet efterlades tomt i frontend vil det blive set som en lukke vagt
        if(shift.getTimeEnd() == null){
            shift.setTimeEnd("LUK");
        }
        shiftRepo.save(shift);
    }

    @Override
    public void deleteById(Long aLong) {
        shiftRepo.deleteById(aLong);
    }

    @Override
    public Optional<Shift> findById(Long aLong) {
        return shiftRepo.findById(aLong);
    }

    public List<Shift> findShiftsByDate(Date date){
        return shiftRepo.findShiftsByDate(date);
    }

    public List<Shift> findShiftsByDateInterval(Date dateStart, Date dateEnd){
        return new ArrayList<>(shiftRepo.findShiftsByDateInterval(dateStart, dateEnd));
    }

    public Shift createShift(String username, Shift shift) {
        List<Shift> shifts = new ArrayList<>();
        User user1 = new User();

        Optional<User> byId = userRepo.findById(username);
        if (!byId.isPresent()) {
            return null;
        }
        User user = byId.get();

        //tie Author to Book
        shift.setUser(user);
        Shift shift1 = shiftRepo.save(shift);
        //tie Book to Author
        shifts.add(shift1);
        user1.setShifts(shifts);

        return shift1;

    }
}
