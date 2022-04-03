/**
 * 
 */
package com.nttdata.ws.prueba.controller.impl;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.ws.prueba.constants.MensajeDelSistema;
import com.nttdata.ws.prueba.controller.contract.IMovimientoController;
import com.nttdata.ws.prueba.model.MovimientosClienteType;
import com.nttdata.ws.prueba.model.MovimientosType;
import com.nttdata.ws.prueba.model.RespuestaType;
import com.nttdata.ws.prueba.service.contract.IMovimientosSvc;
import com.nttdata.ws.prueba.utils.DataValidator;
import com.nttdata.ws.prueba.utils.MovimientosValidator;
import com.nttdata.ws.prueba.utils.exceptions.BusinessException;

/**
 * @author Angelica
 *
 */
@RestController
@CrossOrigin(origins = "*")
public class MovimientoController implements IMovimientoController {
	
	private static final Logger LOG = LoggerFactory.getLogger(MovimientoController.class);
	@Autowired
	IMovimientosSvc mvtsSvc;
	@Override
	public ResponseEntity<RespuestaType> crearMovimiento(@Valid MovimientosType body) {
		ResponseEntity<RespuestaType> respuestaCrear;
		try {	
			LOG.info("INICIA PROCESO DE CREAR MOVIMIENTO");
			MovimientosType result = mvtsSvc.crearMovimiento(body);
			respuestaCrear = MovimientosValidator.validarResultadoaByCreate(result);
		} catch (Exception e) {
			LOG.error("ERROR PROCESO DE CREAR MOVIMIENTO");
			return respuestaCrear = new ResponseEntity<>(
					new RespuestaType().codigoRespuesta("500").descripcion(MensajeDelSistema.ERROR_INTERNO),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		LOG.info("FINALIZA PROCESO DE CREAR MOVIMIENTO");
		return respuestaCrear;
	}
	@Override
	public ResponseEntity<RespuestaType> actualizarMovimiento(@Valid MovimientosClienteType body) {
		ResponseEntity<RespuestaType> respuestaActualizar;
		try {	
			LOG.info("INICIA PROCESO DE ACTUALIZAR MOVIMIENTO");
			MovimientosType result = mvtsSvc.actualizarMovimiento(body);
			respuestaActualizar = MovimientosValidator.validarResultadoaByUpdate(result, body.getId(), body.getNumeroDeCuenta());
			LOG.info("FINALIZA PROCESO DE ACTUALIZAR MOVIMIENTO");
			return respuestaActualizar;
		} catch (BusinessException e) {
			LOG.error("ERROR DE NEGOCIO EN ACTUALIZAR MOVIMIENTO", e.getMessage());
			return DataValidator.validarResultado(e);
		} catch (Exception e) {
			LOG.error("ERROR PROCESO DE ACTUALIZAR MOVIMIENTO",  e.getMessage());
			return new ResponseEntity<>(
					new RespuestaType().codigoRespuesta("500").descripcion("ERROR INTERNO"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@Override
	public ResponseEntity<?> eliminarMovimiento(String movimientoId) {
		try {
			LOG.info("INICIA ELIMINAR MOVIMIENTO POR ID");
			Boolean movimiento = mvtsSvc.eliminarMovimiento(movimientoId);
			return new ResponseEntity<Boolean>(movimiento, HttpStatus.OK);
		}catch (BusinessException e) {
			LOG.error("ERROR DE NEGOCIO ELIMINAR MOVIMIENTO POR ID ->{}", e.getMessage());
			return DataValidator.validarResultado(e);
		}catch (Exception e) {
			LOG.error("ERROR EN EXCEPTION AL ELIMINAR MOVIMIENTO POR ID {}",e.getMessage());
			return new ResponseEntity<RespuestaType>(
					new RespuestaType().codigoRespuesta("500").descripcion(MensajeDelSistema.ERROR_INTERNO),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Override
	public ResponseEntity<?> consultarMovimientosPorFechas(Date fechaDesde, Date fechaHasta) {
		ResponseEntity<?> respuesta;
		LOG.info("INICIA CONSULTA MOVIMIENTOS DE UN RANGO DE FECHAS");
		try {
			List<MovimientosClienteType> movimientosCliente = mvtsSvc.consultarMovimientosPorFechas(fechaDesde, fechaHasta);
			respuesta = MovimientosValidator.validarResultado(movimientosCliente);
		} catch (Exception e) {
			LOG.info("ERROR EN CONSULTA MOVIMIENTOS DE UN RANGO DE FECHAS {}", e.getMessage());
			return new ResponseEntity<>(
					new RespuestaType().codigoRespuesta("500").descripcion(MensajeDelSistema.ERROR_INTERNO),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOG.info("FINALIZA MOVIMIENTOS DE UN RANGO DE FECHAS");
		return respuesta;
	}
	@Override
	public ResponseEntity<?> consultarMovimientosPorCliente(String numIdentificacion, Date fechaDesde,
			Date fechaHasta) {
		ResponseEntity<?> respuesta;
		LOG.info("INICIA CONSULTA MOVIMIENTOS POR CLIENTE");
		try {
			List<MovimientosClienteType> movimientosCliente = mvtsSvc.consultarMovimientosPorCliente(numIdentificacion, fechaDesde, fechaHasta);
			respuesta = MovimientosValidator.validarResultado(movimientosCliente);
		} catch (Exception e) {
			LOG.info("ERROR EN CONSULTA MOVIMIENTOS POR CLIENTE {}", e.getMessage());
			return new ResponseEntity<>(
					new RespuestaType().codigoRespuesta("500").descripcion(MensajeDelSistema.ERROR_INTERNO),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOG.info("FINALIZA MOVIMIENTOS POR CLIENTE");
		return respuesta;
	}
	@Override
	public ResponseEntity<?> consultarMovimientosPorNumeroDeCta(String numCuenta, Date fechaDesde, Date fechaHasta) {
		ResponseEntity<?> respuesta;
		LOG.info("INICIA CONSULTA MOVIMIENTOS POR NUMERO DE CUENTA");
		try {
			List<MovimientosClienteType> movimientosCliente = mvtsSvc.consultarMovimientosPorNumeroDeCta(numCuenta, fechaDesde, fechaHasta);
			respuesta = MovimientosValidator.validarResultado(movimientosCliente);
		} catch (Exception e) {
			LOG.info("ERROR EN CONSULTA MOVIMIENTOS POR NUMERO DE CUENTA {}", e.getMessage());
			return new ResponseEntity<>(
					new RespuestaType().codigoRespuesta("500").descripcion(MensajeDelSistema.ERROR_INTERNO),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOG.info("FINALIZA MOVIMIENTOS POR NUMERO DE CUENTA");
		return respuesta;
	}
	
}
