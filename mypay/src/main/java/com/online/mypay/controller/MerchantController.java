package com.online.mypay.controller;

import com.online.mypay.dto.MerchantDto;
import com.online.mypay.model.Counter;
import com.online.mypay.model.Franchise;
import com.online.mypay.model.Merchant;
import com.online.mypay.service.counter.CounterService;
import com.online.mypay.service.franchise.FranchiseService;
import com.online.mypay.service.merchant.MerchantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/merchant")
public class MerchantController {

    @Autowired
    private MerchantService merchantService;
    @Autowired
    private FranchiseService franchiseService;
    @Autowired
    private CounterService counterService;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class.getName());

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<String> createMerchant(@RequestBody MerchantDto merchantDto) {

        try {
            LOGGER.info("Add Merchant /merchant/add {} {}", "/merchant/add", merchantDto);
            if (merchantDto != null) {
                Merchant merchant = merchantService.createMerchant(merchantDto);

                if (merchant != null) {
                    return new ResponseEntity(HttpStatus.CREATED);
                }

            } else {
                LOGGER.info("userDto cant be empty {}", merchantDto);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("merchantDto cant be empty!");
            }

        } catch (Exception e) {
            LOGGER.info("Merchant creation failed {}", merchantDto);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Merchant creation failed!");
        }
        LOGGER.info("Merchant creation failed {}", merchantDto);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Merchant creation failed!");
    }

    @RequestMapping(value = "/franchise/register", method = RequestMethod.POST)
    public ResponseEntity<String> franchiseRegister(@RequestBody MerchantDto merchantDto) {

        try {
            LOGGER.info("Add franchise /franchise/add {} {}", "/franchise/add", merchantDto);
            if (merchantDto != null) {
                Franchise franchise = franchiseService.createFranchise(merchantDto);

                if (franchise != null) {
                    return new ResponseEntity(HttpStatus.CREATED);
                }

            } else {
                LOGGER.info("merchantDto cant be empty {}", merchantDto);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("merchantDto cant be empty!");
            }

        } catch (Exception e) {
            LOGGER.info("franchise creation failed {}", merchantDto);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("franchise creation failed!");
        }
        LOGGER.info("franchise creation failed {}", merchantDto);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("franchise creation failed!");
    }

    @RequestMapping(value = "/counter/register", method = RequestMethod.POST)
    public ResponseEntity<String> counterRegister(@RequestBody MerchantDto merchantDto) {

        try {
            LOGGER.info("Add counter /counter/add {} {}", "/counter/add", merchantDto);
            if (merchantDto != null) {
                Counter counter = counterService.createCounter(merchantDto);

                if (counter != null) {
                    return new ResponseEntity(HttpStatus.CREATED);
                }

            } else {
                LOGGER.info("merchantDto cant be empty {}", merchantDto);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("merchantDto cant be empty!");
            }

        } catch (Exception e) {
            LOGGER.info("counter creation failed {}", merchantDto);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("counter creation failed!");
        }
        LOGGER.info("counter creation failed {}", merchantDto);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("counter creation failed!");
    }
}
