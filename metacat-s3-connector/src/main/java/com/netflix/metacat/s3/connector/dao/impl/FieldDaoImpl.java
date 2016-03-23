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

package com.netflix.metacat.s3.connector.dao.impl;

import com.netflix.metacat.s3.connector.dao.FieldDao;
import com.netflix.metacat.s3.connector.model.Field;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.persistence.EntityManager;

/**
 * Created by amajumdar on 1/2/15.
 */
public class FieldDaoImpl extends IdEntityDaoImpl<Field> implements FieldDao {
    @Inject
    public FieldDaoImpl(Provider<EntityManager> em) {
        super(em);
    }

    @Override
    protected Class<Field> getEntityClass() {
        return Field.class;
    }
}