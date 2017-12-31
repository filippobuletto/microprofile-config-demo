package io.github.filippobuletto.microprofileconfigdemo.rest;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.inject.Alternative;

@Alternative
public class SysConfigurationResolver implements SortableConfigurationResolver, Serializable {

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
