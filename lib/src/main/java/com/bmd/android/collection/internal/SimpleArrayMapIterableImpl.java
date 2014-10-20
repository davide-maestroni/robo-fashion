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
import android.support.v4.util.SimpleArrayMap;

import com.bmd.android.collection.entry.ObjectSparseObjectEntry;
import com.bmd.android.collection.entry.ParcelableObjectSparseObjectEntry;
import com.bmd.android.collection.entry.SimpleArrayMapEntry;
import com.bmd.android.collection.filter.Filter;
import com.bmd.android.collection.filter.SimpleArrayMapFilterBuilder;
import com.bmd.android.collection.iterator.ElementSparseIterable;
import com.bmd.android.collection.iterator.SimpleArrayMapIterable;
import com.bmd.android.collection.translator.FullTranslator;
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
 * Implementation of {@link com.bmd.android.collection.iterator.SimpleArrayMapIterable}.
 * <p/>
 * Created by davide on 3/11/14.
 *
 * @param <K> the entry key type.
 * @param <V> the entry value type.
 */
class SimpleArrayMapIterableImpl<K, V> extends AbstractSparseIterable<SimpleArrayMapEntry<K, V>>
        implements SimpleArrayMapIterable<K, V> {

    private final SimpleArrayMap<K, V> mArrayMap;

    private SimpleArrayMapFilterBuilderImpl<K, V> mExclusionBuilder;

    private SimpleArrayMapFilterBuilderImpl<K, V> mInclusionBuilder;

    private Translator<SimpleArrayMapEntry<K, V>, K> mKeyTranslator;

    private Translator<SimpleArrayMapEntry<K, V>, V> mValueTranslator;

    public SimpleArrayMapIterableImpl(final SimpleArrayMap<K, V> arrayMap) {

        mArrayMap = arrayMap;
    }

    SimpleArrayMapIterableImpl(final SimpleArrayMapIterableImpl<K, V> other) {

        super(other);

        mArrayMap = other.mArrayMap;
    }

    @Override
    public SimpleArrayMapFilterBuilder<K, V> but() {

        if (mExclusionBuilder == null) {

            mExclusionBuilder = new SimpleArrayMapFilterBuilderImpl<K, V>(this, false);
        }

        return mExclusionBuilder;
    }

    @Override
    public SimpleArrayMapIterable<K, V> but(final Filter<SimpleArrayMapEntry<K, V>> filter) {

        super.but(filter);

        return this;
    }

    @Override
    public SimpleArrayMapIterable<K, V> doWhile(
            final Condition<SimpleArrayMapEntry<K, V>> condition) {

        super.doWhile(condition);

        return this;
    }

    @Override
    public SimpleArrayMapIterable<K, V> forEach(final Action<SimpleArrayMapEntry<K, V>> action) {

        super.forEach(action);

        return this;
    }

    @Override
    public SimpleArrayMapFilterBuilder<K, V> only() {

        if (mInclusionBuilder == null) {

            mInclusionBuilder = new SimpleArrayMapFilterBuilderImpl<K, V>(this, true);
        }

        return mInclusionBuilder;
    }

    @Override
    public SimpleArrayMapIterable<K, V> only(final Filter<SimpleArrayMapEntry<K, V>> filter) {

        super.only(filter);

        return this;
    }

    @Override
    public SimpleArrayMapIterable<K, V> remove() {

        super.remove();

        return this;
    }

    @Override
    public SimpleArrayMapIterable<K, V> retain() {

        super.retain();

        return this;
    }

    @Override
    public SimpleArrayMapIterable<K, V> reverse() {

        super.reverse();

        return this;
    }

    @Override
    public boolean containsAllKeys(final Object... keys) {

        for (final Object key : keys) {

            if (positionOfKey(key) < 0) {

                return false;
            }
        }

        return true;
    }

    @Override
    public boolean containsAllKeys(final Iterable<?> keys) {

        for (final Object key : keys) {

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
    public boolean containsAnyKey(final Object... keys) {

        for (final Object key : keys) {

            if (positionOfKey(key) >= 0) {

                return true;
            }
        }

        return false;
    }

    @Override
    public boolean containsAnyKey(final Iterable<?> keys) {

        for (final Object key : keys) {

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
    public boolean containsKey(final Object key) {

        return positionOfKey(key) >= 0;
    }

    @Override
    public boolean containsValue(final Object value) {

        return firstPositionOfValue(value) >= 0;
    }

    @Override
    public SimpleArrayMapIterable<K, V> fill(final Map<? super K, ? super V> map) {

        for (final SimpleArrayMapEntry<K, V> entry : this) {

            map.put(entry.getKey(), entry.getValue());
        }

        return this;
    }

    @Override
    public SimpleArrayMapIterable<K, V> fillImmutable(
            final Collection<? super ObjectSparseObjectEntry<K, V>> collection) {

        for (final SimpleArrayMapEntry<K, V> entry : this) {

            collection.add(entry.toImmutable());
        }

        return this;
    }

    @Override
    public <T> SimpleArrayMapIterable<K, V> fillImmutable(final T[] array) {

        return fillImmutable(array, 0);
    }

    @Override
    public <T> SimpleArrayMapIterable<K, V> fillImmutable(final T[] array, final int offset) {

        int i = offset;

        for (final SimpleArrayMapEntry<K, V> entry : this) {

            //noinspection unchecked
            array[i++] = (T) entry.toImmutable();
        }

        return this;
    }

    @Override
    public SimpleArrayMapIterable<K, V> fillParcelable(
            final Collection<? super ParcelableObjectSparseObjectEntry<K, V>> collection) {

        for (final SimpleArrayMapEntry<K, V> entry : this) {

            collection.add(entry.toParcelable());
        }

        return this;
    }

    @Override
    public <T extends Parcelable> SimpleArrayMapIterable<K, V> fillParcelable(final T[] array) {

        return fillParcelable(array, 0);
    }

    @Override
    public <T extends Parcelable> SimpleArrayMapIterable<K, V> fillParcelable(final T[] array,
            final int offset) {

        int i = offset;

        for (final SimpleArrayMapEntry<K, V> entry : this) {

            //noinspection unchecked
            array[i++] = (T) entry.toParcelable();
        }

        return this;
    }

    @Override
    public int firstIndexOfValue(final Object value) {

        if (value == null) {

            for (final SimpleArrayMapEntry<K, V> entry : this) {

                if (entry.getValue() == null) {

                    return entry.getIndex();
                }
            }

        } else {

            for (final SimpleArrayMapEntry<K, V> entry : this) {

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

            for (final SimpleArrayMapEntry<K, V> entry : this) {

                if (entry.getValue() == null) {

                    return i;
                }

                ++i;
            }

        } else {

            for (final SimpleArrayMapEntry<K, V> entry : this) {

                if (value.equals(entry.getValue())) {

                    return i;
                }

                ++i;
            }
        }

        return -1;
    }

    @Override
    public int indexOfKey(final Object key) {

        if (key == null) {

            for (final SimpleArrayMapEntry<K, V> entry : this) {

                if (entry.getKey() == null) {

                    return entry.getIndex();
                }
            }

        } else {

            for (final SimpleArrayMapEntry<K, V> entry : this) {

                if (key.equals(entry.getKey())) {

                    return entry.getIndex();
                }
            }
        }

        return -1;
    }

    @Override
    public boolean isEqualTo(final SimpleArrayMap<?, ?> array) {

        int count = 0;

        for (final SimpleArrayMapEntry<K, V> entry : this) {

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

        for (final SimpleArrayMapEntry<K, V> entry : this) {

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
    public ElementSparseIterable<K> keys() {

        if (mKeyTranslator == null) {

            mKeyTranslator = new Translator<SimpleArrayMapEntry<K, V>, K>() {

                @Override
                public K translate(final SimpleArrayMapEntry<K, V> element) {

                    return element.getKey();
                }
            };
        }

        return toElements(mKeyTranslator);
    }

    @Override
    public int positionOfKey(final Object key) {

        int i = 0;

        if (key == null) {

            for (final SimpleArrayMapEntry<K, V> entry : this) {

                if (entry.getKey() == null) {

                    return i;
                }

                ++i;
            }

        } else {

            for (final SimpleArrayMapEntry<K, V> entry : this) {

                if (key.equals(entry.getKey())) {

                    return i;
                }

                ++i;
            }
        }

        return -1;
    }

    @Override
    public SimpleArrayMapIterable<K, V> putInto(final SimpleArrayMap<K, V> other) {

        for (final SimpleArrayMapEntry<K, V> entry : this) {

            other.put(entry.getKey(), entry.getValue());
        }

        return this;
    }

    @Override
    public SimpleArrayMapIterable<K, V> replaceValues(final Translator<V, V> translator) {

        final SimpleArrayMap<K, V> arrayMap = mArrayMap;

        for (final SimpleArrayMapEntry<K, V> entry : this) {

            arrayMap.put(entry.getKey(), translator.translate(entry.getValue()));
        }

        return this;
    }

    @Override
    public <T> T[] toImmutableArray(final Class<T> type) {

        final ArrayList<ObjectSparseObjectEntry<K, V>> list = toImmutableList();

        //noinspection unchecked,SuspiciousToArrayCall
        return list.toArray((T[]) Array.newInstance(type, list.size()));
    }

    @Override
    public ArrayList<ObjectSparseObjectEntry<K, V>> toImmutableList() {

        final ArrayList<ObjectSparseObjectEntry<K, V>> list =
                new ArrayList<ObjectSparseObjectEntry<K, V>>();

        fillImmutable(list);

        return list;
    }

    @Override
    public Map<K, V> toMap() {

        final HashMap<K, V> map = new HashMap<K, V>();

        fill(map);

        return map;
    }

    @Override
    public <T extends Parcelable> T[] toParcelableArray(final Class<T> type) {

        final ArrayList<ParcelableObjectSparseObjectEntry<K, V>> list = toParcelableList();

        //noinspection unchecked,SuspiciousToArrayCall
        return list.toArray((T[]) Array.newInstance(type, list.size()));
    }

    @Override
    public ArrayList<ParcelableObjectSparseObjectEntry<K, V>> toParcelableList() {

        final ArrayList<ParcelableObjectSparseObjectEntry<K, V>> list =
                new ArrayList<ParcelableObjectSparseObjectEntry<K, V>>();

        fillParcelable(list);

        return list;
    }

    @Override
    public SortedMap<K, V> toSortedMap() {

        final TreeMap<K, V> map = new TreeMap<K, V>();

        fill(map);

        return map;
    }

    @Override
    public SimpleArrayMap<K, V> toSparseArray() {

        final SimpleArrayMap<K, V> array = new SimpleArrayMap<K, V>();

        for (final SimpleArrayMapEntry<K, V> entry : this) {

            array.put(entry.getKey(), entry.getValue());
        }

        return array;
    }

    @Override
    public <Nk, Nv> SimpleArrayMapIterable<Nk, Nv> translate(final Translator<K, Nk> keyTranslator,
            final Translator<V, Nv> valueTranslator) {

        return new TranslatedSimpleArrayMapIterableImpl<Nk, K, Nv, V>(this, Translators.full(
                keyTranslator), Translators.full(valueTranslator));
    }

    @Override
    public <Nk> SimpleArrayMapIterable<Nk, V> translateKeys(final Translator<K, Nk> keyTranslator) {

        final FullTranslator<V, V> valueTranslator = Translators.identity();

        return translate(keyTranslator, valueTranslator);
    }

    @Override
    public <Nv> SimpleArrayMapIterable<K, Nv> translateValues(
            final Translator<V, Nv> valueTranslator) {

        final FullTranslator<K, K> keyTranslator = Translators.identity();

        return translate(keyTranslator, valueTranslator);
    }

    @Override
    public ElementSparseIterable<V> values() {

        if (mValueTranslator == null) {

            mValueTranslator = new Translator<SimpleArrayMapEntry<K, V>, V>() {

                @Override
                public V translate(final SimpleArrayMapEntry<K, V> element) {

                    return element.getValue();
                }
            };
        }

        return toElements(mValueTranslator);
    }

    private static class TranslatedSimpleArrayMapIterableImpl<Nk, K, Nv, V>
            extends SimpleArrayMapIterableImpl<Nk, Nv> {

        private final SimpleArrayMapIterableImpl<K, V> mIterable;

        private final FullTranslator<K, Nk> mKeyTranslator;

        private final FullTranslator<V, Nv> mValueTranslator;

        public TranslatedSimpleArrayMapIterableImpl(final SimpleArrayMapIterableImpl<K, V> wrapped,
                final FullTranslator<K, Nk> keyTranslator,
                final FullTranslator<V, Nv> valueTranslator) {

            super((SimpleArrayMap<Nk, Nv>) null);

            mIterable = wrapped;
            mKeyTranslator = keyTranslator;
            mValueTranslator = valueTranslator;
        }

        private TranslatedSimpleArrayMapIterableImpl(
                final TranslatedSimpleArrayMapIterableImpl<Nk, K, Nv, V> other) {

            super(other);

            mIterable = other.mIterable;
            mKeyTranslator = other.mKeyTranslator;
            mValueTranslator = other.mValueTranslator;
        }

        @Override
        protected TranslatedSimpleArrayMapIterableImpl<Nk, K, Nv, V> copy() {

            return new TranslatedSimpleArrayMapIterableImpl<Nk, K, Nv, V>(this);
        }

        @Override
        protected SparseIterator<SimpleArrayMapEntry<Nk, Nv>> createIterator(
                final boolean isReverse) {

            final SimpleArrayMapIterableImpl<K, V> iterable;

            if (isReverse) {

                iterable = mIterable.copy();
                iterable.reverse();

            } else {

                iterable = mIterable;
            }

            final SparseIterator<SimpleArrayMapEntry<K, V>> iterator =
                    iterable.filteredIterator(false);

            return new TranslatedSimpleArrayMapIterator<Nk, K, Nv, V>(iterator, mKeyTranslator,
                                                                      mValueTranslator);
        }

        @Override
        protected SparseIterator<SimpleArrayMapEntry<Nk, Nv>> rawIterator(final boolean isReverse) {

            final SparseIterator<SimpleArrayMapEntry<K, V>> iterator =
                    mIterable.rawIterator(isReverse);

            return new TranslatedSimpleArrayMapIterator<Nk, K, Nv, V>(iterator, mKeyTranslator,
                                                                      mValueTranslator);
        }

        @Override
        protected void clearFilters() {

            super.clearFilters();

            mIterable.clearFilters();
        }
    }

    @Override
    protected SimpleArrayMapIterableImpl<K, V> copy() {

        return new SimpleArrayMapIterableImpl<K, V>(this);
    }

    @Override
    protected SparseIterator<SimpleArrayMapEntry<K, V>> createIterator(final boolean isReverse) {

        if (isReverse) {

            return new SimpleArrayMapReverseIterator<K, V>(mArrayMap);
        }

        return new SimpleArrayMapIterator<K, V>(mArrayMap);
    }

    @Override
    protected SparseIterator<SimpleArrayMapEntry<K, V>> rawIterator(final boolean isReverse) {

        return createIterator(isReverse);
    }
}