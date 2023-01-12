/*
 * Copyright 2022 United States Bureau of Reclamation (USBR).
 * United States Department of the Interior
 * All Rights Reserved. USBR PROPRIETARY/CONFIDENTIAL.
 * Source may not be released without written approval
 * from USBR.
 */

package gov.usbr.wq.dataaccess;

import gov.usbr.wq.dataaccess.http.Access;
import gov.usbr.wq.dataaccess.http.HttpAccessException;
import gov.usbr.wq.dataaccess.http.HttpAccessUtils;
import gov.usbr.wq.dataaccess.json.QualityVersions;
import gov.usbr.wq.dataaccess.json.Template;
import gov.usbr.wq.dataaccess.jwt.TokenContainer;
import gov.usbr.wq.dataaccess.mapper.MerlinObjectMapper;
import gov.usbr.wq.dataaccess.json.Data;
import gov.usbr.wq.dataaccess.json.Measure;
import gov.usbr.wq.dataaccess.model.DataWrapper;
import gov.usbr.wq.dataaccess.model.MeasureWrapper;
import gov.usbr.wq.dataaccess.model.QualityVersionsWrapper;
import gov.usbr.wq.dataaccess.model.TemplateWrapper;

import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.stream.Collectors.toList;

public final class MerlinTimeSeriesDataAccess
{
	private static final Logger LOGGER = Logger.getLogger(MerlinTimeSeriesDataAccess.class.getName());

	private final Supplier<Access> _accessBuilder;

	public MerlinTimeSeriesDataAccess()
	{
		this(HttpAccessUtils::buildHttpAccess);
	}

	public MerlinTimeSeriesDataAccess(Supplier<Access> accessBuilder)
	{
		_accessBuilder = accessBuilder;
	}

	public List<TemplateWrapper> getTemplates(TokenContainer token) throws IOException, HttpAccessException
	{
		Access httpAccess = _accessBuilder.get();

		String json = httpAccess.getJsonTemplates(token);
		LOGGER.log(Level.FINEST, () -> "getTemplates() JSON:" + System.lineSeparator() + json);
		return MerlinObjectMapper.mapJsonToListOfObjectsUsingClass(json, Template.class).stream()
								 .map(TemplateWrapper::new)
								 .collect(toList());
	}

	public List<MeasureWrapper> getMeasurementsByTemplate(TokenContainer token, TemplateWrapper template) throws IOException, HttpAccessException
	{
		Access httpAccess = _accessBuilder.get();
		String json = httpAccess.getJsonMeasurementsByTemplateId(token, template.getDprId());
		LOGGER.log(Level.FINEST, () -> "getMeasurementsByTemplate(" + template + ") JSON:" + System.lineSeparator() + json);
		return MerlinObjectMapper.mapJsonToListOfObjectsUsingClass(json, Measure.class).stream()
								 .map(MeasureWrapper::new)
								 .collect(toList());
	}

	public DataWrapper getEventsBySeries(TokenContainer token, MeasureWrapper measure, Instant start, Instant end) throws IOException, HttpAccessException
	{
		return getEventsBySeries(token, measure, null, start, end);
	}

	public DataWrapper getEventsBySeries(TokenContainer token, MeasureWrapper measure, Integer qualityVersionID, Instant start, Instant end) throws IOException, HttpAccessException
	{
		Access httpAccess = _accessBuilder.get();
		String json = httpAccess.getJsonEventsBySeries(token, measure.getSeriesString(), qualityVersionID, start, end);

		LOGGER.log(Level.FINEST, () -> "getEventsBySeries(" + measure + ", " + qualityVersionID + ", [" + start + ", " + end + "]) JSON:" + System.lineSeparator() + json);

		return new DataWrapper(MerlinObjectMapper.mapJsonToObjectUsingClass(json,Data.class), start, end);
	}

	public List<QualityVersionsWrapper> getQualityVersions(TokenContainer token) throws HttpAccessException, IOException
	{
		Access httpAccess = _accessBuilder.get();
		String json = httpAccess.getJsonQualityVersions(token);

		LOGGER.log(Level.FINEST, () -> "getQualityVersions() JSON:" + System.lineSeparator() + json);

		return MerlinObjectMapper.mapJsonToListOfObjectsUsingClass(json, QualityVersions.class).stream()
				.map(QualityVersionsWrapper::new)
				.collect(toList());
	}
}
