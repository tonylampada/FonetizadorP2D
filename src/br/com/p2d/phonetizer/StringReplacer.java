package br.com.p2d.phonetizer;

import java.util.ArrayList;
import java.util.Map;

public class StringReplacer {

	public static String replaceStrings(String str, Map<String, String> map){
		ArrayList<String> palavras = StringUtils.strToVector(str);
		for(int i=0; i<palavras.size(); i++){
			String rep = map.get(palavras.get(i).toString());
			if(rep != null){
				palavras.set(i, rep);
			}
		}
		return StringUtils.vectorToStr(palavras);
	}
}
