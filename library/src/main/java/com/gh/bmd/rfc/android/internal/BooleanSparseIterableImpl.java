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
package com.gh.bmd.rfc.android.internal;

import com.gh.bmd.rfc.android.filter.BooleanFilterBuilder;
import com.gh.bmd.rfc.android.filter.Filter;
import com.gh.bmd.rfc.android.iterator.BooleanSparseIterable;

import java.util.Collection;

/**
 * Abstract implementation of a {@link com.gh.bmd.rfc.android.iterator.BooleanSparseIterable}.
 * <p/>
 * Created by davide on 3/19/14.
 */
abstract class BooleanSparseIterableImpl extends ElementSparseIterableImpl<Boolean>
        implements BooleanSparseIterable {

    private BooleanFilterBuilderImpl mExclusionBuilder;

    private BooleanFilterBuilderImpl mInclusionBuilder;

    public BooleanSparseIterableImpl() {

    }

    BooleanSparseIterableImpl(final BooleanSparseIterableImpl other) {

        super(other);
    }

    @Override
    public BooleanFilterBuilder but() {

        if (mExclusionBuilder == null) {

            mExclusionBuilder = new BooleanFilterBuilderImpl(this, false);
        }

        return mExclusionBuilder;
    }

    @Override
    public BooleanSparseIterable but(final Filter<Boolean> filter) {

        super.but(filter);

        return this;
    }

    @Override
    public BooleanSparseIterable doWhile(final Condition<Boolean> condition) {

        super.doWhile(condition);

        return this;
    }

    @Override
    public BooleanSparseIterable forEach(final Action<Boolean> action) {

        super.forEach(action);

        return this;
    }

    @Override
    public BooleanFilterBuilder only() {

        if (mInclusionBuilder == null) {

            mInclusionBuilder = new BooleanFilterBuilderImpl(this, true);
        }

        return mInclusionBuilder;
    }

    @Override
    public BooleanSparseIterable only(final Filter<Boolean> filter) {

        super.only(filter);

        return this;
    }

    @Override
    public BooleanSparseIterable remove() {

        super.remove();

        return this;
    }

    @Override
    public BooleanSparseIterable retain() {

        super.retain();

        return this;
    }

    @Override
    public BooleanSparseIterable reverse() {

        super.reverse();

        return this;
    }

    @Override
    public BooleanSparseIterable fill(final Collection<? super Boolean> collection) {

        super.fill(collection);

        return this;
    }

    @Override
    public <T> BooleanSparseIterable fill(final T[] array) {

        super.fill(array);

        return this;
    }

    @Override
    public <T> BooleanSparseIterable fill(final T[] array, final int offset) {

        super.fill(array, offset);

        return this;
    }

    @Override
    public BooleanSparseIterable fill(final boolean[] array) {

        return fill(array, 0);
    }

    @Override
    public BooleanSparseIterable fill(final boolean[] array, final int offset) {

        int i = offset;

        for (final Boolean element : this) {

            array[i++] = (element == null) ? false : element;
        }

        return this;
    }

    @Override
    public boolean[] toArray() {

        int count = 0;

        final SparseIterator<Boolean> iterator = iterator();

        while (iterator().hasNext()) {

            iterator.next();

            ++count;
        }

        final boolean[] array = new boolean[count];

        fill(array);

        return array;
    }
}