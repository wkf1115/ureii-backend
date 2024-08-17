package com.ureii.ureiibackend.service.impl;

import com.ureii.ureiibackend.model.BankAccount;
import com.ureii.ureiibackend.model.ResponseResult;
import com.ureii.ureiibackend.repository.BankAccountRepository;
import com.ureii.ureiibackend.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class BankAccountServiceImpl implements BankAccountService {


    @Autowired
    BankAccountRepository bankAccountRepository;

    @Override
    public ResponseResult<BankAccount> getBankAccount(Long userId) {
        Optional<BankAccount> bankAccount = bankAccountRepository.findByUserId(userId);

        // 如果银行账户存在，返回成功结果，否则返回错误信息
        if (bankAccount.isPresent()) {
            return new ResponseResult<>(200, "Bank account found", bankAccount.get());
        } else {
            return new ResponseResult<>(400, "Bank account not found");
        }
    }

    @Override
    public ResponseResult<String> createBankAccount(BankAccount bankAccount, Long userId) {
        bankAccount.setUserId(userId);
        bankAccountRepository.save(bankAccount);
        return new ResponseResult<>(200, "Create Bank Account Success! new userId is " + userId);
    }
}
