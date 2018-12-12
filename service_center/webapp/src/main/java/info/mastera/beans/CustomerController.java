package info.mastera.beans;

import org.springframework.context.annotation.Scope;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;


@Named
@Scope("session")
public class CustomerController {

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
        message.setSeverity(FacesMessage.SEVERITY_INFO); //так выглядит окошко с сообщением

        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
