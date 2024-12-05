package com.example.email_service.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.email_service.application.EmailSenderService;
import com.example.email_service.core.EmailRequest;
import com.example.email_service.core.exceptions.EmailServiceException;

import org.apache.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/email")
public class EmailSenderController {
    
    private final EmailSenderService emailSenderService;

    public EmailSenderController(EmailSenderService emailSenderService){
        this.emailSenderService=emailSenderService;
    }

    @PostMapping("")
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequest request) {
        try{

            this.emailSenderService.sendEmail(request.to(), request.subject(), request.body());
            return ResponseEntity.ok("Email sent successfully");
        }catch(EmailServiceException ex){
            return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body("Erro while sending email.");
        }
        
        
    }
    
}
