package group.examdesign.service;
import group.examdesign.model.Authority;
import group.examdesign.model.User;
import group.examdesign.repository.IUserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@Service
public class UserService implements IUserService {

    private AuthorityService authService;
    private IUserRepo userRepo;

    @Override
    public Set<User> findAll() {
        Set<User> set = new HashSet<>(userRepo.findAll());
        for(User user : set) {
            if (authService.findById(user.getUsername()).isPresent()) {
                user.setAuthority(authService.findById(user.getUsername()).get());
            } else {
                user.setAuthority(new Authority());
            }
        }
        return set;
    }

    @Override
    public void save(User user) {
        userRepo.save(user);
    }

    @Override
    public void deleteById(String username) {
        userRepo.deleteById(username);
    }

    @Override
    public Optional<User> findById(String username) {
        return userRepo.findById(username);
    }

}