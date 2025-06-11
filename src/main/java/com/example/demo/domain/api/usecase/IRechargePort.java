package com.example.demo.domain.api.usecase;

import com.example.demo.domain.model.Recharge;

public interface IRechargePort {

    void saveTransaction(Recharge recharge, String user);
}
