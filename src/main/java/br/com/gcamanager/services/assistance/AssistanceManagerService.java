package br.com.gcamanager.services.assistance;

import br.com.gcamanager.domains.assistance.AssistanceDto;

import java.util.List;

public interface AssistanceManagerService {

    List<AssistanceDto> listAllAssistance();

    AssistanceDto saveAssistance(AssistanceDto assistanceDto);

    List<AssistanceDto> saveAssistance(List<AssistanceDto> assistanceDtoList);

}
