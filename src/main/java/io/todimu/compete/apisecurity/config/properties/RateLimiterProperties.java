package io.todimu.compete.apisecurity.config.properties;

import lombok.Data;

@Data
public class RateLimiterProperties {
    private int maxNumberOfRequests;
    private int timePeriodForRateLimit;
}
