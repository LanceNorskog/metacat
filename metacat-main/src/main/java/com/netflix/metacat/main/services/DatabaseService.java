/*
 * Copyright 2016 Netflix, Inc.
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *        http://www.apache.org/licenses/LICENSE-2.0
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.netflix.metacat.main.services;

import com.netflix.metacat.common.QualifiedName;
import com.netflix.metacat.common.dto.DatabaseCreateRequestDto;
import com.netflix.metacat.common.dto.DatabaseDto;

public interface DatabaseService {
    void create(QualifiedName name, DatabaseCreateRequestDto databaseCreateRequestDto);

    void update(QualifiedName name, DatabaseCreateRequestDto databaseCreateRequestDto);

    void delete(QualifiedName name);

    DatabaseDto get(QualifiedName name, boolean includeUserMetadata);

    boolean exists(QualifiedName name);
}
