package group.examdesign.service;
import java.util.Optional;
import java.util.Set;

public interface ICrudService<T,ID> {

    Set<T> findAll();
    void save(T arg);
    void deleteById(ID id);
    Optional<T> findById(ID id);

}