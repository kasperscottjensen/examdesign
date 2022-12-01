package group.examdesign.service;
import java.util.List;
import java.util.Optional;

public interface ICrudService<T,ID> {

    List<T> findAll();
    void save(T arg);
    void deleteById(ID id);
    Optional<T> findById(ID id);

}