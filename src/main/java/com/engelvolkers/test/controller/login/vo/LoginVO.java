/**
 * 
 */
package com.engelvolkers.test.controller.login.vo;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class is used to validate the login request
 * 
 * @author Guilherme Vital
 *
 */
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@JsonInclude(value = Include.NON_NULL)
public class LoginVO {

    @NotBlank(message = "Email property is mandatory")
    // using a custom regex to validate the email
    // and reject when it has @test.com domain using regex
    @Email(message = "Email is invalid", regexp = "^(?!.*@test\\.com$)([a-zA-Z0-9_\\-\\.]+)@((\\[\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|\\d{1,3})(\\]?)$")
    private String email;

    @NotBlank(message = "Email property is mandatory")
    private String password;

}
