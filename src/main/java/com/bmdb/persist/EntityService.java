/*
 * EntityService.java
 *
 * created at 8.01.2016 г. by savevjr@gmail.com
 *
 * Copyright (c) 2016
 */
package com.bmdb.persist;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;


public abstract class EntityService<TEntity>
{
    private final EntityManager entityManager;
    private final Class<TEntity> entityClass;


    protected EntityService(EntityManager entityManager, Class<TEntity> entityClass)
    {
        this.entityManager = entityManager;
        this.entityClass = entityClass;
    }


    protected EntityManager getEntityManager()
    {
        return entityManager;
    }


    protected List<TEntity> getEntities()
    {
        TypedQuery<TEntity> createQuery = entityManager.createQuery(entityManager.getCriteriaBuilder()
                                                                                 .createQuery(entityClass));
        return createQuery.getResultList();
    }


    protected TEntity getById(int id)
    {
        return getByPrimaryKey(id, "id", Integer.class);
    }


    protected <V> TEntity getByPrimaryKey(V primaryValue, String primaryKey, Class<V> primaryKeyClass)
    {
        List<TEntity> results = filterEntities(primaryValue, primaryKey, primaryKeyClass);
        return results.isEmpty() ? null : results.get(0);
    }


    protected <V> List<TEntity> filterEntities(V filter, String filterName, Class<V> filterClass)
    {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<TEntity> q = cb.createQuery(entityClass);
        Root<TEntity> c = q.from(entityClass);
        ParameterExpression<V> p = cb.parameter(filterClass);
        q.select(c).where(cb.equal(c.get(filterName), p));

        TypedQuery<TEntity> query = getEntityManager().createQuery(q);
        query.setParameter(p, filter);
        List<TEntity> results = query.getResultList();
        return results;
    }


    protected void addEntity(TEntity entity)
    {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }


    protected void removeEntity(TEntity entity)
    {
        entityManager.getTransaction().begin();
        entityManager.remove(entity);
        entityManager.getTransaction().commit();
    }
}
