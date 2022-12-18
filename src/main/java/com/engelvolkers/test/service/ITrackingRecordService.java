package com.engelvolkers.test.service;

import com.engelvolkers.test.domain.entity.TrackingRecord;

/**
 * This class is the service layer for the TrackingRecord entity
 * 
 * @author Guilherme Vital
 *
 */
public interface ITrackingRecordService {

    TrackingRecord create(String user, String property);

}
