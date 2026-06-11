package com.artur114.bananalib.util.func;

import java.util.Objects;
import java.util.function.Predicate;

/**
 * Represents a predicate (boolean-valued function) of six arguments.  This is
 * the sixth-arity specialization of {@link Predicate}.
 *
 * <p>This is a <a href="package-summary.html">functional interface</a>
 * whose functional method is {@link #test(Object, Object, Object, Object, Object, Object)}.
 *
 * @param <A> the type of the first argument to the predicate
 * @param <B> the type of the second argument to the predicate
 * @param <C> the type of the third argument to the predicate
 * @param <D> the type of the fourth argument to the operation
 * @param <E> the type of the fifth argument to the operation
 * @param <F> the type of the sixth argument to the operation
 *
 * @see QuintPredicate
 * @author Artur114
 * @since 1.0
 */
@FunctionalInterface
public interface HexPredicate<A, B, C, D, E, F> {
    /**
     * Evaluates this predicate on the given arguments.
     *
     * @param a the first input argument
     * @param b the second input argument
     * @param c the third input argument
     * @param d the fourth input argument
     * @param e the fifth input argument
     * @param f the sixth input argument
     * @return {@code true} if the input arguments match the predicate,
     * otherwise {@code false}
     */
    boolean test(A a, B b, C c, D d, E e, F f);

    /**
     * Returns a composed predicate that represents a short-circuiting logical
     * AND of this predicate and another.  When evaluating the composed
     * predicate, if this predicate is {@code false}, then the {@code other}
     * predicate is not evaluated.
     *
     * <p>Any exceptions thrown during evaluation of either predicate are relayed
     * to the caller; if evaluation of this predicate throws an exception, the
     * {@code other} predicate will not be evaluated.
     *
     * @param other a predicate that will be logically-ANDed with this
     *              predicate
     * @return a composed predicate that represents the short-circuiting logical
     * AND of this predicate and the {@code other} predicate
     * @throws NullPointerException if other is null
     */
    default HexPredicate<A, B, C, D, E, F> and(HexPredicate<? super A, ? super B, ? super C, ? super D, ? super E, ? super F> other) {
        Objects.requireNonNull(other);
        return (A a, B b, C c, D d, E e, F f) -> this.test(a, b, c, d, e, f) && other.test(a, b, c, d, e, f);
    }

    /**
     * Returns a predicate that represents the logical negation of this
     * predicate.
     *
     * @return a predicate that represents the logical negation of this
     * predicate
     */
    default HexPredicate<A, B, C, D, E, F> negate() {
        return (A a, B b, C c, D d, E e, F f) -> !this.test(a, b, c, d, e, f);
    }

    /**
     * Returns a composed predicate that represents a short-circuiting logical
     * OR of this predicate and another.  When evaluating the composed
     * predicate, if this predicate is {@code true}, then the {@code other}
     * predicate is not evaluated.
     *
     * <p>Any exceptions thrown during evaluation of either predicate are relayed
     * to the caller; if evaluation of this predicate throws an exception, the
     * {@code other} predicate will not be evaluated.
     *
     * @param other a predicate that will be logically-ORed with this
     *              predicate
     * @return a composed predicate that represents the short-circuiting logical
     * OR of this predicate and the {@code other} predicate
     * @throws NullPointerException if other is null
     */
    default HexPredicate<A, B, C, D, E, F> or(HexPredicate<? super A, ? super B, ? super C, ? super D, ? super E, ? super F> other) {
        Objects.requireNonNull(other);
        return (A a, B b, C c, D d, E e, F f) -> this.test(a, b, c, d, e, f) || other.test(a, b, c, d, e, f);
    }
}
