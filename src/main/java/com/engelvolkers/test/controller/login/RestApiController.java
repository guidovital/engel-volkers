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
import com.engelvolkers.test.domain.entity.Property;
import com.engelvolkers.test.domain.entity.User;
import com.engelvolkers.test.domain.mapper.EngelVolkersPropertyMapper;
import com.engelvolkers.test.service.IPropertyService;
import com.engelvolkers.test.service.IUserService;
import com.engelvolkers.test.similarity.CosineSimilarityCalculator;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Rest API Controller
 * URL: http://localhost:8080/task
 * 
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
    private EngelVolkersPropertyMapper propertyMapper;

    /**
     * Login
     * url: http://localhost:8080/task/login
     * method: POST
     * 
     * @param data LoginVO
     * @return ResponseEntity<String> with the message
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginVO data) {
        log.info("Logging in...");
        userService.findByEmailAndPassword(data.getEmail(), data.getPassword());

        return ResponseEntity.ok("You are successfully logged in");
    }

    /**
     * Get all properties
     * url: http://localhost:8080/task/properties
     * method: GET
     * 
     * @return ResponseEntity<List<PropertyVO>> with the list of properties
     */
    @GetMapping("/properties")
    public ResponseEntity<List<PropertyVO>> getProperties() {
        log.info("Getting properties");
        var properties = propertyService.findAll();
        return ResponseEntity.ok(propertyMapper.map(properties));
    }

    /**
     * Get property details
     * url: http://localhost:8080/task/details/{propertyId}
     * method: GET
     * 
     * @param propertyId
     * @param username
     * @return ResponseEntity<PropertyWithRecomendationListVO> with the property
     *         details
     */
    @GetMapping("/details/{propertyId}")
    public ResponseEntity<PropertyWithRecomendationListVO> getDetails(@PathVariable String propertyId,
            @RequestParam(required = true) String username) {
        log.info("Getting details for propertyId {}", propertyId);

        var property = propertyService.findPropertyById(propertyId);

        List<Property> otherProperties = propertyService.findAll();
        otherProperties.remove(property);

        final List<User> users = userService.findAll();

        List<Property> orderedRecomendedList = CosineSimilarityCalculator.execute(property, otherProperties, users);

        if (username != null && !username.isEmpty()) {
            userService.addProperty(username, property);
        }

        PropertyWithRecomendationListVO vo = propertyMapper.mapToRecomendation(property);
        vo.setProperties(propertyMapper.map(orderedRecomendedList));
        return ResponseEntity.ok(vo);
    }
}