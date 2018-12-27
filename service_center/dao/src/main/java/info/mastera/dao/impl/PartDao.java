package info.mastera.dao.impl;

import info.mastera.dao.IPartDao;
import info.mastera.model.Part;
import info.mastera.model.Part_;
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
public class PartDao extends AbstractDao<Part> implements IPartDao<Part> {

    @Override
    protected Class<Part> getTClass() {
        return Part.class;
    }

    @Override
    public List<Part> getAllWithPartCategoryAndProduct() {
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Part> query = builder.createQuery(getTClass());
        Root<Part> root = query.from(getTClass());
        root.fetch(Part_.partCategory, JoinType.LEFT);
        root.fetch(Part_.product, JoinType.LEFT);
        query.select(root);
        TypedQuery<Part> result = session.createQuery(query);
        return result.getResultList();
    }

    @Override
    public Part getByIdWithPartCategoryProduct(int id) {
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Part> query = builder.createQuery(getTClass());
        Root<Part> root = query.from(getTClass());
        root.fetch(Part_.partCategory, JoinType.LEFT);
        root.fetch(Part_.product, JoinType.LEFT);
        query.select(root).where(builder.equal(root.get(Part_.id), id));
        TypedQuery<Part> typedQuery = session.createQuery(query);
        Part result = ((Query<Part>) typedQuery).uniqueResult();
        return result;
    }

}