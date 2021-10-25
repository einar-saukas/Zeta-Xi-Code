/**
 * Zeta-Xi Code - by Einar Saukas
 *
 * See: https://github.com/einar-saukas/Zeta-Xi-Code
 */

package br.com.summa.zx;

public class StringBitOutputStream implements BitOutputStream {
    private final StringBuilder output = new StringBuilder();

    @Override
    public void writeBit(int bit) {
        output.append(bit > 0 ? '1' : '0');
    }

    public String result() {
        return output.toString();
    }
}
