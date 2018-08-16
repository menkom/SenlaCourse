package com.senla.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Inherited
@Retention(RUNTIME)
@Target(TYPE)
public @interface CsvEntity {
	public String filename();

	public String valueSeparator() default ";";

	public String entityId() default "id";
}
