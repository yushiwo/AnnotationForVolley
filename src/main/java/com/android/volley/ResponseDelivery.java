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

/**
 * 请求结果传输接口，用于传输请求结果或者请求错误
 */
public interface ResponseDelivery {
    /**
     * Parses a response from the network or cache and delivers it.
     */
    /**
     * 传递请求结果
     * @param request  请求消息
     * @param response  返回消息
     */
    public void postResponse(Request<?> request, Response<?> response);

    /**
     * Parses a response from the network or cache and delivers it. The provided
     * Runnable will be executed after delivery.
     */

    /**
     * 传递请求结果，传递完成后执行runnable
     * @param request
     * @param response
     * @param runnable
     */
    public void postResponse(Request<?> request, Response<?> response, Runnable runnable);

    /**
     * Posts an error for the given request.
     */
    /**
     * 传输请求错误
     * @param request
     * @param error
     */
    public void postError(Request<?> request, VolleyError error);
}
