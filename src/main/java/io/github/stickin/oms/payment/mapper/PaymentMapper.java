package io.github.stickin.oms.payment.mapper;

import io.github.stickin.oms.payment.dto.PaymentRequest;
import io.github.stickin.oms.payment.dto.PaymentResponse;
import io.github.stickin.oms.payment.model.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    PaymentResponse toResponse(Payment payment);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Payment toEntity(PaymentRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    void updateEntity(PaymentRequest request, @MappingTarget Payment payment);
}
