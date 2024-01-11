package org.openapitools.geolocation;

import javax.annotation.ManagedBean;

import org.openapitools.model.IpInfo;

@ManagedBean
public class ValidationHelper {
    
    public boolean isValidCountry(IpInfo ipInfo){
        return "Canada".equals(ipInfo.country());
    }
}
