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

import com.gh.bmd.rfc.android.entry.SimpleArrayMapEntry;
import com.gh.bmd.rfc.android.iterator.SimpleArrayMapIterable;

import java.util.Collection;

/**
 * This interface extends the {@link FilterBuilder} one by providing specific methods handling
 * {@link com.gh.bmd.rfc.android.entry.SimpleArrayMapEntry} elements.
 * <p/>
 * Created by davide on 3/16/14.
 *
 * @param <K> The entry key type.
 * @param <V> The entry value type.
 */
public interface SimpleArrayMapFilterBuilder<K, V>
        extends FilterBuilder<SimpleArrayMapIterable<K, V>, SimpleArrayMapEntry<K, V>> {

    /**
     * Creates a filter matching the specified entry key.
     *
     * @param key the key to match.
     * @return the filtered iterable.
     */
    public SimpleArrayMapIterable<K, V> key(Object key);

    /**
     * Creates a filter matching the specified entry keys.
     *
     * @param keys the keys to match.
     * @return the filtered iterable.
     */
    public SimpleArrayMapIterable<K, V> keys(Object... keys);

    /**
     * Creates a filter matching the specified collection of entry keys.
     *
     * @param keys the keys to match.
     * @return the filtered iterable.
     */
    public SimpleArrayMapIterable<K, V> keys(Collection<?> keys);

    /**
     * Creates a filter matching the entry keys returned by the specified iterable.
     *
     * @param keys the iterable of keys to match.
     * @return the filtered iterable.
     */
    public SimpleArrayMapIterable<K, V> keys(Iterable<?> keys);

    /**
     * Creates a filter matching the specified entry value.
     *
     * @param value the value to match.
     * @return the filtered iterable.
     */
    public SimpleArrayMapIterable<K, V> value(Object value);

    /**
     * Creates a filter matching the specified entry values.
     *
     * @param values the values to match.
     * @return the filtered iterable.
     */
    public SimpleArrayMapIterable<K, V> values(Object... values);

    /**
     * Creates a filter matching the specified collection of entry values.
     *
     * @param values the values to match.
     * @return the filtered iterable.
     */
    public SimpleArrayMapIterable<K, V> values(Collection<?> values);

    /**
     * Creates a filter matching the entry values returned by the specified iterable.
     *
     * @param values the iterable of values to match.
     * @return the filtered iterable.
     */
    public SimpleArrayMapIterable<K, V> values(Iterable<?> values);
}