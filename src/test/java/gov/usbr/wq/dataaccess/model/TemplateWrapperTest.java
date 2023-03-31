package gov.usbr.wq.dataaccess.model;

import gov.usbr.wq.dataaccess.MerlinTimeSeriesDataAccess;
import gov.usbr.wq.dataaccess.http.HttpAccessException;
import gov.usbr.wq.dataaccess.http.TokenContainer;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

final class TemplateWrapperTest extends ModelTest
{
    @Test
    void testValidate() throws IOException, HttpAccessException
    {
        TokenContainer token = getToken();
        MerlinTimeSeriesDataAccess dataAccess = new MerlinTimeSeriesDataAccess();
        List<TemplateWrapper> templateWrappers = dataAccess.getTemplates(MERLIN_TEST_WEB_CONNECTION_INFO, token);
        List<String> templatesJson = new ArrayList<>();
        for(TemplateWrapper templateWrapper : templateWrappers)
        {
            templatesJson.add(templateWrapper.toJsonString());
        }
        String json = "[" + String.join(",", templatesJson) + "]";
        String expectedJson = readFileAsString("templates/all_templates.json");
        assertEquals(json, expectedJson);
    }
}
