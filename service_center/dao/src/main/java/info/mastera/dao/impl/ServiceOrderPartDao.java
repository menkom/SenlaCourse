package info.mastera.dao.impl;

import info.mastera.dao.IServiceOrderPartDao;
import info.mastera.model.ServiceOrderPart;
import info.mastera.model.ServiceOrderPart_;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class ServiceOrderPartDao extends AbstractDao<ServiceOrderPart> implements IServiceOrderPartDao<ServiceOrderPart> {

    @Override
    protected Class<ServiceOrderPart> getTClass() {
        return ServiceOrderPart.class;
    }

    @Override
    public List<ServiceOrderPart> getAllAndUserWarehouse() {
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ServiceOrderPart> query = builder.createQuery(getTClass());
        Root<ServiceOrderPart> root = query.from(getTClass());
        root.fetch(ServiceOrderPart_.user, JoinType.LEFT);
        root.fetch(ServiceOrderPart_.warehouse, JoinType.LEFT);
        query.select(root);
        TypedQuery<ServiceOrderPart> result = session.createQuery(query);
        return result.getResultList();
    }

}
