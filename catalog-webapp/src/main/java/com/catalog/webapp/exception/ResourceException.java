/*
 * Copyright (c) 2015. All Rights Reserved
 * You may use, distribute and modify this code under the
 * terms of the Apache License, Version 2.0 (the "License")
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * You should have received a copy of the "License" with this file.
 * If not, please obtain a copy here http://www.apache.org/licenses/LICENSE-2.0
 */

package com.catalog.webapp.exception;

import javax.xml.ws.WebServiceException;

/**
 * Exception for resources
 * Created by Lance on 22/02/2015.
 */
public class ResourceException extends WebServiceException {

    private static final long serialVersionUID = 4958788623894530733L;

    public ResourceException(Exception e) {
        super(e);
    }
}
