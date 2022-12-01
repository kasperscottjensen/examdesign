package group.examdesign.service;
import group.examdesign.model.Profile;
import group.examdesign.repository.IProfileRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.*;

@AllArgsConstructor
@Service
public class ProfileService implements IProfileService{

    private IProfileRepo profileRepo;

    @Override
    public List<Profile> findAll() {
        return new ArrayList<>(profileRepo.findAll(Sort.by(Sort.Direction.ASC, "full_name")));
    }

    @Override
    public void save(Profile profile) {
        profileRepo.save(profile);
    }

    @Override
    public void deleteById(String id) {
        profileRepo.deleteById(id);
    }

    @Override
    public Optional<Profile> findById(String id) {
        return profileRepo.findById(id);
    }

}
