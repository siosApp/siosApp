package com.utn.meraki.model;


import java.util.ArrayList;
import java.util.List;

public class ReporteCalificacionDTO {

    private Integer cantidadUsuariosCinco;
    private Integer cantidadUsuariosCuatro;
    private Integer cantidadUsuariosTres;
    private Integer cantidadUsuariosDos;
    private Integer cantidadUsuariosUno;
    private List<UsuarioCalificaDTO> dtoList;

    public ReporteCalificacionDTO() {
        dtoList = new ArrayList<>();
    }

    public Integer getCantidadUsuariosCinco() {
        return cantidadUsuariosCinco;
    }

    public void setCantidadUsuariosCinco(Integer cantidadUsuariosCinco) {
        this.cantidadUsuariosCinco = cantidadUsuariosCinco;
    }

    public Integer getCantidadUsuariosCuatro() {
        return cantidadUsuariosCuatro;
    }

    public void setCantidadUsuariosCuatro(Integer cantidadUsuariosCuatro) {
        this.cantidadUsuariosCuatro = cantidadUsuariosCuatro;
    }

    public Integer getCantidadUsuariosTres() {
        return cantidadUsuariosTres;
    }

    public void setCantidadUsuariosTres(Integer cantidadUsuariosTres) {
        this.cantidadUsuariosTres = cantidadUsuariosTres;
    }

    public Integer getCantidadUsuariosDos() {
        return cantidadUsuariosDos;
    }

    public void setCantidadUsuariosDos(Integer cantidadUsuariosDos) {
        this.cantidadUsuariosDos = cantidadUsuariosDos;
    }

    public Integer getCantidadUsuariosUno() {
        return cantidadUsuariosUno;
    }

    public void setCantidadUsuariosUno(Integer cantidadUsuariosUno) {
        this.cantidadUsuariosUno = cantidadUsuariosUno;
    }

    public List<UsuarioCalificaDTO> getDtoList() {
        return dtoList;
    }

    public void setDtoList(List<UsuarioCalificaDTO> dtoList) {
        this.dtoList = dtoList;
    }
}
