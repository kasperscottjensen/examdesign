package group.examdesign.service;
import group.examdesign.model.Authority;
import group.examdesign.model.User;
import group.examdesign.repository.IUserRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.*;

@AllArgsConstructor
@Service
public class UserService implements IUserService {

    private AuthorityService authService;
    private IUserRepo userRepo;
    private PasswordEncoder encoder;

    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<>(userRepo.findAll(Sort.by(Sort.Direction.ASC, "username")));
        for(User user : list) {
            if (authService.findById(user.getUsername()).isPresent()) {
                user.setAuthority(authService.findById(user.getUsername()).get());
            } else {
                user.setAuthority(new Authority());
            }
        }
        return list;
    }

    @Override
    public void save(User user) {
        if (user.getPassword() != null) {
            user.setPassword(encoder.encode(user.getPassword()));
        }
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