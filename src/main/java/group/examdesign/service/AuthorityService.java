package group.examdesign.service;
import group.examdesign.model.Authority;
import group.examdesign.repository.IAuthorityRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@AllArgsConstructor
@Service
public class AuthorityService implements IAuthorityService {

    private IAuthorityRepo authRepo;

    @Override
    public List<Authority> findAll() {
        return new ArrayList<>(authRepo.findAll());
    }

    @Override
    public void save(Authority auth) {
        authRepo.save(auth);
    }

    @Override
    public void deleteById(String username) {
        authRepo.deleteById(username);
    }

    @Override
    public Optional<Authority> findById(String username) {
        return authRepo.findById(username);
    }

}