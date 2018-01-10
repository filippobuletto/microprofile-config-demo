package io.github.filippobuletto.microprofileconfigdemo.rest;

import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named @ApplicationScoped
public class EnvConfigurationResolver implements ConfigurationSource, Serializable {

	private static final long serialVersionUID = -9167455523023830262L;

	@Override
	public int getOrdinal() {
		return 300;
	}

	@Override
	public String getConfigurationValue(Property key) {
		return System.getenv().get(getConfigurationKey(key));
	}
	
	@Override
	public String getConfigurationKey(Property key) {
		return key.getKeyString();
	}

}
