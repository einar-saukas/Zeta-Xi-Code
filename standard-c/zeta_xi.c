/**
 * Zeta-Xi Code - by Einar Saukas
 *
 * See: https://github.com/einar-saukas/Zeta-Xi-Code
 */

/* returns next bit (either 0 or 1) */
int read_bit();

/* writes next bit (either 0 or 1) */
void write_bit(int bit);

int decode_zeta_xi_interlaced(int factor, int order) {
    int i;
    int n = 0;

    while (!read_bit()) {
        for (i = 0; i < factor; i++)
            n = n << 1 | read_bit();
        n++;
    }
    while (order--)
        n = n << 1 | read_bit();
    return n;
}

int decode_zeta_xi_classic(int factor, int order) {
    int i;
    int n = 0;
    int count = 0;

    while (!read_bit()) {
        count++;
    }
    while (count--) {
        for (i = 0; i < factor; i++)
            n = n << 1 | read_bit();
        n++;
    }
    while (order--)
        n = n << 1 | read_bit();
    return n;
}

void encode_zeta_xi_interlaced(int value, int factor, int order) {
    int i;
    int msb = value >> order;
    int lsb = value - (msb << order);
    int n = 1;

    while (msb >= n) {
        msb -= n;
        n <<= factor;
    }
    while (n > 1) {
        write_bit(0);
        for (i = 0; i < factor; i++) {
            n >>= 1;
            write_bit(msb & n);
        }
    }
    write_bit(1);
    n = 1 << order;
    while (n >>= 1) {
        write_bit(lsb & n);
    }
}

void encode_zeta_xi_classic(int value, int factor, int order) {
    int i;
    int msb = value >> order;
    int lsb = value - (msb << order);
    int n = 1;
    int count = 0;

    while (msb >= n) {
        msb -= n;
        n <<= factor;
        count++;
    }
    while (count--) {
        write_bit(0);
    }
    write_bit(1);
    while (n > 1) {
        for (i = 0; i < factor; i++) {
            n >>= 1;
            write_bit(msb & n);
        }
    }
    n = 1 << order;
    while (n >>= 1) {
        write_bit(lsb & n);
    }
}

int bits_zeta_xi(int value, int factor, int order) {
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
