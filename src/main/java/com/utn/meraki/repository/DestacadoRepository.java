package com.utn.meraki.repository;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.utn.meraki.entity.Destacado;

@Repository("destacadoRepository")

public interface DestacadoRepository extends JpaRepository<Destacado, Serializable>{

}
