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
 * This interface extends {@link com.bmd.android.collection.translator.IntTranslator} by
 * adding the reverse translation of the value.
 * <p/>
 * Created by davide on 3/31/14.
 */
public interface FullIntTranslator extends IntTranslator {

    /**
     * Reverts the translation of the specified value.
     *
     * @param value The value to translate back.
     * @return The reverted value.
     */
    public int revert(int value);
}