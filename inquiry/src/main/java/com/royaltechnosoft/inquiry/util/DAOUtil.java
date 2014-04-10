package com.royaltechnosoft.inquiry.util;

import java.lang.annotation.Annotation;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Id;
import javax.persistence.Transient;

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
		for (String key : fields.keySet()){
			criteria.add(Restrictions.eq(key, fields.get(key)));
		}
		return criteria;
	}
	
	public static Criteria createCriteriaFromId(int id, Class<?> type, Session session) {
		Criteria criteria = session.createCriteria(type);
		criteria.add(Restrictions.eq(getIdFieldName(type),id));
		return criteria;
	}
	
	public static Object getUpdatedModel(Object oldModel, Object newModel) {
		return getUpdatedModels(new Object[]{oldModel}, newModel).get(0);
	}
	
	public static List<Object> getUpdatedModels(Object[] oldModels, Object newModel) {
		Class<?> type = newModel.getClass();
		if(!fieldMap.containsKey(type))
			findFieldsAndMethods(type);
		Map<String, Object> fields = getFields(newModel);
		
		List<Object> newModels = new ArrayList<Object>();
		for (Object oldModel : oldModels) {
			for (String key : fields.keySet()){
				if(!idNameMap.get(newModel.getClass()).equals(key)){
					for (String fieldName: fieldMap.get(type)) {
						try {
							type.getMethod(getSetMethodName(fieldName)).invoke(oldModel,fields.get(key));
						} catch (Exception e) { e.printStackTrace(); }
					}
					
				}
			}
			newModels.add(oldModel);
		}
		return newModels;
	}
	
	private static Map<String, Object> getFields(Object model){
		Class<?> type = model.getClass();
		Map<String, Object> nonNullFields = new HashMap<String, Object>();
		if(!fieldMap.containsKey(type))
			findFieldsAndMethods(type);
		
		List<String> fieldNames = fieldMap.get(type);
		for (String fieldName: fieldNames) {
			Object value = null;
			try {
				value = type.getMethod(getGetMethodName(fieldName)).invoke(model);
			} catch (Exception e) { e.printStackTrace(); }
			if(value!=null)
				nonNullFields.put(fieldName, value);
		}
		return nonNullFields;
	}
	
	private static void findFieldsAndMethods(Class<?> type) {
		List<String> fieldNames = new ArrayList<String>();
		Field[] fields = type.getDeclaredFields();
		for (Field field : fields) {
			if(field.getType().isPrimitive()) continue;
			try {
				
				Annotation[] annotations = field.getAnnotations();
				for (Annotation annotation : annotations){
					if(annotation.annotationType()==Transient.class) continue;
					else if(annotation.annotationType()==Id.class){
						idNameMap.put(type, field.getName());
					}
				}
				
				type.getMethod(getGetMethodName(field.getName()));
				
			} catch (Exception e) { continue; }
			fieldNames.add(field.getName());
		}
		fieldMap.put(type, fieldNames);
	}
	
	private static String getGetMethodName(String fieldName){
		char begin = Character.toUpperCase(fieldName.charAt(0));
		return "get"+begin+fieldName.substring(1);
	}
	
	private static String getSetMethodName(String fieldName){
		char begin = Character.toUpperCase(fieldName.charAt(0));
		return "get"+begin+fieldName.substring(1);
	}
	
	public static String getIdFieldName(Class<?> type){
		if(!idNameMap.containsKey(type))
			findFieldsAndMethods(type);
		return idNameMap.get(type);
	}

}
