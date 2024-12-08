package M06;

import java.util.List;

public interface DAO <T>{
void add(T item);
List<T> getAll();
List<Athlete> getAll(int sportId);
}
