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
import android.annotation.TargetApi;
import android.os.Parcelable;
import android.util.SparseLongArray;

import com.bmd.android.collection.entry.IntSparseLongEntry;
import com.bmd.android.collection.entry.ParcelableIntSparseLongEntry;
import com.bmd.android.collection.entry.SparseLongArrayEntry;
import com.bmd.android.collection.filter.Filter;
import com.bmd.android.collection.filter.SparseLongArrayFilterBuilder;
import com.bmd.android.collection.iterator.IntSparseIterable;
import com.bmd.android.collection.iterator.LongSparseIterable;
import com.bmd.android.collection.iterator.SparseLongArrayIterable;
import com.bmd.android.collection.translator.FullIntTranslator;
import com.bmd.android.collection.translator.FullLongTranslator;
import com.bmd.android.collection.translator.IntTranslator;
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
 * Implementation of a {@link com.bmd.android.collection.iterator.SparseLongArrayIterable}.
 * <p/>
 * Created by davide on 3/12/14.
 */
@TargetApi(18)
class SparseLongArrayIterableImpl extends AbstractSparseIterable<SparseLongArrayEntry>
        implements SparseLongArrayIterable {

    private final SparseLongArray mArray;

    private SparseLongArrayFilterBuilderImpl mExclusionBuilder;

    private SparseLongArrayFilterBuilderImpl mInclusionBuilder;

    private Translator<SparseLongArrayEntry, Integer> mKeyTranslator;

    private Translator<SparseLongArrayEntry, Long> mValueTranslator;

    public SparseLongArrayIterableImpl(final SparseLongArray array) {

        mArray = array;
    }

    SparseLongArrayIterableImpl(final SparseLongArrayIterableImpl other) {

        super(other);

        mArray = other.mArray;
    }

    @Override
    public SparseLongArrayIterable appendTo(final SparseLongArray other) {

        for (final SparseLongArrayEntry entry : this) {

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
    public boolean containsAllValues(final long... values) {

        for (final long value : values) {

            if (firstPositionOfValue(value) < 0) {

                return false;
            }
        }

        return true;
    }

    @Override
    public boolean containsAllValues(final Iterable<Long> values) {

        for (final long value : values) {

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
    public boolean containsAnyValue(final long... values) {

        for (final long value : values) {

            if (firstPositionOfValue(value) >= 0) {

                return true;
            }
        }

        return false;
    }

    @Override
    public boolean containsAnyValue(final Iterable<Long> values) {

        for (final long value : values) {

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
    public boolean containsValue(final long value) {

        return firstPositionOfValue(value) >= 0;
    }

    @Override
    public SparseLongArrayIterable fill(final Map<? super Integer, ? super Long> map) {

        for (final SparseLongArrayEntry entry : this) {

            map.put(entry.getKey(), entry.getValue());
        }

        return this;
    }

    @Override
    public SparseLongArrayIterable fillImmutable(
            final Collection<? super IntSparseLongEntry> collection) {

        for (final SparseLongArrayEntry entry : this) {

            collection.add(entry.toImmutable());
        }

        return this;
    }

    @Override
    public <T> SparseLongArrayIterable fillImmutable(final T[] array) {

        return fillImmutable(array, 0);
    }

    @Override
    public <T> SparseLongArrayIterable fillImmutable(final T[] array, final int offset) {

        int i = offset;

        for (final SparseLongArrayEntry entry : this) {

            //noinspection unchecked
            array[i++] = (T) entry.toImmutable();
        }

        return this;
    }

    @Override
    public SparseLongArrayIterable fillParcelable(
            final Collection<? super ParcelableIntSparseLongEntry> collection) {

        for (final SparseLongArrayEntry entry : this) {

            collection.add(entry.toParcelable());
        }

        return this;
    }

    @Override
    public <T extends Parcelable> SparseLongArrayIterable fillParcelable(final T[] array) {

        return fillParcelable(array, 0);
    }

    @Override
    public <T extends Parcelable> SparseLongArrayIterable fillParcelable(final T[] array,
            final int offset) {

        int i = offset;

        for (final SparseLongArrayEntry entry : this) {

            //noinspection unchecked
            array[i++] = (T) entry.toParcelable();
        }

        return this;
    }

    @Override
    public int firstIndexOfValue(final long value) {

        for (final SparseLongArrayEntry entry : this) {

            if (entry.getValue() == value) {

                return entry.getIndex();
            }
        }

        return -1;
    }

    @Override
    public int firstPositionOfValue(final long value) {

        int i = 0;

        for (final SparseLongArrayEntry entry : this) {

            if (entry.getValue() == value) {

                return i;
            }

            ++i;
        }

        return -1;
    }

    @Override
    public int indexOfKey(final int key) {

        for (final SparseLongArrayEntry entry : this) {

            if (entry.getKey() == key) {

                return entry.getIndex();
            }
        }

        return -1;
    }

    @Override
    public boolean isEqualTo(final SparseLongArray array) {

        int count = 0;

        for (final SparseLongArrayEntry entry : this) {

            final long value = array.get(entry.getKey());

            if (entry.getValue() != value) {

                return false;
            }

            ++count;
        }

        return (count == array.size());
    }

    @Override
    public boolean isEqualTo(final Map<?, ?> map) {

        int count = 0;

        for (final SparseLongArrayEntry entry : this) {

            final Object value = map.get(entry.getKey());

            if (value == null) {

                return false;

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

            mKeyTranslator = new Translator<SparseLongArrayEntry, Integer>() {

                @Override
                public Integer translate(final SparseLongArrayEntry element) {

                    return element.getKey();
                }
            };
        }

        return toIntegers(mKeyTranslator);
    }

    @Override
    public int positionOfKey(final int key) {

        int i = 0;

        for (final SparseLongArrayEntry entry : this) {

            if (entry.getKey() == key) {

                return i;
            }

            ++i;
        }

        return -1;
    }

    @Override
    public SparseLongArrayIterable putInto(final SparseLongArray other) {

        for (final SparseLongArrayEntry entry : this) {

            other.put(entry.getKey(), entry.getValue());
        }

        return this;
    }

    @Override
    public SparseLongArrayIterable replaceValues(final LongTranslator translator) {

        final SparseLongArray array = mArray;

        for (final SparseLongArrayEntry entry : this) {

            array.append(entry.getKey(), translator.translate(entry.getValue()));
        }

        return this;
    }

    @Override
    public <T> T[] toImmutableArray(final Class<T> type) {

        final ArrayList<IntSparseLongEntry> list = toImmutableList();

        //noinspection unchecked,SuspiciousToArrayCall
        return list.toArray((T[]) Array.newInstance(type, list.size()));
    }

    @Override
    public ArrayList<IntSparseLongEntry> toImmutableList() {

        final ArrayList<IntSparseLongEntry> list = new ArrayList<IntSparseLongEntry>();

        fillImmutable(list);

        return list;
    }

    @Override
    public Map<Integer, Long> toMap() {

        @SuppressLint("UseSparseArrays")
        final HashMap<Integer, Long> map = new HashMap<Integer, Long>();

        fill(map);

        return map;
    }

    @Override
    public <T extends Parcelable> T[] toParcelableArray(final Class<T> type) {

        final ArrayList<ParcelableIntSparseLongEntry> list = toParcelableList();

        //noinspection unchecked,SuspiciousToArrayCall
        return list.toArray((T[]) Array.newInstance(type, list.size()));
    }

    @Override
    public ArrayList<ParcelableIntSparseLongEntry> toParcelableList() {

        final ArrayList<ParcelableIntSparseLongEntry> list =
                new ArrayList<ParcelableIntSparseLongEntry>();

        fillParcelable(list);

        return list;
    }

    @Override
    public SortedMap<Integer, Long> toSortedMap() {

        final TreeMap<Integer, Long> map = new TreeMap<Integer, Long>();

        fill(map);

        return map;
    }

    @Override
    public SparseLongArray toSparseArray() {

        final SparseLongArray array = new SparseLongArray();

        for (final SparseLongArrayEntry entry : this) {

            array.append(entry.getKey(), entry.getValue());
        }

        return array;
    }

    @Override
    public SparseLongArrayIterable translate(final IntTranslator keyTranslator,
            final LongTranslator valueTranslator) {

        return new TranslatedSparseLongArrayIterableImpl(this, Translators.full(keyTranslator),
                                                         Translators.full(valueTranslator));
    }

    @Override
    public SparseLongArrayIterable translateKeys(final IntTranslator keyTranslator) {

        final FullLongTranslator valueTranslator = Translators.longIdentity();

        return translate(keyTranslator, valueTranslator);
    }

    @Override
    public SparseLongArrayIterable translateValues(final LongTranslator translator) {

        return translate(Translators.intIdentity(), translator);
    }

    @Override
    public LongSparseIterable values() {

        if (mValueTranslator == null) {

            mValueTranslator = new Translator<SparseLongArrayEntry, Long>() {

                @Override
                public Long translate(final SparseLongArrayEntry element) {

                    return element.getValue();
                }
            };
        }

        return toLongs(mValueTranslator);
    }

    @Override
    public SparseLongArrayFilterBuilder but() {

        if (mExclusionBuilder == null) {

            mExclusionBuilder = new SparseLongArrayFilterBuilderImpl(this, false);
        }

        return mExclusionBuilder;
    }

    @Override
    public SparseLongArrayIterable but(final Filter<SparseLongArrayEntry> filter) {

        super.but(filter);

        return this;
    }

    @Override
    public SparseLongArrayIterable doWhile(final Condition<SparseLongArrayEntry> condition) {

        super.doWhile(condition);

        return this;
    }

    @Override
    public SparseLongArrayIterable forEach(final Action<SparseLongArrayEntry> action) {

        super.forEach(action);

        return this;
    }

    @Override
    public SparseLongArrayFilterBuilder only() {

        if (mInclusionBuilder == null) {

            mInclusionBuilder = new SparseLongArrayFilterBuilderImpl(this, true);
        }

        return mInclusionBuilder;
    }

    @Override
    public SparseLongArrayIterable only(final Filter<SparseLongArrayEntry> filter) {

        super.only(filter);

        return this;
    }

    @Override
    public SparseLongArrayIterable remove() {

        super.remove();

        return this;
    }

    @Override
    public SparseLongArrayIterable retain() {

        super.retain();

        return this;
    }

    @Override
    public SparseLongArrayIterable reverse() {

        super.reverse();

        return this;
    }

    private static class TranslatedSparseLongArrayIterableImpl extends SparseLongArrayIterableImpl {

        private final SparseLongArrayIterableImpl mIterable;

        private final FullIntTranslator mKeyTranslator;

        private final FullLongTranslator mValueTranslator;

        public TranslatedSparseLongArrayIterableImpl(final SparseLongArrayIterableImpl wrapped,
                final FullIntTranslator keyTranslator, final FullLongTranslator valueTranslator) {

            super((SparseLongArray) null);

            mIterable = wrapped;
            mKeyTranslator = keyTranslator;
            mValueTranslator = valueTranslator;
        }

        private TranslatedSparseLongArrayIterableImpl(
                final TranslatedSparseLongArrayIterableImpl other) {

            super(other);

            mIterable = other.mIterable;
            mKeyTranslator = other.mKeyTranslator;
            mValueTranslator = other.mValueTranslator;
        }

        @Override
        protected TranslatedSparseLongArrayIterableImpl copy() {

            return new TranslatedSparseLongArrayIterableImpl(this);
        }

        @Override
        protected SparseIterator<SparseLongArrayEntry> createIterator(final boolean isReverse) {

            final SparseLongArrayIterableImpl iterable;

            if (isReverse) {

                iterable = mIterable.copy();
                iterable.reverse();

            } else {

                iterable = mIterable;
            }

            final SparseIterator<SparseLongArrayEntry> iterator = iterable.filteredIterator(false);

            return new TranslatedSparseLongArrayIterator(iterator, mKeyTranslator,
                                                         mValueTranslator);
        }

        @Override
        protected SparseIterator<SparseLongArrayEntry> rawIterator(final boolean isReverse) {

            final SparseIterator<SparseLongArrayEntry> iterator = mIterable.rawIterator(isReverse);

            return new TranslatedSparseLongArrayIterator(iterator, mKeyTranslator,
                                                         mValueTranslator);
        }

        @Override
        protected void clearFilters() {

            super.clearFilters();

            mIterable.clearFilters();
        }
    }

    @Override
    protected SparseLongArrayIterableImpl copy() {

        return new SparseLongArrayIterableImpl(this);
    }

    @Override
    protected SparseIterator<SparseLongArrayEntry> createIterator(final boolean isReverse) {

        if (isReverse) {

            return new SparseLongArrayReverseIterator(mArray);
        }

        return new SparseLongArrayIterator(mArray);
    }

    @Override
    protected SparseIterator<SparseLongArrayEntry> rawIterator(final boolean isReverse) {

        return createIterator(isReverse);
    }
}