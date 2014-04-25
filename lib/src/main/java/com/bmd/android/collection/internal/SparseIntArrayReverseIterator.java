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

import android.util.SparseIntArray;

import com.bmd.android.collection.entry.IntSparseIntEntry;
import com.bmd.android.collection.entry.ParcelableIntSparseIntEntry;
import com.bmd.android.collection.entry.SparseEntries;
import com.bmd.android.collection.entry.SparseIntArrayEntry;

import java.util.NoSuchElementException;

/**
 * Same as {@link com.bmd.android.collection.internal.SparseIntArrayIterator} but looping the
 * elements in the reverse order.
 * <p/>
 * Created by davide on 3/10/14.
 */
class SparseIntArrayReverseIterator extends AbstractReverseSparseIterator<SparseIntArrayEntry>
        implements SparseIntArrayEntry {

    private final SparseIntArray mSparseArray;

    private int mPosition;

    private int mValue;

    public SparseIntArrayReverseIterator(final SparseIntArray array) {

        super(array.size());

        mSparseArray = array;
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
    public int getKey() {

        return mSparseArray.keyAt(mPosition);
    }

    @Override
    public int getValue() {

        return mValue;
    }

    @Override
    public void setValue(final int value) {

        mSparseArray.put(getKey(), value);

        mValue = value;
    }

    @Override
    public IntSparseIntEntry toImmutable() {

        return SparseEntries.entry(getKey(), mValue);
    }

    @Override
    public ParcelableIntSparseIntEntry toParcelable() {

        return SparseEntries.parcelableEntry(getKey(), mValue);
    }

    @Override
    protected SparseIntArrayEntry getElementAt(final int position) {

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

        mSparseArray.removeAt(mPosition);
    }
}