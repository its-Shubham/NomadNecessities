package com.NomadNecessities.service;

import com.NomadNecessities.model.Payment;
import com.NomadNecessities.repository.PaymentRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

  @Autowired private PaymentRepository paymentRepository;

  public Payment processPayment(Payment payment) {
    payment.setPaymentDate(LocalDateTime.now());
    //    payment.setPaymentStatus("COMPLETED");
    return paymentRepository.save(payment);
  }

  public Payment getPaymentById(Long id) {
    Optional<Payment> payment = paymentRepository.findById(id);
    return payment.orElse(null);
  }
}
