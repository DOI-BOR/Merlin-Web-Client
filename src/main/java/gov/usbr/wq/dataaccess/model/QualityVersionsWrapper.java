package gov.usbr.wq.dataaccess.model;

import gov.usbr.wq.dataaccess.json.QualityVersions;

/**
 * Created by Bryson Spilman
 */
public final class QualityVersionsWrapper
{
    private final QualityVersions _qualityVersions;
    public QualityVersionsWrapper(QualityVersions qualityVersions)
    {
        _qualityVersions = qualityVersions;
    }

    public Integer getQvID()
    {
        return _qualityVersions.getQvID();
    }

    public String getQvName()
    {
        return _qualityVersions.getQvName();
    }

    public String getQvDescription()
    {
        return _qualityVersions.getQvDescription();
    }

    public Boolean isQvActive()
    {
        return _qualityVersions.isQvActive();
    }

    public Boolean isQvQueryHistory()
    {
        return _qualityVersions.isQvQueryHistory();
    }

    public Integer getQvUI()
    {
        return _qualityVersions.getQvUI();
    }

}
