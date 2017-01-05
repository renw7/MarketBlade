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

package com.smallchill.core.toolbox.redis.serializer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import redis.clients.util.SafeEncoder;

import com.smallchill.core.toolbox.kit.LogKit;

/**
 * RedisSerializer.
 */
public class RedisSerializer implements ISerializer {
	
	public static final ISerializer me = new RedisSerializer();
	
	public byte[] keyToBytes(String key) {
		return SafeEncoder.encode(key);
	}
	
	public String keyFromBytes(byte[] bytes) {
		return SafeEncoder.encode(bytes);
	}
	
	public byte[] fieldToBytes(Object field) {
		return valueToBytes(field);
	}
	
    public Object fieldFromBytes(byte[] bytes) {
    	return valueFromBytes(bytes);
    }
	
	public byte[] valueToBytes(Object value) {
		ByteArrayOutputStream byteOut = null;
		ObjectOutputStream ObjOut = null;
		try {
			byteOut = new ByteArrayOutputStream();
			ObjOut = new ObjectOutputStream(byteOut);
			ObjOut.writeObject(value);
			ObjOut.flush();
			return byteOut.toByteArray();
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
		finally {
			try {
				if (null != ObjOut) {
					ObjOut.close();
				}
			}
			catch (IOException e) {
				ObjOut = null; LogKit.error(e.getMessage(), e);
			}
		}
	}
	
	public Object valueFromBytes(byte[] bytes) {
		if(bytes == null || bytes.length == 0) {
			return null;			
		}
		ObjectInputStream ObjIn = null;
		Object retVal = null;
		try {
			ByteArrayInputStream byteIn = new ByteArrayInputStream(bytes);
			ObjIn = new ObjectInputStream(byteIn);
			retVal = ObjIn.readObject();
			return retVal;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
		finally {
			try {
				if(null != ObjIn) {
					ObjIn.close();
				}
			}
			catch (IOException e) {
				ObjIn = null; LogKit.error(e.getMessage(), e);
			}
		}
	}
	
}



