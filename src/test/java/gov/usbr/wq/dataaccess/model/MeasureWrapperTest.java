package gov.usbr.wq.dataaccess.model;

import gov.usbr.wq.dataaccess.MerlinTimeSeriesDataAccess;
import gov.usbr.wq.dataaccess.http.HttpAccessException;
import gov.usbr.wq.dataaccess.http.TokenContainer;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

final class MeasureWrapperTest extends ModelTest
{

    @Test
    void testValidate() throws IOException, HttpAccessException
    {
        TokenContainer token = getToken();
        MerlinTimeSeriesDataAccess dataAccess = new MerlinTimeSeriesDataAccess();
        TemplateWrapper templateWrapper = new TemplateWrapperBuilder()
                .withDprID(124)
                .build();
        List<MeasureWrapper> measureWrappers = dataAccess.getMeasurementsByTemplate(MERLIN_TEST_WEB_CONNECTION_INFO, token, templateWrapper);
        List<String> measuresJson = new ArrayList<>();
        for(MeasureWrapper measureWrapper : measureWrappers)
        {
            measuresJson.add(measureWrapper.toJsonString());
        }
        String json = "[" + String.join(",", measuresJson) + "]";
        String expectedJson = readFileAsString("measurements/Sac._BC_Shasta Boundary Flow.json");
        assertEquals(json, expectedJson);
    }
}
