/**
 * Copyright (c) 2011-2016, James Zhan 詹波 (jfinal@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springblade.core.plugins.redis.serializer;

/**
 * ISerializer.
 * @author jfinal
 */
public interface ISerializer {

    /**
     * keyToBytes
     * @param key
     * @return
     */
    byte[] keyToBytes(String key);

    /**
     * keyFromBytes
     * @param bytes
     * @return
     */
    String keyFromBytes(byte[] bytes);

    /**
     * fieldToBytes
     * @param field
     * @return
     */
    byte[] fieldToBytes(Object field);

    /**
     * fieldFromBytes
     * @param bytes
     * @return
     */
    Object fieldFromBytes(byte[] bytes);

    /**
     * valueToBytes
     * @param value
     * @return
     */
	byte[] valueToBytes(Object value);

    /**
     * valueFromBytes
     * @param bytes
     * @return
     */
	Object valueFromBytes(byte[] bytes);

    /**
     * serialize
     * @param value
     * @return
     */
    byte[] serialize(Object value);

    /**
     * deserialize
     * @param bytes
     * @return
     */
    Object deserialize(byte[] bytes);

    /**
     * mergeBytes
     * @param array1
     * @param array2
     * @return
     */
    byte[] mergeBytes(final byte[] array1, final byte... array2);
}



