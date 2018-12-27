package info.mastera.dao.impl;

import info.mastera.dao.IProductDao;
import info.mastera.model.Product;
import info.mastera.model.Product_;
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
public class ProductDao extends AbstractDao<Product> implements IProductDao<Product> {

    @Override
    protected Class<Product> getTClass() {
        return Product.class;
    }

    @Override
    public Product getByIdWithManufacturer(int id) {
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Product> query = builder.createQuery(getTClass());
        Root<Product> root = query.from(getTClass());
        root.fetch(Product_.manufacturer, JoinType.LEFT);
        query.select(root).where(builder.equal(root.get(Product_.id), id));
        TypedQuery<Product> typedQuery = session.createQuery(query);
        Product result = ((Query<Product>) typedQuery).uniqueResult();
        return result;
    }

    @Override
    public List<Product> getAllWithManufacturer() {
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Product> query = builder.createQuery(getTClass());
        Root<Product> root = query.from(getTClass());
        root.fetch(Product_.manufacturer, JoinType.LEFT);
        query.select(root);
        TypedQuery<Product> result = session.createQuery(query);
        return result.getResultList();
    }

}
