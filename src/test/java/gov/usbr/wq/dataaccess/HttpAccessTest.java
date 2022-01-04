package gov.usbr.wq.dataaccess;

import gov.usbr.wq.dataaccess.http.HttpAccess;
import gov.usbr.wq.dataaccess.jwt.JwtContainer;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class HttpAccessTest
{

	@Test
	void authenticate() throws IOException
	{
		String webServiceRoot = HttpAccess.getDefaultWebServiceRoot();
		String username="webserviceuser";
		String password= "T3stUser!";
		JwtContainer token = new HttpAccess(webServiceRoot).authenticate(username,password);
		assertNotNull(token);
		assertTrue(token.isValid());
	}
}