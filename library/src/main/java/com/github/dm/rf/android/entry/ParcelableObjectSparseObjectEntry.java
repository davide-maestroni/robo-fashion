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

import android.os.Parcelable;

/**
 * This interface defines a parcelable {@link ObjectSparseObjectEntry}.
 * <p/>
 * Created by davide-maestroni on 3/10/14.
 *
 * @param <K> the key type.
 * @param <V> the value type.
 * @see android.os.Parcelable
 */
public interface ParcelableObjectSparseObjectEntry<K, V>
        extends ObjectSparseObjectEntry<K, V>, Parcelable {

}