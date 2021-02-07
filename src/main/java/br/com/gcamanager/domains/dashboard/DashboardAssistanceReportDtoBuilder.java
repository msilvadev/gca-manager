package br.com.gcamanager.domains.dashboard;

public class DashboardAssistanceReportDtoBuilder {

    private int advisoryQuantity;
    private int consultancyQuantity;
    private int defaultQuantity;

    public DashboardAssistanceReportDtoBuilder setAdvisoryQuantity(int advisoryQuantity) {
        this.advisoryQuantity = advisoryQuantity;
        return this;
    }

    public DashboardAssistanceReportDtoBuilder setConsultancyQuantity(int consultancyQuantity) {
        this.consultancyQuantity = consultancyQuantity;
        return this;
    }

    public DashboardAssistanceReportDtoBuilder setDefaultQuantity(int defaultQuantity) {
        this.defaultQuantity = defaultQuantity;
        return this;
    }

    public DashboardAssistanceReportDto build() {
        return new DashboardAssistanceReportDto(this.advisoryQuantity,
                this.consultancyQuantity,
                this.defaultQuantity);
    }
}
