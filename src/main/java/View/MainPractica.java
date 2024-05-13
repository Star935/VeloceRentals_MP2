package View;

import Mapping.Dtos.UsuarioDto;
import Services.Impl.UsuarioServiceImpl;
import Services.Impl.VehiculoServiceImpl;

import java.util.List;
import java.util.Scanner;

public class MainPractica {
    public static void main(String[] args) throws Exception {
        String op;
        UsuarioServiceImpl uS= new UsuarioServiceImpl();
        VehiculoServiceImpl vS= new VehiculoServiceImpl();
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("----MENU----");
            System.out.println("1. Consultar usuario por ID");
            System.out.println("2. Consultar vehículo por ID");
            System.out.println("3. Consultar reserva por ID");
            System.out.println("4. Salir");
            System.out.print("Ingrese su opción: ");
            op = scanner.next();

            switch (op) {
                case "1" -> {
                    System.out.println("----CONSULTAR USUARIO POR ID----");
                    System.out.print("Ingrese el ID del usuario: ");
                    List<UsuarioDto> ul = uS.list();
                    if (ul.isEmpty()){
                        for (UsuarioDto ud : ul){
                            System.out.println(ud);
                        }
                    }else {
                        System.out.println("No hay usuarios registrados");
                    }

                }
                case "2" -> {
                    System.out.println("----CONSULTAR VEHÍCULO POR ID----");
                    System.out.print("Ingrese el ID del vehículo: ");
                    int idVehiculo = scanner.nextInt();

                }
                case "3" -> {
                    System.out.println("----CONSULTAR RESERVA POR ID----");
                    System.out.print("Ingrese el ID de la reserva: ");
                    int idReserva = scanner.nextInt();

                }
                case "4" -> {
                    System.out.println("Saliendo...");
                }
            }
        } while (!op.equals("4")) ;
    }
}
