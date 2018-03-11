package org.dogsystem.utils;


public final class ServicePath {

	///////////////////////////////////////////////////////////////
	// ROOT PATH
	///////////////////////////////////////////////////////////////

	public static final String ALL = "/**";

	public static final String ROOT_PATH = "/api";

	public static final String PUBLIC_ROOT_PATH = ROOT_PATH + "/public";

	public static final String PRIVATE_ROOT_PATH = ROOT_PATH + "/private";

	///////////////////////////////////////////////////////////////
	// PRIVATE PATHS
	///////////////////////////////////////////////////////////////
	
	public static final String PERMISSION_PATH = PRIVATE_ROOT_PATH + "/permission";
	
	public static final String USER_PATH = PRIVATE_ROOT_PATH + "/user";
	
	public static final String PET_PATH = PRIVATE_ROOT_PATH + "/pet";
	
	public static final String BREED_PATH = PRIVATE_ROOT_PATH + "/breed";
	
	public static final String SERVICES_PATH = PRIVATE_ROOT_PATH + "/services";
	
	public static final String AGENDA_PATH = PRIVATE_ROOT_PATH + "/agenda";
	
	public static final String REPORT_PATH = PRIVATE_ROOT_PATH + "/relatorio";

	///////////////////////////////////////////////////////////////
	// PUBLIC PATHS
	///////////////////////////////////////////////////////////////

	public static final String LOGIN_PATH = PUBLIC_ROOT_PATH + "/login";

	public static final String LOGOUT_PATH = PUBLIC_ROOT_PATH + "/logout";
	
	public static final String EMAIL_PATH = PUBLIC_ROOT_PATH + "/email-send";

}