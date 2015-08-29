/*
 * Copyright (C) 2011 The Android Open Source Project
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

package com.android.volley;

import java.util.Collections;
import java.util.Map;

/**
 * An interface for a cache keyed by a String with a byte array as data.
 * 缓存接口，代表了可以获取请求结果，存储请求结果的缓存
 */
public interface Cache {
    /**
     * Retrieves an entry from the cache. <br />
     * 通过key获取请求的缓存实体
     * @param key Cache key
     * @return An {@link Entry} or null in the event of a cache miss
     */
    public Entry get(String key);

    /**
     * Adds or replaces an entry to the cache.<br />
     * 存入或者替换一个请求的缓存实体
     * @param key Cache key
     * @param entry Data to store and metadata for cache coherency, TTL, etc.
     */
    public void put(String key, Entry entry);

    /**
     * Performs any potentially long-running actions needed to initialize the cache;
     * will be called from a worker thread.
     */
    public void initialize();

    /**
     * Invalidates an entry in the cache.<br />
     * 设置一个缓存实体是否到期无效
     * @param key Cache key
     * @param fullExpire True to fully expire the entry, false to soft expire
     */
    public void invalidate(String key, boolean fullExpire);

    /**
     * Removes an entry from the cache.<br />
     * 移除指定的缓存实体
     * @param key Cache key
     */
    public void remove(String key);

    /**
     * Empties the cache.<br />
     * 清空缓存
     */
    public void clear();

    /**
     * Data and metadata for an entry returned by the cache.
     */
    public static class Entry {
        /** The data returned from cache. */
        public byte[] data;  //请求返回的数据（Body实体）

        /** ETag for cache coherency. */
        public String etag; //Http响应首部中用于缓存新鲜度验证的ETag

        /** Date of this response as reported by the server. */
        public long serverDate;  //Http响应首部中的响应产生时间

        /** The last modified date for the requested object. */
        public long lastModified;  //被请求对象最近修改时间

        /** TTL for this record. */
        public long ttl;   //缓存过期时间

        /** Soft TTL for this record. */
        public long softTtl;  //缓存的新鲜时间

        /** Immutable response headers as received from server; must be non-null. */
        public Map<String, String> responseHeaders = Collections.emptyMap();  //响应的header，必须为非空

        /** True if the entry is expired. */
        public boolean isExpired() {
            return this.ttl < System.currentTimeMillis();
        }  //判断缓存是否过期，过期缓存不能继续使用

        /** True if a refresh is needed from the original data source. */
        //判断缓存是否新鲜，不新鲜的缓存需要发到服务器做新鲜度检测
        public boolean refreshNeeded() {
            return this.softTtl < System.currentTimeMillis();
        } //
    }

}
