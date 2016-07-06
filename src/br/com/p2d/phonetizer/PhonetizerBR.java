package br.com.p2d.phonetizer;

import java.util.ArrayList;

public class PhonetizerBR {
    public static String phonetize(String str) {

        // Função que faz efetivamente a substituição de letras,

        // fonetizando o texto

        // matrizes de caracteres utilizadas para manipular o texto

        char[] foncmp = new char[256];

        char[] fonwrk = new char[256];

        char[] fonaux = new char[256];

        char[] fonfon = new char[256];

        int i, j, x, k, // contadores

        desloc, // posicao atual no vetor

        endfon, // indica se eh ultimo fonema

        copfon, // indica se o fonema deve ser copiado

        copmud, newmud; // indica se o fonema eh mudo

        // Vetor utilizado para armazenar o texto:

        // cada palavra do texto e armazenada em uma posicao do vetor

        i = 0;

        j = 0;// zera os contadores

        str = removeMultiple(str);

        // todos os caracteres duplicados sao eliminados

        // exemplo: SS -> S, RR -> R

        ArrayList<String> component = StringUtils.strToVector(str);

        // o texto eh armazenado no vetor:

        // cada palavra ocupa uma posicao do vetor

        for (desloc = 0; desloc < component.size(); desloc++) {

            // percorre o vetor, palavra a palavra

            for (i = 0; i < 256; i++) {

                fonwrk[i] = ' ';

                fonfon[i] = ' ';// branqueia as matrizes

            }// for

            foncmp = component.get(desloc).toCharArray();

            fonaux = foncmp;

            // matrizes recebem os caracteres da palavra atual

            j = 0;

            if (component.get(desloc).length() == 1) {

                fonwrk[0] = foncmp[0];

                // se a palavra possuir apenas 1 caracter, nao altera a palavra

                if (foncmp[0] == '_') {

                    fonwrk[0] = ' ';

                    // se o caracter for "_", troca por espaco em branco

                }// if

                else

                if ((foncmp[0] == 'E') ||

                (foncmp[0] == '&') ||

                (foncmp[0] == 'I')) {

                    fonwrk[0] = 'i';

                    // se o caracter for "E", "&" ou "I", troca por "i"

                }// if

            }// if

            else {

                for (i = 0; i < component.get(desloc).length(); i++) {
                    if (foncmp[i] == '_') {
                        fonfon[i] = 'Y'; // _ -> Y
                    } else

                    if (foncmp[i] == '&') {
                        fonfon[i] = 'i'; // & -> i
                    } else

                    if ((foncmp[i] == 'E') ||

                    (foncmp[i] == 'Y') ||

                    (foncmp[i] == 'I')) {
                        fonfon[i] = 'i'; // E, Y, I -> i
                    } else

                    if ((foncmp[i] == 'O') ||

                    (foncmp[i] == 'U')) {
                        fonfon[i] = 'o'; // O, U -> u
                    } else

                    if (foncmp[i] == 'A') {
                        fonfon[i] = 'a'; // A -> a
                    } else

                    if (foncmp[i] == 'S') {
                        fonfon[i] = 's'; // S -> s
                    } else {
                        fonfon[i] = foncmp[i];
                    }
                }

                // caracter nao eh modificado

                endfon = 0;

                fonaux = fonfon;

                // palavras formadas por apenas 3 consoantes

                // sao dispensadas do processo de fonetizacao

                if (fonaux[3] == ' ') {
                    if ((fonaux[0] == 'a') ||

                    (fonaux[0] == 'i') ||

                    (fonaux[0] == 'o')) {
                        endfon = 0;
                    } else

                    if ((fonaux[1] == 'a') ||

                    (fonaux[1] == 'i') ||

                    (fonaux[1] == 'o')) {
                        endfon = 0;
                    } else

                    if ((fonaux[2] == 'a') ||

                    (fonaux[2] == 'i') ||

                    (fonaux[2] == 'o')) {
                        endfon = 0;
                    } else {

                        endfon = 1;

                        fonwrk[0] = fonaux[0];

                        fonwrk[1] = fonaux[1];

                        fonwrk[2] = fonaux[2];

                    }// else
                }

                if (endfon != 1) { // se a palavra nao for formada por apenas 3 consoantes...

                    for (i = 0; i < component.get(desloc).length(); i++) {

                        // percorre a palavra corrente, letra a letra

                        copfon = 0;

                        copmud = 0;

                        newmud = 0;

                        // zera variaveis de controle

                        switch (fonaux[i]) {

                        case 'a': // se o caracter for a

                            // se a palavra termina com As, AZ, AM, ou AN,

                            // elimina a consoante do final da palavra

                            if ((fonaux[i + 1] == 's') ||

                            (fonaux[i + 1] == 'Z') ||

                            (fonaux[i + 1] == 'M') ||

                            (fonaux[i + 1] == 'N')) {
                                if (fonaux[i + 2] != ' ') {
                                    copfon = 1;
                                } else {

                                    fonwrk[j] = 'a';

                                    fonwrk[j + 1] = ' ';

                                    j++;

                                    i++;

                                }// else
                            } else {
                                copfon = 1;
                            }

                            break;

                        case 'B': // se o caracter for B

                            // B nao eh modificado

                            copmud = 1;

                            break;

                        case 'C': // se o caracter for C

                            x = 0;

                            if (fonaux[i + 1] == 'i')

                            // ci vira si

                            {
                                fonwrk[j] = 's';

                                j++;

                                break;

                            }// if

                            // coes final vira cao

                            if ((fonaux[i + 1] == 'o') &&

                            (fonaux[i + 2] == 'i') &&

                            (fonaux[i + 3] == 's') &&

                            (fonaux[i + 4] == ' '))

                            {
                                fonwrk[j] = 'K';

                                fonwrk[j + 1] = 'a';

                                fonwrk[j + 2] = 'o';

                                i = i + 4;

                                break;

                            }// if

                            // ct vira t

                            if (fonaux[i + 1] == 'T') {
                                break;
                            }

                            // c vira k

                            if (fonaux[i + 1] != 'H')

                            {
                                fonwrk[j] = 'K';

                                newmud = 1;

                                // ck vira k

                                if (fonaux[i + 1] == 'K')

                                {
                                    i++;

                                    break;

                                }// if
                                else {
                                    break;
                                }

                            }// if

                            // ch vira k para chi final, chi vogal, chini final e

                            // chiti final

                            // chi final ou chi vogal

                            if (fonaux[i + 1] == 'H') {
                                if (fonaux[i + 2] == 'i') {
                                    if ((fonaux[i + 3] == 'a') ||

                                    (fonaux[i + 3] == 'i') ||

                                    (fonaux[i + 3] == 'o')) {
                                        x = 1;
                                    } else

                                    if (fonaux[i + 3] == 'N') {
                                        if (fonaux[i + 4] == 'i') {
                                            if (fonaux[i + 5] == ' ') {
                                                x = 1;
                                            } else {
                                                ;
                                            }
                                        } else {
                                            ;
                                        }
                                    } else

                                    // chiti final

                                    if (fonaux[i + 3] == 'T') {
                                        if (fonaux[i + 4] == 'i') {
                                            if (fonaux[i + 5] == ' ') {
                                                x = 1;
                                            }
                                        }
                                    }
                                }
                            }

                            if (x == 1)

                            {
                                fonwrk[j] = 'K';

                                j++;

                                i++;

                                break;

                            }// if

                            // chi, nao chi final, chi vogal, chini final ou chiti final

                            // ch nao seguido de i

                            // se anterior nao e s, ch = x

                            if (j > 0) {
                                if (fonwrk[j - 1] == 's')

                                {
                                    j--;

                                }// if
                            }

                            fonwrk[j] = 'X';

                            newmud = 1;

                            i++;

                            break;

                        case 'D': // se o caracter for D

                            x = 0;

                            // procura por dor

                            if (fonaux[i + 1] != 'o')

                            {
                                copmud = 1;

                                break;

                            }// if

                            else

                            if (fonaux[i + 2] == 'R') {
                                if (i != 0) {
                                    x = 1; // dor nao inicial
                                } else {
                                    copfon = 1; // dor inicial
                                }
                            } else {
                                copfon = 1; // nao e dor
                            }

                            if (x == 1) {
                                if (fonaux[i + 3] == 'i') {
                                    if (fonaux[i + 4] == 's') {
                                        if (fonaux[i + 5] != ' ') {
                                            x = 0; // nao e dores
                                        } else {
                                            ;
                                        }
                                    } else {
                                        x = 0;
                                    }
                                } else

                                if (fonaux[i + 3] == 'a') {
                                    if (fonaux[i + 4] != ' ') {
                                        if (fonaux[i + 4] != 's') {
                                            x = 0;
                                        } else

                                        if (fonaux[i + 5] != ' ') {
                                            x = 0;
                                        } else {
                                            ;
                                        }
                                    } else {
                                        ;
                                    }
                                } else {
                                    x = 0;
                                }
                            } else {
                                x = 0;
                            }

                            if (x == 1)

                            {
                                fonwrk[j] = 'D';

                                fonwrk[j + 1] = 'o';

                                fonwrk[j + 2] = 'R';

                                i = i + 5;

                            }// if
                            else {
                                copfon = 1;
                            }

                            break;

                        case 'F': // se o caracter for F

                            // F nao eh modificado

                            copmud = 1;

                            break;

                        case 'G': // se o caracter for G

                            // gui -> gi

                            if (fonaux[i + 1] == 'o') {
                                if (fonaux[i + 2] == 'i')

                                {
                                    fonwrk[j] = 'G';

                                    fonwrk[j + 1] = 'i';

                                    j += 2;

                                    i += 2;

                                }// if
                                else {
                                    copmud = 1;
                                }
                            } else

                            // gl

                            if (fonaux[i + 1] == 'L') {
                                if (fonaux[i + 2] == 'i') {
                                    if ((fonaux[i + 3] == 'a') ||

                                    (fonaux[i + 3] == 'i') ||

                                    (fonaux[i + 3] == 'o'))

                                    {
                                        fonwrk[j] = fonaux[i + 1];

                                        fonwrk[j + 1] = fonaux[i + 2];

                                        j += 2;

                                        i += 2;

                                    }// if

                                    else

                                    // glin -> lin

                                    if (fonaux[i + 3] == 'N')

                                    {
                                        fonwrk[j] = fonaux[i + 1];

                                        fonwrk[j + 1] = fonaux[i + 2];

                                        j += 2;

                                        i += 2;

                                    }/* if */else {
                                        copmud = 1;
                                    }
                                } else {
                                    copmud = 1;
                                }
                            } else

                            // gn + vogal -> ni + vogal

                            if (fonaux[i + 1] == 'N') {
                                if ((fonaux[i + 2] != 'a') &&

                                (fonaux[i + 2] != 'i') &&

                                (fonaux[i + 2] != 'o')) {
                                    copmud = 1;
                                } else

                                {
                                    fonwrk[j] = 'N';

                                    fonwrk[j + 1] = 'i';

                                    j += 2;

                                    i++;

                                }// else
                            } else

                            // ghi -> gi

                            if (fonaux[i + 1] == 'H') {
                                if (fonaux[i + 2] == 'i')

                                {
                                    fonwrk[j] = 'G';

                                    fonwrk[j + 1] = 'i';

                                    j += 2;

                                    i += 2;

                                }// if
                                else {
                                    copmud = 1;
                                }
                            } else {
                                copmud = 1;
                            }

                            break;

                        case 'H': // se o caracter for H

                            // H eh desconsiderado

                            break;

                        case 'i': // se o caracter for i

                            if (fonaux[i + 2] == ' ') {
                                if (fonaux[i + 1] == 's')

                                {
                                    fonwrk[j] = 'i';

                                    break;

                                }// if

                                else

                                if (fonaux[i + 1] == 'Z')

                                {
                                    fonwrk[j] = 'i';

                                    break;

                                }// if
                            }

                            // ix

                            if (fonaux[i + 1] != 'X') {
                                copfon = 1;
                            } else

                            if (i != 0) {
                                copfon = 1;
                            } else

                            // ix vogal no inicio torna-se iz

                            if ((fonaux[i + 2] == 'a') ||

                            (fonaux[i + 2] == 'i') ||

                            (fonaux[i + 2] == 'o'))

                            {
                                fonwrk[j] = 'i';

                                fonwrk[j + 1] = 'Z';

                                j += 2;

                                i++;

                                break;

                            }// if

                            else

                            // ix consoante no inicio torna-se is

                            if ((fonaux[i + 2] == 'C') || (fonaux[i + 2] == 's')) {

                                fonwrk[j] = 'i';

                                j++;

                                i++;

                                break;

                            }// if

                            else

                            {
                                fonwrk[j] = 'i';

                                fonwrk[j + 1] = 's';

                                j += 2;

                                i++;

                                break;

                            }// else

                            break;

                        case 'J': // se o caracter for J

                            // J -> Gi

                            fonwrk[j] = 'G';

                            fonwrk[j + 1] = 'i';

                            j += 2;

                            break;

                        case 'K': // se o caracter for K

                            // KT -> T

                            if (fonaux[i + 1] != 'T') {
                                copmud = 1;
                            }

                            break;

                        case 'L': // se o caracter for L

                            // L + vogal nao eh modificado

                            if ((fonaux[i + 1] == 'a') ||

                            (fonaux[i + 1] == 'i') ||

                            (fonaux[i + 1] == 'o')) {
                                copfon = 1;
                            } else

                            // L + consoante -> U + consoante

                            if (fonaux[i + 1] != 'H')

                            {
                                fonwrk[j] = 'o';

                                j++;

                                break;

                            }// if

                            // LH + consoante nao eh modificado

                            else

                            if ((fonaux[i + 2] != 'a') &&

                            (fonaux[i + 2] != 'i') &&

                            (fonaux[i + 2] != 'o')) {
                                copfon = 1;
                            } else

                            // LH + vogal -> LI + vogal

                            {
                                fonwrk[j] = 'L';

                                fonwrk[j + 1] = 'i';

                                j += 2;

                                i++;

                                break;

                            }

                            break;

                        case 'M': // se o caracter for M

                            // M + consoante -> N + consoante

                            // M final -> N

                            if (((fonaux[i + 1] != 'a') &&

                            (fonaux[i + 1] != 'i') &&

                            (fonaux[i + 1] != 'o')) ||

                            (fonaux[i + 1] == ' '))

                            {
                                fonwrk[j] = 'N';

                                j++;

                            }// if
                            else {
                                copfon = 1;
                            }

                            break;

                        case 'N': // se o caracter for N

                            // NGT -> NT

                            if ((fonaux[i + 1] == 'G') &&

                            (fonaux[i + 2] == 'T'))

                            {
                                fonaux[i + 1] = 'N';

                                copfon = 1;

                            }// if

                            else

                            // NH + consoante nao eh modificado

                            if (fonaux[i + 1] == 'H') {
                                if ((fonaux[i + 2] != 'a') &&

                                (fonaux[i + 2] != 'i') &&

                                (fonaux[i + 2] != 'o')) {
                                    copfon = 1;
                                } else

                                {
                                    fonwrk[j] = 'N';

                                    fonwrk[j + 1] = 'i';

                                    j += 2;

                                    i++;

                                }
                            } else {
                                copfon = 1;
                            }

                            break;

                        case 'o': // se o caracter for o

                            // oS final -> o

                            // oZ final -> o

                            if ((fonaux[i + 1] == 's') ||

                            (fonaux[i + 1] == 'Z')) {
                                if (fonaux[i + 2] == ' ')

                                {
                                    fonwrk[j] = 'o';

                                    break;

                                }// if
                                else {
                                    copfon = 1;
                                }
                            } else {
                                copfon = 1;
                            }

                            break;

                        case 'P': // se o caracter for P

                            // PH -> F

                            if (fonaux[i + 1] == 'H')

                            {
                                fonwrk[j] = 'F';

                                i++;

                                newmud = 1;

                            }// if
                            else {
                                copmud = 1;
                            }

                            break;

                        case 'Q': // se o caracter for Q

                            // Koi -> Ki (QUE, QUI -> KE, KI)

                            if (fonaux[i + 1] == 'o') {
                                if (fonaux[i + 2] == 'i')

                                {
                                    fonwrk[j] = 'K';

                                    j++;

                                    i++;

                                    break;

                                }// if
                            }

                            // QoA -> KoA (QUA -> KUA)

                            fonwrk[j] = 'K';

                            j++;

                            break;

                        case 'R': // se o caracter for R

                            // R nao eh modificado

                            copfon = 1;

                            break;

                        case 's': // se o caracter for s

                            // s final eh ignorado

                            if (fonaux[i + 1] == ' ') {
                                break;
                            }

                            // s inicial + vogal nao eh modificado

                            if ((fonaux[i + 1] == 'a') ||

                            (fonaux[i + 1] == 'i') ||

                            (fonaux[i + 1] == 'o')) {
                                if (i == 0)

                                {
                                    copfon = 1;

                                    break;

                                }// if

                                else

                                // s entre duas vogais -> z

                                if ((fonaux[i - 1] != 'a') &&

                                (fonaux[i - 1] != 'i') &&

                                (fonaux[i - 1] != 'o'))

                                {
                                    copfon = 1;

                                    break;

                                }// if

                                else

                                // SoL nao eh modificado

                                if ((fonaux[i + 1] == 'o') &&

                                (fonaux[i + 2] == 'L') &&

                                (fonaux[i + 3] == ' '))

                                {
                                    copfon = 1;

                                    break;

                                }// if

                                else

                                {
                                    fonwrk[j] = 'Z';

                                    j++;

                                    break;

                                }// else
                            }

                            // ss -> s

                            if (fonaux[i + 1] == 's') {
                                if (fonaux[i + 2] != ' ')

                                {
                                    copfon = 1;

                                    i++;

                                    break;

                                }// if

                                else

                                {
                                    fonaux[i + 1] = ' ';

                                    break;

                                }// else
                            }

                            // s inicial seguido de consoante fica precedido de i

                            // se nao for sci, sh ou sch nao seguido de vogal

                            if (i == 0) {
                                if (!((fonaux[i + 1] == 'C') &&

                                (fonaux[i + 2] == 'i'))) {
                                    if (fonaux[i + 1] != 'H') {
                                        if (!((fonaux[i + 1] == 'C') &&

                                        (fonaux[i + 2] == 'H') &&

                                        ((fonaux[i + 3] != 'a') &&

                                        (fonaux[i + 3] != 'i') &&

                                        (fonaux[i + 3] != 'o'))))

                                        {
                                            fonwrk[j] = 'i';

                                            j++;

                                            copfon = 1;

                                            break;

                                        }// if
                                    }
                                }
                            }

                            // sH -> X;

                            if (fonaux[i + 1] == 'H')

                            {
                                fonwrk[j] = 'X';

                                i++;

                                newmud = 1;

                                break;

                            }// if

                            if (fonaux[i + 1] != 'C')

                            {
                                copfon = 1;

                                break;

                            }// if

                            // sCh nao seguido de i torna-se X

                            if (fonaux[i + 2] == 'H')

                            {
                                fonwrk[j] = 'X';

                                i += 2;

                                newmud = 1;

                                break;

                            }// if

                            if (fonaux[i + 2] != 'i')

                            {
                                copfon = 1;

                                break;

                            }// if

                            // sCi final -> Xi

                            if (fonaux[i + 3] == ' ')

                            {
                                fonwrk[j] = 'X';

                                fonwrk[j + 1] = 'i';

                                i = i + 3;

                                break;

                            }// if

                            // sCi vogal -> X

                            if ((fonaux[i + 3] == 'a') ||

                            (fonaux[i + 3] == 'i') ||

                            (fonaux[i + 3] == 'o'))

                            {
                                fonwrk[j] = 'X';

                                j++;

                                i += 2;

                                break;

                            }// if

                            // sCi consoante -> si

                            fonwrk[j] = 's';

                            fonwrk[j + 1] = 'i';

                            j += 2;

                            i += 2;

                            break;

                        case 'T': // se o caracter for T

                            // TS -> S

                            if (fonaux[i + 1] == 's') {
                                break;
                            } else

                            if (fonaux[i + 1] == 'Z') {
                                break;
                            } else {
                                copmud = 1;
                            }

                            break;

                        case 'V': // se o caracter for V

                        case 'W': // ou se o caracter for W

                            // V,W inicial + vogal -> o + vogal (U + vogal)

                            if ((fonaux[i + 1] == 'a') ||

                            (fonaux[i + 1] == 'i') ||

                            (fonaux[i + 1] == 'o')) {
                                if (i == 0)

                                {
                                    fonwrk[j] = 'o';

                                    j++;

                                }// if

                                // V,W NAO inicial + vogal -> V + vogal

                                else

                                {
                                    fonwrk[j] = 'V';

                                    newmud = 1;

                                }// else
                            } else

                            {
                                fonwrk[j] = 'V';

                                newmud = 1;

                            }// else

                            break;

                        case 'X': // se o caracter for X

                            // caracter nao eh modificado

                            copmud = 1;

                            break;

                        case 'Y': // se o caracter for Y

                            // Y jah foi tratado acima

                            break;

                        case 'Z': // se o caracter for Z

                            // Z final eh eliminado

                            if (fonaux[i + 1] == ' ') {
                                break;
                            } else

                            if ((fonaux[i + 1] == 'a') ||

                            (fonaux[i + 1] == 'i') ||

                            (fonaux[i + 1] == 'o')) {
                                copfon = 1;
                            } else

                            {
                                fonwrk[j] = 's';

                                j++;

                            }// else

                            break;

                        default: // se o caracter nao for um dos jah relacionados

                            // o caracter nao eh modificado

                            fonwrk[j] = fonaux[i];

                            j++;

                            break;

                        }// switch

                        // copia caracter corrente

                        if (copfon == 1)

                        {
                            fonwrk[j] = fonaux[i];

                            j++;

                        }// if

                        // insercao de i apos consoante muda

                        if (copmud == 1) {
                            fonwrk[j] = fonaux[i];
                        }

                        if ((copmud == 1) || (newmud == 1))

                        {
                            j++;

                            k = 0;

                            while (k == 0) {
                                if (fonaux[i + 1] == ' ')

                                // e final mudo

                                {
                                    fonwrk[j] = 'i';

                                    k = 1;

                                }// if

                                else

                                if ((fonaux[i + 1] == 'a') ||

                                (fonaux[i + 1] == 'i') ||

                                (fonaux[i + 1] == 'o')) {
                                    k = 1;
                                } else

                                if (fonwrk[j - 1] == 'X')

                                {
                                    fonwrk[j] = 'i';

                                    j++;

                                    k = 1;

                                }// if

                                else

                                if (fonaux[i + 1] == 'R') {
                                    k = 1;
                                } else

                                if (fonaux[i + 1] == 'L') {
                                    k = 1;
                                } else

                                if (fonaux[i + 1] != 'H')

                                {
                                    fonwrk[j] = 'i';

                                    j++;

                                    k = 1;

                                }// if
                                else {
                                    i++;
                                }
                            }

                        }

                    }// for

                }// if

            }// else

            for (i = 0; i < component.get(desloc).length() + 3; i++) {
                if (fonwrk[i] == 'i') {
                    fonwrk[i] = 'I';
                } else

                // a -> A

                if (fonwrk[i] == 'a') {
                    fonwrk[i] = 'A';
                } else

                // o -> U

                if (fonwrk[i] == 'o') {
                    fonwrk[i] = 'U';
                } else

                // s -> S

                if (fonwrk[i] == 's') {
                    fonwrk[i] = 'S';
                } else

                // E -> b

                if (fonwrk[i] == 'E') {
                    fonwrk[i] = ' ';
                } else

                // Y -> _

                if (fonwrk[i] == 'Y') {
                    fonwrk[i] = '_';
                }
            }

            // retorna a palavra, modificada, ao vetor que contem o texto

            component.set(desloc, String.copyValueOf(fonwrk));

            j = 0; // zera o contador

        }// for

        str = StringUtils.vectorToStr(component);

        // remonta as palavras armazenadas no vetor em um unico string

        str = removeMultiple(str);

        // remove os caracteres duplicados

        return str.toUpperCase().trim();

    }

	public static String removeMultiple(String str) {
	
	    // Retira do texto carateres que estao multiplicados:
	
	    // ss -> s, sss -> s, rr -> r
	
	    char[] foncmp = new char[256];
	
	    // matriz de caracteres que armazena o texto sem duplicatas
	
	    char[] fonaux = new char[256];
	
	    // matriz de caracteres que armazena o texto original
	
	    char[] tip = new char[1]; // armazena o caracter anterior
	
	    int i, j; // contadores
	
	    j = 0;
	
	    tip[0] = ' ';
	
	    fonaux = str.toCharArray();
	
	    // a matriz de caracteres recebe o string original
	
	    for (i = 0; i < str.length(); i++) {
	
	        // percorre o texto, caracter a caracter
	
	        // elimina o caracter se ele for duplicata e
	
	        // nao for numero, espaco ou S
	
	        if ((fonaux[i] != tip[0]) || (fonaux[i] == ' ')
	
	        || ((fonaux[i] >= '0') && (fonaux[i] <= '9'))
	
	        || ((fonaux[i] == 'S') && (fonaux[i - 1] == 'S') &&
	
	        ((i > 1) && (fonaux[i - 2] != 'S')))) {
	
	            foncmp[j] = fonaux[i];
	
	            j++;
	
	        }
	
	        tip[0] = fonaux[i];
	
	        // reajusta o caracter de comparacao
	
	    }
	
	    // o string recebe o texto sem duplicatas
	
	    str = String.copyValueOf(foncmp);
	
	    return str.trim();
	
	}// removeMultiple

}
