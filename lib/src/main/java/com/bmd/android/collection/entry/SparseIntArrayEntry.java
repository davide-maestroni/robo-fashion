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
 * This interface defines a {@link com.bmd.android.collection.entry.SparseEntry} associated with a
 * {@link android.util.SparseIntArray} collection.
 * <p/>
 * Created by davide on 3/10/14.
 */
public interface SparseIntArrayEntry extends IntSparseIntEntry {

    /**
     * Returns the index inside the backing sparse collection.
     *
     * @return The entry index.
     */
    public int getIndex();

    /**
     * Removes this entry from the backing sparse collection.
     * <p/>
     * It has the same effect as calling <code>iterator.remove()</code>.
     */
    public void remove();

    /**
     * Modifies the value inside the sparse collection associated with this entry key.
     *
     * @param value The new value.
     */
    public void setValue(int value);

    /**
     * Returns an immutable copy of this entry. The object will just store this entry key and value
     * without allowing any modification of the backing sparse collection.
     *
     * @return The immutable entry.
     */
    public IntSparseIntEntry toImmutable();

    /**
     * Returns a parcelable copy of this entry. The object will just store this entry key and value
     * without allowing any modification of the backing sparse collection.
     *
     * @return The parcelable entry.
     */
    public ParcelableIntSparseIntEntry toParcelable();
}
