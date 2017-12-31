package io.github.filippobuletto.microprofileconfigdemo.rest;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.config.spi.ConfigSource;

@ApplicationScoped
public class MyConfigSource implements ConfigSource, Serializable {
	
	private static final long serialVersionUID = 8680885634649811992L;
	private final Map<String,String> props;
	
	public MyConfigSource() {
		props = new HashMap<>();
		props.put(CONFIG_ORDINAL,"200");
		props.put("myprj.some.url","some");
		props.put("myprj.some.int","10");
	}

	@Override
	public Map<String, String> getProperties() {
		return props;
	}

	@Override
	public String getValue(String propertyName) {
		return props.get(propertyName);
	}

	@Override
	public String getName() {
		return this.getClass().getName();
	}
	
	public String put(String key, String value) {
		return props.put(key, value);
	}

}
