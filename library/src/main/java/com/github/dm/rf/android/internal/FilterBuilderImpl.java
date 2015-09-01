/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.dm.rf.android.internal;

import com.github.dm.rf.android.filter.AdvancedFilter;
import com.github.dm.rf.android.filter.Filter;
import com.github.dm.rf.android.filter.FilterBuilder;
import com.github.dm.rf.android.filter.Filters;
import com.github.dm.rf.android.iterator.SparseIterable;

import java.util.Arrays;
import java.util.Collection;

/**
 * Base implementation of a {@link FilterBuilder}.
 * <p/>
 * Created by davide-maestroni on 3/16/14.
 *
 * @param <T> the iterable type.
 * @param <E> the element type.
 */
class FilterBuilderImpl<T extends SparseIterable<E>, E> implements FilterBuilder<T, E> {

    private final boolean mIsInclusive;

    private final T mIterable;

    public FilterBuilderImpl(final T iterable, final boolean isInclusive) {

        mIsInclusive = isInclusive;
        mIterable = iterable;
    }

    @Override
    public T element(final Object element) {

        final Filter<E> filter = Filters.matching(element);

        return build(mIsInclusive, filter);
    }

    @Override
    public T elements(final Object... elements) {

        final Filter<E> filter = Filters.containedIn(Arrays.asList(elements));

        return build(mIsInclusive, filter);
    }

    @Override
    public T elements(final Collection<?> elements) {

        final Filter<E> filter = Filters.containedIn(elements);

        return build(mIsInclusive, filter);
    }

    @Override
    public T elements(final Iterable<?> elements) {

        final Filter<E> filter = Filters.containedIn(elements);

        return build(mIsInclusive, filter);
    }

    @Override
    public T first(final int count) {

        final Filter<E> filter = Filters.first(count);

        return build(mIsInclusive, filter);
    }

    @Override
    public T from(final int index) {

        final Filter<E> filter = Filters.first(index);

        return build(!mIsInclusive, filter);
    }

    @Override
    public T indexes(final int... indexes) {

        final Filter<E> filter = Filters.indexes(indexes);

        return build(mIsInclusive, filter);
    }

    @Override
    public T indexes(final Collection<Integer> indexes) {

        final Filter<E> filter = Filters.indexes(indexes);

        return build(mIsInclusive, filter);
    }

    @Override
    public T indexes(final Iterable<Integer> indexes) {

        final Filter<E> filter = Filters.indexes(indexes);

        return build(mIsInclusive, filter);
    }

    @Override
    public T last(final int count) {

        final Filter<E> filter = Filters.last(count);

        return build(mIsInclusive, filter);
    }

    @Override
    public T matching(final Filter<E> filter) {

        return build(mIsInclusive, filter);
    }

    @Override
    public T matching(final AdvancedFilter<E> filter) {

        return build(mIsInclusive, filter);
    }

    @Override
    public T to(final int index) {

        return first(index + 1);
    }

    private T build(final boolean isInclusive, final Filter<E> filter) {

        //noinspection unchecked
        return (T) (isInclusive ? mIterable.only(filter) : mIterable.but(filter));
    }
}