package group.examdesign.repository;
import group.examdesign.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAuthorityRepo extends JpaRepository<Authority,String> {

}