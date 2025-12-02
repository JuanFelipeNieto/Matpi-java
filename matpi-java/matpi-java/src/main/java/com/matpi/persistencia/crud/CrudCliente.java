package com.matpi.persistencia.crud;

import com.matpi.persistencia.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface CrudCliente extends JpaRepository<ClienteEntity, BigInteger> {
}
