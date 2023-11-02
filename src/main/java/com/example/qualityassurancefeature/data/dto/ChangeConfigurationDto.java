package com.example.qualityassurancefeature.data.dto;


import com.example.qualityassurancefeature.data.enums.ConnectorOperationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class ChangeConfigurationDto {

    private String cpId;
    private String connectorId;
    private String key;
    private String value;
    private ConnectorOperationType connectorOperationType;

}
