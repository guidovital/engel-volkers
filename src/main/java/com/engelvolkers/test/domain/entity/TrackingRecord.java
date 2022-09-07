/**
 * 
 */
package com.engelvolkers.test.domain.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Guilherme Vital
 *
 */
@Getter
@Setter
@Entity
@Builder
@DynamicInsert
@Table(name = "ev_tracking_record")
public class TrackingRecord {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id;
	
	private String username;
	
	private String property;
	
	@Column(name = "accessed_at", nullable = false)
	private Date accessedAt;
}
