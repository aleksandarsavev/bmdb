/*
 * EntityService.java
 *
 * created at 8.01.2016 г. by Aleksandar Savev a.savev@seeburger.com;
 *
 * Copyright (c) 2016 SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.bmdb.persist;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;


public abstract class EntityService
{
    private EntityManager entityManager;


    protected EntityService(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }


    protected EntityManager getEntityManager()
    {
        return entityManager;
    }


    protected <T> List<T> getEntities(Class<T> clas)
    {
        TypedQuery<T> createQuery = entityManager.createQuery(entityManager.getCriteriaBuilder()
                                                                           .createQuery(clas));
        return createQuery.getResultList();
    }


    protected <T> T getById(int id, Class<T> entityClass)
    {
        return getByPrimaryKey(id, "id", Integer.class, entityClass);
    }


    protected <T, V> T getByPrimaryKey(V primaryValue,
                                       String primaryKey,
                                       Class<V> primaryKeyClass,
                                       Class<T> entityClass)
    {
        List<T> results = filterEntities(primaryValue, primaryKey, primaryKeyClass, entityClass);
        return results.isEmpty() ? null : results.get(0);
    }


    protected <T, V> List<T> filterEntities(V filter,
                                            String filterName,
                                            Class<V> filterClass,
                                            Class<T> entityClass)
    {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<T> q = cb.createQuery(entityClass);
        Root<T> c = q.from(entityClass);
        ParameterExpression<V> p = cb.parameter(filterClass);
        q.select(c).where(cb.equal(c.get(filterName), p));

        TypedQuery<T> query = getEntityManager().createQuery(q);
        query.setParameter(p, filter);
        List<T> results = query.getResultList();
        return results;
    }


    protected <T> void addEntity(T entity)
    {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }


    protected <T> void removeEntity(T entity)
    {
        entityManager.getTransaction().begin();
        entityManager.remove(entity);
        entityManager.getTransaction().commit();
    }
}
