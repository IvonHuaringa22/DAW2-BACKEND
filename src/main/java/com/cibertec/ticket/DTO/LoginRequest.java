package com.cibertec.ticket.DTO;

public class LoginRequest {
	  private String correo;
	    private String contrasenia;

	    // Getters y Setters
	    public String getCorreo() {
	        return correo;
	    }

	    public void setCorreo(String correo) {
	        this.correo = correo;
	    }

	    public String getContrasenia() {
	        return contrasenia;
	    }

	    public void setContrasenia(String contrasenia) {
	        this.contrasenia = contrasenia;
	    }
}
