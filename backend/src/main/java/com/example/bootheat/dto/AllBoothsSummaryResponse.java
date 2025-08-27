// src/main/java/com/example/bootheat/dto/AllBoothsSummaryResponse.java
package com.example.bootheat.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AllBoothsSummaryResponse(
        @JsonProperty("totalSalse") long totalSalse,
        long orderNumbers
) {
    public static AllBoothsSummaryResponse of(long sales, long orders) {
        return new AllBoothsSummaryResponse(sales, orders);
    }
}
