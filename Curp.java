import java.util.Scanner;

public class Curp {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int op;
        do {
            System.out.println("\nMenu CURP:\n 1)Generar CURP\n 2)Validar CURP\n 3)Salir");
            op = sc.nextInt();
            switch (op) {
                case 1:
                    generarCURP();
                    break;
                case 2:
                    validarCurp();
                    break;
                case 3:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opcion invalida");
                    break;
            }
        } while (op != 3);
    }
    static String obtenerPrimeraLetra(String palabra) {
        return palabra.toUpperCase().substring(0, 1);
    }
    static String obtenerPrimeraVocal(String palabra) {
        palabra = palabra.toUpperCase();
        for (int i = 1; i < palabra.length(); i++) {
            char c = palabra.charAt(i);
            if (c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U') {
                return c + "";
            }
        }
        return "X";
    }
    static String obtenerPrimeraConsonante(String palabra) {
        palabra = palabra.toUpperCase();
        for (int i = 1; i < palabra.length(); i++) {
            char c = palabra.charAt(i);
            if (c != 'A' && c != 'E' && c != 'I' && c != 'O' && c != 'U' && c >= 'A' && c <= 'Z') {
                return c + "";
            }
        }
        return "X";
    }
    static String[] obtenerFecha(String fecha) {
        String[] partes = fecha.split("/");
        String dia = partes[0];
        String mes = partes[1];
        String anio = partes[2].substring(2);
        return new String[]{anio, mes, dia};
    }
    static String obtenerClaveEstado(String entidad) {
        entidad = entidad.toUpperCase();
        if (entidad.contains("HIDALGO")) return "HG";
        if (entidad.contains("JALISCO")) return "JC";
        if (entidad.contains("MEXICO")) return "MX";
        if (entidad.contains("NUEVO LEON")) return "NL";
        if (entidad.contains("VERACRUZ")) return "VE";
        return "XX";
    }
    static void generarCURP() {
        sc.nextLine();
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Apellido paterno: ");
        String apPaterno = sc.nextLine();
        System.out.print("Apellido materno: ");
        String apMaterno = sc.nextLine();
        System.out.print("Fecha de nacimiento (dd/mm/aaaa): ");
        String fecha = sc.nextLine();
        System.out.print("Sexo (H/M): ");
        String sexo = sc.nextLine().toUpperCase();
        System.out.print("Entidad federativa de nacimiento: ");
        String entidad = sc.nextLine();

        String l1 = obtenerPrimeraLetra(apPaterno);
        String v1 = obtenerPrimeraVocal(apPaterno);
        String l2 = obtenerPrimeraLetra(apMaterno);
        String l3 = obtenerPrimeraLetra(nombre);

        String[] fechaPartes = obtenerFecha(fecha);
        String anio = fechaPartes[0];
        String mes = fechaPartes[1];
        String dia = fechaPartes[2];

        String clave = obtenerClaveEstado(entidad);

        String c1 = obtenerPrimeraConsonante(apPaterno);
        String c2 = obtenerPrimeraConsonante(apMaterno);
        String c3 = obtenerPrimeraConsonante(nombre);

        int r1 = (int) (Math.random() * 10);
        int r2 = (int) (Math.random() * 10);

        String curp = l1 + v1 + l2 + l3 + anio + mes + dia + sexo + clave + c1 + c2 + c3 + r1 + r2;
        System.out.println("\n    CURP GENERADA   ");
        System.out.println(curp);
        System.out.println("--------------------");
    }
    static void validarCurp() {
        System.out.print("Ingresa la CURP a validar: ");
        String curp = sc.next().toUpperCase();

        if (curp.length() != 18) {
            System.out.println("CURP invalida: debe tener 18 caracteres.");
            return;
        }
        String patron = "^[A-Z][AEIOUX][A-Z]{2}\\d{6}[HM][A-Z]{2}[B-DF-HJ-NP-TV-Z]{3}\\d{2}$";
        if (curp.matches(patron)) {
            System.out.println("CURP valida.");
            descomponerCurp(curp);
        } else {
            System.out.println("CURP invalida: formato incorrecto.");
        }
    }
    static void descomponerCurp(String curp) {
        System.out.println("\n--- DESCOMPOSICION ---");
        System.out.println("Iniciales: " + curp.substring(0, 4));
        System.out.println("Fecha: " + curp.substring(4, 10));
        System.out.println("Sexo: " + curp.substring(10, 11));
        System.out.println("Entidad: " + curp.substring(11, 13));
        System.out.println("Consonantes: " + curp.substring(13, 16));
        System.out.println("Homoclave: " + curp.substring(16));
        System.out.println("--------------------");
    }
}