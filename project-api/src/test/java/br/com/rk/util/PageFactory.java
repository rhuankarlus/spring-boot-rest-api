package br.com.rk.util;

import org.springframework.data.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.IntStream;

/**
 * Simple factory to build test helpful pageable
 *
 * @author Rhuan Karlus
 * @since 09/04/2019
 */
public class PageFactory {

    public static Pageable buildPageable(final int size) {
        return buildPageable(1, size, null);
    }

    public static Pageable buildPageable(final int page, final int size) {
        return buildPageable(page, size, null);
    }

    public static Pageable buildPageable(final int page, final int size, final Sort sortObject) {
        if (sortObject == null) {
            return PageRequest.of(page, size);
        }

        return PageRequest.of(page, size, sortObject);
    }

    public static <T> Page<T> buildPage(int size, final Supplier<T> randomObjectCreator) {
        return buildPage(buildPageable(size), size, randomObjectCreator);
    }

    public static <T> Page<T> buildPage(final Pageable pageable, int size, final Supplier<T> randomObjectCreator) {
        final List<T> content = new ArrayList<>();
        IntStream.rangeClosed(1, size).forEach(value -> content.add(randomObjectCreator.get()));
        return new PageImpl<>(content, pageable, size);
    }

}
