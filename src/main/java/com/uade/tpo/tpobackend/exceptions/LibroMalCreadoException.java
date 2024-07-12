package com.uade.tpo.tpobackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "El libro a crear esta mal generado o le faltan datos")
public class LibroMalCreadoException extends Exception {

}
