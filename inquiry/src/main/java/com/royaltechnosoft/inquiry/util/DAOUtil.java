package com.royaltechnosoft.inquiry.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

public class DAOUtil {
	private static Map<Class<?>, List<String>> fieldMap;
	private static Map<Class<?>, String> idNameMap;
	
	static {
		fieldMap = new HashMap<Class<?>, List<String>>();
		idNameMap = new HashMap<Class<?>, String>();
	}
	
	public static Query getFieldsQuery(Object model) {
		Query query = new Query();
		Map<String, Object> fields = getFields(model);
		for (String key : fields.keySet())
			query.addCriteria(Criteria.where(key).is(fields.get(key)));
		return query;
	}
	
	public static Update getFieldsUpdate(Object model) {
		Update update = new Update();
		Map<String, Object> fields = getFields(model);
		if(fields.size()==0) return null;
		for (String key : fields.keySet()){
			if(!idNameMap.get(model.getClass()).equals(key))
				update.set(key,fields.get(key));
		}
		return update;
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
				value = type.getMethod(getMethodName(fieldName)).invoke(model);
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
				
				type.getMethod(getMethodName(field.getName()));
				
			} catch (Exception e) { continue; }
			fieldNames.add(field.getName());
		}
		fieldMap.put(type, fieldNames);
	}
	
	private static String getMethodName(String fieldName){
		char begin = Character.toUpperCase(fieldName.charAt(0));
		return "get"+begin+fieldName.substring(1);
	}
	
	public static String getIdFieldName(Class<?> type){
		if(!idNameMap.containsKey(type))
			findFieldsAndMethods(type);
		return idNameMap.get(type);
	}
	
}
