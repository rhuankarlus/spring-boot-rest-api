package br.com.rk.util;

import br.com.rk.entities.ProjectEntity;
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

    private static final int MAX_PAGE_SIZE = 5;

    public static Pageable buildSimplePageable() {
        return buildPageable(1, MAX_PAGE_SIZE, null);
    }

    public static Pageable buildPageable(final int page, final int size, final Sort sortObject) {
        if (sortObject == null) {
            return PageRequest.of(page, size);
        }

        return PageRequest.of(page, size, sortObject);
    }

    public static <E extends ProjectEntity> Page<E> buildSimplePage(final Supplier<E> randomObjectCreator) {
        return buildPage(randomObjectCreator, buildSimplePageable(), MAX_PAGE_SIZE);
    }

    public static <T> Page<T> buildPage(final Supplier<T> randomObjectCreator, final Pageable pageable, int size) {
        final List<T> content = new ArrayList<>();
        IntStream.rangeClosed(1, size).forEach(value -> content.add(randomObjectCreator.get()));
        return new PageImpl<>(content, pageable, size);
    }

}
