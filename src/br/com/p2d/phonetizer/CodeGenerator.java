package br.com.p2d.phonetizer;

import java.util.ArrayList;

public class CodeGenerator {
	public static String randomize(String str) {
		// gera um codigo identificador de 10 caracteres para um texto qualquer

		long fon09 = 0;
		long fon11 = 0;
		long fon12 = 0; // inteiros utilizados para manipular o codigo

		char[] reg09 = new char[4];
		char[] reg11 = new char[4];
		char[] reg12 = new char[4];
		// matrizes de caracteres utilizadas para manipular o codigo

		char[] fonrnd = new char[5];
		char[] finalRand = new char[10]; // estruturas que armazenam o codigo

		char[] work = new char[2]; // variavel de manipulacao
		int i, j, k; // contadores
		int w0, w1; // inteiros utilizados para operacoes de shift

		char[] foncmp = new char[256];
		char[] fonaux = new char[256];
		// matrizes de manipulacao

		String priStr = "";
		String auxStr = "";
		String modStr = "";
		String rand = "";
		// string de manipulacao

		// vetor que armazena o texto

		fon09 = 0;
		fon11 = 0;
		fon12 = 0;

		ArrayList<String> component = StringUtils.strToVector(str);
		// texto eh armazenado no vetor

		for (i = 0; i < component.size(); i++) {
			// percorre o texto, palavra a palavra

			auxStr = component.get(i);
			foncmp = auxStr.toCharArray();
			if (foncmp[0] != ' ') {
				// se a palavra nao for vazia
				for (j = 0; j < 256; j++)
					fonaux[j] = ' '; // branqueia matriz

				j = 0;
				while (j < auxStr.length()) {
					// percorre a palavra, letra a letra

					// se a palavra iniciar por vogal,
					// insere um "R" no inicio da palavra
					if ((foncmp[0] == 'I') || (foncmp[0] == 'A')
							|| (foncmp[0] == 'U')) {
						fonaux[0] = 'R';
						for (j = 0; j < auxStr.length(); j++)
							fonaux[j + 1] = foncmp[j];
					}// if
					else {

						// se a palavra iniciar com "GI", suprime o "G"
						if ((foncmp[0] == 'G') && (auxStr.length() > 1)) {
							if (foncmp[1] == 'I') {
								for (j = 0; j < auxStr.length() - 1; j++)
									fonaux[j] = foncmp[j + 1];
								j++;
							}// if
							else {
								// senao apenas copia a palavra original
								for (j = 0; j < auxStr.length(); j++)
									fonaux[j] = foncmp[j];
							}// else
						}// if
						else {
							// senao apenas copia a palavra original
							for (j = 0; j < auxStr.length(); j++)
								fonaux[j] = foncmp[j];
						}// else
					}// else
				}// while

				auxStr = auxStr.copyValueOf(fonaux).trim();
				foncmp = auxStr.toCharArray();

				for (j = 0; j < 256; j++)
					fonaux[j] = ' ';

				j = 0;
				k = 0;
				while (j < auxStr.length()) {
					// percorre a palavra, letra a letra

					if ((j + 2 == auxStr.length()) && (j != 0) &&
					// se a palavra terminar com BI, DI, FI, GI, JI, PI, KI, TI
					// OU VI
							// suprime estas silabas da palavra
							((foncmp[j] == 'B') || (foncmp[j] == 'D')
									|| (foncmp[j] == 'F') || (foncmp[j] == 'G')
									|| (foncmp[j] == 'J') || (foncmp[j] == 'P')
									|| (foncmp[j] == 'K') || (foncmp[j] == 'T') || (foncmp[j] == 'V')))
						if (foncmp[j + 1] == 'I')
							j = j + 2;
						else {
							fonaux[k] = foncmp[j];
							j++;
							k++;
						}// else
					else

					if ((j + 3 <= auxStr.length()) &&
					// NI+vogal ou LI+vogal = N+vogal ou L+vogal
							((foncmp[j] == 'N') || (foncmp[j] == 'L')))
						if ((foncmp[j + 1] == 'I')
								&& ((foncmp[j + 2] == 'A')
										|| (foncmp[j + 2] == 'E')
										|| (foncmp[j + 2] == 'O') || (foncmp[j + 2] == 'U'))) {
							fonaux[k] = foncmp[j];
							fonaux[k + 1] = foncmp[j + 2];
							j = j + 3;
							k = k + 2;
						}// if
						else {
							fonaux[k] = foncmp[j];
							j++;
							k++;
						}// else
					else

					if ((foncmp[j] == 'R') && (j > 0))
						// vogal+R final = vogal
						if ((foncmp[j - 1] != 'A') && (foncmp[j - 1] != 'E')
								&& (foncmp[j - 1] != 'I')
								&& (foncmp[j - 1] != 'O')
								&& (foncmp[j - 1] != 'U')) {
							j++;
						}// if
						else {
							fonaux[k] = foncmp[j];
							j++;
							k++;
						}// else
					else {
						fonaux[k] = foncmp[j];
						j++;
						k++;
					}// else
				}// while

				auxStr = auxStr.copyValueOf(fonaux).trim();
				foncmp = auxStr.toCharArray();
				for (j = 0; j < 256; j++)
					fonaux[j] = ' ';

				for (j = 0; j < auxStr.length(); j++) {
					// percorre a palavra, letra a letra

					if (foncmp[j] == 'V')
						// se a letra for "V", substitui por "F"
						fonaux[j] = 'F';
					else

					// se a letra for "X","Z" ou "K", substitui por "S"
					if ((foncmp[j] == 'X') || (foncmp[j] == 'Z')
							|| (foncmp[j] == 'K'))
						fonaux[j] = 'S';
					else

					// G -> D
					if (foncmp[j] == 'G')
						fonaux[j] = 'D';
					else
						fonaux[j] = foncmp[j];
				}// for

			}// if

			component.set(i, auxStr.copyValueOf(fonaux).trim());
			// a palavra eh recolocada, modificada, no vetor que contem o texto

		}// for

		for (i = 0; i < component.size(); i++) {
			// percorre o texto, palavra a palavra

			auxStr = component.get(i);

			// considera somente as primeiras 7 letras da palavra
			if (auxStr.length() > 7) {
				foncmp = auxStr.toCharArray();
				for (j = 7; j < auxStr.length(); j++)
					foncmp[j] = ' ';
				auxStr = auxStr.valueOf(foncmp).trim();
				component.set(i, auxStr);
			}// if

			// componentes do codigo sao calculados
			fon11 += randomic(tabEbc(auxStr));
			fon12 += randomic(tabNor(tabEbc(auxStr)));
		}// for

		component = StringUtils.strToVector(str);
		for (i = 0; i < component.size(); i++) {
			// percorre o texto, palavra a palavra

			fon09 += randomic(component.get(i));
			// componente do codigo eh calculado
		}// for

		// monta o codigo identificador do texto
		reg09 = fonreg(fon09);
		reg11 = fonreg(fon11);
		reg12 = fonreg(fon12);
		fonrnd[0] = reg12[2];
		fonrnd[1] = reg11[1];
		fonrnd[2] = reg11[2];
		if ((fonrnd[0] == 0) && (fonrnd[1] == 0) && (fonrnd[2] == 0)) {
			fonrnd[0] = reg12[1];
			fonrnd[1] = reg11[0];
			fonrnd[2] = reg11[3];
		}// if
		fonrnd[3] = reg09[1];
		fonrnd[4] = reg09[2];
		if ((fonrnd[3] == 0) && (fonrnd[4] == 0)) {
			fonrnd[3] = reg09[0];
			fonrnd[4] = reg09[3];
			if ((fonrnd[3] == 0) && (fonrnd[4] == 0)) {
				fon09 = fon11 + fon12;
				reg09 = fonreg(fon09);
				fonrnd[3] = reg09[1];
				fonrnd[4] = reg09[2];
			}// if
		}// if

		j = 0;
		for (i = 0; i < 5; i++) {
			auxStr = auxStr.valueOf(fonrnd[i]);
			w0 = (int) fonrnd[i];
			w0 = w0 >>> 4;
			work[0] = (char) w0;
			if (work[0] <= '\u0009')
				finalRand[j] = (char) ((int) work[0] + 48);
			else
				finalRand[j] = (char) ((int) work[0] - 10 + 97); /*
																	 * work[0] -
																	 * 0a + a
																	 */
			w1 = (int) fonrnd[i];
			w1 = w1 << 28;
			w0 = w1 >>> 28;
			work[0] = (char) w0;
			if (work[0] <= '\u0009')
				finalRand[j + 1] = (char) ((int) work[0] + 48);
			else
				finalRand[j + 1] = (char) ((int) work[0] - 10 + 97);
			j += 2;
		}

		return rand.valueOf(finalRand);
	}// randomize

	private static long randomic(String str) {

		int i;
		long i01, i02;
		char[] fonaux = new char[256];

		i01 = 0;

		if (str.length() > 1) {
			fonaux = str.toCharArray();
			i01 = fonaux[0] * 0x0100 + fonaux[1];
			for (i = 1; i < 256; i++) {
				if (i == (str.length() - 1))
					break;
				i02 = (fonaux[i] * 0x0100) + fonaux[i + 1];
				i01 *= i02;
				i01 = i01 >>> 8;
			}// for
		}// if
		else {
			fonaux = str.toCharArray();
			i01 = fonaux[0] * 0x0100;
			i01 = i01 >>> 8;
		}// else

		return i01;
	}// randomic

	private static char[] fonreg(long i03) {

		long i01, i02;
		char[] fonaux = new char[4];

		i02 = i03;
		fonaux[3] = (char) (i02 % 0x0100);
		i01 = (i02 - fonaux[3]) / 0x0100;
		fonaux[2] = (char) (i01 % 0x0100);
		i02 = (i01 - fonaux[2]) / 0x0100;
		fonaux[1] = (char) (i02 % 0x0100);
		i01 = (i02 - fonaux[1]) / 0x0100;
		fonaux[0] = (char) (i01 % 0x0100);

		return fonaux;
	}
    private static String tabEbc (String str) {

        char[] fonaux = new char [256];
        int i;

        fonaux = str.toCharArray();
        for (i = 0; i < str.length(); i++)
          switch (fonaux[i]) {
            case 'A': fonaux[i] = '\u00c1';
                    break;
            case 'B': fonaux[i] = '\u00c2';
                    break;
            case 'C': fonaux[i] = '\u00c3';
                    break;
            case 'D': fonaux[i] = '\u00c4';
                    break;
            case 'E': fonaux[i] = '\u00c5';
                    break;
            case 'F': fonaux[i] = '\u00c6';
                    break;
            case 'G': fonaux[i] = '\u00c7';
                    break;
            case 'H': fonaux[i] = '\u00c8';
                    break;
            case 'I': fonaux[i] = '\u00c9';
                    break;
            case 'J': fonaux[i] = '\u00d1';
                    break;
            case 'K': fonaux[i] = '\u00d2';
                    break;
            case 'L': fonaux[i] = '\u00d3';
                    break;
            case 'M': fonaux[i] = '\u00d4';
                    break;
            case 'N': fonaux[i] = '\u00d5';
                    break;
            case 'O': fonaux[i] = '\u00d6';
                    break;
            case 'P': fonaux[i] = '\u00d7';
                    break;
            case 'Q': fonaux[i] = '\u00d8';
                    break;
            case 'R': fonaux[i] = '\u00d9';
                    break;
            case 'S': fonaux[i] = '\u00e2';
                    break;
            case 'T': fonaux[i] = '\u00e3';
                    break;
            case 'U': fonaux[i] = '\u00e4';
                    break;
            case 'V': fonaux[i] = '\u00e5';
                    break;
            case 'W': fonaux[i] = '\u00e6';
                    break;
            case 'X': fonaux[i] = '\u00e7';
                    break;
            case 'Y': fonaux[i] = '\u00e8';
                    break;
            case 'Z': fonaux[i] = '\u00e9';
                    break;
            case '0': fonaux[i] = '\u00f0';
                    break;
            case '1': fonaux[i] = '\u00f1';
                    break;
            case '2': fonaux[i] = '\u00f2';
                    break;
            case '3': fonaux[i] = '\u00f3';
                    break;
            case '4': fonaux[i] = '\u00f4';
                    break;
            case '5': fonaux[i] = '\u00f5';
                    break;
            case '6': fonaux[i] = '\u00f6';
                    break;
            case '7': fonaux[i] = '\u00f7';
                    break;
            case '8': fonaux[i] = '\u00f8';
                    break;
            case '9': fonaux[i] = '\u00f9';
                    break;
            default:  fonaux[i] = '\u0040';
        }
        str = str.copyValueOf(fonaux);

        return str;
      }//tabEbc

	private static String tabNor (String str) {
	
	     char[] fonaux = new char [256];
	     int i;
	
	     fonaux = str.toCharArray();
	     for (i = 0; i < str.length(); i++)
	       switch (fonaux[i]) {
	         case '\u00c1': fonaux[i] = '\u0013';
	                     break;
	         case '\u00c2': fonaux[i] = '\u0016';
	                     break;
	         case '\u00c3': fonaux[i] = '\u0019';
	                     break;
	         case '\u00c4': fonaux[i] = '\u001c';
	                     break;
	         case '\u00c5': fonaux[i] = '\u0011';
	                     break;
	         case '\u00c6': fonaux[i] = '\u0014';
	                     break;
	         case '\u00c7': fonaux[i] = '\u0017';
	                     break;
	         case '\u00c8': fonaux[i] = '\u001a';
	                     break;
	         case '\u00c9': fonaux[i] = '\u001d';
	                     break;
	         case '\u00d1': fonaux[i] = '\u0033';
	                     break;
	         case '\u00d2': fonaux[i] = '\u0036';
	                     break;
	         case '\u00d3': fonaux[i] = '\u0039';
	                     break;
	         case '\u00d4': fonaux[i] = '\u003c';
	                     break;
	         case '\u00d5': fonaux[i] = '\u0031';
	                     break;
	         case '\u00d6': fonaux[i] = '\u0034';
	                     break;
	         case '\u00d7': fonaux[i] = '\u0037';
	                     break;
	         case '\u00d8': fonaux[i] = '\u003a';
	                     break;
	         case '\u00d9': fonaux[i] = '\u003d';
	                     break;
	         case '\u00e2': fonaux[i] = '\u0053';
	                     break;
	         case '\u00e3': fonaux[i] = '\u0056';
	                     break;
	         case '\u00e4': fonaux[i] = '\u0059';
	                     break;
	         case '\u00e5': fonaux[i] = '\u005c\';
	                     break;
	         case '\u00e6': fonaux[i] = '\u0054';
	                     break;
	         case '\u00e7': fonaux[i] = '\u0057';
	                     break;
	         case '\u00e8': fonaux[i] = '\u005a';
	                     break;
	         case '\u00e9': fonaux[i] = '\u005d';
	                     break;
	         case '\u00f0': fonaux[i] = '\u0070';
	                     break;
	         case '\u00f1': fonaux[i] = '\u0071';
	                     break;
	         case '\u00f2': fonaux[i] = '\u0072';
	                     break;
	         case '\u00f3': fonaux[i] = '\u0073';
	                     break;
	         case '\u00f4': fonaux[i] = '\u0074';
	                     break;
	         case '\u00f5': fonaux[i] = '\u0075';
	                     break;
	         case '\u00f6': fonaux[i] = '\u0076';
	                     break;
	         case '\u00f7': fonaux[i] = '\u0077';
	                     break;
	         case '\u00f8': fonaux[i] = '\u0078';
	                     break;
	         case '\u00f9': fonaux[i] = '\u0079';
	                     break;
	         default      : fonaux[i] = '\u0040';
	       }//switch
	
	       str = str.copyValueOf(fonaux);
	
	       return str;
	   }//tabNor

}
