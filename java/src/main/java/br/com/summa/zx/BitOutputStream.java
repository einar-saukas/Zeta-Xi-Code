/**
 * Zeta-Xi Code - by Einar Saukas
 *
 * See: https://github.com/einar-saukas/Zeta-Xi-Code
 */

package br.com.summa.zx;

public interface BitOutputStream {
    // writes next bit (either zero or not zero)
    void writeBit(int bit);
}
