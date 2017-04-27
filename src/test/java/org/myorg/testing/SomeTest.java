package org.myorg.testing;

import java.io.File;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class SomeTest {

	@Inject
	ManagedBean bean;

	@Deployment
	public static WebArchive createDeployment() {
		File[] libs = Maven.resolver().loadPomFromFile("pom.xml").importRuntimeDependencies().resolve()
				.withTransitivity().asFile();
		WebArchive archive = ShrinkWrap.create(WebArchive.class).addAsLibraries(libs).addClass(ManagedBean.class)
				.setWebXML("WEB-INF/web.xml");
		System.out.println(archive.toString(true));
		return archive;
	}

	@Test
	public void testSomething() {
		Assert.assertNotNull(bean);
		System.out.println(bean.getMessage());
	}

}
