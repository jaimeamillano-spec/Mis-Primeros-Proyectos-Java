package calculadoraaguinaldo;

import java.util.Scanner;

public class CalculadoraVacaciones {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

     System.out.println("--- Calculadora de Vacaciones y Prima Vacacional (Bucle Activo) ---");

        double sueldoMensual = 0;
        int añosAntigüedad = 0;
        
        // INTERRUPTORES DE CONTROL
        boolean sueldoValido = false;
        boolean añosValidos = false;

        // BUCLE 1: Validar el sueldo mensual
        while (!sueldoValido) {
            try {
                System.out.print("\nIngresa el sueldo mensual del trabajador ($): ");
                sueldoMensual = scanner.nextDouble();
                sueldoValido = true; // Si el dato es correcto, apagamos el bucle
            } catch (Exception e) {
                System.out.println("[ERROR]: ¡Entrada inválida! Por favor, ingresa solo números.");
                scanner.next(); // EL SECRETO: Limpiamos la memoria del teclado
            }
        }

        // BUCLE 2: Validar los años de antigüedad
        while (!añosValidos) {
            try {
                System.out.print("Ingresa los años completos trabajados: ");
                añosAntigüedad = scanner.nextInt();
                añosValidos = true; // Si es un número entero, apagamos el bucle
            } catch (Exception e) {
                System.out.println("[ERROR]: ¡Entrada inválida! Por favor, ingresa un número entero.");
                scanner.next(); // EL SECRETO: Limpiamos la memoria del teclado nuevamente
            }
        }

        // 2. Calculamos el salario por día
        double salarioDiario = sueldoMensual / 30.0;

        // 3. Lógica para determinar los días según la ley de Vacaciones Dignas
        int diasVacaciones = 0;

        if (añosAntigüedad == 1) {
            diasVacaciones = 12;
        } else if (añosAntigüedad == 2) {
            diasVacaciones = 14;
        } else if (añosAntigüedad == 3) {
            diasVacaciones = 16;
        } else if (añosAntigüedad == 4) {
            diasVacaciones = 18;
        } else if (añosAntigüedad == 5) {
            diasVacaciones = 20;
        } else if (añosAntigüedad >= 6 && añosAntigüedad <= 10) {
            diasVacaciones = 22;
        } else if (añosAntigüedad >= 11 && añosAntigüedad <= 15) {
            diasVacaciones = 24;
        } else if (añosAntigüedad > 15) {
            diasVacaciones = 26; 
        } else {
            diasVacaciones = 0; 
        }

        // 4. Operaciones matemáticas lógicas
        double pagoVacaciones = salarioDiario * diasVacaciones;
        double primaVacacional = pagoVacaciones * 0.25; 
        double totalRecibir = pagoVacaciones + primaVacacional;

        // 5. Mostramos los resultados en pantalla
        System.out.println("\n=========================================");
        System.out.println("            RESULTADOS                  ");
        System.out.println("=========================================");
        System.out.printf("Salario Diario: $%.2f%n", salarioDiario);
        System.out.println("Días de vacaciones correspondientes: " + diasVacaciones + " días");
        System.out.printf("Pago por los días de vacaciones: $%.2f%n", pagoVacaciones);
        System.out.printf("Prima Vacacional (25%%): $%.2f%n", primaVacacional);
        System.out.printf("Total neto a recibir en el periodo: $%.2f%n", totalRecibir);
        System.out.println("=========================================");

        scanner.close();
    }
}
