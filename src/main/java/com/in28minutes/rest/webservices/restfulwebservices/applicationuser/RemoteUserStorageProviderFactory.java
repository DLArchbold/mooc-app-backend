package com.in28minutes.rest.webservices.restfulwebservices.applicationuser;

import org.keycloak.component.ComponentModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.storage.UserStorageProviderFactory;

public class RemoteUserStorageProviderFactory implements UserStorageProviderFactory<RemoteUserStorageProvider>{
public static final String PROVIDER_NAME = "my-remote-mysql-user-storage-provider";
	
	@Override
	public RemoteUserStorageProvider create(KeycloakSession session, ComponentModel model) {
		// TODO Auto-generated method stub
		
		
		return new RemoteUserStorageProvider(session, model);
	}

	@Override
	public String getId() {
		//IDs factory in runtime and read string value in keycloak admi console when 
		//want to enable SPI for realm
		// TODO Auto-generated method stub
		
		
		
		return PROVIDER_NAME;
	}
	
	

}
