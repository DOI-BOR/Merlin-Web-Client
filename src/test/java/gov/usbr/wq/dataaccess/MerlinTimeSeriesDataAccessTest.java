package gov.usbr.wq.dataaccess;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.logging.Logger;

public class MerlinTimeSeriesDataAccessTest
{

	private static final Logger LOGGER = Logger.getLogger(MerlinTimeSeriesDataAccessTest.class.getName());


	@Test
	void getProfiles() throws IOException
	{
		String username="webserviceuser";
		String password= "T3stUser!";
		JwtContainer token = new HttpAccess(HttpAccess.getDefaultWebServiceRoot()).authenticate(username,password);
		MerlinTimeSeriesDataAccess dataAccess = new MerlinTimeSeriesDataAccess();
		String profilesString = dataAccess.getProfilesString(token);
		Assertions.assertNotNull(profilesString);
		LOGGER.info(profilesString);
	}
}