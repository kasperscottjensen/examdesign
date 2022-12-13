package group.examdesign.service;
import group.examdesign.model.Wishes;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface IWishesService extends ICrudService<Wishes,Long> {

    List<Wishes> findAllWishByProfile_Username(String username);
    Optional<Wishes> findByDateAndProfile_Username(Date date, String profile_username);

}
