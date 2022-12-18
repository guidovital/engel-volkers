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
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class is used to represent the property entity
 * 
 * @author Guilherme Vital
 *
 */
@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ev_property")
public class Property {

    @Id
    private String id;

    private String name;

    private String details;

    private String price;

    private String image;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "ev_property_users", joinColumns = @JoinColumn(name = "property"), inverseJoinColumns = @JoinColumn(name = "username"))
    private Set<User> users;

    @Transient
    private Double similarity;

    @Override
    public String toString() {
        return "Property [id=" + id + ", name=" + name + ", details=" + details + ", price=" + price + ", image="
                + image + "]";
    }
}
