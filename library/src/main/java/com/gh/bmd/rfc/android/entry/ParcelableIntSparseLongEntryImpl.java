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
package com.gh.bmd.rfc.android.entry;

import android.os.Parcel;

/**
 * Implementation of {@link ParcelableIntSparseLongEntry}.
 * <p/>
 * Created by davide on 3/18/14.
 */
class ParcelableIntSparseLongEntryImpl implements ParcelableIntSparseLongEntry {

    public static final Creator<ParcelableIntSparseLongEntryImpl> CREATOR =
            new Creator<ParcelableIntSparseLongEntryImpl>() {

                public ParcelableIntSparseLongEntryImpl createFromParcel(final Parcel parcel) {

                    return new ParcelableIntSparseLongEntryImpl(parcel);
                }

                public ParcelableIntSparseLongEntryImpl[] newArray(final int size) {

                    return new ParcelableIntSparseLongEntryImpl[size];
                }
            };

    private final int mKey;

    private final long mValue;

    public ParcelableIntSparseLongEntryImpl(final int key, final long value) {

        mKey = key;
        mValue = value;
    }

    public ParcelableIntSparseLongEntryImpl(final Parcel parcel) {

        mKey = parcel.readInt();
        mValue = parcel.readLong();
    }

    @Override
    public int describeContents() {

        return 0;
    }

    @Override
    public void writeToParcel(final Parcel parcel, final int i) {

        parcel.writeInt(mKey);
        parcel.writeLong(mValue);
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
    public long getValue() {

        return mValue;
    }
}