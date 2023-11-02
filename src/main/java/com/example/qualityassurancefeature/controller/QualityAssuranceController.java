package com.example.qualityassurancefeature.controller;

import com.example.qualityassurancefeature.data.model.QualityAssurance;
import com.example.qualityassurancefeature.services.QualityAssuranceRequestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("quality-assurance")
@Slf4j
public class QualityAssuranceController {

    @Autowired
    private QualityAssuranceRequestService qualityAssuranceRequestService;

    @PostMapping("/send-request")
    public String sendQualityAssuranceRequest() {
        qualityAssuranceRequestService.createChangeConfigurationDtoAndSend();
        return "Message triggered successfully.";
    }

    @GetMapping("/get-reports-by-connectorId")
    public List<QualityAssurance> getQualityAssuranceReports(@RequestParam String connectorId) {
        return qualityAssuranceRequestService.sendFindByConnectorId(connectorId);
    }

    @GetMapping("/reports-filtered-by-timestamp")
    public List<QualityAssurance> getReportsByTimestamp(@RequestParam String timeStamp) {
        return qualityAssuranceRequestService.sendFindByTimeStamp(stringToLocalDateTime(timeStamp));
    }

    private LocalDateTime stringToLocalDateTime(String stringDate) {
        DateTimeFormatter CUSTOM_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return stringDate == null ? null : LocalDateTime.parse(stringDate, CUSTOM_FORMATTER);
    }
}
