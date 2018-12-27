package info.mastera.dao;

import info.mastera.model.Part;

import java.util.List;

public interface IPartDao<T extends Part> extends IGenericDao<T> {

    List<Part> getAllWithPartCategoryAndProduct();

    Part getByIdWithPartCategoryProduct(int id);

}

