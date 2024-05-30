package com.uade.tpo.tpobackend.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "El libro no está registrado en la base de datos")
public class LibroInexistenteException extends Exception {

}