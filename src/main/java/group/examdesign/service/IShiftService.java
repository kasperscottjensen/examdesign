package group.examdesign.service;
import group.examdesign.model.Shift;


import java.sql.Date;
import java.util.List;

public interface IShiftService extends ICrudService<Shift, Long>{

    List<Shift> findShiftsByDate(Date date);
    List<Shift> findShiftsByDateInterval(Date dateStart, Date dateEnd);
    Shift createShift(String username, Shift shift);
}
