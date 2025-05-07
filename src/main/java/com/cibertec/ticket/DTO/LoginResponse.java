package com.cibertec.ticket.DTO;

public class LoginResponse {
	private String mensaje;
    private String rol;

    public LoginResponse(String mensaje, String rol) {
        this.mensaje = mensaje;
        this.rol = rol;
    }

    // Getters y Setters
    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
