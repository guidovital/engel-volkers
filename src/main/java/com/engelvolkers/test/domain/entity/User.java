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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class is used to represent the user entity
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
@Table(name = "ev_user")
public class User {

    @Id
    private String email;

    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "ev_property_users", joinColumns = {
            @JoinColumn(name = "username")
    }, inverseJoinColumns = {
            @JoinColumn(name = "property")
    })
    private Set<Property> properties;

    @Override
    public String toString() {
        return "User [email=" + email + ", password=" + password + "]";
    }
}
