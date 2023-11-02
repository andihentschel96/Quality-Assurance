package com.example.qualityassurancefeature.utilities;

import com.example.qualityassurancefeature.data.model.QualityAssurance;
import com.example.qualityassurancefeature.exception.QueryOutOfBoundsException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class QualityAssuranceDbUtility {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<QualityAssurance> findByConnectorIdQuery(String connectorId) {
        Query query = new Query();
        try {
            log.info("Creating query for given ConnectorId");
            query.addCriteria(Criteria.where("connectorId").is(connectorId));
            return mongoTemplate.find(query, QualityAssurance.class, "QualityAssurance");
        } catch (Exception e) {
            log.error("Something went wrong finding the ConnectorID");
            throw new QueryOutOfBoundsException("Exception: " + e);
        }
    }

    public List<QualityAssurance> filterByTimeStampQuery(LocalDateTime timeStamp) {
        Query query = new Query();
        try {
            log.info("Creating query for given timestamp");
            query.addCriteria(Criteria.where("timeStamp").lte(timeStamp));
            return mongoTemplate.find(query, QualityAssurance.class, "QualityAssurance");
        } catch (Exception e) {
            log.error("No entries with this timestamp were found.");
            throw new QueryOutOfBoundsException("Exception: " + e);
        }
    }
}
