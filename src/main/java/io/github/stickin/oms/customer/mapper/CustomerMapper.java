package io.github.stickin.oms.customer.mapper;

import io.github.stickin.oms.customer.dto.CustomerRequest;
import io.github.stickin.oms.customer.dto.CustomerResponse;
import io.github.stickin.oms.customer.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerResponse toResponse(Customer customer);

    @Mapping(target = "id", ignore = true)
    Customer toEntity(CustomerRequest request);

    @Mapping(target = "id", ignore = true)
    void updateEntity(CustomerRequest request, @MappingTarget Customer customer);
}
