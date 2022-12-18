package com.engelvolkers.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.engelvolkers.test.domain.entity.TrackingRecord;

/**
 * This class is used to access the TrackingRecord table
 * 
 * @author Guilherme Vital
 *
 */
public interface ITrackingRecordRepository extends JpaRepository<TrackingRecord, Long> {

}
