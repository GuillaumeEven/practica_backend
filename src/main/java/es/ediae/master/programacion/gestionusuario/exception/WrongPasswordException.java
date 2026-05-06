package es.ediae.master.programacion.gestionusuario.exception;

import es.ediae.master.programacion.gestionusuario.constant.GeneralConstant;

public class WrongPasswordException extends GeneralException {

    public WrongPasswordException() {
        super(GeneralConstant.CONTRASENA_NO_COINCIDE_ERROR_CODE, GeneralConstant.CONTRASENA_NO_COINCIDE_ERROR_MESSAGE);
    }
}
