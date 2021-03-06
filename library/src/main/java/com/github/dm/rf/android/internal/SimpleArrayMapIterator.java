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
package com.github.dm.rf.android.internal;

import android.support.v4.util.SimpleArrayMap;

import com.github.dm.rf.android.entry.ObjectSparseObjectEntry;
import com.github.dm.rf.android.entry.ParcelableObjectSparseObjectEntry;
import com.github.dm.rf.android.entry.SimpleArrayMapEntry;
import com.github.dm.rf.android.entry.SparseEntries;

import java.util.NoSuchElementException;

/**
 * Implementation of a {@link SparseIterator} of
 * {@link SimpleArrayMapEntry} elements.
 * <p/>
 * To let this class implement both the iterator and the entry is a precise design choice in order
 * to achieve the highest performance and the lowest memory usage.<br/>
 * The drawback is that the iterated entries cannot be copied and used outside the
 * <code>for</code> use. Fortunately, that is not the common behavior of iteration.<br/>
 * To work around the just described issue, each entry con generate an immutable copy of itself
 * storing only the key and value objects but losing any reference to the backing sparse
 * collection.
 * <p/>
 * Created by davide-maestroni on 3/10/14.
 *
 * @param <K> the entry key type.
 * @param <V> the entry value type.
 */
class SimpleArrayMapIterator<K, V> extends AbstractSparseIterator<SimpleArrayMapEntry<K, V>>
        implements SimpleArrayMapEntry<K, V> {

    private final SimpleArrayMap<K, V> mArrayMap;

    private int mPosition;

    private V mValue;

    public SimpleArrayMapIterator(final SimpleArrayMap<K, V> arrayMap) {

        super(arrayMap.size());

        mArrayMap = arrayMap;
    }

    @Override
    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    public boolean equals(final Object o) {

        return SparseEntries.equal(this, o);
    }

    @Override
    public int hashCode() {

        return SparseEntries.hashCode(this);
    }

    @Override
    public String toString() {

        return getKey() + "=" + getValue();
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