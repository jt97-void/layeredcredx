package com.fintech.layeredcredx.controllers;

import com.fintech.layeredcredx.common.enums.AccountStatus;
import com.fintech.layeredcredx.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transactions")
public class AccountController {

    private final AccountService service;




}
