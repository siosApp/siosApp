package com.utn.meraki.repository;

import com.utn.meraki.entity.EstadoSolicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("estadoSolicitudRepository")
public interface EstadoSolicitudRepository extends JpaRepository<EstadoSolicitud,Serializable> {

    EstadoSolicitud findEstadoSolicitudById(String id);
}
