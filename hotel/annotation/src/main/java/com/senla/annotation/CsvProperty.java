package com.senla.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.senla.annotation.enums.PropertyType;

@Inherited
@Retention(RUNTIME)
@Target(ElementType.FIELD)
public @interface CsvProperty {
	public PropertyType propertyType();

	public int columnNumber() default 0;

	public String keyField() default "";
}
