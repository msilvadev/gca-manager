package br.com.gcamanager.domains.assistance;

public class AssistanceDtoBuilder {

    private AssistanceDtoBuilder(){}

    public static AssistanceDto build(Assistance assistance) {
        return new AssistanceDto(
                assistance.getNumber(),
                assistance.getAssistanceType().getCode(),
                assistance.getDescription(),
                assistance.getStart(),
                assistance.getEnd() != null ? assistance.getEnd() : null
        );
    }

    public static Assistance buildDtoToIndustrialProcess(AssistanceDto dto) {
        AssistanceBuilder builder = new AssistanceBuilder();

        return builder
                .withNumber(dto.getNumber())
                .withDescription(dto.getDescription())
                .withAssistanceType(AssistanceType.valueOfCode(dto.getAssistanceType()))
                .build();
    }

}
