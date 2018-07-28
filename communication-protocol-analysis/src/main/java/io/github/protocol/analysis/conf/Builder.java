package io.github.protocol.analysis.conf;

/**
 * Interface representing a builder. Builders are objects that are used to
 * construct other objects.
 *
 * Create by guoxin on 2018/7/10
 */
@FunctionalInterface
public interface Builder<T> {
    /**
     * Builds and returns the object.
     */
    public T build();
}
