package com.ecomm.backend.Controllers;

import com.ecomm.backend.Models.Order;
import com.ecomm.backend.Models.dto.OrderRequest;
import com.ecomm.backend.Models.dto.OrderResponse;
import com.ecomm.backend.Models.dto.OrderSummaryDTO;
import com.ecomm.backend.Services.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
@CrossOrigin
public class OrderController {

    @Autowired
    OrderServices orderServices;

    @PostMapping("/placeOrder")
    public ResponseEntity<OrderResponse> placeOrder(@RequestBody OrderRequest orderRequest) {
        try {
            return new ResponseEntity<>(orderServices.placeOrder(orderRequest), HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getOrders")
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        try {
            return new ResponseEntity<>(orderServices.getAllOrders(), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAllOrdersOpt")
    public ResponseEntity<List<OrderResponse>> getAllOrdersOpt(){
        try{
            return new ResponseEntity<>(orderServices.findAllOrders(),HttpStatus.OK);
        }catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/getOrderSummaries")
    public ResponseEntity<List<OrderSummaryDTO>> getOrderSummaries() {
        try {
            return new ResponseEntity<>(orderServices.getAllOrderSummaries(), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}