package com.robj.ratingmanager;

import java.util.NoSuchElementException;

import io.reactivex.annotations.Nullable;

/**
 * Created by Rob J on 21/09/17.
 */

public final class Optional<T> {

    private final T optional;

    Optional(@Nullable T optional) {
        this.optional = optional;
    }

    public final boolean isEmpty() {
        return this.optional == null;
    }

    public final T get() {
        if (optional == null)
            throw new NoSuchElementException("Item was null..");
        return optional;
    }
}
