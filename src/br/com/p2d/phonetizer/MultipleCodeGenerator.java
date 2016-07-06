package br.com.p2d.phonetizer;

import java.util.ArrayList;

public class MultipleCodeGenerator {

	private static int[] powersOfTwo = {1,2,4,8,16,32};
	private static boolean[][][] allPermutations = {
		permutations(1),
		permutations(2),
		permutations(3),
		permutations(4),
		permutations(5)
	};
	
	private static boolean[][] permutations(int size){
		if(size > 5){
			throw new RuntimeException("Invalid argument. size must be <= 5");
		}
		boolean[][] result = new boolean[powersOfTwo[size]-1][size];
		for(int i=0; i<result.length; i++){
			for(int j=0; j<size; j++){
				result[i][j] = ((i + 1) & powersOfTwo[j]) > 0; 
			}
		}
		return result;
	}
	
	public static ArrayList<String> generateCodes(String str){
		ArrayList<String> result = new ArrayList<String>();
		ArrayList<String> palavras = StringUtils.strToVector(str);
		if(palavras.size() > 5){
			result.add(CodeGenerator.randomize(str));//adiciona o nome completo, mesmo grandao
//			System.out.println(str);
		}
		while(palavras.size() > 5){
			String palavra = palavras.remove(palavras.size()/2);//remove um nome do meio
			result.add(CodeGenerator.randomize(palavra)); //adiciona o codigo da palavra removida
//			System.out.println(palavra);
		}
		int size = palavras.size();
		boolean[][] permutations = allPermutations[size-1];
		for(int i=0; i<permutations.length; i++){
			ArrayList<String> subPalavras = new ArrayList<String>();
			for(int j=0; j<size; j++){
				if(permutations[i][j]){
					subPalavras.add(palavras.get(j));
				}
			}
			String subNome = StringUtils.vectorToStr(subPalavras);
//			System.out.println(subNome);
			result.add(CodeGenerator.randomize(subNome));
		}
		return result;
	}
	
	public static void main(String[] args) {
		String s = "Lorem Ipsum Dolor Sit Amet Blabla";
		ArrayList<String> codes = generateCodes(s);
		System.out.println(codes);
		System.out.println(codes.size());
	}
}
