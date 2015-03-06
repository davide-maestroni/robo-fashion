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
package com.gh.bmd.rfc.android.internal;

import android.annotation.TargetApi;
import android.util.LongSparseArray;

import com.gh.bmd.rfc.android.entry.LongSparseArrayEntry;
import com.gh.bmd.rfc.android.entry.LongSparseObjectEntry;
import com.gh.bmd.rfc.android.entry.ParcelableLongSparseObjectEntry;
import com.gh.bmd.rfc.android.entry.SparseEntries;

import java.util.NoSuchElementException;

/**
 * Same as {@link LongSparseArrayIterator} but looping the elements in the reverse order.
 * <p/>
 * Created by davide on 3/10/14.
 *
 * @param <V> the entry value type.
 */
@TargetApi(16)
class LongSparseArrayReverseIterator<V>
        extends AbstractReverseSparseIterator<LongSparseArrayEntry<V>>
        implements LongSparseArrayEntry<V> {

    private final LongSparseArray<V> mSparseArray;

    private int mPosition;

    private V mValue;

    public LongSparseArrayReverseIterator(final LongSparseArray<V> array) {

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
    public V getValue() {

        return mValue;
    }

    @Override
    public void setValue(final V value) {

        mSparseArray.setValueAt(mPosition, value);

        mValue = value;
    }

    @Override
    public LongSparseObjectEntry<V> toImmutable() {

        return SparseEntries.entry(getKey(), mValue);
    }

    @Override
    public ParcelableLongSparseObjectEntry<V> toParcelable() {

        return SparseEntries.parcelableEntry(getKey(), mValue);
    }

    @Override
    protected LongSparseArrayEntry<V> getElementAt(final int position) {

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