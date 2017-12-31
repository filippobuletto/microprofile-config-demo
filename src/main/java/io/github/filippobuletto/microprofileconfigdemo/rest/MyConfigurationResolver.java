package io.github.filippobuletto.microprofileconfigdemo.rest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.enterprise.context.ApplicationScoped;
import viper.ConfigurationResolver;

@ApplicationScoped
public class MyConfigurationResolver implements ConfigurationResolver<Property>, Serializable {

  private static final long serialVersionUID = 6005660722941789794L;
  private final List<ConfigurationResolver<Property>> resolvers;

  public MyConfigurationResolver() {
    final List<SortableConfigurationResolver> resolvers = new ArrayList<>();
    resolvers.add(new EnvConfigurationResolver());
    resolvers.add(new SysConfigurationResolver());
    resolvers.add(new PropsConfigurationResolver());
    this.resolvers =
        resolvers.stream()
            .sorted(Comparator.comparing(SortableConfigurationResolver::getOrdinal)
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
