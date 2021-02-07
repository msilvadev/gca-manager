package br.com.gcamanager.domains.dashboard;

import java.io.Serializable;

public class DashboardAssistanceReportDto implements Serializable {

    private static final long serialVersionUID = 607321184173085405L;

    private int advisoryQuantity;
    private int consultancyQuantity;
    private int defaultQuantity;

    public DashboardAssistanceReportDto(int advisoryQuantity, int consultancyQuantity, int defaultQuantity) {
        this.advisoryQuantity = advisoryQuantity;
        this.consultancyQuantity = consultancyQuantity;
        this.defaultQuantity = defaultQuantity;
    }

    public DashboardAssistanceReportDto() {}

    public int getAdvisoryQuantity() {
        return advisoryQuantity;
    }

    public void setAdvisoryQuantity(int advisoryQuantity) {
        this.advisoryQuantity = advisoryQuantity;
    }

    public int getConsultancyQuantity() {
        return consultancyQuantity;
    }

    public void setConsultancyQuantity(int consultancyQuantity) {
        this.consultancyQuantity = consultancyQuantity;
    }

    public int getDefaultQuantity() {
        return defaultQuantity;
    }

    public void setDefaultQuantity(int defaultQuantity) {
        this.defaultQuantity = defaultQuantity;
    }

    @Override
    public String toString() {
        return "DashboardReportDto{" +
                "advisoryQuantity=" + advisoryQuantity +
                ", consultancyQuantity=" + consultancyQuantity +
                ", defaultQuantity=" + defaultQuantity +
                '}';
    }
}
