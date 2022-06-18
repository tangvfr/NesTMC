package fr.tangv.nestmc.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

public class ReflectionUtil {

	public static void setValue(String name, Object object, Object value) {
		Field field = getField(name, object.getClass());
		field.setAccessible(true);
		try {
			field.set(object, value);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			ignite(e);
		}
	}

	public static void setStaticValue(String name, Class<?> clazz, Object value) {
		Field field = getField(name, clazz);
		field.setAccessible(true);
		try {
			field.set(null, value);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			ignite(e);
		}
	}

	public static Object getValue(String name, Object object) {
		Field field = getField(name, object.getClass());
		field.setAccessible(true);
		try {
			return field.get(object);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			ignite(e);
			return null;
		}
	}

	public static Object getStaticValue(String name, Class<?> clazz) {
		Field field = getField(name, clazz);
		field.setAccessible(true);
		try {
			return field.get(null);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			ignite(e);
			return null;
		}
	}

	public static Object excuteMethod(String name, Object object, Object... args) {
		Class<?>[] clazzs = new Class<?>[args.length];
		for (int i = 0; i < clazzs.length; i++)
			clazzs[i] = args[i].getClass();
		Method method = getMethod(name, object.getClass(), clazzs);
		method.setAccessible(true);
		try {
			return method.invoke(object, args);
		} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
			ignite(e);
			return null;
		}
	}

	private static Method getMethod(String name, Class<?> clazz, Class<?>... args) {
		do {
			try {
				return clazz.getDeclaredMethod(name, args);
			} catch (NoSuchMethodException e) {
				try {
					return clazz.getMethod(name, args);
				} catch (SecurityException | NoSuchMethodException e1) {}
			} catch (SecurityException e) {}
		} while((clazz = clazz.getSuperclass()) != null);
		return null;
	}

	private static Field getField(String var, Class<?> clazz) {
		do {
			try {
				return clazz.getDeclaredField(var);
			} catch (NoSuchFieldException e) {
				try {
					return clazz.getClass().getField(var);
				} catch (NoSuchFieldException | SecurityException e1) {}
			} catch (SecurityException e) {}
		} while((clazz = clazz.getSuperclass()) != null);
		return null;
	}

	@SuppressWarnings("unchecked")
	public static <T extends Throwable> void ignite(Throwable throwable) throws T {
		Objects.requireNonNull(throwable, "throwable");
		throw (T) throwable;
	}

}