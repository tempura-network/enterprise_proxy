/*
 * Copyright 2019 Enterprise Proxy Authors
 *
 * Licensed under the Apache License, Version 2.0 (the “License”);
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an “AS IS” BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.klaytn.enterpriseproxy.rpc.common.handler;

import com.klaytn.enterpriseproxy.rpc.common.model.RpcRequest;
import com.klaytn.enterpriseproxy.rpc.common.model.RpcResponse;
import feign.Headers;
import feign.RequestLine;


public interface RpcHandler {
    @RequestLine("POST /")
    @Headers("Content-Type: application/json")
    RpcResponse call(RpcRequest rpcRequest);
}