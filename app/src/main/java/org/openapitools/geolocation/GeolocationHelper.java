package org.openapitools.geolocation;

import javax.annotation.ManagedBean;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.openapitools.model.IpInfo;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

@ManagedBean
public class GeolocationHelper {
    
    private static RestTemplateBuilder builder = new RestTemplateBuilder();
    private static String BASE_URL = "http://ip-api.com/json";
    public IpInfo getIpInfoFromString(@NotNull @Pattern(regexp = "^((25[0-5]|(2[0-4]|1\\d|[1-9]|)\\d)\\.?\\b){4}$") String ip){
        RestTemplate restTemplate = builder.build();
        return restTemplate.getForObject(String.format("%s/%s",BASE_URL,ip), IpInfo.class);
    }

 }
