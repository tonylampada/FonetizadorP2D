package br.com.p2d.phonetizer;

import java.util.Map;

public class CharReplacer {

	public static String replaceChars(String str, Map<Character, Character> map){
        // Substitui os caracteres acentuados por caracteres nao acentuados
        char aux[] = str.toCharArray();
        // matriz de caracteres onde o texto eh manipulado
        int i; // contador
        // matriz recebe o texto
        for (i = 0; i < aux.length; i++) { //percorre o texto, caracter a caracter
        	Character rep = map.get(aux[i]);
        	if(rep != null){
        		aux[i] = rep;
        	}
        }
        str = String.copyValueOf(aux).trim();
        // o string recebe o texto sem acentuacao
        return str;
	}
}
