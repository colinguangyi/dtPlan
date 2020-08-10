package com.colin.server.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * @author zhaolz
 */
public class ReflectUtil {

	/**
	 * 通过属性名获取相应的属性值
	 */
	public static Object getFieldValue(Object object, String fieldName) {
		try {
	        Field field = getDeclaredField(object, fieldName);
	        if (field == null){
				throw new IllegalArgumentException("Could not find field ["
						+ fieldName + "] on target [" + object + "]");
			}
			makeAccessible(field);
			return field.get(object);

	    } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
		return null;
    }
	
	/**
	 * 设置属性值
	 */
	public static void setFieldValue(Object object, String fieldName, Object value) {
		try {
	        Field field = getDeclaredField(object, fieldName);
	       
	        if (field == null){
				throw new IllegalArgumentException("Could not find field ["
						+ fieldName + "] on target [" + object + "]");
			}
			makeAccessible(field);
			field.set(object, value);
	    } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
	
	private static Field getDeclaredField(Object object, String filedName) {
        for (Class<?> superClass = object.getClass(); superClass != Object.class; superClass = superClass.getSuperclass()) {
            try {
                return superClass.getDeclaredField(filedName);
            }
            catch (NoSuchFieldException e) {
            }
        }
        return null;
    }
	
	private static void makeAccessible(Field field) {
		
        if (!Modifier.isPublic(field.getModifiers())) {
            field.setAccessible(true);
        }
    }
}
