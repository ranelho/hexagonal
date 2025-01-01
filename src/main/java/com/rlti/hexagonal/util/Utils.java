package com.rlti.hexagonal.util;

import java.util.Optional;
import java.util.function.LongFunction;
import java.util.function.Supplier;

public class Utils {

    private Utils() {
        throw new UnsupportedOperationException("Util class cannot be instantiated");
    }

    public static <T> T findEntityById(Long id, LongFunction<Optional<T>> repositoryFindById, Supplier<RuntimeException> exceptionSupplier) {
        return id != null ?
                repositoryFindById.apply(id)
                        .orElseThrow(exceptionSupplier)
                : null;
    }
}
