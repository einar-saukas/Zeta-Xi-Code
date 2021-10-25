/**
 * Zeta-Xi Code - by Einar Saukas
 *
 * See: https://github.com/einar-saukas/Zeta-Xi-Code
 */

package br.com.summa.zx

import java.lang.StringBuilder

class StringBitOutputStream : BitOutputStream {
    private val output = StringBuilder()

    override fun writeBit(bit: Int) { output.append(if (bit > 0) '1' else '0') }
    fun result() = output.toString()
}