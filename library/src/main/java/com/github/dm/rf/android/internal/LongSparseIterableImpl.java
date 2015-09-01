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

import com.github.dm.rf.android.filter.Filter;
import com.github.dm.rf.android.filter.LongFilterBuilder;
import com.github.dm.rf.android.iterator.LongSparseIterable;

import java.util.Collection;

/**
 * Implementation of a {@link LongSparseIterable}.
 * <p/>
 * Created by davide-maestroni on 3/19/14.
 */
abstract class LongSparseIterableImpl extends ElementSparseIterableImpl<Long>
        implements LongSparseIterable {

    private LongFilterBuilderImpl mExclusionBuilder;

    private LongFilterBuilderImpl mInclusionBuilder;

    public LongSparseIterableImpl() {

    }

    LongSparseIterableImpl(final LongSparseIterableImpl other) {

        super(other);
    }

    @Override
    public LongFilterBuilder but() {

        if (mExclusionBuilder == null) {

            mExclusionBuilder = new LongFilterBuilderImpl(this, false);
        }

        return mExclusionBuilder;
    }

    @Override
    public LongSparseIterable but(final Filter<Long> filter) {

        super.but(filter);

        return this;
    }

    @Override
    public LongSparseIterable doWhile(final Condition<Long> condition) {

        super.doWhile(condition);

        return this;
    }

    @Override
    public LongSparseIterable forEach(final Action<Long> action) {

        super.forEach(action);

        return this;
    }

    @Override
    public LongFilterBuilder only() {

        if (mInclusionBuilder == null) {

            mInclusionBuilder = new LongFilterBuilderImpl(this, true);
        }

        return mInclusionBuilder;
    }

    @Override
    public LongSparseIterable only(final Filter<Long> filter) {

        super.only(filter);

        return this;
    }

    @Override
    public LongSparseIterable remove() {

        super.remove();

        return this;
    }

    @Override
    public LongSparseIterable retain() {

        super.retain();

        return this;
    }

    @Override
    public LongSparseIterable reverse() {

        super.reverse();

        return this;
    }

    @Override
    public LongSparseIterable fill(final Collection<? super Long> collection) {

        super.fill(collection);

        return this;
    }

    @Override
    public <T> LongSparseIterable fill(final T[] array) {

        super.fill(array);

        return this;
    }

    @Override
    public <T> LongSparseIterable fill(final T[] array, final int offset) {

        super.fill(array, offset);

        return this;
    }

    @Override
    public LongSparseIterable fill(final long[] array) {

        return fill(array, 0);
    }

    @Override
    public LongSparseIterable fill(final long[] array, final int offset) {

        int i = offset;

        for (final Long element : this) {

            array[i++] = (element == null) ? 0 : element;
        }

        return this;
    }

    @Override
    public long[] toArray() {

        int count = 0;

        final SparseIterator<Long> iterator = iterator();

        while (iterator().hasNext()) {

            iterator.next();

            ++count;
        }

        final long[] array = new long[count];

        fill(array);

        return array;
    }
}