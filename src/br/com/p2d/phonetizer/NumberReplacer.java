package br.com.p2d.phonetizer;

import java.util.ArrayList;

public class NumberReplacer {

	public static String replaceNumbers(String str) {
		int soma = 0;
		ArrayList<String> palavras = StringUtils.strToVector(str);
		for (int i = 0; i < palavras.size(); i++) {
			// percorre o texto, palavra a palavra
			// elimina a palavra do texto se ela for "e"
			if ("E".equals(palavras.get(i))) {
				palavras.remove(i);
				i--;
			}// if

			else {
				if ("MIL".equals(palavras.get(i))) {
					// se a palavra for "mil"
					// multiplica o valor acumulado da soma por mil e remove a
					// palavra do texto
					if (soma == 0)
						soma = 1000;
					else {
						soma = soma * 1000;
						palavras.remove(i);
						i--;
					}// else
				}// if

				else {
					Integer valor = null;
					try {
						valor = Integer.parseInt(palavras.get(i).toString());
					} catch (NumberFormatException e) {}
					if (valor != null) {
						if (soma != 0) {
							palavras.remove(i - 1);
							i--;
						}// if
						soma += valor;
						// o valor palavra eh somado ao valor acumulado

					}// if
					else {
						if (soma != 0) {
							// se a palavra nao for um numero e o valor
							// acumulado for
							// diferente de zero
							palavras.set(i-1, ""+soma);
							// o valor descrito eh substituido por sua forma
							// numerica

						}// if
						soma = 0;
					}// else
				}// else
			}// else
		}// for
		if(soma != 0){
			palavras.set(palavras.size()-1,""+soma);
		}
		
		return StringUtils.vectorToStr(palavras);
	}
}
