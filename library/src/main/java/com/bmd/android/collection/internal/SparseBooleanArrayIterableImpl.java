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
import android.util.SparseBooleanArray;

import com.bmd.android.collection.entry.IntSparseBooleanEntry;
import com.bmd.android.collection.entry.ParcelableIntSparseBooleanEntry;
import com.bmd.android.collection.entry.SparseBooleanArrayEntry;
import com.bmd.android.collection.filter.Filter;
import com.bmd.android.collection.filter.SparseBooleanArrayFilterBuilder;
import com.bmd.android.collection.iterator.BooleanSparseIterable;
import com.bmd.android.collection.iterator.IntSparseIterable;
import com.bmd.android.collection.iterator.SparseBooleanArrayIterable;
import com.bmd.android.collection.translator.BooleanTranslator;
import com.bmd.android.collection.translator.FullBooleanTranslator;
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
 * Implementation of a {@link com.bmd.android.collection.iterator.SparseBooleanArrayIterable}.
 * <p/>
 * Created by davide on 3/12/14.
 */
class SparseBooleanArrayIterableImpl extends AbstractSparseIterable<SparseBooleanArrayEntry>
        implements SparseBooleanArrayIterable {

    private static volatile KeyTranslator sKeyTranslator;

    private static volatile ValueTranslator sValueTranslator;

    private final SparseBooleanArray mArray;

    private SparseBooleanArrayFilterBuilderImpl mExclusionBuilder;

    private SparseBooleanArrayFilterBuilderImpl mInclusionBuilder;

    public SparseBooleanArrayIterableImpl(final SparseBooleanArray array) {

        mArray = array;
    }

    SparseBooleanArrayIterableImpl(final SparseBooleanArrayIterableImpl other) {

        super(other);

        mArray = other.mArray;
    }

    @Override
    public SparseBooleanArrayIterable appendTo(final SparseBooleanArray other) {

        for (final SparseBooleanArrayEntry entry : this) {

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
    public boolean containsAllValues(final boolean... values) {

        for (final boolean value : values) {

            if (firstPositionOfValue(value) < 0) {

                return false;
            }
        }

        return true;
    }

    @Override
    public boolean containsAllValues(final Iterable<Boolean> values) {

        for (final boolean value : values) {

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
    public boolean containsAnyValue(final boolean... values) {

        for (final boolean value : values) {

            if (firstPositionOfValue(value) >= 0) {

                return true;
            }
        }

        return false;
    }

    @Override
    public boolean containsAnyValue(final Iterable<Boolean> values) {

        for (final boolean value : values) {

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
    public boolean containsValue(final boolean value) {

        return firstPositionOfValue(value) >= 0;
    }

    @Override
    public SparseBooleanArrayIterable fill(final Map<? super Integer, ? super Boolean> map) {

        for (final SparseBooleanArrayEntry entry : this) {

            map.put(entry.getKey(), entry.getValue());
        }

        return this;
    }

    @Override
    public SparseBooleanArrayIterable fillImmutable(
            final Collection<? super IntSparseBooleanEntry> collection) {

        for (final SparseBooleanArrayEntry entry : this) {

            collection.add(entry.toImmutable());
        }

        return this;
    }

    @Override
    public <T> SparseBooleanArrayIterable fillImmutable(final T[] array) {

        return fillImmutable(array, 0);
    }

    @Override
    public <T> SparseBooleanArrayIterable fillImmutable(final T[] array, final int offset) {

        int i = offset;

        for (final SparseBooleanArrayEntry entry : this) {

            //noinspection unchecked
            array[i++] = (T) entry.toImmutable();
        }

        return this;
    }

    @Override
    public SparseBooleanArrayIterable fillParcelable(
            final Collection<? super ParcelableIntSparseBooleanEntry> collection) {

        for (final SparseBooleanArrayEntry entry : this) {

            collection.add(entry.toParcelable());
        }

        return this;
    }

    @Override
    public <T extends Parcelable> SparseBooleanArrayIterable fillParcelable(final T[] array) {

        return fillParcelable(array, 0);
    }

    @Override
    public <T extends Parcelable> SparseBooleanArrayIterable fillParcelable(final T[] array,
            final int offset) {

        int i = offset;

        for (final SparseBooleanArrayEntry entry : this) {

            //noinspection unchecked
            array[i++] = (T) entry.toParcelable();
        }

        return this;
    }

    @Override
    public int firstIndexOfValue(final boolean value) {

        for (final SparseBooleanArrayEntry entry : this) {

            if (entry.getValue() == value) {

                return entry.getIndex();
            }
        }

        return -1;
    }

    @Override
    public int firstPositionOfValue(final boolean value) {

        int i = 0;

        for (final SparseBooleanArrayEntry entry : this) {

            if (entry.getValue() == value) {

                return i;
            }

            ++i;
        }

        return -1;
    }

    @Override
    public int indexOfKey(final int key) {

        for (final SparseBooleanArrayEntry entry : this) {

            if (entry.getKey() == key) {

                return entry.getIndex();
            }
        }

        return -1;
    }

    @Override
    public boolean isEqualTo(final SparseBooleanArray array) {

        int count = 0;

        for (final SparseBooleanArrayEntry entry : this) {

            final boolean value = array.get(entry.getKey());

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

        for (final SparseBooleanArrayEntry entry : this) {

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

        if (sKeyTranslator == null) {

            sKeyTranslator = new KeyTranslator();
        }

        return toIntegers(sKeyTranslator);
    }

    @Override
    public int positionOfKey(final int key) {

        int i = 0;

        for (final SparseBooleanArrayEntry entry : this) {

            if (entry.getKey() == key) {

                return i;
            }

            ++i;
        }

        return -1;
    }

    @Override
    public SparseBooleanArrayIterable putInto(final SparseBooleanArray other) {

        for (final SparseBooleanArrayEntry entry : this) {

            other.put(entry.getKey(), entry.getValue());
        }

        return this;
    }

    @Override
    public SparseBooleanArrayIterable replaceValues(final BooleanTranslator translator) {

        final SparseBooleanArray array = mArray;

        for (final SparseBooleanArrayEntry entry : this) {

            array.append(entry.getKey(), translator.translate(entry.getValue()));
        }

        return this;
    }

    @Override
    public <T> T[] toImmutableArray(final Class<T> type) {

        final ArrayList<IntSparseBooleanEntry> list = toImmutableList();

        //noinspection unchecked,SuspiciousToArrayCall
        return list.toArray((T[]) Array.newInstance(type, list.size()));
    }

    @Override
    public ArrayList<IntSparseBooleanEntry> toImmutableList() {

        final ArrayList<IntSparseBooleanEntry> list = new ArrayList<IntSparseBooleanEntry>();

        fillImmutable(list);

        return list;
    }

    @Override
    public Map<Integer, Boolean> toMap() {

        @SuppressLint("UseSparseArrays")
        final HashMap<Integer, Boolean> map = new HashMap<Integer, Boolean>();

        fill(map);

        return map;
    }

    @Override
    public <T extends Parcelable> T[] toParcelableArray(final Class<T> type) {

        final ArrayList<ParcelableIntSparseBooleanEntry> list = toParcelableList();

        //noinspection unchecked,SuspiciousToArrayCall
        return list.toArray((T[]) Array.newInstance(type, list.size()));
    }

    @Override
    public ArrayList<ParcelableIntSparseBooleanEntry> toParcelableList() {

        final ArrayList<ParcelableIntSparseBooleanEntry> list =
                new ArrayList<ParcelableIntSparseBooleanEntry>();

        fillParcelable(list);

        return list;
    }

    @Override
    public SortedMap<Integer, Boolean> toSortedMap() {

        final TreeMap<Integer, Boolean> map = new TreeMap<Integer, Boolean>();

        fill(map);

        return map;
    }

    @Override
    public SparseBooleanArray toSparseArray() {

        final SparseBooleanArray array = new SparseBooleanArray();

        for (final SparseBooleanArrayEntry entry : this) {

            array.append(entry.getKey(), entry.getValue());
        }

        return array;
    }

    @Override
    public SparseBooleanArrayIterable translate(final IntTranslator keyTranslator,
            final BooleanTranslator valueTranslator) {

        return new TranslatedSparseBooleanArrayIterableImpl(this, Translators.full(keyTranslator),
                                                            Translators.full(valueTranslator));
    }

    @Override
    public SparseBooleanArrayIterable translateKeys(final IntTranslator keyTranslator) {

        final FullBooleanTranslator valueTranslator = Translators.booleanIdentity();

        return translate(keyTranslator, valueTranslator);
    }

    @Override
    public SparseBooleanArrayIterable translateValues(final BooleanTranslator translator) {

        return translate(Translators.intIdentity(), translator);
    }

    @Override
    public BooleanSparseIterable values() {

        if (sValueTranslator == null) {

            sValueTranslator = new ValueTranslator();
        }

        return toBooleans(sValueTranslator);
    }

    @Override
    public SparseBooleanArrayFilterBuilder but() {

        if (mExclusionBuilder == null) {

            mExclusionBuilder = new SparseBooleanArrayFilterBuilderImpl(this, false);
        }

        return mExclusionBuilder;
    }

    @Override
    public SparseBooleanArrayIterable but(final Filter<SparseBooleanArrayEntry> filter) {

        super.but(filter);

        return this;
    }

    @Override
    public SparseBooleanArrayIterable doWhile(final Condition<SparseBooleanArrayEntry> condition) {

        super.doWhile(condition);

        return this;
    }

    @Override
    public SparseBooleanArrayIterable forEach(final Action<SparseBooleanArrayEntry> action) {

        super.forEach(action);

        return this;
    }

    @Override
    public SparseBooleanArrayFilterBuilder only() {

        if (mInclusionBuilder == null) {

            mInclusionBuilder = new SparseBooleanArrayFilterBuilderImpl(this, true);
        }

        return mInclusionBuilder;
    }

    @Override
    public SparseBooleanArrayIterable only(final Filter<SparseBooleanArrayEntry> filter) {

        super.only(filter);

        return this;
    }

    @Override
    public SparseBooleanArrayIterable remove() {

        super.remove();

        return this;
    }

    @Override
    public SparseBooleanArrayIterable retain() {

        super.retain();

        return this;
    }

    @Override
    public SparseBooleanArrayIterable reverse() {

        super.reverse();

        return this;
    }

    private static class KeyTranslator implements Translator<SparseBooleanArrayEntry, Integer> {

        @Override
        public Integer translate(final SparseBooleanArrayEntry element) {

            return element.getKey();
        }
    }

    private static class TranslatedSparseBooleanArrayIterableImpl
            extends SparseBooleanArrayIterableImpl {

        private final SparseBooleanArrayIterableImpl mIterable;

        private final FullIntTranslator mKeyTranslator;

        private final FullBooleanTranslator mValueTranslator;

        public TranslatedSparseBooleanArrayIterableImpl(
                final SparseBooleanArrayIterableImpl wrapped, final FullIntTranslator keyTranslator,
                final FullBooleanTranslator valueTranslator) {

            super((SparseBooleanArray) null);

            mIterable = wrapped;
            mKeyTranslator = keyTranslator;
            mValueTranslator = valueTranslator;
        }

        private TranslatedSparseBooleanArrayIterableImpl(
                final TranslatedSparseBooleanArrayIterableImpl other) {

            super(other);

            mIterable = other.mIterable;
            mKeyTranslator = other.mKeyTranslator;
            mValueTranslator = other.mValueTranslator;
        }

        @Override
        protected TranslatedSparseBooleanArrayIterableImpl copy() {

            return new TranslatedSparseBooleanArrayIterableImpl(this);
        }

        @Override
        protected SparseIterator<SparseBooleanArrayEntry> createIterator(final boolean isReverse) {

            final SparseBooleanArrayIterableImpl iterable;

            if (isReverse) {

                iterable = mIterable.copy();
                iterable.reverse();

            } else {

                iterable = mIterable;
            }

            final SparseIterator<SparseBooleanArrayEntry> iterator =
                    iterable.filteredIterator(false);

            return new TranslatedSparseBooleanArrayIterator(iterator, mKeyTranslator,
                                                            mValueTranslator);
        }

        @Override
        protected SparseIterator<SparseBooleanArrayEntry> rawIterator(final boolean isReverse) {

            final SparseIterator<SparseBooleanArrayEntry> iterator =
                    mIterable.rawIterator(isReverse);

            return new TranslatedSparseBooleanArrayIterator(iterator, mKeyTranslator,
                                                            mValueTranslator);
        }

        @Override
        protected void clearFilters() {

            super.clearFilters();

            mIterable.clearFilters();
        }
    }

    private static class ValueTranslator implements Translator<SparseBooleanArrayEntry, Boolean> {

        @Override
        public Boolean translate(final SparseBooleanArrayEntry element) {

            return element.getValue();
        }
    }

    @Override
    protected SparseBooleanArrayIterableImpl copy() {

        return new SparseBooleanArrayIterableImpl(this);
    }

    @Override
    protected SparseIterator<SparseBooleanArrayEntry> createIterator(final boolean isReverse) {

        if (isReverse) {

            return new SparseBooleanArrayReverseIterator(mArray);
        }

        return new SparseBooleanArrayIterator(mArray);
    }

    @Override
    protected SparseIterator<SparseBooleanArrayEntry> rawIterator(final boolean isReverse) {

        return createIterator(isReverse);
    }
}