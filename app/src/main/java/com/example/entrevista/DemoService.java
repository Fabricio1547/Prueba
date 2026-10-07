package com.example.entrevista;

/** Punto de conexión: reemplazar por un cliente al recibir URL y contrato del servicio. */
public final class DemoService {
    private DemoService() { }
    public static boolean submitInformation(String phone, String identity, String complement) {
        // Demostración local: no obtiene ubicación ni envía datos a un servidor.
        return InputValidator.isPhoneValid(phone) && InputValidator.isIdentityValid(identity)
                && InputValidator.isComplementValid(complement);
    }
}
