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

import android.annotation.SuppressLint;
import android.os.Parcelable;
import android.support.v4.util.SparseArrayCompat;

import com.gh.bmd.rfc.android.entry.IntSparseObjectEntry;
import com.gh.bmd.rfc.android.entry.ParcelableIntSparseObjectEntry;
import com.gh.bmd.rfc.android.entry.SparseArrayEntry;
import com.gh.bmd.rfc.android.filter.Filter;
import com.gh.bmd.rfc.android.filter.SparseArrayCompatFilterBuilder;
import com.gh.bmd.rfc.android.iterator.ElementSparseIterable;
import com.gh.bmd.rfc.android.iterator.IntSparseIterable;
import com.gh.bmd.rfc.android.iterator.SparseArrayCompatIterable;
import com.gh.bmd.rfc.android.translator.FullIntTranslator;
import com.gh.bmd.rfc.android.translator.FullTranslator;
import com.gh.bmd.rfc.android.translator.IntTranslator;
import com.gh.bmd.rfc.android.translator.Translator;
import com.gh.bmd.rfc.android.translator.Translators;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Implementation of a {@link com.gh.bmd.rfc.android.iterator.SparseArrayCompatIterable}.
 * <p/>
 * Created by davide on 3/12/14.
 *
 * @param <V> the entry value type.
 */
class SparseArrayCompatIterableImpl<V> extends AbstractSparseIterable<SparseArrayEntry<V>>
        implements SparseArrayCompatIterable<V> {

    private final SparseArrayCompat<V> mArray;

    private SparseArrayCompatFilterBuilderImpl<V> mExclusionBuilder;

    private SparseArrayCompatFilterBuilderImpl<V> mInclusionBuilder;

    private Translator<SparseArrayEntry<V>, Integer> mKeyTranslator;

    private Translator<SparseArrayEntry<V>, V> mValueTranslator;

    public SparseArrayCompatIterableImpl(final SparseArrayCompat<V> array) {

        mArray = array;
    }

    SparseArrayCompatIterableImpl(final SparseArrayCompatIterableImpl<V> other) {

        super(other);

        mArray = other.mArray;
    }

    @Override
    public SparseArrayCompatIterable<V> appendTo(final SparseArrayCompat<V> other) {

        for (final SparseArrayEntry<V> entry : this) {

            other.append(entry.getKey(), entry.getValue());
        }

        return this;
    }

    @Override
    public boolean containsAllKeys(final int... keys) {

        for (final int key : keys) {

            if (positionOfKey(key) < 0) {

                return false;
            }
        }

        return true;
    }

    @Override
    public boolean containsAllKeys(final Iterable<Integer> keys) {

        for (final int key : keys) {

            if (positionOfKey(key) < 0) {

                return false;
            }
        }

        return true;
    }

    @Override
    public boolean containsAllValues(final Object... values) {

        for (final Object value : values) {

            if (firstPositionOfValue(value) < 0) {

                return false;
            }
        }

        return true;
    }

    @Override
    public boolean containsAllValues(final Iterable<?> values) {

        for (final Object value : values) {

            if (firstPositionOfValue(value) < 0) {

                return false;
            }
        }

        return true;
    }

    @Override
    public boolean containsAnyKey(final int... keys) {

        for (final int key : keys) {

            if (positionOfKey(key) >= 0) {

                return true;
            }
        }

        return false;
    }

    @Override
    public boolean containsAnyKey(final Iterable<Integer> keys) {

        for (final int key : keys) {

            if (positionOfKey(key) >= 0) {

                return true;
            }
        }

        return false;
    }

    @Override
    public boolean containsAnyValue(final Object... values) {

        for (final Object value : values) {

            if (firstPositionOfValue(value) >= 0) {

                return true;
            }
        }

        return false;
    }

    @Override
    public boolean containsAnyValue(final Iterable<?> values) {

        for (final Object value : values) {

            if (firstPositionOfValue(value) >= 0) {

                return true;
            }
        }

        return false;
    }

    @Override
    public boolean containsKey(final int key) {

        return positionOfKey(key) >= 0;
    }

    @Override
    public boolean containsValue(final Object value) {

        return firstPositionOfValue(value) >= 0;
    }

    @Override
    public SparseArrayCompatIterable<V> fill(final Map<? super Integer, ? super V> map) {

        for (final SparseArrayEntry<V> entry : this) {

            map.put(entry.getKey(), entry.getValue());
        }

        return this;
    }

    @Override
    public SparseArrayCompatIterable<V> fillImmutable(
            final Collection<? super IntSparseObjectEntry<V>> collection) {

        for (final SparseArrayEntry<V> entry : this) {

            collection.add(entry.toImmutable());
        }

        return this;
    }

    @Override
    public <T> SparseArrayCompatIterable<V> fillImmutable(final T[] array) {

        return fillImmutable(array, 0);
    }

    @Override
    public <T> SparseArrayCompatIterable<V> fillImmutable(final T[] array, final int offset) {

        int i = offset;

        for (final SparseArrayEntry<V> entry : this) {

            //noinspection unchecked
            array[i++] = (T) entry.toImmutable();
        }

        return this;
    }

    @Override
    public SparseArrayCompatIterable<V> fillParcelable(
            final Collection<? super ParcelableIntSparseObjectEntry<V>> collection) {

        for (final SparseArrayEntry<V> entry : this) {

            collection.add(entry.toParcelable());
        }

        return this;
    }

    @Override
    public <T extends Parcelable> SparseArrayCompatIterable<V> fillParcelable(final T[] array) {

        return fillParcelable(array, 0);
    }

    @Override
    public <T extends Parcelable> SparseArrayCompatIterable<V> fillParcelable(final T[] array,
            final int offset) {

        int i = offset;

        for (final SparseArrayEntry<V> entry : this) {

            //noinspection unchecked
            array[i++] = (T) entry.toParcelable();
        }

        return this;
    }

    @Override
    public int firstIndexOfValue(final Object value) {

        if (value == null) {

            for (final SparseArrayEntry<V> entry : this) {

                if (entry.getValue() == null) {

                    return entry.getIndex();
                }
            }

        } else {

            for (final SparseArrayEntry<V> entry : this) {

                if (value.equals(entry.getValue())) {

                    return entry.getIndex();
                }
            }
        }

        return -1;
    }

    @Override
    public int firstPositionOfValue(final Object value) {

        int i = 0;

        if (value == null) {

            for (final SparseArrayEntry<V> entry : this) {

                if (entry.getValue() == null) {

                    return i;
                }

                ++i;
            }

        } else {

            for (final SparseArrayEntry<V> entry : this) {

                if (value.equals(entry.getValue())) {

                    return i;
                }

                ++i;
            }
        }

        return -1;
    }

    @Override
    public int indexOfKey(final int key) {

        for (final SparseArrayEntry<V> entry : this) {

            if (entry.getKey() == key) {

                return entry.getIndex();
            }
        }

        return -1;
    }

    @Override
    public boolean isEqualTo(final SparseArrayCompat<?> array) {

        int count = 0;

        for (final SparseArrayEntry<V> entry : this) {

            final Object value = array.get(entry.getKey());

            if (value == null) {

                if (entry.getValue() != null) {

                    return false;
                }

            } else if (!value.equals(entry.getValue())) {

                return false;
            }

            ++count;
        }

        return (count == array.size());
    }

    @Override
    public boolean isEqualTo(final Map<?, ?> map) {

        int count = 0;

        for (final SparseArrayEntry<V> entry : this) {

            final Object value = map.get(entry.getKey());

            if (value == null) {

                if (entry.getValue() != null) {

                    return false;
                }

            } else if (!value.equals(entry.getValue())) {

                return false;
            }

            ++count;
        }

        return (count == map.size());
    }

    @Override
    public IntSparseIterable keys() {

        if (mKeyTranslator == null) {

            mKeyTranslator = new Translator<SparseArrayEntry<V>, Integer>() {

                @Override
                public Integer translate(final SparseArrayEntry<V> element) {

                    return element.getKey();
                }
            };
        }

        return toIntegers(mKeyTranslator);
    }

    @Override
    public int positionOfKey(final int key) {

        int i = 0;

        for (final SparseArrayEntry<V> entry : this) {

            if (entry.getKey() == key) {

                return i;
            }

            ++i;
        }

        return -1;
    }

    @Override
    public SparseArrayCompatIterable<V> putInto(final SparseArrayCompat<V> other) {

        for (final SparseArrayEntry<V> entry : this) {

            other.put(entry.getKey(), entry.getValue());
        }

        return this;
    }

    @Override
    public SparseArrayCompatIterable<V> replaceValues(final Translator<V, V> translator) {

        final SparseArrayCompat<V> array = mArray;

        for (final SparseArrayEntry<V> entry : this) {

            array.append(entry.getKey(), translator.translate(entry.getValue()));
        }

        return this;
    }

    @Override
    public <T> T[] toImmutableArray(final Class<T> type) {

        final ArrayList<IntSparseObjectEntry<V>> list = toImmutableList();

        //noinspection unchecked,SuspiciousToArrayCall
        return list.toArray((T[]) Array.newInstance(type, list.size()));
    }

    @Override
    public ArrayList<IntSparseObjectEntry<V>> toImmutableList() {

        final ArrayList<IntSparseObjectEntry<V>> list = new ArrayList<IntSparseObjectEntry<V>>();

        fillImmutable(list);

        return list;
    }

    @Override
    public Map<Integer, V> toMap() {

        @SuppressLint("UseSparseArrays")
        final HashMap<Integer, V> map = new HashMap<Integer, V>();

        fill(map);

        return map;
    }

    @Override
    public <T extends Parcelable> T[] toParcelableArray(final Class<T> type) {

        final ArrayList<ParcelableIntSparseObjectEntry<V>> list = toParcelableList();

        //noinspection unchecked,SuspiciousToArrayCall
        return list.toArray((T[]) Array.newInstance(type, list.size()));
    }

    @Override
    public ArrayList<ParcelableIntSparseObjectEntry<V>> toParcelableList() {

        final ArrayList<ParcelableIntSparseObjectEntry<V>> list =
                new ArrayList<ParcelableIntSparseObjectEntry<V>>();

        fillParcelable(list);

        return list;
    }

    @Override
    public SortedMap<Integer, V> toSortedMap() {

        final TreeMap<Integer, V> map = new TreeMap<Integer, V>();

        fill(map);

        return map;
    }

    @Override
    public SparseArrayCompat<V> toSparseArray() {

        final SparseArrayCompat<V> array = new SparseArrayCompat<V>();

        for (final SparseArrayEntry<V> entry : this) {

            array.append(entry.getKey(), entry.getValue());
        }

        return array;
    }

    @Override
    public <T> SparseArrayCompatIterable<T> translate(final IntTranslator keyTranslator,
            final Translator<V, T> valueTranslator) {

        return new TranslatedSparseArrayCompatIterableImpl<T, V>(this,
                                                                 Translators.full(keyTranslator),
                                                                 Translators.full(valueTranslator));
    }

    @Override
    public SparseArrayCompatIterable<V> translateKeys(final IntTranslator keyTranslator) {

        final FullTranslator<V, V> valueTranslator = Translators.identity();

        return translate(keyTranslator, valueTranslator);
    }

    @Override
    public <T> SparseArrayCompatIterable<T> translateValues(final Translator<V, T> translator) {

        return translate(Translators.intIdentity(), translator);
    }

    @Override
    public ElementSparseIterable<V> values() {

        if (mValueTranslator == null) {

            mValueTranslator = new Translator<SparseArrayEntry<V>, V>() {

                @Override
                public V translate(final SparseArrayEntry<V> element) {

                    return element.getValue();
                }
            };
        }

        return toElements(mValueTranslator);
    }

    @Override
    public SparseArrayCompatFilterBuilder<V> but() {

        if (mExclusionBuilder == null) {

            mExclusionBuilder = new SparseArrayCompatFilterBuilderImpl<V>(this, false);
        }

        return mExclusionBuilder;
    }

    @Override
    public SparseArrayCompatIterable<V> but(final Filter<SparseArrayEntry<V>> filter) {

        super.but(filter);

        return this;
    }

    @Override
    public SparseArrayCompatIterable<V> doWhile(final Condition<SparseArrayEntry<V>> condition) {

        super.doWhile(condition);

        return this;
    }

    @Override
    public SparseArrayCompatIterable<V> forEach(final Action<SparseArrayEntry<V>> action) {

        super.forEach(action);

        return this;
    }

    @Override
    public SparseArrayCompatFilterBuilder<V> only() {

        if (mInclusionBuilder == null) {

            mInclusionBuilder = new SparseArrayCompatFilterBuilderImpl<V>(this, true);
        }

        return mInclusionBuilder;
    }

    @Override
    public SparseArrayCompatIterable<V> only(final Filter<SparseArrayEntry<V>> filter) {

        super.only(filter);

        return this;
    }

    @Override
    public SparseArrayCompatIterable<V> remove() {

        super.remove();

        return this;
    }

    @Override
    public SparseArrayCompatIterable<V> retain() {

        super.retain();

        return this;
    }

    @Override
    public SparseArrayCompatIterable<V> reverse() {

        super.reverse();

        return this;
    }

    private static class TranslatedSparseArrayCompatIterableImpl<T, V>
            extends SparseArrayCompatIterableImpl<T> {

        private final SparseArrayCompatIterableImpl<V> mIterable;

        private final FullIntTranslator mKeyTranslator;

        private final FullTranslator<V, T> mValueTranslator;

        public TranslatedSparseArrayCompatIterableImpl(
                final SparseArrayCompatIterableImpl<V> wrapped,
                final FullIntTranslator keyTranslator, final FullTranslator<V, T> valueTranslator) {

            super((SparseArrayCompat<T>) null);

            mIterable = wrapped;
            mKeyTranslator = keyTranslator;
            mValueTranslator = valueTranslator;
        }

        private TranslatedSparseArrayCompatIterableImpl(
                final TranslatedSparseArrayCompatIterableImpl<T, V> other) {

            super(other);

            mIterable = other.mIterable;
            mKeyTranslator = other.mKeyTranslator;
            mValueTranslator = other.mValueTranslator;
        }

        @Override
        protected TranslatedSparseArrayCompatIterableImpl<T, V> copy() {

            return new TranslatedSparseArrayCompatIterableImpl<T, V>(this);
        }

        @Override
        protected SparseIterator<SparseArrayEntry<T>> createIterator(final boolean isReverse) {

            final SparseArrayCompatIterableImpl<V> iterable;

            if (isReverse) {

                iterable = mIterable.copy();
                iterable.reverse();

            } else {

                iterable = mIterable;
            }

            final SparseIterator<SparseArrayEntry<V>> iterator = iterable.filteredIterator(false);

            return new TranslatedSparseArrayIterator<T, V>(iterator, mKeyTranslator,
                                                           mValueTranslator);
        }

        @Override
        protected SparseIterator<SparseArrayEntry<T>> rawIterator(final boolean isReverse) {

            final SparseIterator<SparseArrayEntry<V>> iterator = mIterable.rawIterator(isReverse);

            return new TranslatedSparseArrayIterator<T, V>(iterator, mKeyTranslator,
                                                           mValueTranslator);
        }

        @Override
        protected void clearFilters() {

            super.clearFilters();

            mIterable.clearFilters();
        }
    }

    @Override
    protected SparseArrayCompatIterableImpl<V> copy() {

        return new SparseArrayCompatIterableImpl<V>(this);
    }

    @Override
    protected SparseIterator<SparseArrayEntry<V>> createIterator(final boolean isReverse) {

        if (isReverse) {

            return new SparseArrayCompatReversIterator<V>(mArray);
        }

        return new SparseArrayCompatIterator<V>(mArray);
    }

    @Override
    protected SparseIterator<SparseArrayEntry<V>> rawIterator(final boolean isReverse) {

        return createIterator(isReverse);
    }
}