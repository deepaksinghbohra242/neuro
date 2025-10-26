package com.neuromed.pharmarcy.service.impl;

import com.neuromed.pharmarcy.dto.OrdersDTO;
import com.neuromed.pharmarcy.entity.Orders;

import com.neuromed.pharmarcy.execption.ResourceNotFoundException;
import com.neuromed.pharmarcy.mapper.OrdersMapper;
import com.neuromed.pharmarcy.repository.OrdersRepository;
import com.neuromed.pharmarcy.service.IOrdersService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements IOrdersService {

    private OrdersRepository ordersRepository;

    @Override
    public OrdersDTO createOrders(OrdersDTO ordersDTO) {
        Orders orders = OrdersMapper.MapToOrders(ordersDTO);

        Orders saved = ordersRepository.save(orders);

        return OrdersMapper.mapToOrdersDTO(saved);
    }

    @Override
    public List<OrdersDTO> getOrders() {
        return ordersRepository
                .findAll()
                .stream()
                .map(OrdersMapper::mapToOrdersDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OrdersDTO updateOrders(Long id, OrdersDTO ordersDTO) {
        Orders existing = ordersRepository.findById(id).orElse(null);

        if (existing != null) {
            existing.setPatientId(ordersDTO.getPatientId());

            existing.setPrescriptionId(ordersDTO.getPrescriptionId());

            if (ordersDTO.getStatus() != null) {
                existing.setStatus(ordersDTO.getStatus());
            }

            existing.setReasonForReturnReject(ordersDTO.getReasonForReturnReject());

            Orders updated = ordersRepository.save(existing);

            return OrdersMapper.mapToOrdersDTO(updated);
        }

        return null;
    }

    @Override
    public boolean deleteOrders(Long id) {
        if (ordersRepository.existsById(id)) {
            ordersRepository.deleteById(id);
            return true;
        }

        return false;
    }

    @Override
    public OrdersDTO fetchOrders(Long id, String correlationId) {
        Orders orders = ordersRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Orders", "id", id.toString()));
        return OrdersMapper.mapToOrdersDTO(orders);
    }

    @Override
    public List<OrdersDTO> getOrdersByPrescriptionId(Long prescriptionId) {
        List<Orders> ordersList = ordersRepository.findByPrescriptionId(prescriptionId);
        return ordersList.stream()
                .map(OrdersMapper::mapToOrdersDTO)
                .collect(Collectors.toList());
    }
}