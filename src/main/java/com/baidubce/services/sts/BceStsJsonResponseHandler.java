/*
 * Copyright (c) 2016 Baidu.com, Inc. All Rights Reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.baidubce.services.sts;

import com.baidubce.http.BceHttpResponse;
import com.baidubce.http.handler.BceJsonResponseHandler;
import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.sts.model.GetSessionTokenResponse;
import com.baidubce.util.JsonUtils;

import java.io.InputStream;

/**
 * HTTP body json response handler for Baidu Sts responses.
 */
public class BceStsJsonResponseHandler extends BceJsonResponseHandler {

    public BceStsJsonResponseHandler() {
    }

    @Override
    public boolean handle(BceHttpResponse httpResponse, AbstractBceResponse response) throws Exception {
        if (response instanceof GetSessionTokenResponse) {
            GetSessionTokenResponse getSessionTokenResponse = (GetSessionTokenResponse) response;
            InputStream content = httpResponse.getContent();
            if (content != null) {
                if (response.getMetadata().getContentLength() > 0
                        || "chunked".equalsIgnoreCase(response.getMetadata().getTransferEncoding())) {
                    JsonUtils.load(content, getSessionTokenResponse);
                }
                content.close();
            }
            return true;
        }
        return super.handle(httpResponse, response);
    }
}
