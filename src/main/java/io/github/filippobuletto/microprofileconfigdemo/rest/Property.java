package io.github.filippobuletto.microprofileconfigdemo.rest;

import java.util.Objects;
import java.util.function.Predicate;

import javax.enterprise.context.ApplicationScoped;

import viper.CdiConfiguration;

@CdiConfiguration.PassAnnotations(ApplicationScoped.class)
@CdiConfiguration(producersForPrimitives = true)
public enum Property {
	SOME_INT("myprj.some.int", Predicates.isInteger()),
	SOME_URL("myprj.some.url", Predicates.isNotEmpty()),
	DYNAMIC_TIMEOUT("myprj.some.dynamic.timeout", Predicates.isInteger(), "100");

	String key;
	Predicate<String> validator;
	String defaultValue;

	Property(String key) {
		this(key, s -> true);
	}

	Property(String key, Predicate<String> validator) {
		this(key, validator, null);
	}

	Property(String key, Predicate<String> validator, String defaultValue) {
		this.key = Objects.requireNonNull(key);
		this.validator = Objects.requireNonNull(validator);
		this.defaultValue = defaultValue;
	}

	@CdiConfiguration.ConfigValidator
	public Predicate<String> getValidator() {
		return validator;
	}

	public String getKeyString() {
		return key;
	}

	public String getDefaultValue() {
		return defaultValue;
	}
}
