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
 * This interface extends {@link Translator} by adding the reverse translation of the element.
 * <p/>
 * Created by davide on 3/31/14.
 */
public interface FullTranslator<I, O> extends Translator<I, O> {

    /**
     * Reverts the translation of the specified element.
     *
     * @param element the element to translate back.
     * @return the reverted element.
     */
    public I revert(O element);
}