/*
 * Copyright 2022 United States Bureau of Reclamation (USBR).
 * United States Department of the Interior
 * All Rights Reserved. USBR PROPRIETARY/CONFIDENTIAL.
 * Source may not be released without written approval
 * from USBR.
 */

package gov.usbr.wq.dataaccess;

import java.util.ArrayList;
import java.util.List;

public final class ResourceAccess
{
	private static final String USERNAME_PROPERTY = "username";
	private static final String PASSWORD_PROPERTY = "password";
	public static String getUsername()
	{
		validateSetup();
		return System.getProperty(USERNAME_PROPERTY);
	}

	private static void validateSetup()
	{
		String username = System.getProperty(USERNAME_PROPERTY);
		String password = System.getProperty(PASSWORD_PROPERTY);
		List<String> errors = new ArrayList<>();
		if (username == null)
		{
			errors.add("\tUsername missing from VM parameters.  Example usage: " + USERNAME_PROPERTY + "=myusername");
		}
		if (password == null)
		{
			errors.add("\tPassword missing from VM parameters.  Example usage: " + PASSWORD_PROPERTY + "=mypassword");
		}

		if (!errors.isEmpty())
		{
			throw new UnsupportedOperationException("Unable to access Merlin, username and password are required." + System.lineSeparator() + String.join(System.lineSeparator(), errors));
		}
	}

	public static String getPassword()
	{
		validateSetup();
		return System.getProperty(PASSWORD_PROPERTY);
	}
}
