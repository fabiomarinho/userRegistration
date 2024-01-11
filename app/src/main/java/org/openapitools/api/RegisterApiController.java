package org.openapitools.api;

import org.openapitools.geolocation.GeolocationHelper;
import org.openapitools.geolocation.ValidationHelper;
import org.openapitools.model.IpInfo;
import org.openapitools.model.UserRegistrationErrorResponse;
import org.openapitools.model.UserRegistrationRequest;
import org.openapitools.model.UserRegistrationResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import io.swagger.v3.oas.annotations.Parameter;

import org.springframework.web.context.request.NativeWebRequest;

import javax.validation.Valid;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-01-11T00:12:03.300940424Z[Etc/UTC]")
@Controller
@RequestMapping("${openapi.userRegistration.base-path:}")
public class RegisterApiController{

    private final NativeWebRequest request;

    @Autowired
    private GeolocationHelper geolocationHelper;

    @Autowired ValidationHelper validationHelper;

    @Autowired
    public RegisterApiController(NativeWebRequest request) {
        this.request = request;
    }

    //@Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }
    
    @PostMapping("/register")
    public ResponseEntity<UserRegistrationResponse> registerUser(
        @Parameter(name = "UserRegistrationRequest", description = "", required = true) @Valid @RequestBody UserRegistrationRequest userRegistrationRequest
    ) {

        for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
            if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                
                // TODO process other integrations
                // (...)

                IpInfo ipInfo = geolocationHelper.getIpInfoFromString(userRegistrationRequest.getIpAddress());
                if (!validationHelper.isValidCountry(ipInfo)){
                    throw new InvalidCountryException(ipInfo.country());
                }
                UUID uuid = UUID.randomUUID();
                UserRegistrationResponse userRegistrationResponse = new UserRegistrationResponse(String.format("Welcome '%s', from '%s'",userRegistrationRequest.getUsername(), ipInfo.city()), uuid.toString());
                return new ResponseEntity<UserRegistrationResponse>(userRegistrationResponse,HttpStatus.OK);
            }
        }
        
        throw new RuntimeException("Media type invalid");
    }

    private ResponseEntity<Object> buildResponseEntity(UserRegistrationErrorResponse apiError) {
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    // Exception Handling

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleInvalidParameters(
            MethodArgumentNotValidException ex) {
        UserRegistrationErrorResponse userRegistrationErrorResponse = new UserRegistrationErrorResponse();
        
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.add(String.format("Error in field '%s': %s",fieldName,errorMessage));
        });

        String result = errors.stream().collect(Collectors.joining(". "));
        userRegistrationErrorResponse.setMessage(result);
        return buildResponseEntity(userRegistrationErrorResponse);
    }

    @ExceptionHandler(InvalidCountryException.class)
    protected ResponseEntity<Object> handleInvalidCountry(
            InvalidCountryException ex) {
        UserRegistrationErrorResponse userRegistrationErrorResponse = new UserRegistrationErrorResponse();
        userRegistrationErrorResponse.setMessage(ex.getMessage());
        return buildResponseEntity(userRegistrationErrorResponse);
    }

    private class InvalidCountryException extends RuntimeException{

        public InvalidCountryException(String country) {
            super(String.format("Only fellows from Canada may use the system. Your country seems to be %s",country));
        }

    }

}
