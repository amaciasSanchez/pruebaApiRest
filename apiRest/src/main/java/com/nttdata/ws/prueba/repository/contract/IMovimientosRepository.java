/**
 * 
 */
package com.nttdata.ws.prueba.repository.contract;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nttdata.ws.prueba.model.TiposDeMovimiento;
import com.nttdata.ws.prueba.repository.model.Cuenta;
import com.nttdata.ws.prueba.repository.model.Movimientos;
import com.nttdata.ws.prueba.repository.model.Persona;

/**
 * @author Angelica
 *
 */
@Repository

public interface IMovimientosRepository extends JpaRepository<Movimientos, UUID>{
	
	

	@Query (value = "select count(u.id_movimiento) from t_movimientos u where u.fecha \\:\\:DATE = :fecha \\:\\:DATE and u.estado = :#{#tiposDeMovimiento.name()}", nativeQuery = true)
	int consultaMovimientosDia (@Param ("fecha") Date fecha, @Param ("tiposDeMovimiento") TiposDeMovimiento tipo);
	
	
	@Query (value = "select sum(u.id_movimiento) from t_movimientos u where u.fecha \\:\\:DATE = :fecha \\:\\:DATE u.estado = :#{#tiposDeMovimiento.name()}", nativeQuery = true)
	double cupoMovimientosDia (@Param ("fecha") Date fecha, @Param ("tiposDeMovimiento") TiposDeMovimiento tipo);
	
	
	@Query(value = "select u.* from t_movimientos u where u.identificacion_cliente = :identificacion"
			+ " and u.fecha \\:\\:DATE between :fechaDesde and :fechaHasta \\:\\:DATE "
			+ " order by u.fecha desc ", nativeQuery = true)
	List<Movimientos> consultarMovimientosPorCliente(
			@Param("identificacion") String identificacion,
			@Param("fechaDesde") Date fechaInicio, 
			@Param("fechaHasta") Date fechaFin);
	
	
	@Query(value = "select u.* from t_movimientos u where u.numero_de_cuenta = :numCuenta"
			+ " and u.fecha \\:\\:DATE between :fechaDesde and :fechaHasta \\:\\:DATE "
			+ " order by u.fecha desc ", nativeQuery = true)
	List<Movimientos> consultarMovimientosPorNumeroDeCta(
			@Param("numCuenta") String numCuenta,
			@Param("fechaDesde") Date fechaInicio, 
			@Param("fechaHasta") Date fechaFin);
	
	@Query(value = "select u.* from t_movimientos u where "
			+ "  u.fecha \\:\\:DATE between :fechaDesde and :fechaHasta \\:\\:DATE "
			+ " order by u.fecha desc ", nativeQuery = true)
	List<Movimientos> consultarMovimientosPorFechas(
			@Param("fechaDesde") Date fechaInicio, 
			@Param("fechaHasta") Date fechaFin);
	
	
	


}
