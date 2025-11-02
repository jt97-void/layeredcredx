package com.fintech.layeredcredx.common.utils;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.ComparableExpression;
import com.querydsl.core.types.dsl.StringExpression;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public final class QueryDslUtils {

    public static <T> void addIfPresent(BooleanBuilder builder, T value, Consumer<T> consumer) {
        Optional.ofNullable(value).ifPresent(consumer);
    }

    public static void addIfNotBlank(BooleanBuilder builder, String value, Consumer<String> consumer) {
        Optional.ofNullable(value)
                .filter(v -> !v.isBlank())
                .ifPresent(consumer);
    }

    public static <T extends Comparable<?>> void addDateRange(
            BooleanBuilder builder,
            T from,
            T to,
            Supplier<? extends ComparableExpression<T>> path
    ) {
        Optional.ofNullable(from).ifPresent(f -> builder.and(path.get().goe(f)));
        Optional.ofNullable(to).ifPresent(t -> builder.and(path.get().loe(t)));
    }

    public static void addLikeIgnoreCase(BooleanBuilder builder, String value, Function<String, StringExpression> pathFn) {
        Optional.ofNullable(value)
                .filter(v -> !v.isBlank())
                .ifPresent(v -> builder.and(pathFn.apply(v).containsIgnoreCase(v)));
    }

}
