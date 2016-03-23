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

import com.facebook.presto.exception.CatalogNotFoundException;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.netflix.metacat.s3.connector.dao.SourceDao;
import com.netflix.metacat.s3.connector.model.Source;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.persistence.EntityManager;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by amajumdar on 1/2/15.
 */
public class SourceDaoImpl extends IdEntityDaoImpl<Source> implements SourceDao {
    @Inject
    public SourceDaoImpl(Provider<EntityManager> em) {
        super(em);
    }

    LoadingCache<String, Source> sourceCache = CacheBuilder.newBuilder().expireAfterWrite( 120, TimeUnit.MINUTES).build(
            new CacheLoader<String, Source> () {
                @Override
                public Source load(String name) throws Exception {
                    return loadSource(name);
                }
            });

    @Override
    protected Class<Source> getEntityClass() {
        return Source.class;
    }

    private Source loadSource(String name){
        return super.getByName(name);
    }

    public Source getByName(String name){
        Source result = null;
        try {
            result = sourceCache.get(name);
        } catch (ExecutionException ignored) {
            //
        }
        if( result == null){
            throw new CatalogNotFoundException(name);
        }
        return result;
    }

    public Source getByName(String name, boolean fromCache){
        if(!fromCache){
            sourceCache.invalidate(name);
        }
        return getByName(name);
    }
}