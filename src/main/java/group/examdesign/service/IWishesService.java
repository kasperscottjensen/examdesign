package group.examdesign.service;
import group.examdesign.model.Wishes;
import java.util.List;

public interface IWishesService extends ICrudService<Wishes,Long> {

    List<Wishes> findAllWishByProfile_Username(String username);

}
