package group.examdesign.repository;

import group.examdesign.model.Shift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

public interface IShiftRepo extends JpaRepository<Shift, Long> {

    @Query(value = "SELECT * FROM shifts s WHERE s.date = ?1", nativeQuery = true)
    List<Shift> findShiftsByDate(Date date);

    @Query(value = "SELECT * FROM shifts s WHERE s.date BETWEEN ?1 AND ?2", nativeQuery = true)
    List<Shift> findShiftsByDateInterval(Date dateStart, Date dateEnd);
}
