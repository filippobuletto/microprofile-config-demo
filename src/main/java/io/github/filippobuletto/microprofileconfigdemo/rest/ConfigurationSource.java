package io.github.filippobuletto.microprofileconfigdemo.rest;

public interface ConfigurationSource {
	int getOrdinal();
    String getConfigurationValue(Property key);
    default String getConfigurationKey(Property key) {
        return key.name();
    };
}
