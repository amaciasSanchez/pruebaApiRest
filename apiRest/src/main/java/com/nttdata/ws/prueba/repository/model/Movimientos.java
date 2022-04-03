/**
 * 
 */
package com.nttdata.ws.prueba.repository.model;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.nttdata.ws.prueba.model.TiposDeMovimiento;

/**
 * @author Angelica
 *
 */
@Entity
@Table(name = "t_movimientos")
public class Movimientos implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_movimiento", unique = true)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Column(name = "fecha")
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date fecha;
	
	@Column(name = "nombre_del_cliente")
	private String cliente;
	
	@Column(name = "identificacion_cliente")
	private String identificacionCliente;
	
	/**
	 * @return the identificacionCliente
	 */
	public String getIdentificacionCliente() {
		return identificacionCliente;
	}

	/**
	 * @param identificacionCliente the identificacionCliente to set
	 */
	public void setIdentificacionCliente(String identificacionCliente) {
		this.identificacionCliente = identificacionCliente;
	}

	@Column(name = "numero_de_cuenta")
	private String numeroDeCuenta;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_movimiento")
	private TiposDeMovimiento tipo;
	
	@Column(name = "saldo_inicial")
	private double saldoInicial;
	
	@Column(name = "estado")
	private boolean estado;
	
	@Column(name = "movimiento")
	private double movimiento;
	
	@Column(name = "saldo_disponible")
	private double saldoDisponible;

	/**
	 * @return the id
	 */
	public UUID getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(UUID id) {
		this.id = id;
	}

	/**
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the cliente
	 */
	public String getCliente() {
		return cliente;
	}

	/**
	 * @param cliente the cliente to set
	 */
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	/**
	 * @return the numeroDeCuenta
	 */
	public String getNumeroDeCuenta() {
		return numeroDeCuenta;
	}

	/**
	 * @param numeroDeCuenta the numeroDeCuenta to set
	 */
	public void setNumeroDeCuenta(String numeroDeCuenta) {
		this.numeroDeCuenta = numeroDeCuenta;
	}

	/**
	 * @return the tipo
	 */
	public TiposDeMovimiento getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(TiposDeMovimiento tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the saldoInicial
	 */
	public double getSaldoInicial() {
		return saldoInicial;
	}

	/**
	 * @param saldoInicial the saldoInicial to set
	 */
	public void setSaldoInicial(double saldoInicial) {
		this.saldoInicial = saldoInicial;
	}

	/**
	 * @return the estado
	 */
	public boolean isEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	/**
	 * @return the movimiento
	 */
	public double getMovimiento() {
		return movimiento;
	}

	/**
	 * @param movimiento the movimiento to set
	 */
	public void setMovimiento(double movimiento) {
		this.movimiento = movimiento;
	}

	/**
	 * @return the saldo_disponible
	 */
	public double getSaldoDisponible() {
		return saldoDisponible;
	}

	/**
	 * @param saldo_disponible the saldo_disponible to set
	 */
	public void setSaldo_disponible(double saldoDisponible) {
		this.saldoDisponible = saldoDisponible;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
