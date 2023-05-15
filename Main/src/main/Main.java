package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author chanp
 */
public class Main {
    
    private static List<Correo> correos = new ArrayList<>();
    private static List<Correo> borradores = new ArrayList<>();
    private static int contadorId = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("Menu de opciones:");
            System.out.println("1. Escribir correo electrónico");
            System.out.println("2. Consultar correo electrónico");
            System.out.println("3. Borrar correo electrónico");
            System.out.println("4. Borradores");
            System.out.println("0. Salir");
            System.out.println("Ingrese su opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcion) {
                case 1:
                    escribirCorreo();
                    break;
                case 2: 
                    consultarCorreo();
                    break;
                case 3:
                    borrarCorreo();
                    break;
                case 4:
                    mostrarBorradores();
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    System.out.println("Gracias por utilizar Sistemas BCA");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente");
                    break;
            }
        } while (opcion != 0);
    }
    
    private static void escribirCorreo() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Escribir correo electrónico");
        System.out.println("Status: Escribiendo");
        System.out.println("Remitente: ");
        String remitente = scanner.nextLine();
        System.out.println("Destinatario: ");
        String destinatario = scanner.nextLine();
        System.out.println("Asunto: ");
        String asunto = scanner.nextLine();
        System.out.println("Cuerpo: ");
        StringBuilder cuerpoBuilder = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            System.out.println("Linea " + (i + 1) + ": ");
            String linea = scanner.nextLine();
            cuerpoBuilder.append(linea).append("\n");
        }
        String cuerpo = cuerpoBuilder.toString();
        
        System.out.println("Opciones:");
    System.out.println("1. Enviar");
    System.out.println("2. Guardar como borrador");
    System.out.println("3. Salir");
    System.out.println("Ingrese su opción: ");
    int opcion = scanner.nextInt();
    scanner.nextLine(); // Consumir la nueva línea pendiente
    
    switch (opcion) {
        case 1:
            // Enviar el correo
            Correo correo = new Correo(remitente, destinatario, asunto, cuerpo);
            correo.setStatus("Enviado");
            correos.add(correo);
            System.out.println("ID: " + correo.getId());
            System.out.println("Status: Enviado");
            break;
        case 2:
            // Guardar como borrador
            Correo borrador = new Correo(remitente, destinatario, asunto, cuerpo);
            borrador.setStatus("Borrador");
            borradores.add(borrador);
            System.out.println("ID: " + borrador.getId());
            System.out.println("Status: Guardado como borrador");
            break;
        case 3:
            // Regresar al menú
            System.out.println("Regresando al menú...");
            break;
        default:
            System.out.println("Opción inválida. Regresando al menú...");
            break;
    }
    }
    
    private static void consultarCorreo() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el ID del correo a recibir: ");
        int id = scanner.nextInt();
        Correo correo = buscarCorreo(id);
        if (correo != null) {
            correo.setStatus("Recibido");
            System.out.println(correo);
        } else {
            System.out.println("ID de correo no encontrado");
        }
    }
    
    private static void borrarCorreo() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el ID del correo a eliminar");
        int id = scanner.nextInt();
        Correo correo = buscarCorreo(id);
        if (correo != null) {
            correos.remove(correo);
            System.out.println("Status: Eliminado");
            System.out.println("Correo eliminado:\n" + correo);
        } else {
            System.out.println("ID no encontrado");
        }
    }
    private static void mostrarBorradores() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Borradores:");
        if (borradores.isEmpty()) {
            System.out.println("No hay borradores");
        } else {
            for (Correo borrador : borradores) {
                System.out.println(borrador);
            }
        }
    }
    
    private static Correo buscarCorreo(int id) {
        for (Correo correo : correos) {
            if (correo.getId() == id) {
                return correo;
            }
        }
        return null;
    }
        
    
    
}
