/**
 * Zeta-Xi Code - by Einar Saukas
 *
 * See: https://github.com/einar-saukas/Zeta-Xi-Code
 */

package br.com.summa.zx

class StringBitInputStream(private val input: String) : BitInputStream {
    private var index = 0

    override fun readBit() = if (input[index++] != '0') 1 else 0
}
