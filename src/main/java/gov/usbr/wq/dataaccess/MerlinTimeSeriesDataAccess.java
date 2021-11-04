package gov.usbr.wq.dataaccess;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import jdk.nashorn.internal.runtime.JSONFunctions;
import okhttp3.*;

import java.io.IOException;
import java.util.logging.Logger;

public class MerlinTimeSeriesDataAccess
{
	private static final Logger LOGGER = Logger.getLogger(MerlinTimeSeriesDataAccess.class.getName());

	public MerlinTimeSeriesDataAccess()
	{
	}



	public String getProfilesString(JwtContainer token) throws IOException
	{
		HttpAccess httpAccess = new HttpAccess(HttpAccess.getDefaultWebServiceRoot());
		String getProfilesApi = "/MerlinWebService/GetProfiles";
		String json = httpAccess.get(getProfilesApi, token);
		return json;
	}




}