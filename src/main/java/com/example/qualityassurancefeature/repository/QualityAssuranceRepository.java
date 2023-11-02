package com.example.qualityassurancefeature.repository;

import com.example.qualityassurancefeature.data.model.QualityAssurance;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface QualityAssuranceRepository extends MongoRepository<QualityAssurance, String> {
}
