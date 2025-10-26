package com.neuromed.pharmarcy.mapper;

import com.neuromed.pharmarcy.dto.OrdersDTO;
import com.neuromed.pharmarcy.entity.Orders;

public class OrdersMapper {

    public static OrdersDTO mapToOrdersDTO(Orders orders) {
        if (orders == null) return null;
        OrdersDTO dto = new OrdersDTO();
        dto.setId(orders.getId());
        dto.setPatientId(orders.getPatientId());
        dto.setPrescriptionId(orders.getPrescriptionId());
        dto.setStatus(orders.getStatus());
        dto.setReasonForReturnReject(orders.getReasonForReturnReject());
        return dto;
    }

    public static Orders MapToOrders(OrdersDTO dto) {
        if (dto == null) return null;
        Orders orders = new Orders();
        orders.setId(dto.getId());
        orders.setPatientId(dto.getPatientId());
        orders.setPrescriptionId(dto.getPrescriptionId());
        orders.setStatus(dto.getStatus());
        orders.setReasonForReturnReject(dto.getReasonForReturnReject());
        return orders;
    }
}
