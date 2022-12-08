package group.examdesign.service;
import group.examdesign.model.Wishes;
import group.examdesign.repository.IWishesRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.*;

@AllArgsConstructor
@Service
public class WishesService implements IWishesService {

    private IWishesRepo wishesRepo;

    @Override
    public List<Wishes> findAll() {
        return new ArrayList<>(wishesRepo.findAll(Sort.by(Sort.Direction.ASC, "date")));
    }

    @Override
    public void save(Wishes object) {
        wishesRepo.save(object);
    }

    @Override
    public void deleteById(Long aLong) {
        wishesRepo.deleteById(aLong);
    }

    @Override
    public Optional<Wishes> findById(Long aLong) {
        return wishesRepo.findById(aLong);
    }

    public List<Wishes> findAllWishByProfile_Username(String username){
        return new ArrayList<>(wishesRepo.findAllWishByProfile_Username(username, Sort.by(Sort.Direction.ASC, "date")));
    }
    public Optional<Wishes> findByDateAndProfile_Username(Date date, String profile_username){
        return wishesRepo.findByDateAndProfile_Username(date, profile_username);
    }

}
