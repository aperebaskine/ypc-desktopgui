package com.pinguela.yourpc.desktop.util;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ReflectionUtils {

	private static Logger logger = LogManager.getLogger(ReflectionUtils.class);

	private static Collection<String> appendPackageName(String packageName, Collection<String> simpleClassNames) {
		Collection<String> fullyQualifiedNames = new LinkedHashSet<String>();

		for (String className : simpleClassNames) {
			fullyQualifiedNames.add(appendPackageName(packageName, className));
		}
		return fullyQualifiedNames;
	}

	public static Collection<Class<?>> loadClassesFromPackage(String packageName, Collection<String> simpleClassNames)
			throws ClassNotFoundException {

		Collection<String> fullyQualifiedNames = appendPackageName(packageName, simpleClassNames);
		Collection<Class<?>> classes = new LinkedHashSet<Class<?>>();

		for (String name : fullyQualifiedNames) {
			classes.add(Class.forName(name));
		}

		return classes;
	}

	public static Class<?>[] loadObjectClasses(Object[] objects) {

		Class<?>[] classes = new Class<?>[objects.length];
		for (int i = 0; i < classes.length; i++) {
			classes[i] = objects[i].getClass();
		}
		return classes;
	}

	public static <T> Constructor<T> getConstructor(Class<T> clazz, Object... constructorParameters) {
		Constructor<T> constructor;
		try {
			constructor = clazz
					.getDeclaredConstructor((Class<?>[]) ReflectionUtils.loadObjectClasses(constructorParameters));
		} catch (NoSuchMethodException e) {
			throw new IllegalStateException("Arguments provided for the constructor are invalid.");
		}
		return constructor;
	}

	public static String appendPackageName(String packageName, String className) {
		return new StringBuilder(packageName)
				.append('.')
				.append(className.substring(className.lastIndexOf('.')+1))
				.toString();
	}

	public static Collection<Class<?>> loadClasses(Collection<String> fullyQualifiedNames) 
			throws ClassNotFoundException {
		Collection<Class<?>> classes = new LinkedHashSet<Class<?>>();
		for (String name : fullyQualifiedNames) {
			classes.add(Class.forName(name));
		}
		return classes;
	}

	public static Class<?> getClass(Type type) {
		Class<?> clazz = null;
		try {
			clazz = Class.forName(getRawTypeName(type));
		} catch (ClassNotFoundException e) {
			// This exception should never be thrown as the Type object already represents a class
		}
		return clazz;
	}

	public static String getRawTypeName(Type type) {
		return type instanceof ParameterizedType ?
				((ParameterizedType) type).getRawType().getTypeName() :
					type.getTypeName();
	}

	public static Type[] getTypeParameterBounds(Class<?> clazz) {

		TypeVariable<?>[] typeVariables = clazz.getTypeParameters();

		if (typeVariables.length == 0) {
			Type superclassType = clazz.getGenericSuperclass();

			if (superclassType == null) {
				return new Type[0];
			}

			if (superclassType instanceof ParameterizedType) {
				Type[] superclassTypeArguments = ((ParameterizedType) superclassType).getActualTypeArguments();
				if (superclassTypeArguments.length > 0) {
					return superclassTypeArguments;
				}
			}
			// Find the closest class in the upper hierarchy with type parameters
			return getTypeParameterBounds(getClass(superclassType));
		}
		return typeVariables[0].getBounds();
	}

	public static boolean isTypeParameterAssignable(Class<?> candidateTypeParameter, Class<?> targetClass) {

		Type[] typeParameters = getTypeParameterBounds(targetClass);

		if (typeParameters.length < 1) {
			return false;
		}

		try {
			for (Type typeParameter : typeParameters) {
				if (!Class.forName(getRawTypeName(typeParameter)).isAssignableFrom(candidateTypeParameter)) {
					return false;
				}
			}
		} catch (ClassNotFoundException e) { // Invalid parameter passed
			throw new IllegalArgumentException(
					String.format("Invalid class parameters passed: %s, %s", candidateTypeParameter, targetClass));
		}

		return true;
	}

	@SuppressWarnings("unchecked")
	public static <T> T createNullObjectInstanceIfExists(Class<?> targetClass, Object... constructorParameters) {
		String packageName = targetClass.getPackage().getName();
		String className = targetClass.getSimpleName();

		String fullyQualifiedNullSubclassName = 
				String.format("%s.Null%s", packageName, className);

		T nullObjectInstance = null;

		try {
			nullObjectInstance = (T) Class.forName(fullyQualifiedNullSubclassName)
					.getDeclaredConstructor(loadObjectClasses(constructorParameters))
					.newInstance(constructorParameters);
		} catch (Exception e) {
			// No action required
		}

		return nullObjectInstance;
	}

	public static <T> T createNullObjectOrDefaultInstance(Class<T> targetClass, Object... constructorParameters) {
		T object = createNullObjectInstanceIfExists(targetClass, constructorParameters);

		if (object == null) {
			try {
				object = (T) targetClass
						.getDeclaredConstructor(loadObjectClasses(constructorParameters))
						.newInstance(constructorParameters);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new IllegalStateException(String.format(
						"Cannot create instance from class %s and arguments %s.",
						targetClass.getName(), Arrays.asList(constructorParameters)));
			} 
		}

		return object;
	}

	@SuppressWarnings("unchecked")
	public static <T> ComboBoxModel<T> createComboBoxModel(Collection<T> content, Class<?> targetClass, Object... constructorParameters) {
		T[] items = (T[]) Array.newInstance(targetClass, content.size()+1);
		items[0] = (T) createNullObjectOrDefaultInstance(targetClass, constructorParameters); // Add blank object as the first value
	
		Iterator<T> iterator = content.iterator();
		for (int i = 1; i < items.length; i++) {
			items[i] = iterator.next();
		}
	
		return new DefaultComboBoxModel<T>(items);
	}

}
