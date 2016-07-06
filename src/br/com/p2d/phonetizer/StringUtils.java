package br.com.p2d.phonetizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class StringUtils {

	public static ArrayList<String> strToVector(String str) {

		// armazena o texto de um string em um vetor onde

		// cada palavra do texto ocupa uma posicao do vetor

		str = str.trim();

		char[] fonaux = new char[256];

		// matriz de caracteres que armazena o texto completo

		char[] foncmp = new char[256];

		// matriz de caracteres que armazena cada palavra

		ArrayList<String> component = new ArrayList<String>();

		// vetor que armazena o texto

		String aux = new String();

		int i, j, // contadores

		pos, // posicao da matriz

		rep, // indica se eh espaco em branco repetido

		first; // indica se eh o primeiro caracter

		first = 1;

		pos = 0;

		rep = 0;

		fonaux = str.toCharArray();

		// matriz de caracteres recebe o texto

		for (j = 0; j < 256; j++) {
			foncmp[j] = ' ';
		}

		// branqueia matriz de caracteres

		for (i = 0; i < str.length(); i++) {

			// percorre o texto, caracter a caracter

			// se encontrar um espaco e nao for o primeiro caracter,

			// armazena a palavra no vetor

			if ((fonaux[i] == ' ') && (first != 1)) {

				if (rep == 0) {

					component.add(String.copyValueOf(foncmp).trim());

					pos = 0;

					rep = 1;

					for (j = 0; j < 256; j++) {
						foncmp[j] = ' ';
					}

				}// if

			}// if

			// forma a palavra, letra a letra, antes de envia-la a uma

			// posicao do vetor

			else {

				foncmp[pos] = fonaux[i];

				first = 0;

				pos++;

				rep = 0;

			}// else

		}// for

		if (foncmp[0] != ' ') {
			component.add(String.copyValueOf(foncmp).trim());
		}

		return component;

	}// strToVector

	public static String vectorToStr(ArrayList<String> vtr) {
		// converte o texto armazenado em um vetor para um unico string
		char[] foncmp = new char[256];
		// matriz de caracteres que armazena o texto completo
		char[] auxChar = new char[256];
		// matriz de caracteres que armazena cada palavra
		String auxStr = new String();
		String str = new String();
		int i, j, desloc;
		desloc = 0; // deslocamento dentro da matriz
		for (i = 0; i < 256; i++) {
			foncmp[i] = ' ';
		}
		// branqueia a matriz de caracteres
		for (j = 0; j < vtr.size(); j++) {
			// percorre o vetor, palavra a palavra
			auxStr = (vtr.get(j)).trim();
			// string recebe a palavra armazenada pelo vetor
			auxChar = auxStr.toCharArray();
			// matriz de caracteres recebe a palavra armazenada no vetor

			for (i = 0; i < auxStr.length(); i++) {
				foncmp[desloc + i] = auxChar[i];
			}
			desloc = desloc + auxStr.length() + 1;

		}// for
		str = String.valueOf(foncmp);
		// string recebe o texto completo
		return str.trim();
	}// vectorToStr

	public static Map toMap(Object[][] array) {
		Map m = new HashMap();
		for(Object[] pair : array){
			m.put(pair[0], pair[1]);
		}
		return m;
	}

	public static Set<String> toSet(String[] strings) {
		Set<String> result = new HashSet<String>();
		for(String s : strings){
			result.add(s);
		}
		return result;
	}

}
