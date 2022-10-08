package com.online.mypay.controller;

import com.online.mypay.dto.MerchantDto;
import com.online.mypay.dto.PaymentDto;
import com.online.mypay.model.*;
import com.online.mypay.service.card.CardDetailsService;
import com.online.mypay.service.counter.CounterService;
import com.online.mypay.service.franchise.FranchiseService;
import com.online.mypay.service.merchant.MerchantService;
import com.online.mypay.service.payment.PaymentService;
import com.online.mypay.service.user.UserService;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class.getName());
    @Autowired
    private MerchantService merchantService;
    @Autowired
    private FranchiseService franchiseService;
    @Autowired
    private CounterService counterService;
    @Autowired
    private CardDetailsService cardDetailsService;
    @Autowired
    private UserService userService;
    @Autowired
    private PaymentService paymentService;

    @RequestMapping("/viewMerchant")
    public ResponseEntity<Map<String, ArrayList<JSONObject>>> viewMerchant(@RequestBody MerchantDto merchantDto) {
        try {
            LOGGER.info("Merchant detail /Merchant/request {} {}", "/Merchant/request", merchantDto);
            Map<String, ArrayList<JSONObject>> listMap = new HashMap<>();
            ArrayList<JSONObject> cardList = new ArrayList<>();
            ArrayList<JSONObject> merchantArr = new ArrayList<>();
            ArrayList<JSONObject> franchiseArr = new ArrayList<>();
            ArrayList<JSONObject> counterArr = new ArrayList<>();

            JSONObject merchantObj = new JSONObject();
            JSONObject franchiseObj = new JSONObject();
            JSONObject counterObj = new JSONObject();

            if (merchantDto != null) {
                String name = SecurityContextHolder.getContext().getAuthentication().getName();
                User byUsername = userService.findByUsername(name);

                Merchant merchant = merchantService.findById(merchantDto.getMerchant());
                Franchise franchise = franchiseService.findById(merchantDto.getFranchise());
                Counter counter = counterService.findById(merchantDto.getCounter());
                List<CardDetails> cardDetails = cardDetailsService.findByUser(byUsername);

                for (CardDetails detail : cardDetails) {
                    JSONObject object = new JSONObject();

                    object.put("id", detail.getId());
                    object.put("cardNo", detail.getCardNo());

                    cardList.add(object);
                }
                merchantObj.put("mId", merchant.getId());
                merchantObj.put("mName", merchant.getName());
                franchiseObj.put("fId", franchise.getId());
                franchiseObj.put("fName", franchise.getName());
                counterObj.put("cId", counter.getId());
                counterObj.put("cName", counter.getName());

                merchantArr.add(merchantObj);
                franchiseArr.add(franchiseObj);
                counterArr.add(counterObj);

                listMap.put("merchant", merchantArr);
                listMap.put("franchise", franchiseArr);
                listMap.put("counter", counterArr);
                listMap.put("card", cardList);

                return new ResponseEntity<>(listMap, HttpStatus.OK);
            } else {
                LOGGER.info("merchantDto cannot be null {}", merchantDto);
                return ResponseEntity.ok(new HashMap<>());
            }

        } catch (Exception e) {
            LOGGER.info("Merchant detail failed {}", merchantDto);
            return ResponseEntity.ok(new HashMap<>());
        }
    }

    @RequestMapping(value = "confirmPayment", method = RequestMethod.POST)
    public ResponseEntity<?> confirmPayment(@RequestBody PaymentDto paymentDto) {

        try {
            LOGGER.info("confirm payment /payment/add {} {}", "/payment/add", paymentDto);

            if (paymentDto != null) {
                Payment payment = paymentService.createPayment(paymentDto);

                if (payment != null) {
                    return new ResponseEntity(payment, HttpStatus.CREATED);
                }

            } else {
                LOGGER.info("paymentDto cant be empty {}", paymentDto);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("paymentDto cannot be empty!");
            }

        } catch (Exception e) {
            LOGGER.info("confirm payment failed {}", paymentDto);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("confirm payment failed!");
        }
        LOGGER.info("confirm payment failed {}", paymentDto);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("confirm payment failed!");
    }

    @RequestMapping(value = "getAllPayment", method = RequestMethod.GET)
    public ResponseEntity<List<Payment>> getAllPayment() {
        try {
            LOGGER.info("get payment /payment/get {} {}", "/payment/get");

            String name = SecurityContextHolder.getContext().getAuthentication().getName();
            User byUsername = userService.findByUsername(name);
            List<Payment> paymentByUser = paymentService.getPaymentByUser(byUsername);
            if (paymentByUser != null) {
                return new ResponseEntity(paymentByUser, HttpStatus.CREATED);
            }

        } catch (Exception e) {
            LOGGER.info("confirm payment failed {}");
            return ResponseEntity.ok(new ArrayList<>());

        }
        LOGGER.info("confirm payment failed {}");
        return ResponseEntity.ok(new ArrayList<>());

    }


}
