package info.mastera.dao.impl;

import info.mastera.dao.IServiceOrderJobDao;
import info.mastera.model.ServiceOrderJob;
import info.mastera.model.ServiceOrderJob_;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class ServiceOrderJobDao extends AbstractDao<ServiceOrderJob> implements IServiceOrderJobDao<ServiceOrderJob> {

    @Override
    protected Class<ServiceOrderJob> getTClass() {
        return ServiceOrderJob.class;
    }

    @Override
    public List<ServiceOrderJob> getAllAndUserOrderJob() {
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ServiceOrderJob> query = builder.createQuery(getTClass());
        Root<ServiceOrderJob> root = query.from(getTClass());
        root.fetch(ServiceOrderJob_.user, JoinType.LEFT);
        root.fetch(ServiceOrderJob_.orderJob, JoinType.LEFT);
        query.select(root);
        TypedQuery<ServiceOrderJob> result = session.createQuery(query);
        return result.getResultList();
    }

}
