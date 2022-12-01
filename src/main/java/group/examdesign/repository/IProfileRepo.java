package group.examdesign.repository;
import group.examdesign.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProfileRepo extends JpaRepository<Profile,String> {

}
