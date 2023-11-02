package com.example.qualityassurancefeature.data.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

@Document(collection = "QualityAssurance")
public class QualityAssurance {

    private LocalDateTime timeStamp;
    private String connectorId;

    private Map<String, String> confirmationMessages;
}
