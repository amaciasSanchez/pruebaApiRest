/**
 * 
 */
package com.nttdata.ws.prueba.controller.contract;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nttdata.ws.prueba.model.MovimientosClienteType;
import com.nttdata.ws.prueba.model.MovimientosType;
import com.nttdata.ws.prueba.model.RespuestaType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/**
 * @author Angelica
 *
 */
@Validated
public interface IMovimientoController {

	/** GET crearMovimiento
	 * @param body
	 */
	@Operation(summary = "crearMovimiento", description = "Crear una nuevo movimiento", tags = { "Movimientos" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Created", content = @Content(schema = @Schema(implementation = RespuestaType.class))),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = RespuestaType.class))),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = RespuestaType.class))) })
	@RequestMapping(value = "/movimientos", produces = "application/json; charset=UTF-8", consumes = "application/json; charset=UTF-8", method = RequestMethod.POST)
	ResponseEntity<RespuestaType> crearMovimiento(
			@Parameter(in = ParameterIn.DEFAULT, description = "Movimiento", required = true, schema = @Schema()) @Valid @RequestBody MovimientosType body);
	
	/** GET actualizarMovimiento
	 * @param body
	 */
	@Operation(summary = "actualizarMovimiento", description = "Actualizar Movimiento", tags = { "Movimientos" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = RespuestaType.class))),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = RespuestaType.class))),
			@ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = RespuestaType.class))),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = RespuestaType.class))) })
	@RequestMapping(value = "/movimientos", produces = "application/json; charset=UTF-8", consumes = "application/json; charset=UTF-8", method = RequestMethod.PUT)
	ResponseEntity<RespuestaType> actualizarMovimiento(
			@Parameter(in = ParameterIn.DEFAULT, description = "Movimiento", required = true, schema = @Schema()) @Valid @RequestBody MovimientosClienteType body);

	@Operation(summary = "eliminarMovimiento", description = "Elimina un movimiento por ID", tags = { "Movimientos" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Boolean.class))),
			@ApiResponse(responseCode = "204", description = "No Content", content = @Content(schema = @Schema(implementation = RespuestaType.class))),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = RespuestaType.class))),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = RespuestaType.class))) })
	@RequestMapping(value = "/movimientos/{movimientoId}", produces = "application/json; charset=UTF-8", method = RequestMethod.DELETE)
	ResponseEntity<?> eliminarMovimiento(
			@Parameter(in = ParameterIn.PATH, description = "", required = true, schema = @Schema()) @PathVariable("movimientoId") String movimientoId);
	
	
	@Operation(summary = "Consultar los movimientos por un rango de fechas", description = "Consultar los movimientos por un rango de fechas", tags={ "Movimientos" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "OK", content = @Content(array = @ArraySchema(schema = @Schema(implementation = MovimientosClienteType.class)))),
        @ApiResponse(responseCode = "204", description = "No Content", content = @Content(schema = @Schema(implementation = RespuestaType.class))),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = RespuestaType.class))),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = RespuestaType.class))) })
    @RequestMapping(value = "/movimientos/{fechaDesde}/{fechaHasta}",
        produces = "application/json; charset=UTF-8", 
        method = RequestMethod.GET)
    ResponseEntity<?> consultarMovimientosPorFechas(@PathVariable (name = "fechaDesde", required = true)@DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaDesde, @PathVariable (name = "fechaHasta", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaHasta);
	
	@Operation(summary = "Consultar los movimientos por num. de cuenta y un rango de fechas", description = "Consultar los movimientos por num de cuenta y un rango de fechas", tags={ "Movimientos" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "OK", content = @Content(array = @ArraySchema(schema = @Schema(implementation = MovimientosClienteType.class)))),
        @ApiResponse(responseCode = "204", description = "No Content", content = @Content(schema = @Schema(implementation = RespuestaType.class))),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = RespuestaType.class))),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = RespuestaType.class))) })
    @RequestMapping(value = "/movimientos/cuenta/{numCuenta}/{fechaDesde}/{fechaHasta}",
        produces = "application/json; charset=UTF-8", 
        method = RequestMethod.GET)
    ResponseEntity<?> consultarMovimientosPorNumeroDeCta(@PathVariable (name = "numCuenta", required = true) String numCuenta, @PathVariable (name = "fechaDesde", required = true)@DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaDesde, @PathVariable (name = "fechaHasta", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaHasta);

	
	
	@Operation(summary = "Consultar los movimientos por usuario y un rango de fechas", description = "Consultar los movimientos por usuario y un rango de fechas", tags={ "Movimientos" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "OK", content = @Content(array = @ArraySchema(schema = @Schema(implementation = MovimientosClienteType.class)))),
        @ApiResponse(responseCode = "204", description = "No Content", content = @Content(schema = @Schema(implementation = RespuestaType.class))),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = RespuestaType.class))),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = RespuestaType.class))) })
    @RequestMapping(value = "/movimientos/cliente/{numIdentificacion}/{fechaDesde}/{fechaHasta}",
        produces = "application/json; charset=UTF-8", 
        method = RequestMethod.GET)
    ResponseEntity<?> consultarMovimientosPorCliente(@PathVariable (name = "numIdentificacion", required = true) String numIdentificacion, @PathVariable (name = "fechaDesde", required = true)@DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaDesde, @PathVariable (name = "fechaHasta", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaHasta);

}
