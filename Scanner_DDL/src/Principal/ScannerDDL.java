/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import java.awt.Font;
import java.util.HashMap;
import java.util.regex.*;
import javax.swing.table.*;

public final class ScannerDDL extends javax.swing.JFrame {

    DefaultTableModel tablaLexica = new DefaultTableModel(), tablaConstantes = new DefaultTableModel(), tablaIdentificadores = new DefaultTableModel();

    HashMap<Integer, String[][]> lexer = new HashMap<>();
    HashMap<String, String> tablaSintactica = new HashMap<>();
    HashMap<String, String> primeros = new HashMap<>();
    int valorCons = 600, valorIden = 401;
    
    public ScannerDDL() {
        initComponents();
        
        this.setLocationRelativeTo(null);

        tablaLexica = (DefaultTableModel) jTablaLexica.getModel();
        tablaConstantes = (DefaultTableModel) jTablaConstatntes.getModel();
        tablaIdentificadores = (DefaultTableModel) jTablaIdentificadores.getModel();
        
        JTableHeader th = jTablaLexica.getTableHeader();
        th.setFont(new Font("Arial", Font.PLAIN, 17));
        
        JTableHeader headerLex = jTablaLexica.getTableHeader();
        headerLex.setFont(new Font("Arial", Font.PLAIN, 17));
        
        JTableHeader headerConstantes = jTablaConstatntes.getTableHeader();
        headerConstantes.setFont(new Font("Arial", Font.PLAIN, 17));
        
        JTableHeader headerIdentificadores = jTablaIdentificadores.getTableHeader();
        headerIdentificadores.setFont(new Font("Arial", Font.PLAIN, 17));

        cargarDatos();
    }
    
    public void cargarDatos() {
        String[][] reservadas = {{"Palabra Reservada", "1", ""},
        {"SELECT", "10", "s"},
        {"FROM", "11", "f"},
        {"WHERE", "12", "w"},
        {"IN", "13", "n"},
        {"AND", "14", "y"},
        {"OR", "15", "o"},
        {"CREATE", "16", "c"},
        {"TABLE", "17", "t"},
        {"CHAR", "18", "h"},
        {"NUMERIC", "19", "u"},
        {"NOT", "20", "e"},
        {"NULL", "21", "g"},
        {"CONSTRAINT", "22", "b"},
        {"KEY", "23", "k"},
        {"PRIMARY", "24", "p"},
        {"FOREIGN", "25", "j"},
        {"REFERENCES", "26", "l"},
        {"INSERT", "27", "m"},
        {"INTO", "28", "q"},
        {"VALUES", "29", "v"}};

        String[][] delimitadores = {{"Delimitador", "5"},
        {",", "50"},
        {"\\.", "51"},
        {"\\(", "52"},
        {"\\)", "53"},
        {"'", "54"},
        {";", "55"}};

        String[][] operadores = {{"Operador", "7"},
        {"\\+", "70"},
        {"-", "71"},
        {"\\*", "72"},
        {"/", "73"}};

        String[][] constantes = {{"Constante", "6"},
        {"\\d+", "61"},
        {"^'[\\w ]+'?$", "62"}};

        String[][] relacionales = {{"Operador Relacional", "8"},
        {">", "81"},
        {"<", "82"},
        {"=", "83"},
        {">=", "84"},
        {"<=", "85"}};

        String[][] identificadores = {{"Identificador", "4"},
        {"^\\w+#?$", "400"}};

        lexer.put(0, reservadas);
        lexer.put(1, delimitadores);
        lexer.put(2, operadores);
        lexer.put(3, constantes);
        lexer.put(4, relacionales);
        lexer.put(5, identificadores);
    }
    
    public void primerosySiguientes() {//Datos de la tabla primeros y siguientes del DML
        primeros.put("300", "10");
        primeros.put("301", "4 72");
        primeros.put("302", "4");
        primeros.put("303", "50 99");
        primeros.put("304", "4");
        primeros.put("305", "51 99");//Era 51 99
        primeros.put("306", "4");
        primeros.put("307", "50 99");
        primeros.put("308", "4");
        primeros.put("309", "4 99");
        primeros.put("310", "12 99");
        primeros.put("311", "4");
        primeros.put("312", "14 15 99");
        primeros.put("313", "4");
        primeros.put("314", "8 13");
        primeros.put("315", "8");
        primeros.put("316", "4 54 61");
        primeros.put("317", "14 15");
        primeros.put("318", "62");
        primeros.put("319", "61");
    }
    
    public void primerosySiguientesDDL(){//Datos de la tabla primeros y siguientes del DDL
        primeros.put("200", "16");
        primeros.put("201", "16 27 99");
        primeros.put("202", "4");
        primeros.put("203", "18 19");
        primeros.put("204", "20 99");
        primeros.put("205", "50 99");
        primeros.put("206", "4 22");
        primeros.put("207", "22");
        primeros.put("208", "24 25");
        primeros.put("209", "50 26 99");
        primeros.put("210", "50 99");
        primeros.put("211", "27");
        primeros.put("212", "61");//Era 54 61
        primeros.put("213", "54 61");
        primeros.put("214", "50 99");
        primeros.put("215", "27 16 99");
    }

    public void datosTablaSintactica() {//Datos de la tabla sintáctica del DML
        tablaSintactica.put("300 10", "10 301 11 306 310");
        tablaSintactica.put("301 4", "302");
        tablaSintactica.put("301 72", "72");
        tablaSintactica.put("302 4", "304 303");
        tablaSintactica.put("303 11", "99");
        tablaSintactica.put("303 50", "50 302");
        tablaSintactica.put("303 199", "99");
        tablaSintactica.put("304 4", "4 305");
        tablaSintactica.put("305 8", "99");
        tablaSintactica.put("305 11", "99");
        tablaSintactica.put("305 13", "99");
        tablaSintactica.put("305 14", "99");
        tablaSintactica.put("305 15", "99");
        tablaSintactica.put("305 50", "99");
        tablaSintactica.put("305 51", "51 4");
        tablaSintactica.put("305 53", "99");
        tablaSintactica.put("305 199", "99");
        tablaSintactica.put("306 4", "308 307");
        tablaSintactica.put("307 12", "99");
        tablaSintactica.put("307 50", "50 306");
        tablaSintactica.put("307 53", "99");
        tablaSintactica.put("307 199", "99");
        tablaSintactica.put("308 4", "4 309");
        tablaSintactica.put("309 4", "4");
        tablaSintactica.put("309 12", "99");
        tablaSintactica.put("309 50", "99");
        tablaSintactica.put("309 53", "99");
        tablaSintactica.put("309 199", "99");
        tablaSintactica.put("310 12", "12 311");
        tablaSintactica.put("310 53", "99");
        tablaSintactica.put("310 199", "99");
        tablaSintactica.put("311 4", "313 312");
        tablaSintactica.put("312 14", "317 311");
        tablaSintactica.put("312 15", "317 311");
        tablaSintactica.put("312 53", "99");
        tablaSintactica.put("312 199", "99");
        tablaSintactica.put("313 4", "304 314");
        tablaSintactica.put("314 8", "315 316");
        tablaSintactica.put("314 13", "13 52 300 53");
        tablaSintactica.put("315 8", "8");
        tablaSintactica.put("316 4", "304");
        tablaSintactica.put("316 54", "54 318 54");
        tablaSintactica.put("316 61", "319");
        tablaSintactica.put("317 14", "14");
        tablaSintactica.put("317 15", "15");
        tablaSintactica.put("318 62", "62");
        tablaSintactica.put("319 61", "61");
    }
    
    public void datosTablaSintacticaDDL(){//Tabla sintáctica del DDL
        tablaSintactica.put("200 16", "16 17 4 52 202 53 55 201");
        tablaSintactica.put("201 16", "200");
        tablaSintactica.put("201 27", "211");
        tablaSintactica.put("201 199", "99");
        tablaSintactica.put("202 4", "4 203 52 61 53 204 205");
        tablaSintactica.put("203 18", "18");
        tablaSintactica.put("203 19", "19");
        tablaSintactica.put("204 20", "20 21");
        tablaSintactica.put("204 50", "99");
        tablaSintactica.put("205 50", "50 206");
        tablaSintactica.put("205 53", "99");
        tablaSintactica.put("206 4", "202");
        tablaSintactica.put("206 22", "207");
        tablaSintactica.put("207 22", "22 4 208 52 4 53 209");
        tablaSintactica.put("208 24", "24 23");
        tablaSintactica.put("208 25", "25 23");
        tablaSintactica.put("209 26", "26 4 52 4 53 210");
        tablaSintactica.put("209 50", "50 207");
        tablaSintactica.put("209 53", "99");
        tablaSintactica.put("210 50", "50 207");
        tablaSintactica.put("210 53", "99");
        tablaSintactica.put("211 27", "27 28 4 29 52 212 53 55 215");
        tablaSintactica.put("212 54", "213 214");
        tablaSintactica.put("212 61", "213 214");
        tablaSintactica.put("213 54", "54 62 54");
        tablaSintactica.put("213 61", "61");
        tablaSintactica.put("214 50", "50 212");
        tablaSintactica.put("214 53", "99");
        tablaSintactica.put("215 16", "200");
        tablaSintactica.put("215 27", "211");
        tablaSintactica.put("215 199", "99");
    }

    public void validarSintaxis() {
        HashMap<Integer, String> pila = new HashMap<>();
        String x, k = "";
        int apuntador = 0, contadorPila = 2;

        pila.put(0, "199");
        
        if(jTablaLexica.getValueAt(apuntador, 2).toString().equals("SELECT")){//Se pregunta si empieza con el SELECT para usar la tabla sintáctica y de primeros y siguientes del DML
            pila.put(1, "300");
            datosTablaSintactica();
            primerosySiguientes();
        }else{//En caso contrario se usara la tabla sintáctica y de primeros y siguientes del DDL
            if(jTablaLexica.getValueAt(apuntador, 2).toString().equals("INSERT"))//Si se empiez con INSERT se usara primero la regla 201
                pila.put(1, "201");
            else //Si se empieza con CREATE se usara la regla 200
                pila.put(1, "200");
            datosTablaSintacticaDDL();
            primerosySiguientesDDL();
        }
        
        tablaLexica.addRow(new Object[]{"", "", "$", "9", "199"});
        do {
            x = pila.get(pila.size() - 1);
            pila.remove(pila.size() - 1);
            contadorPila--;
            //Se almacena en k el valor de las palabras pertenecientes a la sentencia SQL
            if (Integer.parseInt(jTablaLexica.getValueAt(apuntador, 4).toString()) > 80 && Integer.parseInt(jTablaLexica.getValueAt(apuntador, 4).toString()) < 86) {
                k = "8";
            } else if (Integer.parseInt(jTablaLexica.getValueAt(apuntador, 4).toString()) > 400 && Integer.parseInt(jTablaLexica.getValueAt(apuntador, 4).toString()) < 600) {
                k = "4";
            } else if (Integer.parseInt(jTablaLexica.getValueAt(apuntador, 3).toString()) == 6) {
                for (int i = 0; i < jTablaConstatntes.getRowCount(); i++) {
                    if (Integer.parseInt(jTablaLexica.getValueAt(apuntador, 4).toString()) == Integer.parseInt(jTablaConstatntes.getValueAt(i, 3).toString())) {//Si se encuentra se retorna el valor que tiene
                        k = jTablaConstatntes.getValueAt(i, 2).toString();
                        break;
                    }
                }
            } else {
                k = jTablaLexica.getValueAt(apuntador, 4).toString();
            }
            if (Integer.parseInt(x) < 100 || x.equals("199")) {//Si el valor que esta en x es terminal o igual a $
                if (x.equals(k)) {//Si x y k son iguales
                    apuntador++;
                } else {//En caso de no ser iguales muestra el error
                    jTablaErrores.setText(error(jTablaLexica.getValueAt(apuntador - 1, 1).toString(),x));
                    break;
                }
            } else {
                if (tablaSintactica.containsKey(x + " " + k)) {//Se pregunta si existe la intersección de x y k en la tabla sintáctica
                    if (!tablaSintactica.get(x + " " + k).equals("99")) {//En caso de existir intersección se pregunta si es diferente de cadena vacia
                        String produccion[] = tablaSintactica.get(x + " " + k).split(" ");
                        for (int i = produccion.length - 1; i >= 0; i--) {//En caso de ser diferente de cadena vacia se guarda el producto en la pila de forma inversa
                            pila.put(contadorPila++, produccion[i]);
                        }
                    }
                } else {//En caso de no existir la interacción muestra el error
                    jTablaErrores.setText(error(jTablaLexica.getValueAt(apuntador - 1, 1).toString(),x));
                    break;
                }
            }
        } while (!x.equals("199"));
        tablaLexica.removeRow(tablaLexica.getRowCount() - 1);
    }

    public String error(String linea,String x) {
        String tipo;
        if (Integer.parseInt(x) > 100) {//Si x es una regla se extraen los valores primeros de la tabla primeros y siguientes          
            tipo = tipoDeError(primeros.get(x));
        } else {
            tipo = tipoDeError(x);
        }
        //Se clasifica el error que se mostrará deacuerdo al tipo
        switch (tipo) {
            case "Se esperaba Palabra Reservada":
                tipo = "Error| 2:201 Línea " + linea + " " + tipo;
                break;
            case "Se esperaba Identificador":
                tipo = "Error| 2:204 Línea " + linea + " " + tipo;
                break;
            case "Se esperaba Delimitador":
                tipo = "Error| 2:205 Línea " + linea + " " + tipo;
                break;
            case "Se esperaba Constante":
                tipo = "Error| 2:206 Línea " + linea + " " + tipo;
                break;
            case "Se esperaba Operador":
                tipo = "Error| 2:207 Línea " + linea + " " + tipo;
                break;
            case "Se esperaba Operador Relacional":
                tipo = "Error| 2:208 Línea " + linea + " " + tipo;
                break;
            default:
                break;
        }
        
        
        return tipo;
    }

    public String tipoDeError(String palabra) {//Se busca que tipo de error fue el que se encontró
        String primeros[] = palabra.split(" ");
        for (String primero1 : primeros) {
            for (int j = 0; j < lexer.size(); j++) {
                for (String[] get : lexer.get(j)) {
                    if (get[1].equals(primero1)) {
                        return "Se esperaba " + lexer.get(j)[0][0];
                    }
                }
            }
        }
        return null;
    }

    public void tablaLexica() {
        Pattern patron = Pattern.compile("'[\\w ]+'?|\\w+#?|,|\\.|\\(|\\)|[+]|-|[*]|/|\\d+|=|[>][=]?|[<][=]?|\\W|\\n");
        Matcher matcher = patron.matcher(txtACode.getText());
        Pattern clasificacion;
        Matcher matcher2;
        int contador = 1, linea = 1;
        boolean ban = false, error = false;

        while (matcher.find()) {
            if (matcher.group().matches("\\n")) {
                linea++;
            } else if (!matcher.group().matches("'[\\w+ ]+'?|\\w+#?|,|\\.|\\(|\\)|[+]|-|[*]|/|\\d+|=|[>][=]?|[<][=]?| |;|\\n")) {
                error = true;
                break;
            } else {
                for (int i = 0; i < lexer.size(); i++) {
                    for (int y = 1; y < lexer.get(i).length; y++) {
                        clasificacion = Pattern.compile(lexer.get(i)[y][0]);
                        matcher2 = clasificacion.matcher(matcher.group().replace(" ", ""));
                        if (matcher2.matches()) {
                            if (lexer.get(i)[0][0].equals("Constante")) {
                                if (matcher.group().matches("^\\d+$")) {
                                    tablaLexica.addRow(new Object[]{contador++, linea, matcher.group(), lexer.get(i)[0][1], tablaConstante(contador - 1, matcher.group().toString().replace("'", ""), Integer.parseInt(lexer.get(i)[y][1]))});
                                } else {
                                    tablaLexica.addRow(new Object[]{contador++, linea, "'", 5, 54});
                                    tablaLexica.addRow(new Object[]{contador++, linea, "CONSTANTE", lexer.get(i)[0][1], tablaConstante(contador - 1, matcher.group().toString().replace("'", ""), Integer.parseInt(lexer.get(i)[y][1]))});
                                    if ("'".equals(String.valueOf(matcher.group().charAt(matcher.group().length() - 1)))) {
                                        tablaLexica.addRow(new Object[]{contador++, linea, "'", 5, 54});
                                    }
                                }

                            } else if (lexer.get(i)[0][0].equals("Identificador")) {
                                tablaLexica.addRow(new Object[]{contador++, linea, matcher.group(), lexer.get(i)[0][1], tablaIdentificador(matcher.group().toString(), linea)});
                            } else {
                                tablaLexica.addRow(new Object[]{contador++, linea, matcher.group(), lexer.get(i)[0][1], lexer.get(i)[y][1]});
                            }
                            ban = true;
                            break;
                        }
                    }
                    if (ban) {
                        ban = false;
                        break;
                    }

                }
            }

        }

        if (error) {
            jTablaErrores.setText("Error| 1:101 Línea " + linea + " simbolo " + matcher.group() + " desconocido.");
            reiniciar();
        } else {
            jTablaErrores.setText("Error| 1:100 Sin error.\n\nError| 2:200 Sin error.");
            validarSintaxis();//Se llama a la función que que verifica la sintaxis
        }

    }

    public int tablaConstante(int num, String palabra, int tipo) {
        for (int i = 0; i < jTablaConstatntes.getRowCount(); i++) {
            if (palabra.equals(jTablaConstatntes.getValueAt(i, 1).toString())) {
                return Integer.parseInt(jTablaConstatntes.getValueAt(i, 3).toString());
            }
        }
        tablaConstantes.addRow(new Object[]{num, palabra, tipo, valorCons++});
        return valorCons - 1;
    }

    public int tablaIdentificador(String palabra, int linea) {
        String[] parts;
        for (int i = 0; i < jTablaIdentificadores.getRowCount(); i++) {
            if (palabra.equals(jTablaIdentificadores.getValueAt(i, 0).toString())) {
                parts = jTablaIdentificadores.getValueAt(i, 2).toString().split(", ");
                if (Integer.parseInt(parts[parts.length - 1]) != linea) {
                    jTablaIdentificadores.setValueAt(jTablaIdentificadores.getValueAt(i, 2).toString() + ", " + linea, i, 2);
                }
                return Integer.parseInt(jTablaIdentificadores.getValueAt(i, 1).toString());
            }
        }

        tablaIdentificadores.addRow(new Object[]{palabra, valorIden++, linea});
        return valorIden - 1;
    }
    
    public void reiniciar(){
        tablaLexica.setRowCount(0);
        tablaConstantes.setRowCount(0);
        tablaIdentificadores.setRowCount(0);
        valorCons = 600;
        valorIden = 401;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jspTextEntrada = new javax.swing.JScrollPane();
        txtACode = new javax.swing.JTextArea();
        lbl1 = new javax.swing.JLabel();
        lbl2 = new javax.swing.JLabel();
        btnAnalizar = new javax.swing.JButton();
        btnReiniciar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTablaIdentificadores = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTablaLexica = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTablaConstatntes = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTablaErrores = new javax.swing.JTextArea();
        jlbErrores = new javax.swing.JLabel();
        lblTablaLexica = new javax.swing.JLabel();
        lblTablaIdents = new javax.swing.JLabel();
        lblTablaConsts = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtACode.setBackground(new java.awt.Color(255, 255, 204));
        txtACode.setColumns(20);
        txtACode.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        txtACode.setRows(5);
        jspTextEntrada.setViewportView(txtACode);

        lbl1.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        lbl1.setText("Escriba el código en el recuadro presentado a continuación.");
        lbl1.setToolTipText("");
        lbl1.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);

        lbl2.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        lbl2.setText("Una vez ingresada su consulta, presione el botón \"Realizar análisis\" para que pueda procesar su solicitud.");
        lbl2.setToolTipText("");
        lbl2.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);

        btnAnalizar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnAnalizar.setText("Realizar Analisis");
        btnAnalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnalizarActionPerformed(evt);
            }
        });

        btnReiniciar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnReiniciar.setText("Nueva consulta");
        btnReiniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReiniciarActionPerformed(evt);
            }
        });

        btnSalir.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        jTablaIdentificadores.setBackground(new java.awt.Color(255, 255, 204));
        jTablaIdentificadores.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        jTablaIdentificadores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IDENTIFICADOR", "VALOR", "LÍNEA"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTablaIdentificadores.getTableHeader().setResizingAllowed(false);
        jTablaIdentificadores.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(jTablaIdentificadores);

        jTablaLexica.setBackground(new java.awt.Color(255, 255, 204));
        jTablaLexica.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        jTablaLexica.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NO.", "LÍNEA", "TOKEN", "TIPO", "CÓDIGO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTablaLexica.getTableHeader().setResizingAllowed(false);
        jTablaLexica.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jTablaLexica);
        if (jTablaLexica.getColumnModel().getColumnCount() > 0) {
            jTablaLexica.getColumnModel().getColumn(0).setPreferredWidth(50);
            jTablaLexica.getColumnModel().getColumn(0).setMaxWidth(50);
            jTablaLexica.getColumnModel().getColumn(1).setPreferredWidth(60);
            jTablaLexica.getColumnModel().getColumn(1).setMaxWidth(60);
            jTablaLexica.getColumnModel().getColumn(3).setPreferredWidth(60);
            jTablaLexica.getColumnModel().getColumn(3).setMaxWidth(60);
            jTablaLexica.getColumnModel().getColumn(4).setPreferredWidth(80);
            jTablaLexica.getColumnModel().getColumn(4).setMaxWidth(80);
        }

        jTablaConstatntes.setBackground(new java.awt.Color(255, 255, 204));
        jTablaConstatntes.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        jTablaConstatntes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NO.", "CONSTANTE", "TIPO", "VALOR"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTablaConstatntes.getTableHeader().setResizingAllowed(false);
        jTablaConstatntes.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(jTablaConstatntes);

        jTablaErrores.setEditable(false);
        jTablaErrores.setColumns(20);
        jTablaErrores.setRows(5);
        jScrollPane5.setViewportView(jTablaErrores);

        jlbErrores.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        jlbErrores.setText("Modulo de errores");

        lblTablaLexica.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        lblTablaLexica.setText("Tabla lexica");

        lblTablaIdents.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        lblTablaIdents.setText("Tabla de Identificadores");

        lblTablaConsts.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        lblTablaConsts.setText("Tabla de Constantes");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl2)
                            .addComponent(lbl1))
                        .addGap(18, 18, 18)
                        .addComponent(btnAnalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                        .addComponent(btnReiniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(jlbErrores, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 903, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jspTextEntrada)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(163, 163, 163)
                .addComponent(lblTablaLexica)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTablaConsts)
                .addGap(260, 260, 260)
                .addComponent(lblTablaIdents)
                .addGap(112, 112, 112))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(lbl1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(31, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAnalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnReiniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addComponent(jspTextEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTablaConsts)
                    .addComponent(lblTablaIdents)
                    .addComponent(lblTablaLexica))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jlbErrores, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAnalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnalizarActionPerformed
        if(!txtACode.getText().equals("")){
            reiniciar();
            tablaLexica();
        }
            
    }//GEN-LAST:event_btnAnalizarActionPerformed

    private void btnReiniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReiniciarActionPerformed
        reiniciar();
        txtACode.setText("");
        jTablaErrores.setText("");
    }//GEN-LAST:event_btnReiniciarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ScannerDDL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ScannerDDL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ScannerDDL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ScannerDDL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ScannerDDL().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnalizar;
    private javax.swing.JButton btnReiniciar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTablaConstatntes;
    private javax.swing.JTextArea jTablaErrores;
    private javax.swing.JTable jTablaIdentificadores;
    private javax.swing.JTable jTablaLexica;
    private javax.swing.JLabel jlbErrores;
    private javax.swing.JScrollPane jspTextEntrada;
    private javax.swing.JLabel lbl1;
    private javax.swing.JLabel lbl2;
    private javax.swing.JLabel lblTablaConsts;
    private javax.swing.JLabel lblTablaIdents;
    private javax.swing.JLabel lblTablaLexica;
    private javax.swing.JTextArea txtACode;
    // End of variables declaration//GEN-END:variables
}
