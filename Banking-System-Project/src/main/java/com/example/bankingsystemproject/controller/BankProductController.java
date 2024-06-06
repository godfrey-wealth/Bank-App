package com.example.bankingsystemproject.controller;



import com.example.bankingsystemproject.business.BankProductService;
import com.example.bankingsystemproject.dto.BankProductRequest;

import com.example.bankingsystemproject.persistence.entity.AccountType;
import com.example.bankingsystemproject.persistence.entity.BankProducts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bank-products")
@CrossOrigin(origins = "http://localhost:3000/")
public class BankProductController {

    private final BankProductService bankProductService;



    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<BankProducts> addBankProduct(@RequestParam("name") String name,
                                                      @RequestParam("description") String description,
                                                      @RequestParam("type") AccountType type,
                                                      @RequestPart("prodImages") MultipartFile imageFile) {
        // Handle image file here, you can save it to the server or process it as needed
        // For simplicity, I'm assuming you'll save it to the `bankProductRequest` object
        BankProductRequest bankProductRequest = BankProductRequest.builder()
                .name(name)
                .description(description)
                .type(type)
                .build();

        try {
            bankProductRequest.setProdImages(imageFile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        BankProducts bankProduct = bankProductService.addBankProduct(bankProductRequest);
        return new ResponseEntity<>(bankProduct, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<BankProducts> getBankProduct(@PathVariable Long id) {
        BankProducts bankProduct = bankProductService.getBankProduct(id);
        if (bankProduct != null) {
            return new ResponseEntity<>(bankProduct, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<BankProducts>> getAllBankProducts() {
        List<BankProducts> bankProducts = bankProductService.getAllBankProducts();
        return new ResponseEntity<>(bankProducts, HttpStatus.OK);
    }
}
