package com.in28minutes.rest.webservices.restfulwebservices.applicationuser;

import java.io.IOException;

import org.jboss.logging.Logger;
import org.keycloak.broker.provider.util.SimpleHttp;
import org.keycloak.component.ComponentModel;
import org.keycloak.credential.CredentialInput;
import org.keycloak.credential.CredentialInputValidator;
import org.keycloak.credential.CredentialModel;
import org.keycloak.credential.UserCredentialStore;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.SubjectCredentialManager;
import org.keycloak.models.UserModel;
import org.keycloak.models.credential.PasswordCredentialModel;
import org.keycloak.storage.UserStorageProvider;
import org.keycloak.storage.adapter.AbstractUserAdapter;
import org.keycloak.storage.user.UserLookupProvider;
import org.springframework.beans.BeanUtils;

public class RemoteUserStorageProvider implements UserStorageProvider,
UserLookupProvider, CredentialInputValidator{
	private static final Logger LOG = Logger.getLogger(RemoteUserStorageProvider.class);
	private KeycloakSession session;
	private ComponentModel model;
//	private ApplicationUserApiService applicationUserApiService;
	private CredentialModel storedCredentialByNameAndType;
	/**
	 * @param session
	 * @param model
	 */
	public RemoteUserStorageProvider(KeycloakSession session, ComponentModel model) {
		super();
		this.session = session;
		this.model = model;
//		this.applicationUserApiService = usersService;
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean supportsCredentialType(String credentialType) {
		
		return PasswordCredentialModel.TYPE.equals(credentialType);
	}

	@Override
	public boolean isConfiguredFor(RealmModel realm, UserModel user, String credentialType) {
		if(!supportsCredentialType(credentialType)) {
			return false;
		}
//		return !getCredentialStore().getStoredCredentialsByType(realm, user, credentialType).isEmpty() ;
		
		storedCredentialByNameAndType = user.credentialManager().getStoredCredentialByNameAndType(user.getUsername(), credentialType);
		return !storedCredentialByNameAndType.getCredentialData().isEmpty();
	}

	@SuppressWarnings("deprecation")
	private UserCredentialStore getCredentialStore() {
		return session.userCredentialManager();
	}
	
	@Override
	public boolean isValid(RealmModel realm, UserModel user, CredentialInput credentialInput) {
		// TODO Auto-generated method stub
		boolean validity = verifyPassword(user.getUsername(), credentialInput.getChallengeResponse());
//		if(validity == null) {
//			return validity;
//		}
		return validity;
	}

	
	
	
	
	private boolean verifyPassword(String username, String password) {
	    try {
	        return SimpleHttp
	                .doPost("http://localhost:8099/oauth/application_user/verify-password/" + username, this.session)
	                .json(new VerifyPasswordRequest(password))
	                .asJson(VerifyPasswordResponse.class).getResult();
	    } catch (IOException e) {
	        LOG.warn("Error verifying password for user " + username + " with external service: " + e.getMessage(), e);
	    }
	    return false;
	}
	
	
	
	
	@Override
	public UserModel getUserById(RealmModel realm, String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserModel getUserByUsername(RealmModel realm, String username) {
		// TODO Auto-generated method stub
		
		UserModel returnValue = null;
//		ApplicationUserRest applicationUser = applicationUserApiService.getUserDetails(username);
		ApplicationUserRest applicationUser = new ApplicationUserRest();
		ApplicationUser temp = fetchUser(username);
		
		BeanUtils.copyProperties(temp, applicationUser);
		
		if(applicationUser!=null) {
			returnValue=createUserModel(username, realm);
		}
		return returnValue;
	}
	
	private UserModel createUserModel(String username, RealmModel realm) {
		return new AbstractUserAdapter(session, realm, model) {
		
			@Override
			public String getUsername() {
				// TODO Auto-generated method stub
				return username;
			}

			@Override
			public SubjectCredentialManager credentialManager() {
				// TODO Auto-generated method stub
				return null;
			}
		};
	}

	private ApplicationUser fetchUser(String email) {
		
	    try {
	        return SimpleHttp
	                .doGet("http://localhost:8099/oauth/application_user/get/usingemail/" + email, this.session)
	                .asJson(ApplicationUser.class);
	    } catch (IOException e) {
	        LOG.warn("Error fetching user " + email + " from external service: " + e.getMessage(), e);
	    }
	    return null;
	}
	
	


	
	
	@Override
	public UserModel getUserByEmail(RealmModel realm, String email) {
		// TODO Auto-generated method stub
			
		
		return null;
	}

}
