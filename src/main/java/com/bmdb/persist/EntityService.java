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


    protected <T> T getById(int id, Class<T> clas)
    {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<T> q = cb.createQuery(clas);
        Root<T> c = q.from(clas);
        ParameterExpression<Integer> p = cb.parameter(Integer.class);
        q.select(c).where(cb.equal(c.get("id"), p));

        TypedQuery<T> query = getEntityManager().createQuery(q);
        query.setParameter(p, id);
        List<T> results = query.getResultList();
        if (results.isEmpty())
            return null;
        return results.get(0);
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
