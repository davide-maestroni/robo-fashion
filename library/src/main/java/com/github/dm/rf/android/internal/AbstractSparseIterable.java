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
import com.github.dm.rf.android.iterator.BooleanSparseIterable;
import com.github.dm.rf.android.iterator.ElementSparseIterable;
import com.github.dm.rf.android.iterator.IntSparseIterable;
import com.github.dm.rf.android.iterator.LongSparseIterable;
import com.github.dm.rf.android.iterator.SparseIterable;
import com.github.dm.rf.android.translator.Translator;
import com.github.dm.rf.android.utils.BinarySearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Abstract base implementation of a {@link SparseIterable}.
 * <p/>
 * This class implements the common logic shared among all the sparse iterable specializations,
 * delegating to subclasses the handling of low level data.
 * <p/>
 * Created by davide-maestroni on 3/10/14.
 *
 * @param <E> the element type.
 */
abstract class AbstractSparseIterable<E> implements SparseIterable<E> {

    private FilterBuilderImpl<SparseIterable<E>, E> mExclusionBuilder;

    private ArrayList<AdvancedFilter<E>> mFilters = new ArrayList<AdvancedFilter<E>>();

    private FilterBuilderImpl<SparseIterable<E>, E> mInclusionBuilder;

    public AbstractSparseIterable() {

    }

    AbstractSparseIterable(final AbstractSparseIterable<E> other) {

        mFilters.addAll(other.mFilters);
    }

    @Override
    public boolean any(final Condition<E> condition) {

        int count = 0;

        final SparseIterator<E> iterator = iterator();

        while (iterator.hasNext()) {

            if (condition.onNext(iterator.next(), count++, iterator.originalIndex())) {

                return true;
            }
        }

        return false;
    }

    @Override
    public FilterBuilder<? extends SparseIterable<E>, E> but() {

        if (mExclusionBuilder == null) {

            mExclusionBuilder = new FilterBuilderImpl<SparseIterable<E>, E>(this, false);
        }

        return mExclusionBuilder;
    }

    @Override
    public SparseIterable<E> but(final Filter<E> filter) {

        mFilters.add(Filters.inverse(Filters.advanced(filter)));

        return this;
    }

    @Override
    public boolean contains(final Object element) {

        return firstPositionOf(element) >= 0;
    }

    @Override
    public boolean containsAll(final Object... elements) {

        for (final Object element : elements) {

            if (firstPositionOf(element) < 0) {

                return false;
            }
        }

        return true;
    }

    @Override
    public boolean containsAll(final Iterable<?> elements) {

        for (final Object element : elements) {

            if (firstPositionOf(element) < 0) {

                return false;
            }
        }

        return true;
    }

    @Override
    public boolean containsAny(final Object... elements) {

        for (final Object element : elements) {

            if (firstPositionOf(element) >= 0) {

                return true;
            }
        }

        return false;
    }

    @Override
    public boolean containsAny(final Iterable<?> elements) {

        for (final Object element : elements) {

            if (firstPositionOf(element) >= 0) {

                return true;
            }
        }

        return false;
    }

    @Override
    public int countOf(final Object element) {

        int count = 0;

        if (element == null) {

            for (E e : this) {

                if (e == null) {

                    ++count;
                }
            }

        } else {

            for (E e : this) {

                if (element.equals(e)) {

                    ++count;
                }
            }
        }

        return count;
    }

    @Override
    public SparseIterable<E> doWhile(final Condition<E> condition) {

        int count = 0;

        final SparseIterator<E> iterator = iterator();

        while (iterator.hasNext()) {

            if (!condition.onNext(iterator.next(), count++, iterator.originalIndex())) {

                return this;
            }
        }

        return this;
    }

    @Override
    public boolean each(final Condition<E> condition) {

        int count = 0;

        final SparseIterator<E> iterator = iterator();

        while (iterator.hasNext()) {

            if (!condition.onNext(iterator.next(), count++, iterator.originalIndex())) {

                return false;
            }
        }

        return true;
    }

    @Override
    public int firstIndexOf(final Object element) {

        if (element == null) {

            return -1;
        }

        final SparseIterator<E> iterator = filteredIterator(false);

        while (iterator.hasNext()) {

            final E next = iterator.next();

            if (element.equals(next)) {

                return iterator.originalIndex();
            }
        }

        return -1;
    }

    @Override
    public int firstPositionOf(final Object element) {

        if (element == null) {

            return -1;
        }

        int i = 0;

        for (final E e : this) {

            if (element.equals(e)) {

                return i;
            }

            ++i;
        }

        return -1;
    }

    @Override
    public SparseIterable<E> forEach(final Action<E> action) {

        int count = 0;

        final SparseIterator<E> iterator = iterator();

        while (iterator.hasNext()) {

            action.onNext(iterator.next(), count++, iterator.originalIndex());
        }

        return this;
    }

    @Override
    public boolean isEqualTo(final Collection<?> collection) {

        int count = 0;

        for (final E element : this) {

            if (!collection.contains(element)) {

                return false;
            }

            ++count;
        }

        return (count == collection.size());
    }

    @Override
    public boolean isStrictlyEqualTo(final Iterable<?> iterable) {

        final Iterator<?> iterator = iterable.iterator();

        for (final E element : this) {

            if (!iterator.hasNext()) {

                return false;
            }

            if (element == null) {

                if (iterator.next() != null) {

                    return false;
                }

            } else if (!element.equals(iterator.next())) {

                return false;
            }
        }

        return true;
    }

    @Override
    public FilterBuilder<? extends SparseIterable<E>, E> only() {

        if (mInclusionBuilder == null) {

            mInclusionBuilder = new FilterBuilderImpl<SparseIterable<E>, E>(this, true);
        }

        return mInclusionBuilder;
    }

    @Override
    public SparseIterable<E> only(final Filter<E> filter) {

        mFilters.add(Filters.advanced(filter));

        return this;
    }

    @Override
    public SparseIterable<E> remove() {

        final SparseIterator<E> iterator = iterator();

        while (iterator.hasNext()) {

            iterator.next();
            iterator.remove();
        }

        clearFilters();

        return this;
    }

    @Override
    public SparseIterable<E> retain() {

        final SparseIterator<E> iterator = filteredIterator(false);

        final SnapshotFilter<E> filter = new SnapshotFilter<E>();

        filter.initialize(iterator);

        final FilteredIterator<E> filteredIterator =
                new FilteredIterator<E>(rawIterator(false), Filters.inverse(filter));

        while (filteredIterator.hasNext()) {

            filteredIterator.next();
            filteredIterator.remove();
        }

        clearFilters();

        return this;
    }

    @Override
    public SparseIterable<E> reverse() {

        mFilters.add(new SnapshotFilter<E>());

        return this;
    }

    @Override
    public BooleanSparseIterable toBooleans(final Translator<E, Boolean> translator) {

        return new BooleanTranslatedIterable<E>(copy(), translator);
    }

    @Override
    public IntSparseIterable toIntegers(final Translator<E, Integer> translator) {

        return new IntTranslatedIterable<E>(copy(), translator);
    }

    @Override
    public LongSparseIterable toLongs(final Translator<E, Long> translator) {

        return new LongTranslatedIterable<E>(copy(), translator);
    }

    @Override
    public SparseIterator<E> iterator() {

        return filteredIterator(false);
    }

    protected void clearFilters() {

        boolean isReverse = false;

        for (final AdvancedFilter<E> filter : mFilters) {

            if (filter instanceof SnapshotFilter) {

                isReverse = !isReverse;
            }
        }

        mFilters.clear();

        if (isReverse) {

            reverse();
        }
    }

    /**
     * Returns a copy of this instance.<br/>
     * The backing sparse collection is retained in the process.
     *
     * @return the copy instance.
     */
    protected abstract AbstractSparseIterable<E> copy();

    /**
     * Returns the base iterator on which to apply this instance filters.
     *
     * @param isReverse whether to loop in reverse order.
     * @return the iterator instance.
     */
    protected abstract SparseIterator<E> createIterator(boolean isReverse);

    protected SparseIterator<E> filteredIterator(final boolean isReverse) {

        SparseIterator<E> iterator = createIterator(isReverse);

        final ArrayList<AdvancedFilter<E>> filters = mFilters;

        if (filters.isEmpty()) {

            return iterator;
        }

        boolean reverse = isReverse;

        boolean skipFilter = true;

        for (final AdvancedFilter<E> filter : filters) {

            if (filter instanceof SnapshotFilter) {

                reverse = !reverse;

                if (!skipFilter) {

                    filter.initialize(iterator);
                }

                iterator = createIterator(reverse);

                iterator.reset();

            } else {

                filter.initialize(iterator);

                iterator.reset();

                skipFilter = false;
            }

            if (!skipFilter) {

                iterator = new FilteredIterator<E>(iterator, filter);
            }
        }

        return iterator;
    }

    /**
     * Returns the very basic iterator, without any filter applied, which loops through all the
     * elements of the backing sparse collection.
     *
     * @param isReverse whether to loop in reverse order.
     * @return the iterator instance.
     */
    protected abstract SparseIterator<E> rawIterator(boolean isReverse);

    <T> ElementSparseIterable<T> toElements(final Translator<E, T> translator) {

        return new ElementTranslatedIterable<E, T>(copy(), translator);
    }

    private static class BooleanTranslatedIterable<E> extends BooleanSparseIterableImpl {

        private final AbstractSparseIterable<E> mIterable;

        private final Translator<E, Boolean> mTranslator;

        public BooleanTranslatedIterable(final AbstractSparseIterable<E> iterable,
                final Translator<E, Boolean> translator) {

            mIterable = iterable;
            mTranslator = translator;
        }

        BooleanTranslatedIterable(final BooleanTranslatedIterable<E> other) {

            super(other);

            mIterable = other.mIterable;
            mTranslator = other.mTranslator;
        }

        @Override
        protected void clearFilters() {

            super.clearFilters();

            mIterable.clearFilters();
        }

        @Override
        protected BooleanTranslatedIterable<E> copy() {

            return new BooleanTranslatedIterable<E>(this);
        }

        @Override
        protected SparseIterator<Boolean> createIterator(final boolean isReverse) {

            final AbstractSparseIterable<E> iterable;

            if (isReverse) {

                iterable = mIterable.copy();
                iterable.reverse();

            } else {

                iterable = mIterable;
            }

            final SparseIterator<E> iterator = iterable.filteredIterator(false);

            return new TranslatedIterator<E, Boolean>(iterator, mTranslator);
        }

        @Override
        protected SparseIterator<Boolean> rawIterator(final boolean isReverse) {

            final SparseIterator<E> iterator = mIterable.rawIterator(isReverse);

            return new TranslatedIterator<E, Boolean>(iterator, mTranslator);
        }
    }

    private static class ElementTranslatedIterable<I, O> extends ElementSparseIterableImpl<O> {

        private final AbstractSparseIterable<I> mIterable;

        private final Translator<I, O> mTranslator;

        public ElementTranslatedIterable(final AbstractSparseIterable<I> iterable,
                final Translator<I, O> translator) {

            mIterable = iterable;
            mTranslator = translator;
        }

        ElementTranslatedIterable(final ElementTranslatedIterable<I, O> other) {

            super(other);

            mIterable = other.mIterable;
            mTranslator = other.mTranslator;
        }

        @Override
        protected ElementTranslatedIterable<I, O> copy() {

            return new ElementTranslatedIterable<I, O>(this);
        }

        @Override
        protected void clearFilters() {

            super.clearFilters();

            mIterable.clearFilters();
        }

        @Override
        protected SparseIterator<O> createIterator(final boolean isReverse) {

            final AbstractSparseIterable<I> iterable;

            if (isReverse) {

                iterable = mIterable.copy();
                iterable.reverse();

            } else {

                iterable = mIterable;
            }

            final SparseIterator<I> iterator = iterable.filteredIterator(false);

            return new TranslatedIterator<I, O>(iterator, mTranslator);
        }

        @Override
        protected SparseIterator<O> rawIterator(final boolean isReverse) {

            final SparseIterator<I> iterator = mIterable.rawIterator(isReverse);

            return new TranslatedIterator<I, O>(iterator, mTranslator);
        }
    }

    /**
     * Wrapper of a {@link SparseIterator} instance on which the specified filter is applied.
     *
     * @param <E> the element type.
     */
    private static class FilteredIterator<E> implements SparseIterator<E> {

        private final Filter<E> mFilter;

        private final SparseIterator<E> mIterator;

        private int mCount;

        private E mElement;

        private boolean mHasNext;

        public FilteredIterator(final SparseIterator<E> wrapped, final Filter<E> filter) {

            mIterator = wrapped;
            mFilter = filter;
        }

        @Override
        public boolean hasNext() {

            if (mHasNext) {

                return true;
            }

            final Filter<E> filter = mFilter;

            final SparseIterator<E> iterator = mIterator;

            while (iterator.hasNext()) {

                final E element = iterator.next();

                if (filter.matches(element, mCount++, iterator.originalIndex())) {

                    mHasNext = true;

                    mElement = element;

                    return true;
                }
            }

            return false;
        }

        @Override
        public int originalIndex() {

            return mIterator.originalIndex();
        }

        @Override
        public void reset() {

            mCount = 0;
            mHasNext = false;
            mElement = null;

            mIterator.reset();
        }

        @Override
        public E next() {

            if (!hasNext()) {

                throw new NoSuchElementException();
            }

            mHasNext = false;

            final E element = mElement;

            mElement = null;

            return element;
        }

        @Override
        public void remove() {

            mIterator.remove();
        }
    }

    private static class IntTranslatedIterable<E> extends IntSparseIterableImpl {

        private final AbstractSparseIterable<E> mIterable;

        private final Translator<E, Integer> mTranslator;

        public IntTranslatedIterable(final AbstractSparseIterable<E> iterable,
                final Translator<E, Integer> translator) {

            mIterable = iterable;
            mTranslator = translator;
        }

        IntTranslatedIterable(final IntTranslatedIterable<E> other) {

            super(other);

            mIterable = other.mIterable;
            mTranslator = other.mTranslator;
        }

        @Override
        protected IntTranslatedIterable<E> copy() {

            return new IntTranslatedIterable<E>(this);
        }

        @Override
        protected void clearFilters() {

            super.clearFilters();

            mIterable.clearFilters();
        }

        @Override
        protected SparseIterator<Integer> createIterator(final boolean isReverse) {

            final AbstractSparseIterable<E> iterable;

            if (isReverse) {

                iterable = mIterable.copy();
                iterable.reverse();

            } else {

                iterable = mIterable;
            }

            final SparseIterator<E> iterator = iterable.filteredIterator(false);

            return new TranslatedIterator<E, Integer>(iterator, mTranslator);
        }

        @Override
        protected SparseIterator<Integer> rawIterator(final boolean isReverse) {

            final SparseIterator<E> iterator = mIterable.rawIterator(isReverse);

            return new TranslatedIterator<E, Integer>(iterator, mTranslator);
        }
    }

    private static class LongTranslatedIterable<E> extends LongSparseIterableImpl {

        private final AbstractSparseIterable<E> mIterable;

        private final Translator<E, Long> mTranslator;

        public LongTranslatedIterable(final AbstractSparseIterable<E> iterable,
                final Translator<E, Long> translator) {

            mIterable = iterable;
            mTranslator = translator;
        }

        LongTranslatedIterable(final LongTranslatedIterable<E> other) {

            super(other);

            mIterable = other.mIterable;
            mTranslator = other.mTranslator;
        }

        @Override
        protected LongTranslatedIterable<E> copy() {

            return new LongTranslatedIterable<E>(this);
        }

        @Override
        protected void clearFilters() {

            super.clearFilters();

            mIterable.clearFilters();
        }

        @Override
        protected SparseIterator<Long> createIterator(final boolean isReverse) {

            final AbstractSparseIterable<E> iterable;

            if (isReverse) {

                iterable = mIterable.copy();
                iterable.reverse();

            } else {

                iterable = mIterable;
            }

            final SparseIterator<E> iterator = iterable.filteredIterator(false);

            return new TranslatedIterator<E, Long>(iterator, mTranslator);
        }

        @Override
        protected SparseIterator<Long> rawIterator(final boolean isReverse) {

            final SparseIterator<E> iterator = mIterable.rawIterator(isReverse);

            return new TranslatedIterator<E, Long>(iterator, mTranslator);
        }
    }

    /**
     * This class is used to create a snapshot of which elements would be iterated after applying
     * a set of filters.<br/>
     * By employing such snapshot of element indexes we can than reverse the order of the iteration
     * or the logic of the filters themselves.
     *
     * @param <E> the element type.
     */
    private static class SnapshotFilter<E> implements AdvancedFilter<E> {

        private int mCount;

        private int[] mPositions = new int[8];

        @Override
        public void initialize(final FilterIterator<E> iterator) {

            mCount = 0;

            while (iterator.hasNext()) {

                iterator.next();

                add(iterator.originalIndex());
            }

            Arrays.sort(mPositions, 0, mCount);
        }

        @Override
        public boolean matches(final E element, final int count, final int index) {

            return BinarySearch.contains(mPositions, mCount, index);
        }

        private void add(final int pos) {

            final int length = mPositions.length;

            if (mCount >= length) {

                final int[] newPositions = new int[length * 2];

                System.arraycopy(mPositions, 0, newPositions, 0, length);

                mPositions = newPositions;
            }

            mPositions[mCount++] = pos;
        }
    }

    private static class TranslatedIterator<I, O> implements SparseIterator<O> {

        private final SparseIterator<I> mIterator;

        private final Translator<I, O> mTranslator;

        public TranslatedIterator(final SparseIterator<I> iterator,
                final Translator<I, O> translator) {

            mIterator = iterator;
            mTranslator = translator;
        }

        @Override
        public int originalIndex() {

            return mIterator.originalIndex();
        }

        @Override
        public boolean hasNext() {

            return mIterator.hasNext();
        }

        @Override
        public O next() {

            return mTranslator.translate(mIterator.next());
        }

        @Override
        public void remove() {

            mIterator.remove();
        }

        @Override
        public void reset() {

            mIterator.reset();
        }
    }
}