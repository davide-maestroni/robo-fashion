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
package com.bmd.android.collection.internal;

import com.bmd.android.collection.filter.Filter;
import com.bmd.android.collection.filter.IntFilterBuilder;
import com.bmd.android.collection.iterator.IntSparseIterable;

import java.util.Collection;

/**
 * Abstract implementation of a {@link com.bmd.android.collection.iterator.IntSparseIterable}.
 * <p/>
 * Created by davide on 3/19/14.
 */
abstract class IntSparseIterableImpl extends ElementSparseIterableImpl<Integer>
        implements IntSparseIterable {

    private IntFilterBuilderImpl mExclusionBuilder;

    private IntFilterBuilderImpl mInclusionBuilder;

    public IntSparseIterableImpl() {

    }

    IntSparseIterableImpl(final IntSparseIterableImpl other) {

        super(other);
    }

    @Override
    public IntFilterBuilder but() {

        if (mExclusionBuilder == null) {

            mExclusionBuilder = new IntFilterBuilderImpl(this, false);
        }

        return mExclusionBuilder;
    }

    @Override
    public IntSparseIterable but(final Filter<Integer> filter) {

        super.but(filter);

        return this;
    }

    @Override
    public IntSparseIterable doWhile(final Condition<Integer> condition) {

        super.doWhile(condition);

        return this;
    }

    @Override
    public IntSparseIterable forEach(final Action<Integer> action) {

        super.forEach(action);

        return this;
    }

    @Override
    public IntFilterBuilder only() {

        if (mInclusionBuilder == null) {

            mInclusionBuilder = new IntFilterBuilderImpl(this, true);
        }

        return mInclusionBuilder;
    }

    @Override
    public IntSparseIterable only(final Filter<Integer> filter) {

        super.only(filter);

        return this;
    }

    @Override
    public IntSparseIterable remove() {

        super.remove();

        return this;
    }

    @Override
    public IntSparseIterable retain() {

        super.retain();

        return this;
    }

    @Override
    public IntSparseIterable reverse() {

        super.reverse();

        return this;
    }

    @Override
    public IntSparseIterable fill(final Collection<? super Integer> collection) {

        super.fill(collection);

        return this;
    }

    @Override
    public <T> IntSparseIterable fill(final T[] array) {

        super.fill(array);

        return this;
    }

    @Override
    public <T> IntSparseIterable fill(final T[] array, final int offset) {

        super.fill(array, offset);

        return this;
    }

    @Override
    public IntSparseIterable fill(final int[] array) {

        return fill(array, 0);
    }

    @Override
    public IntSparseIterable fill(final int[] array, final int offset) {

        int i = offset;

        for (final Integer element : this) {

            array[i++] = (element == null) ? 0 : element;
        }

        return this;
    }

    @Override
    public int[] toArray() {

        int count = 0;

        for (final Integer i : this) {

            ++count;
        }

        final int[] array = new int[count];

        fill(array);

        return array;
    }
}