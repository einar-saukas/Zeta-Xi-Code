/**
 * Zeta-Xi Code - by Einar Saukas
 *
 * See: https://github.com/einar-saukas/Zeta-Xi-Code
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "zeta_xi.h"

char *input = NULL;
char *output = NULL;
int input_index = 0;

/* returns next bit (either 0 or 1) */
int read_bit() {
    return !input[input_index] || input[input_index++] != '0' ? 1 : 0;
}

/* writes next bit (either 0 or 1) */
void write_bit(int bit) {
    strcat(output, bit > 0 ? "1" : "0");
}

char to_char(char *s) {
    return s[0] && !s[1] ? s[0] : '?';
}

int main(int argc, char *argv[]) {
    int factor;
    char variant;
    int order;
    char command;
    int value;
    int length;

    if (argc != 6) {
        fprintf(stderr, "Zeta-Xi Coder/Decoder by Einar Saukas\n"
                "Usage: zeta-xi <factor> <variant> <order> <command> <value>\n"
                "  <factor>      factor (>0)\n"
                "  <variant>     Either 'c' (classic) or 'i' (interlaced)\n"
                "  <order>       Order (>=0)\n"
                "  <command>     Either 'e' (encode) or 'd' (decode)\n"
                "  <value>       Value in decimal (to encode) or binary (to decode)\n"
                "Example: zeta-xi 1 c 0 e 42\n");
        exit(1);
    }

    factor = atoi(argv[1]);
    variant = to_char(argv[2]);
    order = atoi(argv[3]);
    command = to_char(argv[4]);
    input = argv[5];

    if (factor < 1 || (variant != 'c' && variant != 'i') || order < 0 || (command != 'e' && command != 'd')) {
        fprintf(stderr, "Error: Invalid parameters\n");
        exit(1);
    }

    if (command == 'e') {
        value = atoi(input);
        if (value < 0) {
            fprintf(stderr, "Error: Invalid value\n");
            exit(1);
        }
        length = bits_zeta_xi(value, factor, order);
        output = (char *)calloc(length+1, sizeof(char));
        if (!output) {
            fprintf(stderr, "Error: Insufficient memory\n");
            exit(1);
        }
        if (variant == 'c')
            encode_zeta_xi_classic(value, factor, order);
        else
            encode_zeta_xi_interlaced(value, factor, order);
        printf("%s\n", output);
    } else {
        if (variant == 'c')
            value = decode_zeta_xi_classic(factor, order);
        else
            value = decode_zeta_xi_interlaced(factor, order);
        printf("%d\n", value);
    }

    return 0;
}
