/**
 * Zeta-Xi Code - by Einar Saukas
 *
 * See: https://github.com/einar-saukas/Zeta-Xi-Code
 */

 /* returns next bit (either 0 or 1) */
int read_bit();

/* writes next bit (either 0 or 1) */
void write_bit(int bit);

int decode_zeta_xi_interlaced(int factor, int order);
int decode_zeta_xi_classic(int factor, int order);
void encode_zeta_xi_interlaced(int value, int factor, int order);
void encode_zeta_xi_classic(int value, int factor, int order);
int bits_zeta_xi(int value, int factor, int order);
