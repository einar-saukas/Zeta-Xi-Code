# Zeta-Xi Code

**Zeta-Xi Code** is a
[universal code](https://en.wikipedia.org/wiki/Universal_code_(data_compression))
for representing variable-length nonnegative integers in binary format,
developed by Einar Saukas. It's basically a generalization of both
[Elias Gamma Code](https://en.wikipedia.org/wiki/Elias_gamma_coding) and
[Exponential-Golomb Code](https://en.wikipedia.org/wiki/Exponential-Golomb_coding)
that uses different proportions between control and data bits. This way, larger
numbers can be encoded in fewer bits, at the expense of more bits to represent
some of the smaller numbers.


## Factor R

In both [Elias Gamma Code](https://en.wikipedia.org/wiki/Elias_gamma_coding) and
[Exponential-Golomb Code](https://en.wikipedia.org/wiki/Exponential-Golomb_coding),
there's a 1:1 proportion between control and data bits, as represented below:

```
Number N      Number N+1   Bits  Classic Elias Gamma Code          Interlaced Elias Gamma Code
0             1              1   1                                 1
1-2           2-3            3   01x                               0x1
3-6           4-7            5   001xx                             0x0x1
7-14          8-15           7   0001xxx                           0x0x0x1
15-30         16-31          9   00001xxxx                         0x0x0x0x1
31-62         32-63         11   000001xxxxx                       0x0x0x0x0x1
63-126        64-127        13   0000001xxxxxx                     0x0x0x0x0x0x1
127-254       128-255       15   00000001xxxxxxx                   0x0x0x0x0x0x0x1
255-510       256-511       17   000000001xxxxxxxx                 0x0x0x0x0x0x0x0x1
511-1022      512-1023      19   0000000001xxxxxxxxx               0x0x0x0x0x0x0x0x0x1
1023-2046     1024-2047     21   00000000001xxxxxxxxxx             0x0x0x0x0x0x0x0x0x0x1
2047-4094     2048-4095     23   000000000001xxxxxxxxxxx           0x0x0x0x0x0x0x0x0x0x0x1
4095-8190     4096-8191     25   0000000000001xxxxxxxxxxxx         0x0x0x0x0x0x0x0x0x0x0x0x1
8191-16382    8192-16383    27   00000000000001xxxxxxxxxxxxx       0x0x0x0x0x0x0x0x0x0x0x0x0x1
16383-32766   16384-32767   29   000000000000001xxxxxxxxxxxxxx     0x0x0x0x0x0x0x0x0x0x0x0x0x0x1
32767-65534   32768-65535   31   0000000000000001xxxxxxxxxxxxxxx   0x0x0x0x0x0x0x0x0x0x0x0x0x0x0x1
```

In **Zeta-Xi Code** with factor R, there's a 1:R proportion between control and
data bits. For instance **Zeta-Xi Code** with factor `R=2` is represented below:

```
Number N      Number N+1   Bits  Classic Zeta-Xi[2c] Code          Interlaced Zeta-Xi[2i] Code
0             1              1   1                                 1
1-4           2-5            4   01xx                              0xx1
5-20          6-21           7   001xxxx                           0xx0xx1
21-84         22-85         10   0001xxxxxx                        0xx0xx0xx1
85-340        86-341        13   00001xxxxxxxx                     0xx0xx0xx0xx1
341-1364      342-1365      16   000001xxxxxxxxxx                  0xx0xx0xx0xx0xx1
1365-5460     1366-5461     19   0000001xxxxxxxxxxxx               0xx0xx0xx0xx0xx0xx1
5461-21844    5462-21845    22   00000001xxxxxxxxxxxxxx            0xx0xx0xx0xx0xx0xx0xx1
21845-87380   21846-87381   25   000000001xxxxxxxxxxxxxxxx         0xx0xx0xx0xx0xx0xx0xx0xx1
```

Examples:

```
Zeta-Xi[2c](0) = 1                            Zeta-Xi[2i](0) = 1
Zeta-Xi[2c](1) = 0100                         Zeta-Xi[2i](1) = 0001
Zeta-Xi[2c](2) = 0101                         Zeta-Xi[2i](2) = 0011
Zeta-Xi[2c](3) = 0110                         Zeta-Xi[2i](3) = 0101
Zeta-Xi[2c](4) = 0111                         Zeta-Xi[2i](4) = 0111
Zeta-Xi[2c](5) = 0010000                      Zeta-Xi[2i](5) = 0000001
Zeta-Xi[2c](6) = 0010001                      Zeta-Xi[2i](6) = 0000011
Zeta-Xi[2c](7) = 0010010                      Zeta-Xi[2i](7) = 0000101
Zeta-Xi[2c](8) = 0010011                      Zeta-Xi[2i](8) = 0000111
Zeta-Xi[2c](9) = 0010100                      Zeta-Xi[2i](9) = 0010001
```

**Zeta-Xi Code** with factor `R=3` is represented below:

```
Number N      Number N+1   Bits  Classic Zeta-Xi[3c] Code          Interlaced Zeta-Xi[3i] Code
0             1              1   1                                 1
1-8           2-9            5   01xxx                             0xxx1
9-72          10-73          9   001xxxxxx                         0xxx0xxx1
73-584        74-585        13   0001xxxxxxxxx                     0xxx0xxx0xxx1
585-4680      586-4681      17   00001xxxxxxxxxxxx                 0xxx0xxx0xxx0xxx1
4681-37448    4682-37449    21   000001xxxxxxxxxxxxxxx             0xxx0xxx0xxx0xxx0xxx1
37449-299592  37450-299593  25   0000001xxxxxxxxxxxxxxxxxx         0xxx0xxx0xxx0xxx0xxx0xxx1
```

Examples:

```
Zeta-Xi[3c](0) = 1                            Zeta-Xi[3i](0) = 1
Zeta-Xi[3c](1) = 01000                        Zeta-Xi[3i](1) = 00001
Zeta-Xi[3c](2) = 01001                        Zeta-Xi[3i](2) = 00011
Zeta-Xi[3c](3) = 01010                        Zeta-Xi[3i](3) = 00101
Zeta-Xi[3c](4) = 01011                        Zeta-Xi[3i](4) = 00111
Zeta-Xi[3c](5) = 01100                        Zeta-Xi[3i](5) = 01001
Zeta-Xi[3c](6) = 01101                        Zeta-Xi[3i](6) = 01011
Zeta-Xi[3c](7) = 01110                        Zeta-Xi[3i](7) = 01101
Zeta-Xi[3c](8) = 01111                        Zeta-Xi[3i](8) = 01111
Zeta-Xi[3c](9) = 001000000                    Zeta-Xi[3i](9) = 000000001
```

Notice that both
[Elias Gamma Code](https://en.wikipedia.org/wiki/Elias_gamma_coding) and
[Exponential-Golomb Code](https://en.wikipedia.org/wiki/Exponential-Golomb_coding)
are simply special cases of **Zeta-Xi Code** with factor `R=1`, i.e:

```
Exp-Golomb(x) = Zeta-Xi[1c](x)
```

and

```
Elias-Gamma(x) = Zeta-Xi[1c](x-1)
```


## Order K

To encode larger numbers in even fewer bits, a fixed number K of bits can be
allocated to represent the least significant bits. In this case, each number
can be represented in 2 parts, as follows:

* `MSB(n) = floor(n/(2^K))` represented using Zeta-Xi[R]
* `LSB(n) = n mod (2^K)` represented in binary using K bits

For instance **Zeta-Xi Code** with factor `R=3` and order `K=1` is represented
below:

```
Number X       Number X+1    Bits  Classic Zeta-Xi[3c1] Code         Interlaced Zeta-Xi[3i1] Code
0-1            1-2             2   1y                                1y
2-17           3-18            6   01xxxy                            0xxx1y
18-145         19-146         10   001xxxxxxy                        0xxx0xxx1y
146-1169       147-1170       14   0001xxxxxxxxxy                    0xxx0xxx0xxx1y
1170-9361      1171-9362      18   00001xxxxxxxxxxxxy                0xxx0xxx0xxx0xxx1y
9362-74897     9363-74898     22   000001xxxxxxxxxxxxxxxy            0xxx0xxx0xxx0xxx0xxx1y
74898-599185   74899-599184   26   0000001xxxxxxxxxxxxxxxxxxy        0xxx0xxx0xxx0xxx0xxx0xxx1y
```

Examples:

```
Zeta-Xi[3c1](0) = 10                          Zeta-Xi[3i1](0) = 10
Zeta-Xi[3c1](1) = 11                          Zeta-Xi[3i1](1) = 11
Zeta-Xi[3c1](2) = 010000                      Zeta-Xi[3i1](2) = 000010
Zeta-Xi[3c1](3) = 010001                      Zeta-Xi[3i1](3) = 000011
Zeta-Xi[3c1](4) = 010010                      Zeta-Xi[3i1](4) = 000110
Zeta-Xi[3c1](5) = 010011                      Zeta-Xi[3i1](5) = 000111
Zeta-Xi[3c1](6) = 010100                      Zeta-Xi[3i1](6) = 001010
Zeta-Xi[3c1](7) = 010101                      Zeta-Xi[3i1](7) = 001011
Zeta-Xi[3c1](8) = 010110                      Zeta-Xi[3i1](8) = 001110
Zeta-Xi[3c1](9) = 010111                      Zeta-Xi[3i1](9) = 001111
```

**Zeta-Xi Code** with factor `R=3` and order `K=2` is represented below:

```
Number X        Number X+1     Bits  Classic Zeta-Xi[3c2] Code     Interlaced Zeta-Xi[3i2] Code
0-3             1-4              3   1yy                           1yy
4-35            5-36             7   01xxxyy                       0xxx1yy
36-291          37-292          11   001xxxxxxyy                   0xxx0xxx1yy
292-2339        293-2340        15   0001xxxxxxxxxyy               0xxx0xxx0xxx1yy
2340-18723      2341-18724      19   00001xxxxxxxxxxxxyy           0xxx0xxx0xxx0xxx1yy
18724-149795    18725-149796    23   000001xxxxxxxxxxxxxxxyy       0xxx0xxx0xxx0xxx0xxx1yy
149796-1198371  149797-1198372  27   0000001xxxxxxxxxxxxxxxxxxyy   0xxx0xxx0xxx0xxx0xxx0xxx1yy
```

Examples:

```
Zeta-Xi[3c2](0) = 100                         Zeta-Xi[3i2](0) = 100
Zeta-Xi[3c2](1) = 101                         Zeta-Xi[3i2](1) = 101
Zeta-Xi[3c2](2) = 110                         Zeta-Xi[3i2](2) = 110
Zeta-Xi[3c2](3) = 111                         Zeta-Xi[3i2](3) = 111
Zeta-Xi[3c2](4) = 0100000                     Zeta-Xi[3i2](4) = 0000100
Zeta-Xi[3c2](5) = 0100001                     Zeta-Xi[3i2](5) = 0000101
Zeta-Xi[3c2](6) = 0100010                     Zeta-Xi[3i2](6) = 0000110
Zeta-Xi[3c2](7) = 0100011                     Zeta-Xi[3i2](7) = 0000111
Zeta-Xi[3c2](8) = 0100100                     Zeta-Xi[3i2](8) = 0001100
Zeta-Xi[3c2](9) = 0100101                     Zeta-Xi[3i2](9) = 0001101
```

Again both
[Elias Gamma Code](https://en.wikipedia.org/wiki/Elias_gamma_coding) and
[Exponential-Golomb Code](https://en.wikipedia.org/wiki/Exponential-Golomb_coding)
are simply special cases of **Zeta-Xi Code** with factor `R=1` and Order `K=0`, i.e:

```
Exp-Golomb(x) = Zeta-Xi[1c0](x)
```

and

```
Elias-Gamma(x) = Zeta-Xi[1c0](x-1)
```

Moreover **Zeta-Xi Code** is also a generalization of [VLQ (Variable-Length Quantity)
without redundancy](https://en.wikipedia.org/wiki/Variable-length_quantity#Removing_redundancy),
which is simply a special case of interlaced **Zeta-Xi Code** with factor `R=7`
and Order `K=7` (except with "continuation bit" inverted), i.e:

```
VLQ(x) = Zeta-Xi[7i7](x)
```


## Algorithms

**Zeta-Xi Code** is a very simple and efficient bit stream encoding. The 
following algorithm decodes a sequence of bits encoded with Interlaced
**Zeta-Xi Code** for a certain factor R and order K:

```
decodeZetaXiInterlaced(factor, order) {
    n = 0
    while (!readBit()) {
        for (1..factor)
            n = n*2 + readBit()
        n++
    }
    for (1..order)
        n = n*2 + readBit()
    return n
}
```

The simplicity of this algorithm is even more evident in low-level programming
languages. This is the same algorithm in Assembly Z80:

```
        ld hl, 0                        ;     hl = 0;
        call decode_zeta_xi_inter       ;     hl = decode_zeta_xi_inter(hl);
        ...                             ;     ...
loop:                                   ; loop:
    REPT factor                         ;     for (1..factor) {
        call read_bit                   ;         c = read_bit();
        adc hl,hl                       ;         hl = hl*2 + c;
    ENDR                                ;     }
        inc hl                          ;     hl = hl + 1;
decode_zeta_xi_inter:                   ; decode_zeta_xi_inter:
        call read_bit                   ;     c = read_bit();
        jr nc, loop                     ;     if (!c) goto loop;
    REPT order                          ;     for (1..order) {
        call read_bit                   ;         c = read_bit();
        adc hl,hl                       ;         hl = hl*2 + c;
    ENDR                                ;     }
        ret                             ;     return hl;
```

The classic (non-interlaced) variant of this algorithm is similar, except it
first needs to count control bits that are stored separately:

```
decodeZetaXiClassic(factor, order) {
    count = 0
    while (!readBit())
        count++
    n = 0
    for (1..count) {
        for (1..factor)
            n = n*2 + readBit()
        n++
    }
    for (1..order)
        n = n*2 + readBit()
    return n
}
```

The encoding algorithm is non-intuitive but very efficient bit stream encoder. 
The following algorithm encodes a value to Interlaced **Zeta-Xi Code** for a 
certain factor R and order K:

```
encodeZetaXiInterlaced(value, factor, order) {
    msb = value >> order
    lsb = value - (msb << order)
    n = 1
    while (msb >= n) {
        msb -= n
        n <<= factor
    }
    while (n > 1) {
        writeBit(0)
        for (1..factor) {
            n >>= 1
            writeBit(msb & n)
        }
    }
    writeBit(1)
    n = 1 << order
    while ((n >>= 1) > 0)
        writeBit(lsb & n)
}
```

The corresponding classic (non-interlaced) variant is similar, except all 
control bits are stored in advance:

```
encodeZetaXiClassic(value, factor, order) {
    msb = value >> order
    lsb = value - (msb << order)
    n = 1
    count = 0
    while (msb >= n) {
        msb -= n
        n <<= factor
        count++
    }
    for (1..count)
        writeBit(0)
    writeBit(1)
    while (n > 1)
        for (1..factor) {
            n >>= 1
            writeBit(msb & n)
        }
    n = 1 << order
    while ((n >>= 1) > 0)
        writeBit(lsb & n);
}
```

A simpler algorithm can predict the exact number of bits required to represent 
a certain value in **Zeta-Xi Code** (interlaced or classic):

```
bitsZetaXi(value, factor, order) {
    msb = value >> order
    bits = order + 1
    n = 1
    while (msb >= n) {
        msb -= n
        n <<= factor
        bits += factor + 1
    }
    return bits
}
```

# Examples

This repository contains implementations of this algorithm in a few programming
languages (standard C, Java, Kotlin). A sample program is provided in each case.


# Credits

**Zeta-Xi Code** was designed and implemented by Einar Saukas. You can freely
copy, redistribute and use this material for any purpose, even commercially,
as long as you give proper credit to the author (see
[CC-BY](https://creativecommons.org/licenses/by/4.0/)).
