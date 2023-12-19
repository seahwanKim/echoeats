package com.pofol.main.orders.delivery.service;

import com.pofol.main.orders.delivery.domain.DeliveryDto;
import com.pofol.main.orders.delivery.repository.DeliveryRepository;
import com.pofol.main.orders.order.domain.OrderDetailDto;
import com.pofol.main.orders.order.repository.OrderDetailRepository;
import com.pofol.main.orders.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService{

    private final DeliveryRepository deliveryRepository;
    private final OrderDetailRepository orderDetailRepository;

    @Override
    public void writeDelivery(DeliveryDto delivery) {
        try {
            Long ord_id = orderDetailRepository.select(delivery.getOrd_det_id()).getOrd_id();
            delivery.setOrd_id(ord_id);
            deliveryRepository.insert(delivery);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void modifyDelivery(DeliveryDto delivery) {
        try {
            deliveryRepository.update(delivery);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<DeliveryDto> selectListByWaybillNum(Long waybill_num) {
        try {
            return deliveryRepository.selectListByWaybillNum(waybill_num);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}