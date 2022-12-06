package group.examdesign.repository;

import group.examdesign.model.Newsfeed;
import group.examdesign.model.Wishes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface INewsfeedRepo extends JpaRepository<Newsfeed,Long> {
    List<Newsfeed> findAllNewsfeedByProfile_Username(String username);
}
