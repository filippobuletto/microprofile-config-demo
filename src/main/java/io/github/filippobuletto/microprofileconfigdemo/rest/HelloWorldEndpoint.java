package io.github.filippobuletto.microprofileconfigdemo.rest;

import java.util.Optional;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("/hello")
public class HelloWorldEndpoint {

	@Inject
	private Config config;

	@Inject
	@ConfigProperty(name = "myprj.some.url")
	private String someUrl;

	@Inject
	@ConfigProperty(name = "myprj.some.int")
	private Optional<Integer> someInt;

	@Inject
	@ConfigProperty(name = "myprj.some.dynamic.timeout", defaultValue = "100")
	private javax.inject.Provider<Long> timeout;

	@Inject
	@PropertyConfiguration(Property.SOME_URL)
	private String someUrlViper;

	@Inject
	@PropertyConfiguration(Property.SOME_INT)
	private Integer someIntViper;

	@Inject
	@PropertyConfiguration(Property.DYNAMIC_TIMEOUT)
	private javax.inject.Provider<Long> timeoutViper;

	@GET
	@Path("/micro")
	@Produces("text/plain")
	public Response doGetMicroprofile() {
		return Response.ok("Hello from WildFly Swarm! " + someUrl + " - " + someInt + " - " + timeout.get()).build();
	}

	@GET
	@Path("/viper")
	@Produces("text/plain")
	public Response doGetViper() {
		return Response
				.ok("Hello from WildFly Swarm! " + someUrlViper + " - " + someIntViper + " - " + timeoutViper.get())
				.build();
	}

}