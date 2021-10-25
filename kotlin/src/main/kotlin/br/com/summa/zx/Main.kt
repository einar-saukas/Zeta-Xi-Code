/**
 * Zeta-Xi Code - by Einar Saukas
 *
 * See: https://github.com/einar-saukas/Zeta-Xi-Code
 */

package br.com.summa.zx

import kotlin.system.exitProcess

private fun parseInt(s: String) = try { s.toInt() } catch (e: NumberFormatException) { -1 }

private fun toChar(s: String) = if (s.length == 1) s[0] else '?'

fun main(args: Array<String>) {
    if (args.size != 5) {
        System.err.println("""Zeta-Xi Coder/Decoder by Einar Saukas
Usage: java -jar zeta-xi.jar <factor> <variant> <order> <command> <value>
  <factor>      factor (>0)
  <variant>     Either 'c' (classic) or 'i' (interlaced)
  <order>       Order (>=0)
  <command>     Either 'e' (encode) or 'd' (decode)
  <value>       Value in decimal (to encode) or binary (to decode)
Example: java -jar zeta-xi.jar 1 c 0 e 42""")
        exitProcess(1)
    }

    val factor = parseInt(args[0])
    val variant = toChar(args[1])
    val order = parseInt(args[2])
    val command = toChar(args[3])
    val input = args[4]

    if (factor < 1 || variant != 'c' && variant != 'i' || order < 0 || command != 'e' && command != 'd') {
        System.err.println("Error: Invalid parameters")
        exitProcess(1)
    }

    if (command == 'e') {
        val b = StringBitOutputStream()
        val value = parseInt(input)
        if (value < 0) {
            System.err.println("Error: Invalid value")
            exitProcess(1)
        }
        if (variant == 'c') {
            encodeZetaXiClassic(value, factor, order, b)
        } else {
            encodeZetaXiInterlaced(value, factor, order, b)
        }
        println(b.result())
    } else {
        val b = StringBitInputStream(input)
        val result = try {
            if (variant == 'c') {
                decodeZetaXiClassic(factor, order, b)
            } else {
                decodeZetaXiInterlaced(factor, order, b)
            }
        } catch (e: IndexOutOfBoundsException) {
            System.err.println("Error: Invalid value")
            exitProcess(1)
        }
        println(result)
    }
    exitProcess(1)
}
