package io.github.filippobuletto.microprofileconfigdemo.rest;

import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named @ApplicationScoped
public class SysConfigurationResolver implements ConfigurationSource, Serializable {

	private static final long serialVersionUID = -8084199293618417513L;

	@Override
	public int getOrdinal() {
		return 400;
	}

	@Override
	public String getConfigurationValue(Property key) {
		return (String) System.getProperties().get(getConfigurationKey(key));
	}
	
	@Override
	public String getConfigurationKey(Property key) {
		return key.getKeyString();
	}

}
