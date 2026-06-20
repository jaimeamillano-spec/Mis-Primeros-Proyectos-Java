import java.util.Scanner;
public class CalculadoraAguinaldo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Calculadora de Aguinaldo (Versión Blindada) ---");
        System.out.println("Cálculo configurado con: 30 días de aguinaldo.");

        double sueldoMensual = 0;
        
        // Aquí empieza el escudo protector (try-catch)
        try {
            System.out.print("Ingresa tu sueldo mensual ($): ");
            sueldoMensual = scanner.nextDouble();
            
            // Si el usuario escribe un número, el programa sigue aquí abajo normalmente:
            System.out.print("¿Trabajaste el año completo? (Escribe true o false): ");
            boolean añoCompleto = scanner.nextBoolean();

            double diasAguinaldo = 30.0; 
            double salarioDiario = sueldoMensual / 30.0;

            if (añoCompleto) {
                double aguinaldoTotal = salarioDiario * diasAguinaldo;
                System.out.printf("Tu aguinaldo total es: $%.2f%n", aguinaldoTotal);
            } else {
            int diasTrabajados = 0;
            boolean diasValidos = false;

            // BUCLE 3: Validar los días trabajados (¡Ya corregido!)
            while (!diasValidos) {
                try {
                    System.out.print("\nIngresa los días que realmente trabajaste: ");
                    diasTrabajados = scanner.nextInt();
                    diasValidos = true; // Si es un número entero, salimos del bucle
                } catch (Exception e) {
                    System.out.println("[ERROR]: ¡Ingresa solo números enteros!");
                    scanner.next(); // <-- ¡EL SECRETO REGRESÓ! Limpiamos la memoria para evitar el bucle infinito
                }
            }

            double aguinaldoAnualTeorico = salarioDiario * diasAguinaldo;
            double aguinaldoProporcional = aguinaldoAnualTeorico * (diasTrabajados / 365.0);
            
            System.out.printf("Tu aguinaldo proporcional es: $%.2f%n", aguinaldoProporcional);
        }

        } catch (Exception e) {
            // Si el usuario escribe letras, el programa salta directo a esta sección:
            System.out.println("\n[ERROR]: ¡Entrada inválida! Por favor, ingresa solo números y usa 'true' o 'false'.");
        } finally {
            scanner.close();
        }
    
    }
   
}
