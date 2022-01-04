package gov.usbr.wq.dataaccess;

import gov.usbr.wq.dataaccess.http.HttpAccessException;
import gov.usbr.wq.dataaccess.jwt.JwtContainer;
import gov.usbr.wq.dataaccess.mapper.MerlinObjectMapper;
import gov.usbr.wq.dataaccess.http.HttpAccess;
import gov.usbr.wq.dataaccess.model.Data;
import gov.usbr.wq.dataaccess.model.Measure;
import gov.usbr.wq.dataaccess.model.Profile;

import java.io.IOException;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public final class MerlinTimeSeriesDataAccess
{
	private static final Logger LOGGER = Logger.getLogger(MerlinTimeSeriesDataAccess.class.getName());

	public MerlinTimeSeriesDataAccess()
	{
	}

	public List<Profile> getProfiles(JwtContainer token) throws IOException
	{
		HttpAccess httpAccess = new HttpAccess(HttpAccess.getDefaultWebServiceRoot());
		String api = "/MerlinWebService/GetProfiles";
		String json = httpAccess.get(api, token);
		List<Profile> retval = MerlinObjectMapper.mapJsonToListOfObjectsUsingClass(json, Profile.class);
		return retval;
	}

	public List<Measure> getMeasurementsByProfile(JwtContainer token, Profile profile) throws IOException, HttpAccessException
	{
		HttpAccess httpAccess = new HttpAccess(HttpAccess.getDefaultWebServiceRoot());
		String api = "/MerlinWebService/GetMeasurementsByProfile";
		Integer dprID = profile.getDprID();
		Map<String, String> queryParams = new HashMap<>();
		queryParams.put("profileID", String.valueOf(dprID));
		String json = httpAccess.get(api, token, queryParams);
		return MerlinObjectMapper.mapJsonToListOfObjectsUsingClass(json, Measure.class);
	}

	public Data getEventsBySeries(JwtContainer token, Measure measure, Instant start, Instant end) throws IOException, HttpAccessException
	{
		HttpAccess httpAccess = new HttpAccess(HttpAccess.getDefaultWebServiceRoot());
		String api = "/MerlinWebService/GetEventsBySeriesString";
		String seriesString = measure.getSeriesString();
		Map<String, String> queryParams = new HashMap<>();
		queryParams.put("seriesString", seriesString);
		if (start != null)
		{
			queryParams.put("startDate", start.toString());
		}
		if (end != null)
		{
			queryParams.put("endDate", end.toString());
		}
		String json = httpAccess.get(api, token, queryParams);
		return MerlinObjectMapper.mapJsonToObjectUsingClass(json,Data.class);
	}
}