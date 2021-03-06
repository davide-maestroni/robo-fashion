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
package com.github.dm.rf.android.entry;

import android.os.Parcel;

/**
 * Implementation of {@link ParcelableIntSparseIntEntry}.
 * <p/>
 * Created by davide-maestroni on 3/18/14.
 */
class ParcelableIntSparseIntEntryImpl implements ParcelableIntSparseIntEntry {

    public static final Creator<ParcelableIntSparseIntEntryImpl> CREATOR =
            new Creator<ParcelableIntSparseIntEntryImpl>() {

                public ParcelableIntSparseIntEntryImpl createFromParcel(final Parcel parcel) {

                    return new ParcelableIntSparseIntEntryImpl(parcel);
                }

                public ParcelableIntSparseIntEntryImpl[] newArray(final int size) {

                    return new ParcelableIntSparseIntEntryImpl[size];
                }
            };

    private final int mKey;

    private final int mValue;

    public ParcelableIntSparseIntEntryImpl(final int key, final int value) {

        mKey = key;
        mValue = value;
    }

    public ParcelableIntSparseIntEntryImpl(final Parcel parcel) {

        mKey = parcel.readInt();
        mValue = parcel.readInt();
    }

    @Override
    public int describeContents() {

        return 0;
    }

    @Override
    public void writeToParcel(final Parcel parcel, final int i) {

        parcel.writeInt(mKey);
        parcel.writeInt(mValue);
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
    public int getKey() {

        return mKey;
    }

    @Override
    public int getValue() {

        return mValue;
    }
}