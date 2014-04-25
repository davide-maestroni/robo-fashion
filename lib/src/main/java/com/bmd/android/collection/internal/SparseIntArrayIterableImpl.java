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

import android.os.Parcelable;
import android.util.SparseIntArray;

import com.bmd.android.collection.entry.IntSparseIntEntry;
import com.bmd.android.collection.entry.ParcelableIntSparseIntEntry;
import com.bmd.android.collection.entry.SparseIntArrayEntry;
import com.bmd.android.collection.filter.Filter;
import com.bmd.android.collection.filter.SparseIntArrayFilterBuilder;
import com.bmd.android.collection.iterator.IntSparseIterable;
import com.bmd.android.collection.iterator.SparseIntArrayIterable;
import com.bmd.android.collection.translator.FullIntTranslator;
import com.bmd.android.collection.translator.IntTranslator;
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
 * Implementation of a {@link com.bmd.android.collection.iterator.SparseIntArrayIterable}.
 * <p/>
 * Created by davide on 3/12/14.
 */
class SparseIntArrayIterableImpl extends AbstractSparseIterable<SparseIntArrayEntry>
        implements SparseIntArrayIterable {

    private final SparseIntArray mArray;

    private SparseIntArrayFilterBuilderImpl mExclusionBuilder;

    private SparseIntArrayFilterBuilderImpl mInclusionBuilder;

    private Translator<SparseIntArrayEntry, Integer> mKeyTranslator;

    private Translator<SparseIntArrayEntry, Integer> mValueTranslator;

    public SparseIntArrayIterableImpl(final SparseIntArray array) {

        mArray = array;
    }

    SparseIntArrayIterableImpl(final SparseIntArrayIterableImpl other) {

        super(other);

        mArray = other.mArray;
    }

    @Override
    public SparseIntArrayIterable appendTo(final SparseIntArray other) {

        for (final SparseIntArrayEntry entry : this) {

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
    public boolean containsAllValues(final int... values) {

        for (final int value : values) {

            if (firstPositionOfValue(value) < 0) {

                return false;
            }
        }

        return true;
    }

    @Override
    public boolean containsAllValues(final Iterable<Integer> values) {

        for (final int value : values) {

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
    public boolean containsAnyValue(final int... values) {

        for (final int value : values) {

            if (firstPositionOfValue(value) >= 0) {

                return true;
            }
        }

        return false;
    }

    @Override
    public boolean containsAnyValue(final Iterable<Integer> values) {

        for (final int value : values) {

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
    public boolean containsValue(final int value) {

        return firstPositionOfValue(value) >= 0;
    }

    @Override
    public SparseIntArrayIterable fill(final Map<? super Integer, ? super Integer> map) {

        for (final SparseIntArrayEntry entry : this) {

            map.put(entry.getKey(), entry.getValue());
        }

        return this;
    }

    @Override
    public SparseIntArrayIterable fillImmutable(
            final Collection<? super IntSparseIntEntry> collection) {

        for (final SparseIntArrayEntry entry : this) {

            collection.add(entry.toImmutable());
        }

        return this;
    }

    @Override
    public <T> SparseIntArrayIterable fillImmutable(final T[] array) {

        return fillImmutable(array, 0);
    }

    @Override
    public <T> SparseIntArrayIterable fillImmutable(final T[] array, final int offset) {

        int i = offset;

        for (final SparseIntArrayEntry entry : this) {

            array[i++] = (T) entry.toImmutable();
        }

        return this;
    }

    @Override
    public SparseIntArrayIterable fillParcelable(
            final Collection<? super ParcelableIntSparseIntEntry> collection) {

        for (final SparseIntArrayEntry entry : this) {

            collection.add(entry.toParcelable());
        }

        return this;
    }

    @Override
    public <T extends Parcelable> SparseIntArrayIterable fillParcelable(final T[] array) {

        return fillParcelable(array, 0);
    }

    @Override
    public <T extends Parcelable> SparseIntArrayIterable fillParcelable(final T[] array,
            final int offset) {

        int i = offset;

        for (final SparseIntArrayEntry entry : this) {

            array[i++] = (T) entry.toParcelable();
        }

        return this;
    }

    @Override
    public int firstIndexOfValue(final int value) {

        for (final SparseIntArrayEntry entry : this) {

            if (entry.getValue() == value) {

                return entry.getIndex();
            }
        }

        return -1;
    }

    @Override
    public int firstPositionOfValue(final int value) {

        int i = 0;

        for (final SparseIntArrayEntry entry : this) {

            if (entry.getValue() == value) {

                return i;
            }

            ++i;
        }

        return -1;
    }

    @Override
    public int indexOfKey(final int key) {

        for (final SparseIntArrayEntry entry : this) {

            if (entry.getKey() == key) {

                return entry.getIndex();
            }
        }

        return -1;
    }

    @Override
    public boolean isEqualTo(final SparseIntArray array) {

        int count = 0;

        for (final SparseIntArrayEntry entry : this) {

            final int value = array.get(entry.getKey());

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

        for (final SparseIntArrayEntry entry : this) {

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

            mKeyTranslator = new Translator<SparseIntArrayEntry, Integer>() {

                @Override
                public Integer translate(final SparseIntArrayEntry element) {

                    return element.getKey();
                }
            };
        }

        return toIntegers(mKeyTranslator);
    }

    @Override
    public int positionOfKey(final int key) {

        int i = 0;

        for (final SparseIntArrayEntry entry : this) {

            if (entry.getKey() == key) {

                return i;
            }

            ++i;
        }

        return -1;
    }

    @Override
    public SparseIntArrayIterable putInto(final SparseIntArray other) {

        for (final SparseIntArrayEntry entry : this) {

            other.put(entry.getKey(), entry.getValue());
        }

        return this;
    }

    @Override
    public SparseIntArrayIterable replaceValues(final IntTranslator translator) {

        final SparseIntArray array = mArray;

        for (final SparseIntArrayEntry entry : this) {

            array.append(entry.getKey(), translator.translate(entry.getValue()));
        }

        return this;
    }

    @Override
    public <T> T[] toImmutableArray(final Class<T> type) {

        final ArrayList<IntSparseIntEntry> list = toImmutableList();

        return list.toArray((T[]) Array.newInstance(type, list.size()));
    }

    @Override
    public ArrayList<IntSparseIntEntry> toImmutableList() {

        final ArrayList<IntSparseIntEntry> list = new ArrayList<IntSparseIntEntry>();

        fillImmutable(list);

        return list;
    }

    @Override
    public Map<Integer, Integer> toMap() {

        final HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

        fill(map);

        return map;
    }

    @Override
    public <T extends Parcelable> T[] toParcelableArray(final Class<T> type) {

        final ArrayList<ParcelableIntSparseIntEntry> list = toParcelableList();

        return list.toArray((T[]) Array.newInstance(type, list.size()));
    }

    @Override
    public ArrayList<ParcelableIntSparseIntEntry> toParcelableList() {

        final ArrayList<ParcelableIntSparseIntEntry> list =
                new ArrayList<ParcelableIntSparseIntEntry>();

        fillParcelable(list);

        return list;
    }

    @Override
    public SortedMap<Integer, Integer> toSortedMap() {

        final TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();

        fill(map);

        return map;
    }

    @Override
    public SparseIntArray toSparseArray() {

        final SparseIntArray array = new SparseIntArray();

        for (final SparseIntArrayEntry entry : this) {

            array.append(entry.getKey(), entry.getValue());
        }

        return array;
    }

    @Override
    public SparseIntArrayIterable translate(final IntTranslator keyTranslator,
            final IntTranslator valueTranslator) {

        return new TranslatedSparseIntArrayIterableImpl(this, Translators.full(keyTranslator),
                                                        Translators.full(valueTranslator));
    }

    @Override
    public SparseIntArrayIterable translateKeys(final IntTranslator keyTranslator) {

        final FullIntTranslator valueTranslator = Translators.intIdentity();

        return translate(keyTranslator, valueTranslator);
    }

    @Override
    public SparseIntArrayIterable translateValues(final IntTranslator translator) {

        return translate(Translators.intIdentity(), translator);
    }

    @Override
    public IntSparseIterable values() {

        if (mValueTranslator == null) {

            mValueTranslator = new Translator<SparseIntArrayEntry, Integer>() {

                @Override
                public Integer translate(final SparseIntArrayEntry element) {

                    return element.getValue();
                }
            };
        }

        return toIntegers(mValueTranslator);
    }

    @Override
    public SparseIntArrayFilterBuilder but() {

        if (mExclusionBuilder == null) {

            mExclusionBuilder = new SparseIntArrayFilterBuilderImpl(this, false);
        }

        return mExclusionBuilder;
    }

    @Override
    public SparseIntArrayIterable but(final Filter<SparseIntArrayEntry> filter) {

        super.but(filter);

        return this;
    }

    @Override
    public SparseIntArrayIterable doWhile(final Condition<SparseIntArrayEntry> condition) {

        super.doWhile(condition);

        return this;
    }

    @Override
    public SparseIntArrayIterable forEach(final Action<SparseIntArrayEntry> action) {

        super.forEach(action);

        return this;
    }

    @Override
    public SparseIntArrayFilterBuilder only() {

        if (mInclusionBuilder == null) {

            mInclusionBuilder = new SparseIntArrayFilterBuilderImpl(this, true);
        }

        return mInclusionBuilder;
    }

    @Override
    public SparseIntArrayIterable only(final Filter<SparseIntArrayEntry> filter) {

        super.only(filter);

        return this;
    }

    @Override
    public SparseIntArrayIterable remove() {

        super.remove();

        return this;
    }

    @Override
    public SparseIntArrayIterable retain() {

        super.retain();

        return this;
    }

    @Override
    public SparseIntArrayIterable reverse() {

        super.reverse();

        return this;
    }

    private static class TranslatedSparseIntArrayIterableImpl extends SparseIntArrayIterableImpl {

        private final SparseIntArrayIterableImpl mIterable;

        private final FullIntTranslator mKeyTranslator;

        private final FullIntTranslator mValueTranslator;

        public TranslatedSparseIntArrayIterableImpl(final SparseIntArrayIterableImpl wrapped,
                final FullIntTranslator keyTranslator, final FullIntTranslator valueTranslator) {

            super((SparseIntArray) null);

            mIterable = wrapped;
            mKeyTranslator = keyTranslator;
            mValueTranslator = valueTranslator;
        }

        private TranslatedSparseIntArrayIterableImpl(
                final TranslatedSparseIntArrayIterableImpl other) {

            super(other);

            mIterable = other.mIterable;
            mKeyTranslator = other.mKeyTranslator;
            mValueTranslator = other.mValueTranslator;
        }

        @Override
        protected TranslatedSparseIntArrayIterableImpl copy() {

            return new TranslatedSparseIntArrayIterableImpl(this);
        }

        @Override
        protected SparseIterator<SparseIntArrayEntry> createIterator(final boolean isReverse) {

            final SparseIntArrayIterableImpl iterable;

            if (isReverse) {

                iterable = mIterable.copy();
                iterable.reverse();

            } else {

                iterable = mIterable;
            }

            final SparseIterator<SparseIntArrayEntry> iterator = iterable.filteredIterator(false);

            return new TranslatedSparseIntArrayIterator(iterator, mKeyTranslator, mValueTranslator);
        }

        @Override
        protected SparseIterator<SparseIntArrayEntry> rawIterator(final boolean isReverse) {

            final SparseIterator<SparseIntArrayEntry> iterator = mIterable.rawIterator(isReverse);

            return new TranslatedSparseIntArrayIterator(iterator, mKeyTranslator, mValueTranslator);
        }

        @Override
        protected void clearFilters() {

            super.clearFilters();

            mIterable.clearFilters();
        }
    }

    @Override
    protected SparseIntArrayIterableImpl copy() {

        return new SparseIntArrayIterableImpl(this);
    }


    @Override
    protected SparseIterator<SparseIntArrayEntry> createIterator(final boolean isReverse) {

        if (isReverse) {

            return new SparseIntArrayReverseIterator(mArray);
        }

        return new SparseIntArrayIterator(mArray);
    }

    @Override
    protected SparseIterator<SparseIntArrayEntry> rawIterator(final boolean isReverse) {

        return createIterator(isReverse);
    }
}