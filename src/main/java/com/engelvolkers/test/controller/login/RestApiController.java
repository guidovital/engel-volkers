/**
 * 
 */
package com.engelvolkers.test.controller.login;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.engelvolkers.test.controller.login.vo.LoginVO;
import com.engelvolkers.test.controller.login.vo.PropertyVO;
import com.engelvolkers.test.controller.login.vo.PropertyWithRecomendationListVO;
import com.engelvolkers.test.domain.mapper.EngelVolkersPropertyMapper;
import com.engelvolkers.test.service.IPropertyService;
import com.engelvolkers.test.service.ITrackingRecordService;
import com.engelvolkers.test.service.IUserService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Guilherme Vital
 *
 */
@Slf4j
@RestController
@RequestMapping("task")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RestApiController {
	
	private IPropertyService propertyService;
	
	private IUserService userService;
	
	private ITrackingRecordService trackingRecordService;
	
	private EngelVolkersPropertyMapper propertyMapper;

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody @Valid LoginVO data) {
		log.info("Logging in...");
		var user = userService.findByEmailAndPassword(data.getEmail(), data.getPassword());
		if(user != null && !user.getEmail().isEmpty()) {
			return ResponseEntity.ok("You are successfully logged in");
		}
		
		return ResponseEntity.ok("Email and Password do not match");
	}

	@GetMapping("/properties")
	public ResponseEntity<List<PropertyVO>> getProperties() {
		log.info("Getting properties");
		var properties = propertyService.findAll();
		return ResponseEntity.ok(propertyMapper.map(properties));
	}

	@GetMapping("/details/{propertyId}")
	public ResponseEntity<PropertyWithRecomendationListVO> getDetails(@PathVariable String propertyId, @RequestParam(required = true) String user) {
		log.info("Getting details for propertyId {}", propertyId);
		var property = propertyService.findPropertyById(propertyId);
		trackingRecordService.create(user, propertyId);
		return ResponseEntity.ok(propertyMapper.mapToRecomendation(property));
	}
}
