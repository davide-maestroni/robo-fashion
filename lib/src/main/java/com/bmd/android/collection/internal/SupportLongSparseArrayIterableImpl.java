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

import android.annotation.SuppressLint;
import android.os.Parcelable;
import android.support.v4.util.LongSparseArray;

import com.bmd.android.collection.entry.LongSparseArrayEntry;
import com.bmd.android.collection.entry.LongSparseObjectEntry;
import com.bmd.android.collection.entry.ParcelableLongSparseObjectEntry;
import com.bmd.android.collection.filter.Filter;
import com.bmd.android.collection.filter.SupportLongSparseArrayFilterBuilder;
import com.bmd.android.collection.iterator.ElementSparseIterable;
import com.bmd.android.collection.iterator.LongSparseIterable;
import com.bmd.android.collection.iterator.SupportLongSparseArrayIterable;
import com.bmd.android.collection.translator.FullLongTranslator;
import com.bmd.android.collection.translator.FullTranslator;
import com.bmd.android.collection.translator.LongTranslator;
import com.bmd.android.collection.translator.Translator;
import com.bmd.android.collection.translator.Translators;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Implementation of a {@link com.bmd.android.collection.iterator.SupportLongSparseArrayIterable}.
 * <p/>
 * Created by davide on 3/11/14.
 *
 * @param <V> the element value type.
 */
class SupportLongSparseArrayIterableImpl<V> extends AbstractSparseIterable<LongSparseArrayEntry<V>>
        implements SupportLongSparseArrayIterable<V> {

    private final LongSparseArray<V> mArray;

    private SupportLongSparseArrayFilterBuilderImpl<V> mExclusionBuilder;

    private SupportLongSparseArrayFilterBuilderImpl<V> mInclusionBuilder;

    private Translator<LongSparseArrayEntry<V>, Long> mKeyTranslator;

    private Translator<LongSparseArrayEntry<V>, V> mValueTranslator;

    public SupportLongSparseArrayIterableImpl(final LongSparseArray<V> array) {

        mArray = array;
    }

    SupportLongSparseArrayIterableImpl(final SupportLongSparseArrayIterableImpl<V> other) {

        super(other);

        mArray = other.mArray;
    }

    @Override
    public SupportLongSparseArrayIterable<V> appendTo(final LongSparseArray<V> other) {

        for (final LongSparseArrayEntry<V> entry : this) {

            other.append(entry.getKey(), entry.getValue());
        }

        return this;
    }

    @Override
    public boolean containsAllKeys(final long... keys) {

        for (final long key : keys) {

            if (positionOfKey(key) < 0) {

                return false;
            }
        }

        return true;
    }

    @Override
    public boolean containsAllKeys(final Iterable<Long> keys) {

        for (final long key : keys) {

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
    public boolean containsAnyKey(final long... keys) {

        for (final long key : keys) {

            if (positionOfKey(key) >= 0) {

                return true;
            }
        }

        return false;
    }

    @Override
    public boolean containsAnyKey(final Iterable<Long> keys) {

        for (final long key : keys) {

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
    public boolean containsKey(final long key) {

        return positionOfKey(key) >= 0;
    }

    @Override
    public boolean containsValue(final Object value) {

        return firstPositionOfValue(value) >= 0;
    }

    @Override
    public SupportLongSparseArrayIterable<V> fill(final Map<? super Long, ? super V> map) {

        for (final LongSparseArrayEntry<V> entry : this) {

            map.put(entry.getKey(), entry.getValue());
        }

        return this;
    }

    @Override
    public SupportLongSparseArrayIterable<V> fillImmutable(
            final Collection<? super LongSparseObjectEntry<V>> collection) {

        for (final LongSparseArrayEntry<V> entry : this) {

            collection.add(entry.toImmutable());
        }

        return this;
    }

    @Override
    public <T> SupportLongSparseArrayIterable<V> fillImmutable(final T[] array) {

        return fillImmutable(array, 0);
    }

    @Override
    public <T> SupportLongSparseArrayIterable<V> fillImmutable(final T[] array, final int offset) {

        int i = offset;

        for (final LongSparseArrayEntry<V> entry : this) {

            //noinspection unchecked
            array[i++] = (T) entry.toImmutable();
        }

        return this;
    }

    @Override
    public SupportLongSparseArrayIterable<V> fillParcelable(
            final Collection<? super ParcelableLongSparseObjectEntry<V>> collection) {

        for (final LongSparseArrayEntry<V> entry : this) {

            collection.add(entry.toParcelable());
        }

        return this;
    }

    @Override
    public <T extends Parcelable> SupportLongSparseArrayIterable<V> fillParcelable(
            final T[] array) {

        return fillParcelable(array, 0);
    }

    @Override
    public <T extends Parcelable> SupportLongSparseArrayIterable<V> fillParcelable(final T[] array,
            final int offset) {

        int i = offset;

        for (final LongSparseArrayEntry<V> entry : this) {

            //noinspection unchecked
            array[i++] = (T) entry.toParcelable();
        }

        return this;
    }


    @Override
    public int firstIndexOfValue(final Object value) {

        if (value == null) {

            for (final LongSparseArrayEntry<V> entry : this) {

                if (entry.getValue() == null) {

                    return entry.getIndex();
                }
            }

        } else {

            for (final LongSparseArrayEntry<V> entry : this) {

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

            for (final LongSparseArrayEntry<V> entry : this) {

                if (entry.getValue() == null) {

                    return i;
                }

                ++i;
            }

        } else {

            for (final LongSparseArrayEntry<V> entry : this) {

                if (value.equals(entry.getValue())) {

                    return i;
                }

                ++i;
            }
        }

        return -1;
    }

    @Override
    public int indexOfKey(final long key) {

        for (final LongSparseArrayEntry<V> entry : this) {

            if (entry.getKey() == key) {

                return entry.getIndex();
            }
        }

        return -1;
    }

    @Override
    public boolean isEqualTo(final LongSparseArray<?> array) {

        int count = 0;

        for (final LongSparseArrayEntry<V> entry : this) {

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

        for (final LongSparseArrayEntry<V> entry : this) {

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
    public LongSparseIterable keys() {

        if (mKeyTranslator == null) {

            mKeyTranslator = new Translator<LongSparseArrayEntry<V>, Long>() {

                @Override
                public Long translate(final LongSparseArrayEntry<V> element) {

                    return element.getKey();
                }
            };
        }

        return toLongs(mKeyTranslator);
    }

    @Override
    public int positionOfKey(final long key) {

        int i = 0;

        for (final LongSparseArrayEntry<V> entry : this) {

            if (entry.getKey() == key) {

                return i;
            }

            ++i;
        }

        return -1;
    }

    @Override
    public SupportLongSparseArrayIterable<V> putInto(final LongSparseArray<V> other) {

        for (final LongSparseArrayEntry<V> entry : this) {

            other.put(entry.getKey(), entry.getValue());
        }

        return this;
    }

    @Override
    public SupportLongSparseArrayIterable<V> replaceValues(final Translator<V, V> translator) {

        final LongSparseArray<V> array = mArray;

        for (final LongSparseArrayEntry<V> entry : this) {

            array.append(entry.getKey(), translator.translate(entry.getValue()));
        }

        return this;
    }

    @Override
    public <T> T[] toImmutableArray(final Class<T> type) {

        final ArrayList<LongSparseObjectEntry<V>> list = toImmutableList();

        //noinspection unchecked,SuspiciousToArrayCall
        return list.toArray((T[]) Array.newInstance(type, list.size()));
    }

    @Override
    public ArrayList<LongSparseObjectEntry<V>> toImmutableList() {

        final ArrayList<LongSparseObjectEntry<V>> list = new ArrayList<LongSparseObjectEntry<V>>();

        fillImmutable(list);

        return list;
    }

    @Override
    public Map<Long, V> toMap() {

        @SuppressLint("UseSparseArrays")
        final HashMap<Long, V> map = new HashMap<Long, V>();

        fill(map);

        return map;
    }

    @Override
    public <T extends Parcelable> T[] toParcelableArray(final Class<T> type) {

        final ArrayList<ParcelableLongSparseObjectEntry<V>> list = toParcelableList();

        //noinspection unchecked,SuspiciousToArrayCall
        return list.toArray((T[]) Array.newInstance(type, list.size()));
    }

    @Override
    public ArrayList<ParcelableLongSparseObjectEntry<V>> toParcelableList() {

        final ArrayList<ParcelableLongSparseObjectEntry<V>> list =
                new ArrayList<ParcelableLongSparseObjectEntry<V>>();

        fillParcelable(list);

        return list;
    }

    @Override
    public SortedMap<Long, V> toSortedMap() {

        final TreeMap<Long, V> map = new TreeMap<Long, V>();

        fill(map);

        return map;
    }

    @Override
    public LongSparseArray<V> toSparseArray() {

        final LongSparseArray<V> array = new LongSparseArray<V>();

        for (final LongSparseArrayEntry<V> entry : this) {

            array.append(entry.getKey(), entry.getValue());
        }

        return array;
    }

    @Override
    public <T> SupportLongSparseArrayIterable<T> translate(final LongTranslator keyTranslator,
            final Translator<V, T> valueTranslator) {

        return new TranslatedSupportLongSparseArrayIterableImpl<T, V>(this, Translators.full(
                keyTranslator), Translators.full(valueTranslator));
    }

    @Override
    public SupportLongSparseArrayIterable<V> translateKeys(final LongTranslator keyTranslator) {

        final FullTranslator<V, V> valueTranslator = Translators.identity();

        return translate(keyTranslator, valueTranslator);
    }

    @Override
    public <T> SupportLongSparseArrayIterable<T> translateValues(
            final Translator<V, T> translator) {

        return translate(Translators.longIdentity(), translator);
    }

    @Override
    public ElementSparseIterable<V> values() {

        if (mValueTranslator == null) {

            mValueTranslator = new Translator<LongSparseArrayEntry<V>, V>() {

                @Override
                public V translate(final LongSparseArrayEntry<V> element) {

                    return element.getValue();
                }
            };
        }

        return toElements(mValueTranslator);
    }

    @Override
    public SupportLongSparseArrayFilterBuilder<V> but() {

        if (mExclusionBuilder == null) {

            mExclusionBuilder = new SupportLongSparseArrayFilterBuilderImpl<V>(this, false);
        }

        return mExclusionBuilder;
    }

    @Override
    public SupportLongSparseArrayIterable<V> but(final Filter<LongSparseArrayEntry<V>> filter) {

        super.but(filter);

        return this;
    }

    @Override
    public SupportLongSparseArrayIterable<V> doWhile(
            final Condition<LongSparseArrayEntry<V>> condition) {

        super.doWhile(condition);

        return this;
    }

    @Override
    public SupportLongSparseArrayIterable<V> forEach(final Action<LongSparseArrayEntry<V>> action) {

        super.forEach(action);

        return this;
    }

    @Override
    public SupportLongSparseArrayFilterBuilder<V> only() {

        if (mInclusionBuilder == null) {

            mInclusionBuilder = new SupportLongSparseArrayFilterBuilderImpl<V>(this, true);
        }

        return mInclusionBuilder;
    }

    @Override
    public SupportLongSparseArrayIterable<V> only(final Filter<LongSparseArrayEntry<V>> filter) {

        super.only(filter);

        return this;
    }

    @Override
    public SupportLongSparseArrayIterable<V> remove() {

        super.remove();

        return this;
    }

    @Override
    public SupportLongSparseArrayIterable<V> retain() {

        super.retain();

        return this;
    }

    @Override
    public SupportLongSparseArrayIterable<V> reverse() {

        super.reverse();

        return this;
    }

    private static class TranslatedSupportLongSparseArrayIterableImpl<T, V>
            extends SupportLongSparseArrayIterableImpl<T> {

        private final SupportLongSparseArrayIterableImpl<V> mIterable;

        private final FullLongTranslator mKeyTranslator;

        private final FullTranslator<V, T> mValueTranslator;

        public TranslatedSupportLongSparseArrayIterableImpl(
                final SupportLongSparseArrayIterableImpl<V> wrapped,
                final FullLongTranslator keyTranslator,
                final FullTranslator<V, T> valueTranslator) {

            super((LongSparseArray<T>) null);

            mIterable = wrapped;
            mKeyTranslator = keyTranslator;
            mValueTranslator = valueTranslator;
        }

        private TranslatedSupportLongSparseArrayIterableImpl(
                final TranslatedSupportLongSparseArrayIterableImpl<T, V> other) {

            super(other);

            mIterable = other.mIterable;
            mKeyTranslator = other.mKeyTranslator;
            mValueTranslator = other.mValueTranslator;
        }

        @Override
        protected TranslatedSupportLongSparseArrayIterableImpl<T, V> copy() {

            return new TranslatedSupportLongSparseArrayIterableImpl<T, V>(this);
        }

        @Override
        protected SparseIterator<LongSparseArrayEntry<T>> createIterator(final boolean isReverse) {

            final SupportLongSparseArrayIterableImpl<V> iterable;

            if (isReverse) {

                iterable = mIterable.copy();
                iterable.reverse();

            } else {

                iterable = mIterable;
            }

            final SparseIterator<LongSparseArrayEntry<V>> iterator =
                    iterable.filteredIterator(false);

            return new TranslatedLongSparseArrayIterator<T, V>(iterator, mKeyTranslator,
                                                               mValueTranslator);
        }

        @Override
        protected SparseIterator<LongSparseArrayEntry<T>> rawIterator(final boolean isReverse) {

            final SparseIterator<LongSparseArrayEntry<V>> iterator =
                    mIterable.rawIterator(isReverse);

            return new TranslatedLongSparseArrayIterator<T, V>(iterator, mKeyTranslator,
                                                               mValueTranslator);
        }

        @Override
        protected void clearFilters() {

            super.clearFilters();

            mIterable.clearFilters();
        }
    }

    @Override
    protected SupportLongSparseArrayIterableImpl<V> copy() {

        return new SupportLongSparseArrayIterableImpl<V>(this);
    }

    @Override
    protected SparseIterator<LongSparseArrayEntry<V>> createIterator(final boolean isReverse) {

        if (isReverse) {

            return new SupportLongSparseArrayReverseIterator<V>(mArray);
        }

        return new SupportLongSparseArrayIterator<V>(mArray);
    }

    @Override
    protected SparseIterator<LongSparseArrayEntry<V>> rawIterator(final boolean isReverse) {

        return createIterator(isReverse);
    }
}