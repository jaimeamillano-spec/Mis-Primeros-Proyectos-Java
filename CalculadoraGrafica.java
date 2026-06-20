package calculadoraaguinaldo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Heredamos de JFrame para convertir esta clase en una ventana
public class CalculadoraGrafica extends JFrame {

    // Componentes de la interfaz
    private JTextField txtSueldo;
    private JCheckBox chkAñoCompleto;
    private JTextField txtDias;
    private JLabel lblResultado;
    private JButton btnCalcular;

    public CalculadoraGrafica() {
        // Configuraciones básicas de la ventana
        setTitle("Calculadora de Aguinaldo Pro");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra la ventana en la pantalla
        setLayout(null); // Nos permite acomodar los componentes manualmente

        // 1. Etiqueta y Caja de texto para el Sueldo
        JLabel lblSueldo = new JLabel("Sueldo Mensual ($):");
        lblSueldo.setBounds(30, 30, 150, 30);
        add(lblSueldo);

        txtSueldo = new JTextField();
        txtSueldo.setBounds(180, 30, 150, 30);
        add(txtSueldo);

        // 2. Checkbox para el Año Completo
        chkAñoCompleto = new JCheckBox("¿Trabajó el año completo?", true);
        chkAñoCompleto.setBounds(30, 80, 250, 30);
        add(chkAñoCompleto);

        // 3. Etiqueta y Caja de texto para los Días (empiezan ocultas por defecto)
        JLabel lblDias = new JLabel("Días trabajados:");
        lblDias.setBounds(30, 130, 150, 30);
        lblDias.setVisible(false);
        add(lblDias);

        txtDias = new JTextField();
        txtDias.setBounds(180, 130, 150, 30);
        txtDias.setVisible(false);
        add(txtDias);

        // 4. Botón para Calcular
        btnCalcular = new JButton("Calcular Aguinaldo");
        btnCalcular.setBounds(30, 190, 300, 40);
        add(btnCalcular);

        // 5. Etiqueta para mostrar el Resultado
        lblResultado = new JLabel("Total a pagar: $0.00", SwingConstants.CENTER);
        lblResultado.setBounds(30, 250, 300, 30);
        lblResultado.setBorder(BorderFactory.createLineBorder(java.awt.Color.GRAY));
        add(lblResultado);

        // ACCIÓN DEL CHECKBOX: Si lo desmarcan, aparecen los campos de los días
        chkAñoCompleto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean mostrarDias = !chkAñoCompleto.isSelected();
                lblDias.setVisible(mostrarDias);
                txtDias.setVisible(mostrarDias);
            }
        });

        // ACCIÓN DEL BOTÓN: Aquí ocurre la magia del cálculo y el blindaje
        btnCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí usamos nuestro escudo protector TRY-CATCH
                try {
                    // Obtenemos el texto de la ventana y lo convertimos a número
                    double sueldoMensual = Double.parseDouble(txtSueldo.getText());
                    double diasAguinaldo = 30.0; // Conservamos tus 30 días configurados
                    double salarioDiario = sueldoMensual / 30.0;
                    double resultadoFinal = 0;

                    if (chkAñoCompleto.isSelected()) {
                        resultadoFinal = salarioDiario * diasAguinaldo;
                    } else {
                        int diasTrabajados = Integer.parseInt(txtDias.getText());
                        double aguinaldoAnualTeorico = salarioDiario * diasAguinaldo;
                        resultadoFinal = aguinaldoAnualTeorico * (diasTrabajados / 365.0);
                    }

                    // Mostramos el resultado en la ventana de forma bonita
                    lblResultado.setText(String.format("Total a pagar: $%.2f", resultadoFinal));

                } catch (NumberFormatException ex) {
                    // Si el usuario deja vacío o escribe letras, atrapamos el error aquí
                    JOptionPane.showMessageDialog(null, 
                            "Por favor, ingresa datos numéricos válidos.", 
                            "Error de Entrada", 
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    // El método main para iniciar la ventana
    public static void main(String[] args) {
        // Ejecutamos la interfaz de forma segura
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CalculadoraGrafica().setVisible(true);
            }
        });
    }
}