package com.example.demo.adapters.driven.client.mappers;

import com.example.demo.adapters.driven.client.model.RechargeClient;
import com.example.demo.domain.model.Recharge;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RechargeClientMapper {
    RechargeClient toClient(Recharge recharge);
}
