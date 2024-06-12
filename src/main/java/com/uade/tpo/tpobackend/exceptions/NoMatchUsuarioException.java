package com.uade.tpo.tpobackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Usuario Incorrecto/ no sos el dueño del libro")
public class NoMatchUsuarioException extends Exception {

}