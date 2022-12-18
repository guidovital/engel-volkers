/**
 * 
 */
package com.engelvolkers.test.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.engelvolkers.test.domain.entity.TrackingRecord;
import com.engelvolkers.test.repository.ITrackingRecordRepository;
import com.engelvolkers.test.service.ITrackingRecordService;

import lombok.AllArgsConstructor;

/**
 * This class is the service layer for the TrackingRecord entity
 * 
 * @author Guilherme Vital
 *
 */
@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class TrackingRecordService implements ITrackingRecordService {

    private ITrackingRecordRepository repository;

    @Override
    public TrackingRecord create(String user, String property) {
        var trackingRecord = TrackingRecord.builder().username(user).property(property).build();
        return repository.save(trackingRecord);
    }

}
