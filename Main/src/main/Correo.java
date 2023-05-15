//clase
package main;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 * @author chanp
 */
class Correo {
 private static int contadorId = 0;
 
    private final int id;
    private String remitente;
    private String destinatario;
    private String asunto;
    private String cuerpo;
    private String fecha;
    private String status;

    public Correo(String remitente, String destinatario, String asunto, String cuerpo) {
        this.id = contadorId++;
        this.remitente = remitente;
        this.destinatario = destinatario;
        this.asunto = asunto;
        this.cuerpo = cuerpo;
        this.fecha = obtenerFechaActual();
        this.status = "Escribiendo";
    }

    
    
    private String obtenerFechaActual() {
        LocalDateTime fechaActual = LocalDateTime.now();
        DateTimeFormatter fechaFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter horaFormatter = DateTimeFormatter.ofPattern("HH:mm");
        String fecha = fechaActual.format(fechaFormatter);
        String hora = fechaActual.format(horaFormatter);
        return fecha + " " + hora;
    
        //return "2023-05-14";
    }
    
    public String getLineaCuerpo(int index) {
        if (cuerpo != null && index >= 0 && index < cuerpo.length()) {
            String[] lineas = cuerpo.split("\n");
            if (index < lineas.length) {
                return lineas[index];
            }
        }
        return "";
    }
    
    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setRemitente(String remitente) {
        this.remitente = remitente;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public static int getContadorId() {
        return contadorId;
    }

    public String getRemitente() {
        return remitente;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public String getAsunto() {
        return asunto;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public String getFecha() {
        return fecha;
    }
    
    @Override
    public String toString() {
        return "ID: " + id + "\nRemitente: " + remitente + "\nDestinatario: " + destinatario + "\nAsunto: " +
                asunto + "\nCuerpo: " + cuerpo + "\nFecha: " + fecha + "\nStatus: " + status;
    }  
}
