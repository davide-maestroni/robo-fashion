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

import android.support.v4.util.SimpleArrayMap;

import com.bmd.android.collection.entry.ObjectSparseObjectEntry;
import com.bmd.android.collection.entry.ParcelableObjectSparseObjectEntry;
import com.bmd.android.collection.entry.SimpleArrayMapEntry;
import com.bmd.android.collection.entry.SparseEntries;

import java.util.NoSuchElementException;

/**
 * Same as {@link com.bmd.android.collection.internal.SimpleArrayMapIterator} but looping the
 * elements in the reverse order.
 * <p/>
 * Created by davide on 3/10/14.
 *
 * @param <K> The entry key type.
 * @param <V> The entry value type.
 */
class SimpleArrayMapReverseIterator<K, V>
        extends AbstractReverseSparseIterator<SimpleArrayMapEntry<K, V>>
        implements SimpleArrayMapEntry<K, V> {

    private final SimpleArrayMap<K, V> mArrayMap;

    private int mPosition;

    private V mValue;

    public SimpleArrayMapReverseIterator(final SimpleArrayMap<K, V> arrayMap) {

        super(arrayMap.size());

        mArrayMap = arrayMap;
    }

    @Override
    public boolean equals(final Object o) {

        return SparseEntries.equal(this, o);
    }

    @Override
    public int hashCode() {

        return SparseEntries.hashCode(this);
    }

    @Override
    public int getIndex() {

        return mPosition;
    }

    @Override
    public K getKey() {

        return mArrayMap.keyAt(mPosition);
    }

    @Override
    public V getValue() {

        return mValue;
    }

    @Override
    public void setValue(final V value) {

        mArrayMap.setValueAt(mPosition, value);

        mValue = value;
    }

    @Override
    public ObjectSparseObjectEntry<K, V> toImmutable() {

        return SparseEntries.entry(getKey(), mValue);
    }

    @Override
    public ParcelableObjectSparseObjectEntry<K, V> toParcelable() {

        return SparseEntries.parcelableEntry(getKey(), mValue);
    }

    @Override
    protected SimpleArrayMapEntry<K, V> getElementAt(final int position) {

        try {

            mValue = mArrayMap.valueAt(position);

        } catch (final IndexOutOfBoundsException e) {

            throw new NoSuchElementException();
        }

        mPosition = position;

        return this;
    }

    @Override
    protected void removeElement() {

        mArrayMap.removeAt(mPosition);
    }
}