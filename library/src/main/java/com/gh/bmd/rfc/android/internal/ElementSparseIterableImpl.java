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

import com.gh.bmd.rfc.android.filter.Filter;
import com.gh.bmd.rfc.android.filter.FilterBuilder;
import com.gh.bmd.rfc.android.iterator.ElementSparseIterable;
import com.gh.bmd.rfc.android.translator.Translator;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Abstract implementation of a {@link com.gh.bmd.rfc.android.iterator.ElementSparseIterable}.
 * <p/>
 * Created by davide on 3/19/14.
 *
 * @param <E> the element type.
 */
abstract class ElementSparseIterableImpl<E> extends AbstractSparseIterable<E>
        implements ElementSparseIterable<E> {

    private FilterBuilderImpl<ElementSparseIterable<E>, E> mExclusionBuilder;

    private FilterBuilderImpl<ElementSparseIterable<E>, E> mInclusionBuilder;

    public ElementSparseIterableImpl() {

    }

    ElementSparseIterableImpl(final ElementSparseIterableImpl<E> other) {

        super(other);
    }

    @Override
    public FilterBuilder<? extends ElementSparseIterable<E>, E> but() {

        if (mExclusionBuilder == null) {

            mExclusionBuilder = new FilterBuilderImpl<ElementSparseIterable<E>, E>(this, false);
        }

        return mExclusionBuilder;
    }

    @Override
    public ElementSparseIterable<E> but(final Filter<E> filter) {

        super.but(filter);

        return this;
    }

    @Override
    public ElementSparseIterable<E> doWhile(final Condition<E> condition) {

        super.doWhile(condition);

        return this;
    }

    @Override
    public ElementSparseIterable<E> forEach(final Action<E> action) {

        super.forEach(action);

        return this;
    }

    @Override
    public FilterBuilder<? extends ElementSparseIterable<E>, E> only() {

        if (mInclusionBuilder == null) {

            mInclusionBuilder = new FilterBuilderImpl<ElementSparseIterable<E>, E>(this, true);
        }

        return mInclusionBuilder;
    }

    @Override
    public ElementSparseIterable<E> only(final Filter<E> filter) {

        super.only(filter);

        return this;
    }

    @Override
    public ElementSparseIterable<E> remove() {

        super.remove();

        return this;
    }

    @Override
    public ElementSparseIterable<E> retain() {

        super.retain();

        return this;
    }

    @Override
    public ElementSparseIterable<E> reverse() {

        super.reverse();

        return this;
    }

    @Override
    public ElementSparseIterable<E> fill(final Collection<? super E> collection) {

        for (final E element : this) {

            collection.add(element);
        }

        return this;
    }

    @Override
    public <T> ElementSparseIterable<E> fill(final T[] array) {

        return fill(array, 0);
    }

    @Override
    public <T> ElementSparseIterable<E> fill(final T[] array, final int offset) {

        int i = 0;

        for (final E element : this) {

            //noinspection unchecked
            array[i++] = (T) element;
        }

        return this;
    }

    @Override
    public <T> T[] toArray(final Class<T> type) {

        final ArrayList<E> list = toList();

        //noinspection unchecked,SuspiciousToArrayCall
        return list.toArray((T[]) Array.newInstance(type, list.size()));
    }

    @Override
    public ArrayList<E> toList() {

        final ArrayList<E> list = new ArrayList<E>();

        fill(list);

        return list;
    }

    @Override
    public <T> ElementSparseIterable<T> translate(final Translator<E, T> translator) {

        return toElements(translator);
    }
}