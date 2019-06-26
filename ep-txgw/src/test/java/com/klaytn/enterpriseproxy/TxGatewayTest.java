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
package com.klaytn.enterpriseproxy;

import com.klaytn.enterpriseproxy.txgw.model.RequestLog;
import com.klaytn.enterpriseproxy.txgw.model.RequestParamsLog;
import com.klaytn.enterpriseproxy.txgw.request.MetaSeparate;
import com.klaytn.enterpriseproxy.txgw.request.ParameterSeparate;
import com.klaytn.enterpriseproxy.config.TestLogDatabaseConfig;
import com.klaytn.enterpriseproxy.utils.CollectionUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest
@SpringBootConfiguration
@Import(TestLogDatabaseConfig.class)
public class TxGatewayTest {
    private static final Logger logger = LoggerFactory.getLogger(TxGatewayTest.class);


    @Mock
    private HttpServletRequest request;

    @Autowired
    private MetaSeparate metaSeparate;


    @Autowired
    private ParameterSeparate parameterSeparate;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void REQUEST_LOG_SEPARATE_TEST() throws Exception {
        when(request.getMethod()).thenReturn("POST");
        when(request.getContentType()).thenReturn("text/html");

        Map<String,String[]> params = CollectionUtil.createHashMap();
        params.put("param1",new String[]{"true"});
        params.put("param2",new String[]{"false"});
        when(request.getParameterMap()).thenReturn(params);
        when(request.getParameter("param1")).thenReturn("true");
        when(request.getParameter("param2")).thenReturn("false");

        RequestLog requestLog = metaSeparate.execution(request);
        RequestParamsLog requestParamsLog = parameterSeparate.execution(request);

        logger.info("@@@@ REQUEST LOG : " + requestLog.toString() + " @@@@");
        logger.info("@@@@ REQUEST PARAMS LOG : " + requestParamsLog.toString() + " @@@@");
    }


    @SpringBootApplication
    static class init {

    }
}