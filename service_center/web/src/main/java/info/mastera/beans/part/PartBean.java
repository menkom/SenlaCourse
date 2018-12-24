package info.mastera.beans.part;

import info.mastera.beans.base.BaseBean;
import info.mastera.service.IManufacturerService;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class PartBean extends BaseBean {

    @Inject
    private IManufacturerService manufacturerService;


}
