package br.com.p2d.phonetizer;

import java.util.ArrayList;
import java.util.Set;

public class Remover {
    public static String remove(String str, Set<String> toRemove) {
        ArrayList<String> palavra = StringUtils.strToVector(str);
        for(int i=0; i<palavra.size(); ){
        	if(toRemove.contains(palavra.get(i))){
        		palavra.remove(i);
        	} else {
        		i++;
        	}
        }
        return StringUtils.vectorToStr(palavra);
    }

}
