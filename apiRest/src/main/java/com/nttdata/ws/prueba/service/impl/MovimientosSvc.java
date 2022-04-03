/**
 * 
 */
package com.nttdata.ws.prueba.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.ws.prueba.constants.ConstantesApiRest;
import com.nttdata.ws.prueba.constants.MensajeDelSistema;
import com.nttdata.ws.prueba.model.MovimientosClienteType;
import com.nttdata.ws.prueba.model.MovimientosType;
import com.nttdata.ws.prueba.model.TiposDeMovimiento;
import com.nttdata.ws.prueba.repository.contract.ICuentaRepository;
import com.nttdata.ws.prueba.repository.contract.IMovimientosRepository;
import com.nttdata.ws.prueba.repository.contract.IPersonaRepository;
import com.nttdata.ws.prueba.repository.contract.IClienteRepository;
import com.nttdata.ws.prueba.repository.model.Cuenta;
import com.nttdata.ws.prueba.repository.model.Movimientos;
import com.nttdata.ws.prueba.repository.model.Persona;
import com.nttdata.ws.prueba.service.contract.IMovimientosSvc;
import com.nttdata.ws.prueba.utils.MovimientosConvert;
import com.nttdata.ws.prueba.utils.exceptions.BusinessException;
import com.nttdata.ws.prueba.utils.exceptions.TipoError;

/**
 * @author Angelica
 *
 */
@Service
public class MovimientosSvc implements IMovimientosSvc {
	
	@Autowired
	IMovimientosRepository mvtsRepository;
	
	@Autowired
	ICuentaRepository cuentaRepository;
	
	@Autowired
	IClienteRepository clienteRepository;
	@Autowired
	IPersonaRepository personaRepository;


	@Override
	public MovimientosType crearMovimiento(MovimientosType movimientosType) throws BusinessException {
		String numeroDeCuenta = movimientosType.getNumeroDeCuenta().trim();
		Cuenta datosCuenta = cuentaRepository.verificaNumeroCuenta(numeroDeCuenta);
		if (!(numeroDeCuenta.equals(datosCuenta.getNumeroDeCuenta()))) {
			throw new BusinessException(
					String.format("El número de cuenta: [%s] no se encuentra registrado, por favor verifique el numero.", movimientosType.getNumeroDeCuenta()),
					TipoError.SOLICITUD_INVALIDA);	
		}
		Persona datosCliente = clienteRepository.consultarClientePorIdentificacion(datosCuenta.getIdentificacion());
		
		
		Date FechaMvmt = movimientosType.getFecha();
		// si el tipo de movimiento es DEBITO - RETIRO
		if (movimientosType.equals(TiposDeMovimiento.RETIRO)) {
			// VALIDA el valor del saldo inicial que sea > 0 o que sea sea mayor al monto a retirar
			if(datosCuenta.getSaldoInicial() == 0.00 || datosCuenta.getSaldoInicial() < movimientosType.getMovimiento()) {
				throw new BusinessException(
						String.format(ConstantesApiRest.MENSAJE_ERROR_SALDO, movimientosType.getNumeroDeCuenta()),
						TipoError.SOLICITUD_INVALIDA);
				
			}
			//int numeroMovimientos = mvtsRepository.consultaMovimientosDia(FechaMvmt, TiposDeMovimiento.RETIRO);
			double montoMovimientos = mvtsRepository.cupoMovimientosDia(FechaMvmt, TiposDeMovimiento.RETIRO);
			// valida que no exceda el cupo maximo establecido por dia
			if (Math.abs(montoMovimientos) >ConstantesApiRest.LIMITE_DIARIO_DEBITOS) {
				throw new BusinessException(
						String.format(ConstantesApiRest.MENSAJE_ERROR_CUPO, movimientosType.getNumeroDeCuenta()),
						TipoError.SOLICITUD_INVALIDA);
			}
			movimientosType.setMovimiento(-(movimientosType.getMovimiento()));
			movimientosType.setCliente(datosCliente.getNombreCompleto());
			movimientosType.setIdentificacionCliente(datosCliente.getIdentificacion());
			movimientosType.setSaldoInicial(datosCuenta.getSaldoInicial());
			movimientosType.setSaldoDisponible((datosCuenta.getSaldoInicial() - Math.abs(movimientosType.getMovimiento()) ));
			
			Movimientos movimientos = mvtsRepository.save(MovimientosConvert.typeToModel(movimientosType));
			return MovimientosConvert.modelToType(movimientos);
			
		}
		
		movimientosType.setMovimiento(movimientosType.getMovimiento());
		movimientosType.setCliente(datosCliente.getNombreCompleto());
		movimientosType.setIdentificacionCliente(datosCliente.getIdentificacion());
		movimientosType.setSaldoInicial(datosCuenta.getSaldoInicial());
		movimientosType.setSaldoDisponible((datosCuenta.getSaldoInicial() + movimientosType.getMovimiento()));
		Movimientos movimientos = mvtsRepository.save(MovimientosConvert.typeToModel(movimientosType));
		return MovimientosConvert.modelToType(movimientos);
	}


	@Override
	public MovimientosType actualizarMovimiento(MovimientosClienteType movimientosType) throws BusinessException {
		return null;
	}


	@Override
	public Boolean eliminarMovimiento(String id) throws BusinessException {
		Boolean recursoBorrado = false;
		UUID movimientoID = UUID.fromString(id.trim());
		if (mvtsRepository.findById(movimientoID).isPresent()) {
			mvtsRepository.deleteById(movimientoID);
			recursoBorrado = true;
		} else {
			throw new BusinessException(MensajeDelSistema.RECURSO_NO_ENCONTRADO, TipoError.NO_ENCONTRADO);
		}

		return recursoBorrado;
	}


	@Override
	public List<MovimientosClienteType> consultarMovimientosPorNumeroDeCta(String numCuenta, Date fechaDesde,
			Date fechaHasta) {
		return MovimientosConvert.listModelToTypeCliente(
				mvtsRepository.consultarMovimientosPorNumeroDeCta(numCuenta, fechaDesde, fechaHasta));
	}


	@Override
	public List<MovimientosClienteType> consultarMovimientosPorCliente(String numIdentificacion, Date fechaDesde,
			Date fechaHasta) {
		return MovimientosConvert.listModelToTypeCliente(
				mvtsRepository.consultarMovimientosPorCliente(numIdentificacion, fechaDesde, fechaHasta));
	}


	@Override
	public List<MovimientosClienteType> consultarMovimientosPorFechas(Date fechaDesde, Date fechaHasta) {
		return MovimientosConvert.listModelToTypeCliente(
				mvtsRepository.consultarMovimientosPorFechas(fechaDesde, fechaHasta));
	}
	
	
	


}
