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
package com.gh.bmd.rfc.android.filter;

import com.gh.bmd.rfc.android.entry.SparseArrayEntry;
import com.gh.bmd.rfc.android.iterator.SparseArrayCompatIterable;

import java.util.Collection;

/**
 * This interface extends the {@link FilterBuilder} one by providing specific methods handling
 * {@link com.gh.bmd.rfc.android.entry.SparseArrayEntry} elements.
 * <p/>
 * Created by davide on 3/16/14.
 *
 * @param <V> the entry value type.
 */
public interface SparseArrayCompatFilterBuilder<V>
        extends FilterBuilder<SparseArrayCompatIterable<V>, SparseArrayEntry<V>> {

    /**
     * Creates a filter matching the specified entry key.
     *
     * @param key the key to match.
     * @return the filtered iterable.
     */
    public SparseArrayCompatIterable<V> key(int key);

    /**
     * Creates a filter matching the specified entry keys.
     *
     * @param keys the keys to match.
     * @return the filtered iterable.
     */
    public SparseArrayCompatIterable<V> keys(int... keys);

    /**
     * Creates a filter matching the specified collection of entry keys.
     *
     * @param keys the keys to match.
     * @return the filtered iterable.
     */
    public SparseArrayCompatIterable<V> keys(Collection<Integer> keys);

    /**
     * Creates a filter matching the entry keys returned by the specified iterable.
     *
     * @param keys the iterable of keys to match.
     * @return the filtered iterable.
     */
    public SparseArrayCompatIterable<V> keys(Iterable<Integer> keys);

    /**
     * Creates a filter matching the specified entry value.
     *
     * @param value the value to match.
     * @return the filtered iterable.
     */
    public SparseArrayCompatIterable<V> value(Object value);

    /**
     * Creates a filter matching the specified entry values.
     *
     * @param values the values to match.
     * @return the filtered iterable.
     */
    public SparseArrayCompatIterable<V> values(Object... values);

    /**
     * Creates a filter matching the specified collection of entry values.
     *
     * @param values the values to match.
     * @return the filtered iterable.
     */
    public SparseArrayCompatIterable<V> values(Collection<Object> values);

    /**
     * Creates a filter matching the entry values returned by the specified iterable.
     *
     * @param values the iterable of values to match.
     * @return the filtered iterable.
     */
    public SparseArrayCompatIterable<V> values(Iterable<Object> values);
}