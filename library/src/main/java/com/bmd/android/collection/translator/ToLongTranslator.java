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
package com.bmd.android.collection.translator;

/**
 * This interface defines a translator of elements into long values.
 * <p/>
 * Created by davide on 3/14/14.
 *
 * @param <E> The element type.
 */
public interface ToLongTranslator<E> {

    /**
     * Translates the specified element.
     *
     * @param element The element to translate.
     * @return The translated element.
     */
    public long translate(E element);
}