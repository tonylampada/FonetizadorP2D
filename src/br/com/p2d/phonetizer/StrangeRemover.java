package br.com.p2d.phonetizer;

public class StrangeRemover {
	public static String remove(String str) {
		// Elimina os caracteres que NAO sejam alfanumericos ou espacos
		char[] foncmp = new char[256];
		// matriz de caracteres que armazena o texto original
		char[] fonaux = new char[256];
		// matriz de caracteres que armazena o texto modificado
		int i, j, // contadores
		first; // indica se exitem espacos em branco antes do primeiro
		// caracter: se 1 -> existem, se 0 -> nao existem
		j = 0;
		first = 1;
		fonaux = str.toCharArray();
		// matriz de caracteres recebe o texto
		for (i = 0; i < 256; i++) {
			foncmp[i] = ' ';
		}
		// branqueia a matriz de caracteres
		for (i = 0; i < str.length(); i++) {
			// percorre o texto, caracter a caracter
			// elimina os caracteres que nao forem alfanumericos ou espacos
			if (((fonaux[i] >= 'A') &&
			(fonaux[i] <= 'Z')) ||
			((fonaux[i] >= 'a') &&
			(fonaux[i] <= 'z')) ||
			((fonaux[i] >= '0') &&
			(fonaux[i] <= '9')) ||
			(fonaux[i] == '&') ||
			(fonaux[i] == '_') ||
			((fonaux[i] == ' ') && (first == 0))) {
				foncmp[j] = fonaux[i];
				j++;
				first = 0;
			}// if
		}// for
		str = String.valueOf(foncmp);
		// string recebe o texto da matriz de caracteres
		return str.trim();
	}

}
