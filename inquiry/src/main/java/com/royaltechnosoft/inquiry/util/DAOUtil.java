package com.royaltechnosoft.inquiry.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Id;
import javax.persistence.Transient;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class DAOUtil {
	private static Map<Class<?>, List<String>> fieldMap;
	private static Map<Class<?>, String> idNameMap;

	static {
		fieldMap = new HashMap<Class<?>, List<String>>();
		idNameMap = new HashMap<Class<?>, String>();
	}

	public static Criteria createCriteria(Object model, Session session) {
		Criteria criteria = session.createCriteria(model.getClass());
		Map<String, Object> fields = getFields(model);
		for (String key : fields.keySet()) {
			criteria.add(Restrictions.eq(key, fields.get(key)));
		}
		return criteria;
	}

	public static Criteria createCriteriaFromId(int id, Class<?> type,
			Session session) {
		Criteria criteria = session.createCriteria(type);
		criteria.add(Restrictions.eq(getIdFieldName(type), id));
		return criteria;
	}

	public static Object getUpdatedModel(Object oldModel, Object newModel) {
		return getUpdatedModels(new Object[] { oldModel }, newModel).get(0);
	}

	public static List<Object> getUpdatedModels(Object[] oldModels,
			Object newModel) {
		Class<?> type = newModel.getClass();
		if (!fieldMap.containsKey(type))
			findFieldsAndMethods(type);
		Map<String, Object> fields = getFields(newModel);

		List<Object> newModels = new ArrayList<Object>();
		for (Object oldModel : oldModels) {
			for (String key : fields.keySet()) {
				if (!idNameMap.get(newModel.getClass()).equals(key)) {
					try {
						BeanUtils.setProperty(oldModel, key, fields.get(key));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			newModels.add(oldModel);
		}
		return newModels;
	}

	/*
	 * Searches for all nun-null fields in a model class and returns a Map with
	 * key as field names and values with the value stored in them.
	 */
	private static Map<String, Object> getFields(Object model) {
		Class<?> type = model.getClass();
		Map<String, Object> nonNullFields = new HashMap<String, Object>();

		// Check if the fields for the class of model has been identified. If
		// not, load the field and method names for this class type.
		if (!fieldMap.containsKey(type))
			findFieldsAndMethods(type);

		List<String> fieldNames = fieldMap.get(type);

		// Check all the field value for the model object for non-null fields
		for (String fieldName : fieldNames) {
			
			Object value = null;
			// Get the value stored in a field.
			try {
				value = type.getMethod(getGetMethodName(fieldName)).invoke(
						model);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			// Check if the field is an instance of String and its value is not "" or null. If it is not, then add it to the map.
			if (value instanceof String) {
				if (((String) value).trim().length() != 0) {
					nonNullFields.put(fieldName, value);
				}
			}
			
			// Check if the field value is not null. If it is not, then add it to the map.
			else if (value != null)
				nonNullFields.put(fieldName, value);
		}
		return nonNullFields;
	}
	
	
	/*
	 * Look for all non-transient member variables in the class and put them into a static map
	 */
	private static void findFieldsAndMethods(Class<?> type) {
		List<String> fieldNames = new ArrayList<String>();
		Field[] fields = type.getDeclaredFields();
		for (Field field : fields) {
			if (field.getType().isPrimitive())
				continue;
			try {

				Annotation[] annotations = field.getAnnotations();
				for (Annotation annotation : annotations) {
					if (annotation.annotationType() == Transient.class)
						continue;
					else if (annotation.annotationType() == Id.class) {
						idNameMap.put(type, field.getName());
					}
				}

				type.getMethod(getGetMethodName(field.getName()));

			} catch (Exception e) {
				continue;
			}
			fieldNames.add(field.getName());
		}
		fieldMap.put(type, fieldNames);
	}

	private static String getGetMethodName(String fieldName) {
		char begin = Character.toUpperCase(fieldName.charAt(0));
		return "get" + begin + fieldName.substring(1);
	}

	public static String getIdFieldName(Class<?> type) {
		if (!idNameMap.containsKey(type))
			findFieldsAndMethods(type);
		return idNameMap.get(type);
	}

}
