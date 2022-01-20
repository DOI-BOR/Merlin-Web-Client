/*
 * Copyright 2021  Hydrologic Engineering Center (HEC).
 * United States Army Corps of Engineers
 * All Rights Reserved.  HEC PROPRIETARY/CONFIDENTIAL.
 * Source may not be released without written approval
 * from HEC
 */

package gov.usbr.wq.dataaccess.model;

import gov.usbr.wq.dataaccess.json.Event;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Created by Ryan Miles
 */
public final class EventWrapper implements Comparable<EventWrapper>
{
	private final Event _event;
	private final LocalDateTime _date;

	public EventWrapper(Event event)
	{
		_event = event;
		_date = LocalDateTime.parse(_event.getDate(), DateTimeFormatter.ISO_DATE_TIME);
		//We need to convert the String to Date instance, or consider doing a Date
	}

	public Double getValue()
	{
		return _event.getValue();
	}

	public LocalDateTime getDate()
	{
		return _date;
	}

	public Integer getQuality()
	{
		return _event.getQuality();
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (o == null || getClass() != o.getClass())
		{
			return false;
		}
		EventWrapper that = (EventWrapper) o;
		return _event.equals(that._event);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(_event);
	}

	@Override
	public int compareTo(@NotNull EventWrapper o)
	{
		if (_date != null && o.getDate() != null)
		{
			return _date.compareTo(o.getDate());
		}
		return 0;
	}

	@Override
	public String toString()
	{
		return _event.toString();
	}
}
