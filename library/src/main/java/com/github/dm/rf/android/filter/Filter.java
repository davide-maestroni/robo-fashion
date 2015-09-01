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
package com.github.dm.rf.android.filter;

/**
 * This interface defines a filter used to include or exclude elements, matching a specific
 * condition, from the iteration.
 * <p/>
 * Created by davide-maestroni on 3/14/14.
 *
 * @param <E> the element type.
 */
public interface Filter<E> {

    /**
     * Returns true if the specified element matches with the condition.
     *
     * @param element the element to check.
     * @param count   the number of element iterated until now.
     * @param index   the index of the element in the sparse collection.
     * @return whether the elements matches the condition.
     */
    public boolean matches(E element, int count, int index);
}