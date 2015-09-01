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

import android.support.v4.util.LongSparseArray;

import com.github.dm.rf.android.entry.LongSparseArrayEntry;
import com.github.dm.rf.android.entry.LongSparseObjectEntry;
import com.github.dm.rf.android.entry.ParcelableLongSparseObjectEntry;
import com.github.dm.rf.android.entry.SparseEntries;

import java.util.NoSuchElementException;

/**
 * Same as {@link SupportLongSparseArrayIterator} but looping the elements in the reverse order.
 * <p/>
 * Created by davide-maestroni on 3/10/14.
 *
 * @param <E> the element type.
 */
class SupportLongSparseArrayReverseIterator<E>
        extends AbstractReverseSparseIterator<LongSparseArrayEntry<E>>
        implements LongSparseArrayEntry<E> {

    private final LongSparseArray<E> mSparseArray;

    private int mPosition;

    private E mValue;

    public SupportLongSparseArrayReverseIterator(final LongSparseArray<E> array) {

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
    public String toString() {

        return getKey() + "=" + getValue();
    }

    @Override
    public int getIndex() {

        return mPosition;
    }

    @Override
    public long getKey() {

        return mSparseArray.keyAt(mPosition);
    }

    @Override
    public E getValue() {

        return mValue;
    }

    @Override
    public void setValue(final E value) {

        mSparseArray.setValueAt(mPosition, value);

        mValue = value;
    }

    @Override
    public LongSparseObjectEntry<E> toImmutable() {

        return SparseEntries.entry(getKey(), mValue);
    }

    @Override
    public ParcelableLongSparseObjectEntry<E> toParcelable() {

        return SparseEntries.parcelableEntry(getKey(), mValue);
    }

    @Override
    protected LongSparseArrayEntry<E> getElementAt(final int position) {

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