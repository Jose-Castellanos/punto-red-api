package com.example.demo.domain.spi;

import com.example.demo.domain.model.Recharge;
import com.example.demo.domain.model.Transaction;

public interface IRechargeClientPort {

    Transaction saveRecharge(Recharge recharge);

}
