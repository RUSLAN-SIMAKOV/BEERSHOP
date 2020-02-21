package beershop.service;

import beershop.exception.DataProcessingException;
import beershop.model.Order;

public interface OrderService {

    Order create(Order order) throws DataProcessingException;

    Order get(Long id) throws DataProcessingException;

    Order update(Order order) throws DataProcessingException;

    void delete(Long id) throws DataProcessingException;

    void delete(Order order) throws DataProcessingException;
}
