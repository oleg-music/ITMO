package Beans;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import java.math.BigDecimal;


@FacesConverter("doubleConverter")
public class YConverter implements Converter<BigDecimal> {

    @Override
    public BigDecimal getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null) return null;

        String s = value.trim();
        if (s.isEmpty()) return null;

        // принимаем и запятую, и точку
        s = s.replace(',', '.');

        try {
            // BigDecimal строго из строки (без потери точности)
            return new BigDecimal(s);
        } catch (NumberFormatException e) {
            throw new ConverterException(
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ошибка",
                            "Введите число. Можно использовать точку или запятую (например: 1.25 или 1,25).")
            );
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, BigDecimal value) {
        return value == null ? "" : value.toPlainString();
    }
}
