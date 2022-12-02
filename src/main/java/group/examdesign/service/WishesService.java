package group.examdesign.service;
import group.examdesign.model.Wishes;
import group.examdesign.repository.IWishesRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
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

    public List<Wishes> findAllWishByUser_Username(String username){
        return new ArrayList<>(wishesRepo.findAllWishByUser_Username(username, Sort.by(Sort.Direction.ASC, "date")));
    }
}
