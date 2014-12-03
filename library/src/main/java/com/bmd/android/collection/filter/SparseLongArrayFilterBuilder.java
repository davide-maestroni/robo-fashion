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
package com.bmd.android.collection.filter;

import com.bmd.android.collection.entry.SparseLongArrayEntry;
import com.bmd.android.collection.iterator.SparseLongArrayIterable;

import java.util.Collection;

/**
 * This interface extends the {@link FilterBuilder} one by providing specific methods handling
 * {@link com.bmd.android.collection.entry.SparseLongArrayEntry} elements.
 * <p/>
 * Created by davide on 3/16/14.
 */
public interface SparseLongArrayFilterBuilder
        extends FilterBuilder<SparseLongArrayIterable, SparseLongArrayEntry> {

    /**
     * Creates a filter matching the specified entry key.
     *
     * @param key the key to match.
     * @return the filtered iterable.
     */
    public SparseLongArrayIterable key(int key);

    /**
     * Creates a filter matching the specified entry keys.
     *
     * @param keys the keys to match.
     * @return the filtered iterable.
     */
    public SparseLongArrayIterable keys(int... keys);

    /**
     * Creates a filter matching the specified collection of entry keys.
     *
     * @param keys the keys to match.
     * @return the filtered iterable.
     */
    public SparseLongArrayIterable keys(Collection<Integer> keys);

    /**
     * Creates a filter matching the entry keys returned by the specified iterable.
     *
     * @param keys the iterable of keys to match.
     * @return the filtered iterable.
     */
    public SparseLongArrayIterable keys(Iterable<Integer> keys);

    /**
     * Creates a filter matching the specified entry value.
     *
     * @param value the value to match.
     * @return the filtered iterable.
     */
    public SparseLongArrayIterable value(long value);

    /**
     * Creates a filter matching the specified entry values.
     *
     * @param values the values to match.
     * @return the filtered iterable.
     */
    public SparseLongArrayIterable values(long... values);

    /**
     * Creates a filter matching the specified collection of entry values.
     *
     * @param values the values to match.
     * @return the filtered iterable.
     */
    public SparseLongArrayIterable values(Collection<Long> values);

    /**
     * Creates a filter matching the entry values returned by the specified iterable.
     *
     * @param values the iterable of values to match.
     * @return the filtered iterable.
     */
    public SparseLongArrayIterable values(Iterable<Long> values);
}