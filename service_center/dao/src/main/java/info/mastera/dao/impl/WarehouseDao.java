package info.mastera.dao.impl;

import info.mastera.dao.IWarehouseDao;
import info.mastera.model.Warehouse;
import info.mastera.model.Warehouse_;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class WarehouseDao extends AbstractDao<Warehouse> implements IWarehouseDao<Warehouse> {

    @Override
    protected Class<Warehouse> getTClass() {
        return Warehouse.class;
    }

    @Override
    public List<Warehouse> getAllAndPart() {
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Warehouse> query = builder.createQuery(getTClass());
        Root<Warehouse> root = query.from(getTClass());
        root.fetch(Warehouse_.part, JoinType.LEFT);
        query.select(root);
        TypedQuery<Warehouse> result = session.createQuery(query);
        return result.getResultList();
    }

}
