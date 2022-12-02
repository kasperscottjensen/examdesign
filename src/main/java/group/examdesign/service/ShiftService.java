package group.examdesign.service;

import group.examdesign.model.Shift;
import group.examdesign.repository.IShiftRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ShiftService implements IShiftService{

    private IShiftRepo shiftRepo;

    @Override
    public List<Shift> findAll() {
        return new ArrayList<>(shiftRepo.findAll());
    }

    @Override
    public void save(Shift shift) {
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
        return  shiftRepo.findShiftsByDateInterval(dateStart, dateEnd);
    }
}
