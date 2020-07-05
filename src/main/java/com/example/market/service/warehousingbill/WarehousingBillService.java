package com.example.market.service.warehousingbill;

import com.example.market.model.WareHouseBill;
import com.example.market.repository.IWarehousingBillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WarehousingBillService implements IWarehousingBillService {
    @Autowired
    private IWarehousingBillRepository warehosingBillRepository;

    @Override
    public Iterable<WareHouseBill> findAll() {
        return warehosingBillRepository.findAll();
    }

    @Override
    public Optional<WareHouseBill> findById(Long id) {
        return warehosingBillRepository.findById(id);
    }

    @Override
    public WareHouseBill save(WareHouseBill wareHouseBill) {
        return warehosingBillRepository.save(wareHouseBill);
    }

    @Override
    public void remove(Long id) {
        warehosingBillRepository.deleteById(id);
    }

    @Override
    public Long sumTotalPriceHaveBought(Integer month, Integer year) {
        return warehosingBillRepository.sumTotalPriceHaveBought(month, year);
    }
}
