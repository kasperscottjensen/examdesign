package group.examdesign.service;

import group.examdesign.model.Wishes;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface IWishesService extends ICrudService<Wishes, Long> {
    List<Wishes> findAllWishByUser_Username(String username);
}
