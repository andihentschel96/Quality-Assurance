package com.example.qualityassurancefeature.services;

import com.example.qualityassurancefeature.data.dto.ChangeConfigurationDto;
import com.example.qualityassurancefeature.data.enums.ConnectorOperationType;
import com.example.qualityassurancefeature.data.model.QualityAssurance;
import com.example.qualityassurancefeature.repository.QualityAssuranceRepository;
import com.example.qualityassurancefeature.sender.QualityAssuranceSender;
import com.example.qualityassurancefeature.utilities.QualityAssuranceDbUtility;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
@Component
public class QualityAssuranceRequestService {

    @Autowired
    private QualityAssuranceSender qualityAssuranceSender;
    @Autowired
    private QualityAssuranceRepository repository;
    @Autowired
    private QualityAssuranceDbUtility qualityAssuranceDbUtility;

    public void createChangeConfigurationDtoAndSend() {
        log.info("Creating ChangeConfigurationDto.");
        ChangeConfigurationDto changeConfigurationDto = new ChangeConfigurationDto("ST1_KBP30_1", "ST1_KBP30_1_1",
                null, null, ConnectorOperationType.QUALITY_ASSURANCE);
        log.info("Send ChangeConfigurationDto to OCPP-Handler");
        qualityAssuranceSender.sendQualityAssuranceRequest(changeConfigurationDto);
    }

    public List<QualityAssurance> sendFindByConnectorId(String connectorId) {
        return qualityAssuranceDbUtility.findByConnectorIdQuery(connectorId);
    }

    public List<QualityAssurance> sendFindByTimeStamp(LocalDateTime timeStamp) {
        return qualityAssuranceDbUtility.filterByTimeStampQuery(timeStamp);
    }
}
