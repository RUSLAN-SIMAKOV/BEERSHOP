package beershop.service.impl;

import beershop.dao.OrderDao;
import beershop.exception.DataProcessingException;
import beershop.lib.Inject;
import beershop.lib.Service;
import beershop.model.Order;
import beershop.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    @Inject
    private static OrderDao orderDao;

    @Override
    public Order create(Order order) throws DataProcessingException {
        return orderDao.create(order);
    }

    @Override
    public Order get(Long id) throws DataProcessingException {
        return orderDao.get(id).get();
    }

    @Override
    public Order update(Order order) throws DataProcessingException {
        return orderDao.update(order);
    }

    @Override
    public void delete(Long id) throws DataProcessingException {
        orderDao.delete(id);
    }

    @Override
    public void delete(Order order) throws DataProcessingException {
        orderDao.delete(order);
    }
}
