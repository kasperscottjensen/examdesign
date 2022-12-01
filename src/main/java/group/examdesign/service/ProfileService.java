package group.examdesign.service;

import group.examdesign.model.Authority;
import group.examdesign.model.Profile;
import group.examdesign.model.User;
import group.examdesign.repository.IProfileRepo;
import group.examdesign.repository.IUserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@AllArgsConstructor
@Service
public class ProfileService implements IProfileService{

    private IProfileRepo profileRepo;
    //private UserService userService;
    @Override
    public List<Profile> findAll() {
        return new ArrayList<>(profileRepo.findAll());
    }

    @Override
    public void save(Profile profile) {
        profileRepo.save(profile);
    }

    @Override
    public void deleteById(Long id) {
        profileRepo.deleteById(id);
    }

    @Override
    public Optional<Profile> findById(Long id) {
        return profileRepo.findById(id);
    }

    public void deleteByUser_Username(String username){
        profileRepo.deleteByUser_Username(username);
    }
    public Optional<Profile> findByUsername(String username){
        return profileRepo.findByUsername(username);
    }

}
