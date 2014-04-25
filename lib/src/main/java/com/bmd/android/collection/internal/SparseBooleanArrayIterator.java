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

import android.util.SparseBooleanArray;

import com.bmd.android.collection.entry.IntSparseBooleanEntry;
import com.bmd.android.collection.entry.ParcelableIntSparseBooleanEntry;
import com.bmd.android.collection.entry.SparseBooleanArrayEntry;
import com.bmd.android.collection.entry.SparseEntries;

import java.util.NoSuchElementException;

/**
 * Implementation of a {@link com.bmd.android.collection.internal.SparseIterator} of
 * {@link com.bmd.android.collection.entry.SparseBooleanArrayEntry} elements.
 * <p/>
 * To let this class implement both the iterator and the entry is a precise design choice in order
 * to achieve the highest performance and the lowest memory usage.<br/>
 * The drawback is that the iterated entries cannot be copied and used outside the
 * <code>for</code> use. Fortunately, that is not the common behavior of iteration.<br/>
 * To work around the just described issue, each entry con generate an immutable copy of itself
 * storing only the key and value objects but losing any reference to the backing sparse
 * collection.
 * <p/>
 * Created by davide on 3/10/14.
 */
class SparseBooleanArrayIterator extends AbstractSparseIterator<SparseBooleanArrayEntry>
        implements SparseBooleanArrayEntry {

    private final SparseBooleanArray mSparseArray;

    private int mPosition;

    private boolean mValue;

    public SparseBooleanArrayIterator(final SparseBooleanArray array) {

        super(array.size());

        mSparseArray = array;
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
    public int getIndex() {

        return mPosition;
    }

    @Override
    public int getKey() {

        return mSparseArray.keyAt(mPosition);
    }

    @Override
    public boolean getValue() {

        return mValue;
    }

    @Override
    public void setValue(final boolean value) {

        mSparseArray.put(getKey(), value);

        mValue = value;
    }

    @Override
    public IntSparseBooleanEntry toImmutable() {

        return SparseEntries.entry(getKey(), mValue);
    }

    @Override
    public ParcelableIntSparseBooleanEntry toParcelable() {

        return SparseEntries.parcelableEntry(getKey(), mValue);
    }

    @Override
    protected SparseBooleanArrayEntry getElementAt(final int position) {

        try {

            mValue = mSparseArray.valueAt(position);

        } catch (final IndexOutOfBoundsException e) {

            throw new NoSuchElementException();
        }

        mPosition = position;

        return this;
    }

    @Override
    protected void removeElement() {

        mSparseArray.delete(getKey());
    }
}