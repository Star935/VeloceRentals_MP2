package Services;

import java.util.List;

public interface Service<T>{
    List<T> list();
    T byId(int id);
    void save(T t);
    void delete(int id);
    void update(T t);
}
