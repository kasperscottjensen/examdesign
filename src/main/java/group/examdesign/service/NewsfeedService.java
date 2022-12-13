package group.examdesign.service;

import group.examdesign.model.Newsfeed;
import group.examdesign.model.Wishes;
import group.examdesign.repository.INewsfeedRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class NewsfeedService implements INewsfeedService {
    private INewsfeedRepo newsfeedRepo;
    @Override
    public List<Newsfeed> findAll() {
        return new ArrayList<>(newsfeedRepo.findAll(Sort.by(Sort.Direction.ASC, "date")));
    }

    @Override
    public void save(Newsfeed arg) {
        newsfeedRepo.save(arg);
    }

    @Override
    public void deleteById(Long aLong) {
        newsfeedRepo.deleteById(aLong);
    }

    @Override
    public Optional<Newsfeed> findById(Long aLong) {
        return newsfeedRepo.findById(aLong);
    }
    public List<Newsfeed> findAllNewsfeedByProfile_Username(String username){
        return new ArrayList<>(newsfeedRepo.findAllNewsfeedByProfile_Username(username));
    }
}
