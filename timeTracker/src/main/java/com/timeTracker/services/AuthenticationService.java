package com.timeTracker.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
	
	private static final Logger logger = Logger.getLogger(AuthenticationService.class.getName());
	private static final String CONTEXT_FACTORY = "com.sun.jndi.ldap.LdapCtxFactory";
	private static final Pattern EMAIL_REGEX = Pattern.compile("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", Pattern.CASE_INSENSITIVE);
	private static final String LDAP_PROVIDER_URL = "ldap://dc2.ultramain.com:389,ldap://dc1.ultramain.com:389";
	private static final String LDAP_DOMAIN_NAME = "ultramain.com";
	
	public boolean beginLdapAuthentication(String userId,String password) {
		
		List<String> ldapUrlList = Arrays.asList(LDAP_PROVIDER_URL.split(","));

		for(String ldapUrl:ldapUrlList)
		{
			if(authenticateLdap(userId,password,ldapUrl,LDAP_DOMAIN_NAME))
				return true;
		}
		return false;
	}

	private boolean authenticateLdap(String userId,String password,String ldapUrl,String domainName){

		boolean authenticated = false;
		LdapContext ctx = null;

		//bind by using the specified username/password
		Hashtable<String, String> props = new Hashtable<String, String>();
		ArrayList<String> principalNameList = new ArrayList<String>();

		if(isEmailValid(userId)) {
			principalNameList.add(userId);
		}else {
			logger.log(Level.SEVERE,"Invalid email id.");
			return false;
		}
		for(String principalName : principalNameList) {
			props.put(Context.SECURITY_PRINCIPAL, principalName);
			props.put(Context.SECURITY_CREDENTIALS, password);
			props.put(Context.INITIAL_CONTEXT_FACTORY, CONTEXT_FACTORY);
			props.put(Context.PROVIDER_URL, ldapUrl);

			//apply the ldap CONNECT timeout
			Integer ldapConnectTimeout = 5000;
			props.put("com.sun.jndi.ldap.connect.timeout", ldapConnectTimeout.toString()); //Shorten the LDAP CONNECT timeout


			//apply the ldap READ timeout
			Integer ldapReadTimeout = 5000;//convert from second to millisecond. 
			props.put("com.sun.jndi.ldap.read.timeout", ldapReadTimeout.toString()); //Shorten the LDAP READ timeout.
//			props.put(Context.SECURITY_PROTOCOL, "ssl");

			try{
				ctx = new InitialLdapContext(props, null);
				return true;
			}
			catch(javax.naming.CommunicationException e){
				logger.log(Level.WARNING, "Failed to connect to " + domainName + " through " + ldapUrl+" message:"+e.getMessage());
			}
			catch(NamingException e){
				logger.log(Level.WARNING, "Failed to authenticate " + principalName + " through " + ldapUrl+" message:"+e.getMessage());
			}
			finally
			{
				if (ctx != null)
				{
					try {
						ctx.close();
					} catch (NamingException e) {
						logger.log(Level.WARNING, "Failed to close LDAP Context");
					}
				}
			}
		}
		return authenticated;

	}

	private boolean isEmailValid(String email) {

		return EMAIL_REGEX.matcher(email).matches();
	}
}
