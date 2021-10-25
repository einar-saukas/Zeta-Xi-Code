/**
 * Zeta-Xi Code - by Einar Saukas
 *
 * See: https://github.com/einar-saukas/Zeta-Xi-Code
 */

package br.com.summa.zx

interface BitInputStream {
    // returns next bit (either 0 or 1)
    fun readBit(): Int
}