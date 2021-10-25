/**
 * Zeta-Xi Code - by Einar Saukas
 *
 * See: https://github.com/einar-saukas/Zeta-Xi-Code
 */

package br.com.summa.zx

fun decodeZetaXiInterlaced(factor: Int, order: Int, b: BitInputStream): Int {
    var n = 0
    while (b.readBit() == 0) {
        for (i in 1..factor)
            n = n shl 1 or b.readBit()
        n++
    }
    for (i in 1..order)
        n = n shl 1 or b.readBit()
    return n
}

fun decodeZetaXiClassic(factor: Int, order: Int, b: BitInputStream): Int {
    var count = 0
    while (b.readBit() == 0)
        count++
    var n = 0
    for (j in 1..count) {
        for (i in 1..factor)
            n = n shl 1 or b.readBit()
        n++
    }
    for (i in 1..order)
        n = n shl 1 or b.readBit()
    return n
}

fun encodeZetaXiInterlaced(value: Int, factor: Int, order: Int, b: BitOutputStream) {
    var msb = value shr order
    val lsb = value - (msb shl order)
    var n = 1
    while (msb >= n) {
        msb -= n
        n = n shl factor
    }
    while (n > 1) {
        b.writeBit(0)
        for (i in 1..factor) {
            n = n shr 1
            b.writeBit(msb and n)
        }
    }
    b.writeBit(1)
    n = 1 shl order
    n = n shr 1
    while (n > 0) {
        b.writeBit(lsb and n)
        n = n shr 1
    }
}

fun encodeZetaXiClassic(value: Int, factor: Int, order: Int, b: BitOutputStream) {
    var msb = value shr order
    val lsb = value - (msb shl order)
    var n = 1
    var count = 0
    while (msb >= n) {
        msb -= n
        n = n shl factor
        count++
    }
    for (i in 1..count)
        b.writeBit(0)
    b.writeBit(1)
    while (n > 1)
        for (i in 1..factor) {
            n = n shr 1
            b.writeBit(msb and n)
        }
    n = 1 shl order
    n = n shr 1
    while (n > 0) {
        b.writeBit(lsb and n)
        n = n shr 1
    }
}

fun bitsZetaXi(value: Int, factor: Int, order: Int): Int {
    var msb = value shr order
    var bits = order + 1
    var n = 1
    while (msb >= n) {
        msb -= n
        n = n shl factor
        bits += factor + 1
    }
    return bits
}
