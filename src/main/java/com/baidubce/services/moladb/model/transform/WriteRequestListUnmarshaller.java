/*
 * Copyright (c) 2014 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.moladb.model.transform;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.baidubce.services.moladb.model.WriteRequest;
import com.fasterxml.jackson.databind.JsonNode;

public class WriteRequestListUnmarshaller implements
        Unmarshaller<List<WriteRequest>, JsonNode> {

    @Override
    public List<WriteRequest> unmarshall(JsonNode root) throws Exception {
        List<WriteRequest> list = new ArrayList<WriteRequest>();
        Iterator<JsonNode> reqs = root.elements();
        while (reqs.hasNext()) {
            WriteRequestUnmarshaller unmarshaller = new WriteRequestUnmarshaller();
            WriteRequest writeReq = unmarshaller.unmarshall(reqs.next());
            list.add(writeReq);
        }
        return list;
    }
}
