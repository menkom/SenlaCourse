package info.mastera;

import info.mastera.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;


@ManagedBean
@SessionScoped
public class CustomerController implements Serializable {

    @Autowired
    private ICustomerService customerService;

    private String inputText;

    public String getInputText() {
        return inputText;
    }

    public void setInputText(String inputText) {
        this.inputText = inputText;
    }

    @Override
    public String toString() {
        return "CustomerController{}";
    }

    public void showMessage() {
        FacesMessage message = new FacesMessage("Заголовок", "Частичное обновление страницы");
        message.setSeverity(FacesMessage.SEVERITY_INFO); //как выглядит окошко с сообщением

        FacesContext.getCurrentInstance().addMessage(null, message);

//        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,
//                "Значение", String.valueOf(customerService.count())));

    }
}
