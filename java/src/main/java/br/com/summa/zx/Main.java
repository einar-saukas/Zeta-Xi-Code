/**
 * Zeta-Xi Code - by Einar Saukas
 *
 * See: https://github.com/einar-saukas/Zeta-Xi-Code
 */

package br.com.summa.zx;

public class Main {

    private static int toInt(String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static char toChar(String s) {
        return s.length() == 1 ? s.charAt(0) : '?';
    }

    public static void main(String[] args) {
        if (args.length != 5) {
            System.err.println("Zeta-Xi Coder/Decoder by Einar Saukas\n" +
                    "Usage: java -jar zeta-xi.jar <factor> <variant> <order> <command> <value>\n" +
                    "  <factor>      factor (>0)\n" +
                    "  <variant>     Either 'c' (classic) or 'i' (interlaced)\n" +
                    "  <order>       Order (>=0)\n" +
                    "  <command>     Either 'e' (encode) or 'd' (decode)\n" +
                    "  <value>       Value in decimal (to encode) or binary (to decode)\n" +
                    "Example: java -jar zeta-xi.jar 1 c 0 e 42");
            System.exit(1);
        }

        int factor = toInt(args[0]);
        char variant = toChar(args[1]);
        int order = toInt(args[2]);
        char command = toChar(args[3]);
        String input = args[4];

        if (factor < 1 || variant != 'c' && variant != 'i' || order < 0 || command != 'e' && command != 'd') {
            System.err.println("Error: Invalid parameters");
            System.exit(1);
        }

        if (command == 'e') {
            StringBitOutputStream b = new StringBitOutputStream();
            int value = toInt(input);
            if (value < 0) {
                System.err.println("Error: Invalid value");
                System.exit(1);
            }
            if (variant == 'c') {
                ZetaXi.encodeZetaXiClassic(value, factor, order, b);
            } else {
                ZetaXi.encodeZetaXiInterlaced(value, factor, order, b);
            }
            System.out.println(b.result());
        } else {
            StringBitInputStream b = new StringBitInputStream(input);
            int result = 0;
            try {
                if (variant == 'c') {
                    result = ZetaXi.decodeZetaXiClassic(factor, order, b);
                } else {
                    result = ZetaXi.decodeZetaXiInterlaced(factor, order, b);
                }
            } catch (IndexOutOfBoundsException e) {
                System.err.println("Error: Invalid value");
                System.exit(1);
            }
            System.out.println(result);
        }
        System.exit(0);
    }
}
