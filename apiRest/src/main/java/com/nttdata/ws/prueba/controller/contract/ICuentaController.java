/**
 * 
 */
package com.nttdata.ws.prueba.controller.contract;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nttdata.ws.prueba.model.CuentaType;
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
public interface ICuentaController {

	@Operation(summary = "crearCuenta", description = "Crear una nueva cuenta", tags = { "Cuentas" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Created", content = @Content(schema = @Schema(implementation = RespuestaType.class))),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = RespuestaType.class))),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = RespuestaType.class))) })
	@RequestMapping(value = "/cuentas", produces = "application/json; charset=UTF-8", consumes = "application/json; charset=UTF-8", method = RequestMethod.POST)
	ResponseEntity<RespuestaType> crearCuenta(
			@Parameter(in = ParameterIn.DEFAULT, description = "Cuenta", required = true, schema = @Schema()) @Valid @RequestBody CuentaType body);

	@Operation(summary = "actualizarCuenta", description = "Actualizar Cuenta", tags = { "Cuentas" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = RespuestaType.class))),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = RespuestaType.class))),
			@ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = RespuestaType.class))),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = RespuestaType.class))) })
	@RequestMapping(value = "/cuentas", produces = "application/json; charset=UTF-8", consumes = "application/json; charset=UTF-8", method = RequestMethod.PUT)
	ResponseEntity<RespuestaType> actualizarCuenta(
			@Parameter(in = ParameterIn.DEFAULT, description = "Cuenta", required = true, schema = @Schema()) @Valid @RequestBody CuentaType body);

	@Operation(summary = "eliminarCuenta", description = "Elimina una cuenta por ID", tags = { "Cuentas" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Boolean.class))),
			@ApiResponse(responseCode = "204", description = "No Content", content = @Content(schema = @Schema(implementation = RespuestaType.class))),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = RespuestaType.class))),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = RespuestaType.class))) })
	@RequestMapping(value = "/cuentas/{cuentaId}", produces = "application/json; charset=UTF-8", method = RequestMethod.DELETE)
	ResponseEntity<?> eliminarCuenta(
			@Parameter(in = ParameterIn.PATH, description = "", required = true, schema = @Schema()) @PathVariable("cuentaId") String cuentaId);
	
	@Operation(summary = "consulta cuenta por num. de cuenta", description = "consulta cuenta por num. de cuenta", tags={ "Cuentas" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = CuentaType.class))),
        @ApiResponse(responseCode = "204", description = "No Content", content = @Content(schema = @Schema(implementation = RespuestaType.class))),
        @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = RespuestaType.class))),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = RespuestaType.class))) })
    @RequestMapping(value = "/cuentas/{numCuenta}",
        produces = "application/json; charset=UTF-8", 
        method = RequestMethod.GET)
    ResponseEntity<?> consultarCuentaPorNumero(@Parameter(in = ParameterIn.PATH, description = "num.de cuenta", required=true, schema=@Schema()) @PathVariable("numCuenta") String numCuenta);
	
	
	@Operation(summary = "consulta cuenta por num. identificación del cliente", description = "consulta cuenta por num. identificación del cliente", tags={ "Cuentas" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "OK", content = @Content(array = @ArraySchema(schema = @Schema(implementation = CuentaType.class)))),
        @ApiResponse(responseCode = "204", description = "No Content", content = @Content(schema = @Schema(implementation = RespuestaType.class))),
        @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = RespuestaType.class))),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = RespuestaType.class))) })
    @RequestMapping(value = "/cuentas/cliente/{numIdentificacion}",
        produces = "application/json; charset=UTF-8", 
        method = RequestMethod.GET)
    ResponseEntity<?> consultarCuentaPorNumeroIdentificacion(@Parameter(in = ParameterIn.PATH, description = "num.de Identificación del Cliente", required=true, schema=@Schema()) @PathVariable("numIdentificacion") String numIdentificacion);
	
	@Operation(summary = "consultar cuentas por estado", description = "consulta num. de cuenta por estado", tags={ "Cuentas" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "OK", content = @Content(array = @ArraySchema(schema = @Schema(implementation = CuentaType.class)))),
        @ApiResponse(responseCode = "204", description = "No Content", content = @Content(schema = @Schema(implementation = RespuestaType.class))),
        @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = RespuestaType.class))),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = RespuestaType.class))) })
    @RequestMapping(value = "/cuentas/estado/{estado}",
        produces = "application/json; charset=UTF-8", 
        method = RequestMethod.GET)
    ResponseEntity<?> consultarCuentasPorEstado(@Parameter(in = ParameterIn.PATH, description = "estado de la cuenta", required=true, schema=@Schema()) @PathVariable("estado") Boolean estado);

	
	
	
}
