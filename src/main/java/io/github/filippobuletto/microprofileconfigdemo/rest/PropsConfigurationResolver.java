package io.github.filippobuletto.microprofileconfigdemo.rest;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named @ApplicationScoped
public class PropsConfigurationResolver implements ConfigurationSource, Serializable {

	private static final long serialVersionUID = -4673972447435787055L;
	private final Map<String,String> props;

	public PropsConfigurationResolver() {
		props = new HashMap<>();
		props.put("myprj.some.url","some");
		props.put("myprj.some.int","10");
	}

	@Override
	public String getConfigurationValue(Property key) {
		return props.get(getConfigurationKey(key));
	}
	
	@Override
	public String getConfigurationKey(Property key) {
		return key.getKeyString();
	}
	
	public String put(String key, String value) {
		return props.put(key, value);
	}

	@Override
	public int getOrdinal() {
		return 100;
	}
}
