package com.task.service;

import java.util.HashSet;
import java.util.Set;

import org.kie.server.api.marshalling.MarshallingFormat;
import org.kie.server.client.KieServicesClient;
import org.kie.server.client.KieServicesConfiguration;
import org.kie.server.client.KieServicesFactory;
import org.springframework.stereotype.Component;

import com.jbpm.task.Employee;

@Component
public class KieServerConfiguration {

	protected String serverURL = "http://localhost:8080/kie-server/services/rest/server";
	protected String kieUser = "krisv";
	protected String password = "krisv";

	private KieServicesConfiguration configureKieServices() {
		KieServicesConfiguration kieConfiguration = null;
		kieConfiguration = KieServicesFactory.newRestConfiguration(serverURL, kieUser, password);
		kieConfiguration.setMarshallingFormat(MarshallingFormat.JSON);
		Set<Class<?>> extraClassList = new HashSet<Class<?>>();
		extraClassList.add(Employee.class);
		kieConfiguration.setExtraJaxbClasses(extraClassList);
		return kieConfiguration;
	}

	public KieServicesClient getKieClient() {
		KieServicesClient kieServicesClient = null;
		KieServicesConfiguration configuration = configureKieServices();
		kieServicesClient = KieServicesFactory.newKieServicesClient(configuration);
		return kieServicesClient;

	}

}
