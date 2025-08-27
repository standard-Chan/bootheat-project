// web/ManagerStatsController.java
package com.example.bootheat.web;

import com.example.bootheat.dto.*;
import com.example.bootheat.service.StatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/manager")
@RequiredArgsConstructor
public class ManagerStatsController {

    private final StatsService statsService;

    // GET /api/manager/booths/{boothId}/menus/{menuItemId}/metrics/total-orders
    @GetMapping("/booths/{boothId}/menus/{menuItemId}/metrics/total-orders")
    public MenuTotalOrdersNewResponse totalOrders(@PathVariable Long boothId,
                                                  @PathVariable Long menuItemId) {
        long total = statsService.totalOrdersForMenu(boothId, menuItemId);
        return new MenuTotalOrdersNewResponse(menuItemId, total);
    }


    // GET /api/manager/booths/{boothId}/stats/menu-sales?date=YYYY-MM-DD
    @GetMapping("/booths/{boothId}/stats/menu-sales")
    public java.util.List<MenuSalesItem> menuSales(@PathVariable Long boothId,
                                                   @RequestParam(required = false)
                                                   @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE)
                                                   java.time.LocalDate date) {
        return statsService.menuSalesItems(boothId, date);
    }

    // web/ManagerStatsController.java (엔드포인트 추가/교체)
    @GetMapping("/booths/{boothId}/stats/date/{date}")
    public StatsSummaryResponse summaryByDate(@PathVariable Long boothId,
                                              @PathVariable @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE)
                                              java.time.LocalDate date) {
        return statsService.statsSummaryByDate(boothId, date);
    }

    // GET /api/manager/rankings/menu?boothId=1&metric=qty|amount&limit=5
    @GetMapping("/rankings/menu")
    public MenuRankingResponse ranking(@RequestParam Long boothId,
                                       @RequestParam(defaultValue = "qty") String metric,
                                       @RequestParam(defaultValue = "5") int limit) {
        return statsService.ranking(boothId, metric, limit);
    }
    // GET /api/manager/booths/stats/date/{date}
    // ?묐떟: { boothId: [ {orderId, totalAmount, createdAt, orderItems:[...]}, ... ], ... }
    @GetMapping("/booths/stats/date/{date}")
    public Map<Long, List<OrderWithItemsDto>> allBoothsStatsByDate(
            @PathVariable @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE)
            LocalDate date) {
        return statsService.allBoothsOrdersByDate(date);
    }

    // GET /api/manager/booths/order/stats/date/{date}
    // url 예시: /api/manager/booths/order/stats/date/2023-10-01
    @GetMapping("/order/stats/date/{date}")
    public AllBoothsSummaryResponse allBoothsOrderStatsByDate(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return statsService.allBoothsSummaryByDate(date);
    }

    // GET /api/manager/tableVisit/stats/date/{date}
    // url 예시: /api/manager/tableVisit/stats/date/2023-10-01
    @GetMapping("/tableVisit/stats/date/{date}")
    public List<Long> tableVisitDurations(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return statsService.visitDurationsByDate(date);
    }
}
