/**
 * 
 */
package com.engelvolkers.test.domain.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Guilherme Vital
 *
 */
@Getter
@Setter
@Entity
@Table(name = "ev_property")
public class Property {

	@Id
	private String id;

	private String name;

	private String details;

	private String price;

	private String image;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "ev_property_users", joinColumns = @JoinColumn(name = "property"), inverseJoinColumns = @JoinColumn(name = "user"))
	private Set<User> users;
	
	@Override
	public String toString() {
		return "Property [id=" + id + ", name=" + name + ", details=" + details + ", price=" + price + ", image="
				+ image + "]";
	}

}
