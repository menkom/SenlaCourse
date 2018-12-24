package info.mastera.dao.impl;

import info.mastera.dao.IServiceOrderDao;
import info.mastera.model.ServiceOrder;
import info.mastera.model.ServiceOrder_;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class ServiceOrderDao extends AbstractDao<ServiceOrder> implements IServiceOrderDao<ServiceOrder> {

    @Override
    protected Class<ServiceOrder> getTClass() {
        return ServiceOrder.class;
    }

    @Override
    public List<ServiceOrder> getAllAndProductAndCustomer() {
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ServiceOrder> query = builder.createQuery(getTClass());
        Root<ServiceOrder> root = query.from(getTClass());
        root.fetch(ServiceOrder_.product, JoinType.LEFT);
        root.fetch(ServiceOrder_.customer, JoinType.LEFT);
        query.select(root);
        TypedQuery<ServiceOrder> result = session.createQuery(query);
        return result.getResultList();
    }

    @Override
    public ServiceOrder getByIdAndPartsAndJobs(int id) {
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ServiceOrder> query = builder.createQuery(getTClass());
        Root<ServiceOrder> root = query.from(getTClass());
        query.select(root).where(builder.equal(root.get(ServiceOrder_.id), id));
//        root.fetch(ServiceOrder_.product, JoinType.LEFT);
        //TODO fetch??? or something else
        TypedQuery<ServiceOrder> typedQuery = session.createQuery(query);
        ServiceOrder result = ((Query<ServiceOrder>) typedQuery).uniqueResult();
        return result;
    }


}
