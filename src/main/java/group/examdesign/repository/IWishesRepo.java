package group.examdesign.repository;
import group.examdesign.model.Wishes;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface IWishesRepo extends JpaRepository<Wishes,Long> {

    List<Wishes> findAllWishByProfile_Username(String username, Sort sort);
    Optional<Wishes> findByDateAndProfile_Username(Date date, String profile_username);

}
