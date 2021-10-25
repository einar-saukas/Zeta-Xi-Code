/**
 * Zeta-Xi Code - by Einar Saukas
 *
 * See: https://github.com/einar-saukas/Zeta-Xi-Code
 */

package br.com.summa.zx;

public class StringBitInputStream implements BitInputStream {
    private final String input;
    private int index = 0;

    public StringBitInputStream(String input) {
        this.input = input;
    }

    @Override
    public int readBit() {
        return input.charAt(index++) != '0' ? 1 : 0;
    }
}
