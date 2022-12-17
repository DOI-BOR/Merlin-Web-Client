/*
 * Copyright 2022 United States Bureau of Reclamation (USBR).
 * United States Department of the Interior
 * All Rights Reserved. USBR PROPRIETARY/CONFIDENTIAL.
 * Source may not be released without written approval
 * from USBR.
 */

package gov.usbr.wq.dataaccess.http;

public class HttpAccessException extends Exception
{

	public HttpAccessException(int code, String url, String body)
	{
		super(constructMessage(code, url, body));
	}

	public HttpAccessException(Exception ex)
	{
		super(ex);
	}

	private static String constructMessage(int code, String url, String body)
	{
		return "URL:" + url + System.lineSeparator() + "Code: " + code + System.lineSeparator() + "Body: " + body;
	}
}
