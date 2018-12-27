package info.mastera.service;

import info.mastera.model.Part;

import java.util.List;

public interface IPartService {
    Part create(Part entity);

    void delete(Part entity);

    void update(Part entity);

    Part getById(int id);

    List<Part> getAll();

    Long count();

    List<Part> getAllWithPartCategoryAndProduct();

    Part getByIdWithPartCategoryAndProduct(int id);

}
