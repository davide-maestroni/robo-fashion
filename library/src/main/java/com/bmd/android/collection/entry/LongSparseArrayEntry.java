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
package com.bmd.android.collection.entry;

/**
 * This interface defines a {@link SparseEntry} associated with a
 * {@link android.util.LongSparseArray} collection.
 * <p/>
 * Created by davide on 3/10/14.
 *
 * @param <E> the value type.
 */
public interface LongSparseArrayEntry<E> extends LongSparseObjectEntry<E> {

    /**
     * Returns the index inside the backing sparse collection.
     *
     * @return the entry index.
     */
    public int getIndex();

    /**
     * Removes this entry from the backing sparse collection.
     * <p/>
     * It has the same effect as calling {@link java.util.Iterator#remove()}.
     */
    public void remove();

    /**
     * Modifies the value inside the sparse collection associated with this entry key.
     *
     * @param value the new value.
     */
    public void setValue(E value);

    /**
     * Returns an immutable copy of this entry. The object will just store this entry key and value
     * without allowing any modification of the backing sparse collection.
     *
     * @return the immutable entry.
     */
    public LongSparseObjectEntry<E> toImmutable();

    /**
     * Returns a parcelable copy of this entry. The object will just store this entry key and value
     * without allowing any modification of the backing sparse collection.
     * <p/>
     * Note that the {@link android.os.Parcel#writeValue(Object)} function will be called to
     * serialize the entry value. So, if the object does not comply with the specific requirements,
     * the serialization might have unexpected results.
     *
     * @return the parcelable entry.
     */
    public ParcelableLongSparseObjectEntry<E> toParcelable();
}