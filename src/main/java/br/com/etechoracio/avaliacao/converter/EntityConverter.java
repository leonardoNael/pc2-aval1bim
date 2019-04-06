package br.com.etechoracio.avaliacao.converter;

import java.io.Serializable;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.stereotype.Component;

import br.com.etechoracio.common.model.BaseORM;

@Component(value = "entityConverter")
public class EntityConverter implements Converter {

	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value != null) {
			return this.getAttributesFrom(component).get(value);
		}
		return null;
	}

	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value != null && !"".equals(value)) {

			BaseORM entity = (BaseORM) value;

			if (entity.getId() == null) {
				return "";
			}

			// adiciona item como atributo do componente
			this.addAttribute(component, entity);

			Serializable codigo = entity.getId();
			if (codigo != null) {
				return String.valueOf(codigo);
			}
		}
		return (String) value;
	}

	protected void addAttribute(UIComponent component, BaseORM o) {
		String key = o.getId().toString(); 
		this.getAttributesFrom(component).put(key, o);
	}

	protected Map<String, Object> getAttributesFrom(UIComponent component) {
		return component.getAttributes();
	}

}
