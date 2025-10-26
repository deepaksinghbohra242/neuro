package com.neuromed.pharmarcy.controller;

import com.neuromed.pharmarcy.constants.PharmacyConstants;
import com.neuromed.pharmarcy.dto.ErrorResponseDTO;
import com.neuromed.pharmarcy.dto.OrdersDTO;
import com.neuromed.pharmarcy.dto.ResponseDTO;
import com.neuromed.pharmarcy.service.IOrdersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "CRUD REST APIs for Orders Management",
        description = "Orders Management"
)
@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrdersController {

    private final IOrdersService ordersService;

    @Operation(summary = "Create a new order", description = "Adds a new order to the system")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Order created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    public ResponseEntity<ResponseDTO> createOrder(@RequestBody OrdersDTO ordersDTO) {
        ordersService.createOrders(ordersDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(PharmacyConstants.STATUS_201, PharmacyConstants.MESSAGE_201));
    }

    @Operation(summary = "Get all orders", description = "Retrieves a list of all orders")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of orders returned")
    })
    @GetMapping
    public ResponseEntity<List<OrdersDTO>> getOrders() {
        return ResponseEntity.ok(ordersService.getOrders());
    }

    @Operation(summary = "Update an order", description = "Updates details of an existing order")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Order updated successfully"),
            @ApiResponse(responseCode = "417", description = "Update operation failed"),
            @ApiResponse(responseCode = "404", description = "Order not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updateOrder(@PathVariable Long id, @RequestBody OrdersDTO ordersDTO) {
        OrdersDTO updated = ordersService.updateOrders(id, ordersDTO);

        if (updated != null) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(PharmacyConstants.STATUS_200, PharmacyConstants.MESSAGE_200));
        }

        return ResponseEntity
                .status(HttpStatus.EXPECTATION_FAILED)
                .body(new ResponseDTO(PharmacyConstants.STATUS_417, PharmacyConstants.MESSAGE_417_UPDATE));
    }

    @Operation(summary = "Delete an order", description = "Deletes an order by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Order deleted successfully"),
            @ApiResponse(responseCode = "417", description = "Delete operation failed"),
            @ApiResponse(responseCode = "404", description = "Order not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteOrder(@PathVariable Long id) {
        boolean deleted = ordersService.deleteOrders(id);

        if (deleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(PharmacyConstants.STATUS_200, PharmacyConstants.MESSAGE_200));
        }

        return ResponseEntity
                .status(HttpStatus.EXPECTATION_FAILED)
                .body(new ResponseDTO(PharmacyConstants.STATUS_417, PharmacyConstants.MESSAGE_417_DELETE));
    }

    @Operation(
            summary = "Fetch Order Details",
            description = "Fetches order details by order ID"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Order details fetched successfully"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))
            )
    })
    @GetMapping("/fetch")
    public ResponseEntity<OrdersDTO> fetchOrderDetails(
            @RequestHeader("neuromed-correlation-id") String correlationId,
            @RequestParam Long id) {
        OrdersDTO ordersDTO = ordersService.fetchOrders(id, correlationId);
        return ResponseEntity.status(HttpStatus.OK).body(ordersDTO);
    }

    @Operation(summary = "Fetch all orders by Prescription ID",
            description = "Fetch all order records associated with a given Prescription ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Orders fetched successfully"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @GetMapping("/by-prescription/{prescriptionId}")
    public ResponseEntity<List<OrdersDTO>> getOrdersByPrescriptionId(@PathVariable("prescriptionId") Long prescriptionId) {
        List<OrdersDTO> orders = ordersService.getOrdersByPrescriptionId(prescriptionId);
        return ResponseEntity.ok(orders);
    }

}
