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
            System.out.println("\u001B[37m" + "         SISTEMAS BCA");
            System.out.println("\u001B[37m" + "Sistema de correos electronicos\n");
            System.out.println("--------Menu de opciones--------");
            System.out.println("1. Escribir correo electronico");
            System.out.println("2. Consultar correo electronico");
            System.out.println("3. Borrar correo electronico");
            System.out.println("4. Borradores");
            System.out.println("0. Salir");
            System.out.println("Ingrese su opcion: ");
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
                    System.out.println("Saliendo del programa...\n");
                    System.out.println("\u001B[37m" + "Gracias por utilizar Sistemas BCA\n");
                    System.out.println("Todos los derechos reservados");
                    System.out.println("\033[0;1m" + "@BCA 2023");
                    break;
                default:
                    System.out.println("\u001B[31m" + "Opcion invalida. Intente nuevamente\n");
                    break;
            }
        } while (opcion != 0);
    }

    private static void escribirCorreo() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Escribir correo electronico");
        System.out.println("Status: Escribiendo...");
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

        System.out.println("--------Opciones--------");
        System.out.println("1. Enviar");
        System.out.println("2. Guardar como borrador");
        System.out.println("3. Salir sin guardar cambios");
        System.out.println("Ingrese su opcion: ");
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Consumir la nueva línea pendiente

        switch (opcion) {
            case 1:
                // Enviar el correo
                Correo correo = new Correo(remitente, destinatario, asunto, cuerpo);
                correo.setStatus("Enviado");
                correos.add(correo);
                System.out.println("ID: " + correo.getId());
                System.out.println("Status: Enviado\n");
                break;
            case 2:
                // Guardar como borrador
                Correo borrador = new Correo(remitente, destinatario, asunto, cuerpo);
                borrador.setStatus("Borrador\n");
                borradores.add(borrador);
                System.out.println("ID: " + borrador.getId());
                System.out.println("Status: Guardado como borrador\n");
                break;
            case 3:
                // Regresar al menú
                System.out.println("Redirigiendo al menu...\n");
                break;
            default:
                System.out.println("\u001B[31m" + "Opcion invalida. Redirigiendo al menu...\n");
                break;
        }
    }

    private static void consultarCorreo() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el ID del correo a recibir: ");
        int id = scanner.nextInt();
        Correo correo = buscarCorreo(id);
        if (correo != null) {
            correo.setStatus("Recibido\n");
            System.out.println(correo);
        } else {
            System.out.println("\u001B[31m" + "ID de correo no encontrado\n");
        }
    }

    private static void borrarCorreo() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el ID del correo a eliminar");
        int id = scanner.nextInt();
        Correo correo = buscarCorreo(id);
        if (correo != null) {
            correos.remove(correo);
            System.out.println("Status: Eliminado\n");
            System.out.println("Correo eliminado:\n" + correo);
        } else {
            System.out.println("\u001B[31m" + "ID no encontrado\n");
        }
    }

    private static void mostrarBorradores() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Borradores:");
        if (borradores.isEmpty()) {
            System.out.println("\u001B[31m" + "No hay borradores\n");
        } else {
            for (Correo borrador : borradores) {
                System.out.println(borrador);
            }
            System.out.println("--------Opciones--------");
            System.out.println("1. Enviar");
            System.out.println("2. Guardar");
            System.out.println("3. Eliminar");
            System.out.println("4. Editar");
            System.out.println("5. Salir sin guardar cambios");
            System.out.println("Ingrese su opcion: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcion){
                case 1:
                    System.out.println("Ingrese el ID del correo a enviar:");
                    int idEnviar = scanner.nextInt();
                    Correo correoEnviar = buscarCorreoBorrador(idEnviar);
                    if (correoEnviar != null) {
                        correoEnviar.setStatus("Enviado");
                        correos.add(correoEnviar);
                        borradores.remove(correoEnviar);
                        System.out.println("Correo enviado: \n" + correoEnviar);
                    } else {
                        System.out.println("\u001B[31m" + "ID de correo no encontrado\n");
                    }
                    break;
                case 2:
                    System.out.println("Ingrese el ID del correo a guardar: ");
                    int idGuardar = scanner.nextInt();
                    Correo correoGuardar = buscarCorreoBorrador(idGuardar);
                    if (correoGuardar != null) {
                        System.out.println("Correo guardado como borrador: \n" + correoGuardar);
                    } else {
                        System.out.println("\u001B[31m" + "ID de correo no encontrado\n");
                    }
                    break;
                case 3:
                    System.out.println("Ingrese el ID del correo a eliminar: ");
                    int idEliminar = scanner.nextInt();
                    Correo correoEliminar = buscarCorreoBorrador(idEliminar);
                    if (correoEliminar != null) {
                        correoEliminar.setStatus("Eliminado");
                        borradores.remove(correoEliminar);
                        System.out.println("Correo eliminado: \n" + correoEliminar);
                    } else {
                        System.out.println("\u001B[31m" + "ID de correo no encontrado\n");
                    }
                    break;
                case 4:
                    System.out.println("Ingrese el ID del borrador a editar: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    Correo borrador = buscarCorreoBorrador(id);
                    if (borrador != null) {
                        System.out.println("Borrador encontrado:\n" + borrador);
                        editarBorrador(borrador);
                    } else {
                        System.out.println("\u001B[31m" + "ID de borrador no encontrado\n"); 
                    }
                    break;
                case 5:
                    System.out.println("Redirigiendo al menú");
                    break;
                default:
                    System.out.println("\u001B[31m" + "Opción inválida. Redirigiendo al menu\n");
                    break;
            }
        }
    }

    private static void editarBorrador(Correo borrador) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Editar borrador");
        System.out.println("Status: Editando...");
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

        borrador.setRemitente(remitente);
        borrador.setDestinatario(destinatario);
        borrador.setAsunto(asunto);
        borrador.setCuerpo(cuerpo);

        System.out.println("--------Opciones--------");
        System.out.println("1. Enviar");
        System.out.println("2. Guardar");
        System.out.println("3. Eliminar");
        System.out.println("4. Salir sin guardar cambios");
        System.out.println("Ingrese su opcion: ");
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Consumir la nueva línea pendiente

        switch (opcion) {
            case 1:
                // Enviar el correo
                borrador.setStatus("Enviado");
                correos.add(borrador);
                borradores.remove(borrador);
                System.out.println("ID: " + borrador.getId());
                System.out.println("Status: Enviado\n");
                break;
            case 2:
                // Conservar como borrador
                borrador.setStatus("Borrador");
                System.out.println("ID: " + borrador.getId());
                System.out.println("Status: Guardado como borrador\n");
                break;
            case 3:
                // Eliminar el borrador
                borrador.setStatus("Eliminado");
                borradores.remove(borrador);
                System.out.println("Status: Eliminado\n");
                break;
            case 4:
                // Regresar al menú sin guardar cambios
                System.out.println("Redirigiendo al menu...\n");
                break;
            default:
                System.out.println("\u001B[31m" + "Opcion invalida. Redirigiendo al menu...\n");
                break;
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

    private static Correo buscarCorreoBorrador(int id) {
        for (Correo borrador : borradores) {
            if (borrador.getId() == id) {
                return borrador;
            }
        }
        return null;
    }
        
    
    //Comprobacion de cambios en Github
    //Primera version de cambios 
    
}
