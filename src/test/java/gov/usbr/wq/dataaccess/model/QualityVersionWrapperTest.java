package gov.usbr.wq.dataaccess.model;

import gov.usbr.wq.dataaccess.json.QualityVersions;
import gov.usbr.wq.dataaccess.mapper.MerlinObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;

final class QualityVersionWrapperTest extends ModelTest
{
    @Test
    void testValidate() throws IOException
    {
        String expectedJson = readFileAsString("qualityversions/qualityversions.json");
        List<QualityVersionWrapper> qualityVersionWrappers = MerlinObjectMapper.mapJsonToListOfObjectsUsingClass(expectedJson, QualityVersions.class).stream()
                .map(QualityVersionWrapper::new)
                .collect(toList());
        List<String> qualityVersionsJson = new ArrayList<>();
        for(QualityVersionWrapper qualityVersionWrapper : qualityVersionWrappers)
        {
            qualityVersionsJson.add(qualityVersionWrapper.toJsonString());
        }
        String json = "[" + String.join(",", qualityVersionsJson) + "]";
        assertEquals(json, expectedJson);
    }
}
