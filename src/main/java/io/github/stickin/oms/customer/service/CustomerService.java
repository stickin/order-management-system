package io.github.stickin.oms.customer.service;

import io.github.stickin.oms.customer.dto.CustomerRequest;
import io.github.stickin.oms.customer.dto.CustomerResponse;
import io.github.stickin.oms.customer.mapper.CustomerMapper;
import io.github.stickin.oms.customer.model.Customer;
import io.github.stickin.oms.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerResponse create(final CustomerRequest request) {
        final Customer customer = customerMapper.toEntity(request);
        return customerMapper.toResponse(customerRepository.save(customer));
    }

    @Transactional(readOnly = true)
    public CustomerResponse findById(final UUID id) {
        return customerMapper.toResponse(getCustomer(id));
    }

    @Transactional(readOnly = true)
    public List<CustomerResponse> findAll() {
        return customerRepository.findAll().stream()
                .map(customerMapper::toResponse)
                .toList();
    }

    public CustomerResponse update(final UUID id, final CustomerRequest request) {
        final Customer customer = getCustomer(id);
        customerMapper.updateEntity(request, customer);
        return customerMapper.toResponse(customerRepository.save(customer));
    }

    public void delete(final UUID id) {
        customerRepository.delete(getCustomer(id));
    }

    private Customer getCustomer(final UUID id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found: " + id));
    }
}
