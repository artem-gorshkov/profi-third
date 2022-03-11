package gorshkov.profi.software.util;

import javax.persistence.EntityNotFoundException;
import java.util.function.Supplier;

public class ExceptionUtil {
    public static Supplier<EntityNotFoundException> createEntityNotFoundException(int id) {
        return () -> new EntityNotFoundException(String.valueOf(id));
    }
}
