package io.github.filippobuletto.microprofileconfigdemo.rest;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.wildfly.swarm.Swarm;
import org.wildfly.swarm.container.DeploymentException;
import org.wildfly.swarm.jaxrs.JAXRSArchive;

public class Main {

	public static void main(String[] args) throws Exception {
		try {
			// I'd like swarm
			Swarm container = new Swarm();
			// Start the container
			container.start();
			// Build an archive (war)
			JAXRSArchive jaxrsArchive = ShrinkWrap.create(JAXRSArchive.class)
			          .addAsResource(new StringAsset(MyConfigSource.class.getCanonicalName()),
			              "/META-INF/services/org.eclipse.microprofile.config.spi.ConfigSource")
			          .addAsResource(EmptyAsset.INSTANCE, "/META-INF/beans.xml")
					.addPackages(true, "io.github.filippobuletto.microprofileconfigdemo.rest").addAllDependencies();

			// Deploy the archive, please
			container.deploy(jaxrsArchive);
		} catch (DeploymentException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}

}
