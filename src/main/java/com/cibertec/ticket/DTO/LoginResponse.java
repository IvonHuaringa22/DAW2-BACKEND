package com.cibertec.ticket.DTO;

import lombok.Data;

@Data
public class LoginResponse {
	private String mensaje;
    private String rol;
	private String token;
	private String fechaLogin;

    public LoginResponse(String mensaje, String rol, String token, String fechaLogin) {
        this.mensaje = mensaje;
        this.rol = rol;
        this.token = token;
        this.fechaLogin = fechaLogin;
    }
}
