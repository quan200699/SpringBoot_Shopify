package com.example.market.service.warehousingbill;

import com.example.market.model.WarehousingBill;
import com.example.market.repository.IWarehousingBillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WarehousingBillService implements IWarehousingBillService {
    @Autowired
    private IWarehousingBillRepository warehosingBillRepository;

    @Override
    public Iterable<WarehousingBill> findAll() {
        return warehosingBillRepository.findAll();
    }

    @Override
    public Optional<WarehousingBill> findById(Long id) {
        return warehosingBillRepository.findById(id);
    }

    @Override
    public WarehousingBill save(WarehousingBill warehousingBill) {
        return warehosingBillRepository.save(warehousingBill);
    }

    @Override
    public void remove(Long id) {
        warehosingBillRepository.deleteById(id);
    }
}
