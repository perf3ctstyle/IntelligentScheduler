package by.bsuir.intelligentscheduler.service;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractService<T> {

    protected void checkPaginationParameters(int page, int pageSize) {
        if (page <= 0 || pageSize <= 0) {
            throw new IllegalArgumentException();
        }
    }

    protected List<T> getPaginated(List<T> entities, int limit, int offset) {
        return entities
                .stream()
                .skip(offset)
                .limit(limit)
                .collect(Collectors.toList());
    }

    public abstract T getById(long id);
    public abstract void create(T entity);
    public abstract void delete(long id);
}
