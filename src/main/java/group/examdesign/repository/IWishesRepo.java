package group.examdesign.repository;

import group.examdesign.model.Wishes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IWishesRepo extends JpaRepository<Wishes,Long> {
}
