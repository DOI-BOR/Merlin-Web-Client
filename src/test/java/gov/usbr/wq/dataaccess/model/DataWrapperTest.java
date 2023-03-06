package gov.usbr.wq.dataaccess.model;

import java.time.ZoneId;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataWrapperTest
{

	@Test
	void goodTimeZones()
	{
		String[] timeZones = {
				"GMT",
				"UTC",
				"UTC-08:00",
				"Etc/GMT+8",
				"America/Los_Angeles"
		};

		DataWrapperBuilder builder = new DataWrapperBuilder();
		for (String tz : timeZones)
		{
			DataWrapper dataWrapper = builder.withTimeZone(tz).build();
			Assertions.assertEquals(ZoneId.of(tz),dataWrapper.getTimeZone());
		}
	}

	@Test
	void timeZonesWithSpaces()
	{
		String[] timeZones = {
				"GMT ",
				"UTC -08:00",
				" Etc/GMT+8",
				" America / Los_Angeles "
		};

		DataWrapperBuilder builder = new DataWrapperBuilder();
		for (String tz : timeZones)
		{
			DataWrapper dataWrapper = builder.withTimeZone(tz).build();
			tz = tz.replace(" ", "");
			Assertions.assertEquals(ZoneId.of(tz),dataWrapper.getTimeZone());
		}

	}

	@Test
	void badTimeZones()
	{
		String[] timeZones = {
				"Bert",
				" Ernie ",
				"Cookie Monster",
				"Oscar ",
				" Grover"
		};

		DataWrapperBuilder builder = new DataWrapperBuilder();
		for (String tz : timeZones)
		{
			DataWrapper dataWrapper = builder.withTimeZone(tz).build();
			tz = tz.replace(" ", " ");
			Assertions.assertEquals(ZoneId.of("GMT"),dataWrapper.getTimeZone());
		}

	}
}