package info.mastera.beans.customer;

import info.mastera.beans.base.BaseListBean;
import info.mastera.model.Customer;
import info.mastera.service.ICustomerService;
import org.springframework.context.annotation.Scope;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@ViewScoped
public class CustomerListBean extends BaseListBean<Customer> {

    @Inject
    private ICustomerService customerService;

    @Override
    public List<Customer> getAll() {
        return customerService.getAll();
    }

    @Override
    public void delete() {
        customerService.delete(getSelectedItem());
        if (getSelectedItem() != null) {
           addMessage(String.format(MESSAGE_ITEM_DELETED, getSelectedItem().getId()));
        }
        clearSelected();
    }

    @Override
    public void update() {
        customerService.update(getSelectedItem());
    }

}
