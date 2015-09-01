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

/**
 * This interface defines a {@link SparseEntry} associated with a
 * {@link android.support.v4.util.SimpleArrayMap} collection.
 * <p/>
 * Created by davide-maestroni on 3/10/14.
 *
 * @param <K> the key type.
 * @param <V> the value type.
 */
public interface SimpleArrayMapEntry<K, V> extends ObjectSparseObjectEntry<K, V> {

    /**
     * Returns the index inside the backing array map.
     *
     * @return the entry index.
     */
    public int getIndex();

    /**
     * Removes this entry from the backing array map.
     * <p/>
     * It has the same effect as calling {@link java.util.Iterator#remove()}.
     */
    public void remove();

    /**
     * Modifies the value inside the array map associated with this entry key.
     *
     * @param value the new value.
     */
    public void setValue(V value);

    /**
     * Returns an immutable copy of this entry. The object will just store this entry key and value
     * without allowing any modification of the backing array map.
     *
     * @return the immutable entry.
     */
    public ObjectSparseObjectEntry<K, V> toImmutable();

    /**
     * Returns a parcelable copy of this entry. The object will just store this entry key and value
     * without allowing any modification of the backing array map.
     * <p/>
     * Note that the {@link android.os.Parcel#writeValue(Object)} function will be called to
     * serialize the entry key and value. So, if any of the two does not comply with the specific
     * requirements, the serialization might have unexpected results.
     *
     * @return the parcelable entry.
     */
    public ParcelableObjectSparseObjectEntry<K, V> toParcelable();
}