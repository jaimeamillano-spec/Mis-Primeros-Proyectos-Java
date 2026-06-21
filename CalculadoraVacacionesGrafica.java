package calculadoraaguinaldo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculadoraVacacionesGrafica extends JFrame {

    // Componentes que se verán en la ventana
    private JTextField txtSueldo;
    private JTextField txtAños;
    private JLabel lblDiasVacaciones;
    private JLabel lblPagoVacaciones;
    private JLabel lblPrimaVacacional;
    private JLabel lblTotal;
    private JButton btnCalcular;

    public CalculadoraVacacionesGrafica() {
        // Configuraciones base de la ventana
        setTitle("Calculadora de Vacaciones Dignas");
        setSize(450, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra la ventana en tu pantalla
        setLayout(null); // Acomodo manual por coordenadas (x, y, ancho, alto)

        // 1. Etiqueta y Caja de texto para el Sueldo
        JLabel lblSueldo = new JLabel("Sueldo Mensual ($):");
        lblSueldo.setBounds(30, 30, 180, 30);
        add(lblSueldo);

        txtSueldo = new JTextField();
        txtSueldo.setBounds(220, 30, 160, 30);
        add(txtSueldo);

        // 2. Etiqueta y Caja de texto para los Años de Antigüedad
        JLabel lblAños = new JLabel("Años de Antigüedad:");
        lblAños.setBounds(30, 80, 180, 30);
        add(lblAños);

        txtAños = new JTextField();
        txtAños.setBounds(220, 80, 160, 30);
        add(txtAños);

        // 3. Botón para Calcular
        btnCalcular = new JButton("Calcular Prestaciones");
        btnCalcular.setBounds(30, 130, 350, 40);
        add(btnCalcular);

        // 4. Panel de Resultados (Etiquetas de texto)
        JLabel lblTituloRes = new JLabel("--- RESULTADOS ---", SwingConstants.CENTER);
        lblTituloRes.setBounds(30, 190, 350, 30);
        add(lblTituloRes);

        lblDiasVacaciones = new JLabel("Días correspondientes: 0 días");
        lblDiasVacaciones.setBounds(30, 230, 350, 25);
        add(lblDiasVacaciones);

        lblPagoVacaciones = new JLabel("Pago por Vacaciones: $0.00");
        lblPagoVacaciones.setBounds(30, 260, 350, 25);
        add(lblPagoVacaciones);

        lblPrimaVacacional = new JLabel("Prima Vacacional (25%): $0.00");
        lblPrimaVacacional.setBounds(30, 290, 350, 25);
        add(lblPrimaVacacional);

        lblTotal = new JLabel("TOTAL NETO A RECIBIR: $0.00");
        lblTotal.setBounds(30, 330, 350, 30);
        lblTotal.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14)); // Texto en negrita
        add(lblTotal);

        // ACCIÓN DEL BOTÓN: Aquí se conecta tu lógica matemática y el blindaje
        btnCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // 1. Extraemos y convertimos los datos de las cajas de texto
                    double sueldoMensual = Double.parseDouble(txtSueldo.getText());
                    int añosAntigüedad = Integer.parseInt(txtAños.getText());

                    // Validación lógica humana: No existen sueldos ni años negativos
                    if (sueldoMensual < 0 || añosAntigüedad < 0) {
                        throw new NumberFormatException(); // Fuerza el salto al catch de abajo
                    }

                    // 2. Tu algoritmo de Vacaciones Dignas
                    double salarioDiario = sueldoMensual / 30.0;
                    int diasVacaciones = 0;

                    if (añosAntigüedad == 1) { diasVacaciones = 12; }
                    else if (añosAntigüedad == 2) { diasVacaciones = 14; }
                    else if (añosAntigüedad == 3) { diasVacaciones = 16; }
                    else if (añosAntigüedad == 4) { diasVacaciones = 18; }
                    else if (añosAntigüedad == 5) { diasVacaciones = 20; }
                    else if (añosAntigüedad >= 6 && añosAntigüedad <= 10) { diasVacaciones = 22; }
                    else if (añosAntigüedad >= 11 && añosAntigüedad <= 15) { diasVacaciones = 24; }
                    else if (añosAntigüedad > 15) { diasVacaciones = 26; }
                    else { diasVacaciones = 0; }

                    // 3. Operaciones matemáticas
                    double pagoVacaciones = salarioDiario * diasVacaciones;
                    double primaVacacional = pagoVacaciones * 0.25;
                    double totalRecibir = pagoVacaciones + primaVacacional;

                    // 4. Modificamos los textos de la ventana con los resultados reales
                    lblDiasVacaciones.setText("Días correspondientes: " + diasVacaciones + " días");
                    lblPagoVacaciones.setText(String.format("Pago por Vacaciones: $%.2f", pagoVacaciones));
                    lblPrimaVacacional.setText(String.format("Prima Vacacional (25%%): $%.2f", primaVacacional));
                    lblTotal.setText(String.format("TOTAL NETO A RECIBIR: $%.2f", totalRecibir));

                } catch (NumberFormatException ex) {
                    // Si dejan vacíos los campos o escriben letras/números negativos
                    JOptionPane.showMessageDialog(null, 
                            "Por favor, ingresa sueldos y años válidos (solo números positivos).", 
                            "Error en los Datos", 
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    // Método principal para encender la ventana gráfica
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CalculadoraVacacionesGrafica().setVisible(true);
            }
        });
    }
}
