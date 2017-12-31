package io.github.filippobuletto.microprofileconfigdemo.rest;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;

import viper.ConfigurationResolver;

@ApplicationScoped
public class MyConfigurationResolver implements ConfigurationResolver<Property>, Serializable {
	
	private static final long serialVersionUID = 6005660722941789794L;
	private final Map<String,String> props;

	public MyConfigurationResolver() {
		props = new HashMap<>();
		props.put("myprj.some.url","some");
		props.put("myprj.some.int","10");
	}

	@Override
	public String getConfigurationValue(Property key) {
		String result = props.get(getConfigurationKey(key));
		return result == null ? key.getDefaultValue() : result;
	}
	
	@Override
	public String getConfigurationKey(Property key) {
		return key.getKeyString();
	}
	
	public String put(String key, String value) {
		return props.put(key, value);
	}

}
