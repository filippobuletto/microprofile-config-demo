package io.github.filippobuletto.microprofileconfigdemo.rest;

import viper.ConfigurationResolver;

public interface SortableConfigurationResolver extends ConfigurationResolver<Property> {
	int getOrdinal();
}
