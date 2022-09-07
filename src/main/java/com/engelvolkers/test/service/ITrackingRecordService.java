package com.engelvolkers.test.service;

import com.engelvolkers.test.domain.entity.TrackingRecord;

public interface ITrackingRecordService {

	TrackingRecord create(String user, String property);

}
