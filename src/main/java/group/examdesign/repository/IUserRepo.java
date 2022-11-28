package group.examdesign.repository;
import group.examdesign.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepo extends JpaRepository<User,String> {

}