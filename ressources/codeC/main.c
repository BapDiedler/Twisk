#include "def.h"
#include <stdio.h>
#include <stdlib.h>

int main(int argc, char** argv) {
    int* pid;
    pid = start_simulation(1, 0, 10, NULL);
    nettoyage();
    return 0 ;
}