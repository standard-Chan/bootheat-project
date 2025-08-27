// web/ManagerOrderQueryController.java (신규)
package com.example.bootheat.web;

import com.example.bootheat.dto.OrderDetailManagerResponse;
import com.example.bootheat.service.QueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/manager")
@RequiredArgsConstructor
public class ManagerOrderQueryController {

    private final QueryService queryService;

    // 상세 1건 (매니저용)
//    @GetMapping("/orders/{orderId}")
//    public OrderDetailManagerResponse getOne(@PathVariable Long orderId) {
//        return queryService.getOrderDetailForManager(orderId);
//    }

    // 테이블의 모든 주문 (상세 배열)
    @GetMapping("/booths/{boothId}/tables/{tableId}/orders")
    public List<OrderDetailManagerResponse> tableOrders(@PathVariable Long boothId,
                                                        @PathVariable Long tableId) {
        return queryService.getTableOrderDetails(boothId, tableId);
    }
}
