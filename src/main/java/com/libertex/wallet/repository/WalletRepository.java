package com.libertex.wallet.repository;

import com.libertex.wallet.entity.WalletEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends CrudRepository<WalletEntity, Long> {
    WalletEntity findByClient_Id (Long idClient);
}

