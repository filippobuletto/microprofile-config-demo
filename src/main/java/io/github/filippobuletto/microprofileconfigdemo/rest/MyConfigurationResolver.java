package io.github.filippobuletto.microprofileconfigdemo.rest;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import viper.ConfigurationResolver;

@ApplicationScoped
public class MyConfigurationResolver implements ConfigurationResolver<Property>, Serializable {

  private static final long serialVersionUID = 6005660722941789794L;
  private List<ConfigurationSource> resolvers;
  
  @Inject @Any
  private Instance<ConfigurationSource> resolverInstances;

  @PostConstruct
  void init() {
    this.resolvers =
    StreamSupport.stream(resolverInstances.spliterator(),
        false)
            .sorted(Comparator.comparing(ConfigurationSource::getOrdinal)
                              .thenComparing(obj -> obj.getClass().getName()))
            .collect(Collectors.toList());
  }

  @Override
  public String getConfigurationValue(Property key) {
    return resolvers.stream()
                      .map(c -> c.getConfigurationValue(key))
                      .filter(Objects::nonNull)
                      .findFirst()
                        .orElse(key.getDefaultValue());
  }

  @Override
  public String getConfigurationKey(Property key) {
    return key.getKeyString();
  }

}
