package com.example.jwt.controller;

import com.example.jwt.dto.OutComeDto;
import com.example.jwt.response.ApiResponse;
import com.example.jwt.service.OutComeInComeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/transfer")
public class OutComeInComeController {
    @Autowired
    OutComeInComeService outComeInComeService;

    @PostMapping()
    public ResponseEntity<ApiResponse> transfer(@RequestBody OutComeDto outComeDtom, HttpServletRequest request){
        ApiResponse apiResponse = outComeInComeService.outComeWriter(outComeDtom,request);
        if (apiResponse.isStatus()){
            return ResponseEntity.status(201).body(apiResponse);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        }
    }

    @GetMapping("/personoutcome/{owner_id}")
    public ResponseEntity<?> getOneOutCome(@PathVariable Integer owner_id){
        return ResponseEntity.ok(outComeInComeService.getPersonOutCome(owner_id));
    }

    @GetMapping("/personincome/{owner_id}")
    public ResponseEntity<?> getOneInCome(@PathVariable Integer owner_id){
        return ResponseEntity.ok(outComeInComeService.getPersonInCome(owner_id));
    }

}
