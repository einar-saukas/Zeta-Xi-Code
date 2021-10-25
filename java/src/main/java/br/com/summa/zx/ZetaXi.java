/**
 * Zeta-Xi Code - by Einar Saukas
 *
 * See: https://github.com/einar-saukas/Zeta-Xi-Code
 */

package br.com.summa.zx;

public class ZetaXi {
    private ZetaXi() {}

    public static int decodeZetaXiInterlaced(int factor, int order, BitInputStream b) {
        int n = 0;
        while (b.readBit() == 0) {
            for (int i = 0; i < factor; i++) {
                n = n << 1 | b.readBit();
            }
            n++;
        }
        while (order-- > 0) {
            n = n << 1 | b.readBit();
        }
        return n;
    }

    public static int decodeZetaXiClassic(int factor, int order, BitInputStream b) {
        int count = 0;
        while (b.readBit() == 0) {
            count++;
        }    
        int n = 0;
        while (count-- > 0) {
            for (int i = 0; i < factor; i++) {
                n = n << 1 | b.readBit();
            }
            n++;
        }
        while (order-- > 0) {
            n = n << 1 | b.readBit();
        }
        return n;
    }

    public static void encodeZetaXiInterlaced(int value, int factor, int order, BitOutputStream b) {
        int msb = value >> order;
        int lsb = value - (msb << order);
        int n = 1;
        while (msb >= n) {
            msb -= n;
            n <<= factor;
        }
        while (n > 1) {
            b.writeBit(0);
            for (int i = 0; i < factor; i++) {
                n >>= 1;
                b.writeBit(msb & n);
            }
        }
        b.writeBit(1);
        n = 1 << order;
        while ((n >>= 1) > 0)
            b.writeBit(lsb & n);
    }

    public static void encodeZetaXiClassic(int value, int factor, int order, BitOutputStream b) {
        int msb = value >> order;
        int lsb = value - (msb << order);
        int n = 1;
        int count = 0;
        while (msb >= n) {
            msb -= n;
            n <<= factor;
            count++;
        }
        while (count-- > 0)
            b.writeBit(0);
        b.writeBit(1);
        while (n > 1)
            for (int i = 0; i < factor; i++) {
                n >>= 1;
                b.writeBit(msb & n);
            }
        n = 1 << order;
        while ((n >>= 1) > 0) {
            b.writeBit(lsb & n);
        }
    }

    public static int bitsZetaXi(int value, int factor, int order) {
        int msb = value >> order;
        int bits = order + 1;
        int n = 1;
        while (msb >= n) {
            msb -= n;
            n <<= factor;
            bits += factor + 1;
        }
        return bits;
    }
}
