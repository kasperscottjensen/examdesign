package group.examdesign.repository;

import group.examdesign.model.Authority;
import group.examdesign.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IProfileRepo extends JpaRepository<Profile,String> {
}
