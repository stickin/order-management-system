package io.github.stickin.oms.payment.service;

import io.github.stickin.oms.payment.dto.PaymentRequest;
import io.github.stickin.oms.payment.dto.PaymentResponse;
import io.github.stickin.oms.payment.mapper.PaymentMapper;
import io.github.stickin.oms.payment.model.Payment;
import io.github.stickin.oms.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    public PaymentResponse create(final PaymentRequest request) {
        final Payment payment = paymentMapper.toEntity(request);
        payment.setCreatedAt(Instant.now());
        return paymentMapper.toResponse(paymentRepository.save(payment));
    }

    @Transactional(readOnly = true)
    public PaymentResponse findById(final UUID id) {
        return paymentMapper.toResponse(getPayment(id));
    }

    @Transactional(readOnly = true)
    public List<PaymentResponse> findAll() {
        return paymentRepository.findAll().stream()
                .map(paymentMapper::toResponse)
                .toList();
    }

    public PaymentResponse update(final UUID id, final PaymentRequest request) {
        final Payment payment = getPayment(id);
        paymentMapper.updateEntity(request, payment);
        return paymentMapper.toResponse(paymentRepository.save(payment));
    }

    public void delete(final UUID id) {
        paymentRepository.delete(getPayment(id));
    }

    private Payment getPayment(final UUID id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Payment not found: " + id));
    }
}
