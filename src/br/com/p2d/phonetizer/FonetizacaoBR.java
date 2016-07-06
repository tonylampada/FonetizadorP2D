/*                                      
 * Copyright (c) 1999-2009 Touch Tecnologia e Informatica Ltda.
 * Gomes de Carvalho, 1666, 3o. Andar, Vila Olimpia, Sao Paulo, SP, Brasil.
 * Todos os direitos reservados.
 *                              
 * Este software e confidencial e de propriedade da Touch Tecnologia e 
 * Informatica Ltda. (Informacao Confidencial). As informacoes contidas neste
 * arquivo nao podem ser publicadas, e seu uso esta limitado de acordo com os 
 * termos do contrato de licenca.
 */

package br.com.p2d.phonetizer;


import java.util.ArrayList;
import java.util.Map;
import java.util.Set;


/**
 * Algoritmo de fonetização em português
 * 
 * @author vanlazar
 */
public class FonetizacaoBR {

	private static Set<String> prepositions = StringUtils.toSet(new String[]{ "DEL", "DA", "DE", "DI", "DO", "DU", "DAS", "DOS", "DEU", "DER", "E", "LA", "LE", "LES", "LOS", "VAN", "VON", "EL" });
	private static Set<String> titles = StringUtils.toSet(new String[]{"BEL.","CEL.","CEL","ENG","ENG.","MAJ.","MAJ","PROF","MIN.","PROF.","TEN","TEN.","CAP","CAP.","DR","DR.","DRA.","DRA","GAL.","GEN","GEN.","MED","MED.","PE.","SARG.","SARG","SGT","SGT.","VVA.","VVA","SR","SR.","SRA","SRA.","MSC","MSC.","CB","CB.","SD","SD.","SEN","SEN.","BACHAREL","CORONEL","ENGENHEIRO","PROFESSOR","MINISTRO","TENENTE","DOUTOR","DOUTORA","GENERAL","MEDICO","PADRE","SARGENTO","CABO","SOLDADO","DEP.","DEP"});
	private static Map<Character, Character> accentuationReplacements = StringUtils.toMap(new Character[][]{{'É','E'},{'Ê','E'},{'Ë','E'},{'Á','A'},{'À','A'},{'Â','A'},{'Ã','A'},{'Ä','A'},{'Ç','C'},{'Í','I'},{'Ó','O'},{'Õ','O'},{'Ô','O'},{'Ö','O'},{'Ú','U'},{'Ü','U'},{'Ñ','N'}});
	private static Map<String, String> numberReplacements = StringUtils.toMap(new String[][]{{"CATORZE","0014"},{"CEM","0100"},{"CENTO","0100"},{"CINCO","0005"},{"CINCOENTA","0050"},{"CINQUENTA","0050"},{"DEZ","0010"},{"DEZENOVE","0019"},{"DEZESSEIS","0016"},{"DEZESSETE","0017"},{"DEZOITO","0018"},{"DOIS","0002"},{"DOZE","0012"},{"DUZENTOS","0200"},{"HUM","0001"},{"NOVE","0009"},{"NOVECENTOS","0900"},{"NOVENTA","0090"},{"OITENTA","0080"},{"OITO","0008"},{"OITOCENTOS","0800"},{"ONZE","0011"},{"QUARENTA","0040"},{"QUATORZE","0014"},{"QUATRO","0004"},{"QUATROCENTOS","0400"},{"QUINHENTOS","0500"},{"QUINZE","0015"},{"SEICENTOS","0600"},{"SEIS","0006"},{"SEISCENTOS","0600"},{"SEISENTOS","0600"},{"SESSENTA","0060"},{"SETE","0007"},{"SETECENTOS","0700"},{"SETENTA","0070"},{"TREIS","0003"},{"TRES","0003"},{"TRESENTOS","0300"},{"TREZ","0003"},{"TREZE","0013"},{"TREZENTOS","0300"},{"TRINTA","0030"},{"UM","0001"},{"VINTE","0020"},{"ZERO","0000"},{"I","0001"},{"II","0002"},{"III","0003"},{"IV","0004"},{"IX","0009"},{"V","0005"},{"VI","0006"},{"VII","0007"},{"VIII","0008"},{"X","0010"},{"XI","0011"},{"XII","0012"},{"XIII","0013"},{"XIV","0014"},{"XIX","0019"},{"XV","0015"},{"XVI","0016"},{"XVII","0017"},{"XVIII","0018"},{"XX","0020"},{"XXI","0021"},{"XXII","0022"},{"XXIII","0023"},{"XXIV","0024"},{"XXIX","0029"},{"XXV","0025"},{"XXVI","0026"},{"XXVII","0027"},{"XXVIII","0028"},{"XXX","0030"},{"XXXI","0031"}});
	private static Map<String, String> letterReplacements = StringUtils.toMap(new String[][]{{"AGA","H"},{"BE","B"},{"CA","K"},{"CE","C"},{"DABLIU","W"},{"EFE","F"},{"ELE","L"},{"EME","M"},{"ENE","N"},{"ERRE","R"},{"ESSE","S"},{"GE","G"},{"IPSILOM","Y"},{"IPSILON","Y"},{"JOTA","J"},{"PE","P"},{"QUE","Q"},{"TE","T"},{"VE","V"},{"XIS","X"},{"ZE","Z"}});
	private static Map<String, String> nameReplacements = StringUtils.toMap(new String[][]{{"ABRA","ABRAU"},{"ADRIANI","ADRIANA"},{"AIAKI","AIAXI"},{"ANI","AGINI"},{"AXILI","AKILI"},{"BAPITISTA","BATISTA"},{"BRIKUPIFI","BRIKUFI"},{"BRITAUPITI","BRITAUFI"},{"BRITAUTI","BRITAUFI"},{"BRITIKUFI","BRIKUFI"},{"BRITIKUPIFI","BRIKUFI"},{"DABADIA","BADIA"},{"DABUITI","BUITI"},{"DAGRANGIA","GRANGIA"},{"DAKANPURA","KANPURA"},{"DALA","LA"},{"DAMARIU","MARIU"},{"DANUVA","NUVA"},{"DARI","RI"},{"DARUI","RUI"},{"DATIRA","TIRA"},{"DAVIDI","DAVI"},{"DIBARBA","BARBA"},{"DIBASTIANI","BASTIANI"},{"DIBIAZI","BIAZI"},{"DIBILA","BILA"},{"DIBITIU","BITIU"},{"DIBUNA","BUNA"},{"DIBURTULI","BURTULI"},{"DIFRIN","FRIN"},{"DIFRITA","FRITA"},{"DIGASPIRI","GASPIRI"},{"DIGRIGURIU","GRIGURIU"},{"DIKARLI","KARLI"},{"DIKU","KU"},{"DILUKA","LUKA"},{"DILURDI","LURDI"},{"DIMARIA","MARIA"},{"DIMARIU","MARIU"},{"DIMARKI","MARKI"},{"DIMARKU","MARKU"},{"DIMARTINI","MARTINI"},{"DIMILU","MILU"},{"DIMIRANDA","MIRANDA"},{"DIMURA","MURA"},{"DINARDI","NARDI"},{"DINUNI","NUNI"},{"DIPAIVA","PAIVA"},{"DIPARI","PARI"},{"DIPIRI","PIRI"},{"DIRIN","RIN"},{"DIRIU","RIU"},{"DIRUSI","RUSI"},{"DISIZARI","SIZARI"},{"DISTIFANI","ISTIFANI"},{"DITUFU","TUFU"},{"DITUNI","TUNI"},{"DUVALI","UALI"},{"FABIANI","FABIANA"},{"GABRILI","GABRILA"},{"GIAKIKI","GIAKI"},{"GIAKUBI","GIAKU"},{"GIUAXIN","GIUAKIN"},{"GUAKU","GUASU"},{"ILIANI","ILIANA"},{"INISI","NS"},{"ISXAIFIR","XIFIR"},{"ISXIFIR","XIFIR"},{"ISXINATU","ISKINATU"},{"KRISTIANI","KRISTIANA"},{"KRUGIR","KRIGIR"},{"KRUIGIR","KRIGIR"},{"KUR","KURTI"},{"KUXIRANI","KUKRANI"},{"LUSIANI","LUSIANA"},{"MAGIDALINA","MADALINA"},{"MUILIR","MILIR"},{"MULIR","MILIR"},{"NIVITUN","NIUTUN"},{"PASTIR","PASTIUR"},{"RAXIU","RAKIU"},{"RUZANI","RUZANA"},{"TAXINARDI","TAKINARDI"},{"TATIANI","TATIANA"},{"TIKIRA","TIXIRA"},{"UI","UAI"},{"UXINTUN","UAXINTUN"},{"XIMITI","XIMIDITI"},{"XINAIDIR","XINIDIR"},{"XIRISTIA","KRISTIA"},{"XIRISTIANA","KRISTIANA"},{"XIRISTIANI","KRISTIANA"},{"XIRISTINA","KRISTINA"},{"XIRISTINI","KRISTINI"},{"XIRUIDIR","XIRUDIR"}});
	private static Map<String, String> synonimReplacements = StringUtils.toMap(new String[][]{{"ABATIDURU","AVI"},{"ANBULATURIU","USPITAU"},{"ARKIDIUSIZI","IGRIGIA"},{"ARMADUR","FUNIRARIA"},{"AVIARIU","AVI"},{"AVIKULA","AVI"},{"BATALIAU","MILITAR"},{"BIRKARIU","KRIKI"},{"BRIGADA","MILITAR"},{"BUTIKI","MUDA"},{"DIUSIZI","IGRIGIA"},{"DIZINSITIZADUR","DIDITIZAKAU"},{"FAKUDADI","UNIVERSIDADI"},{"FIANBRIRIA","AKUGI"},{"FIRTILIZANTI","ADUBU"},{"FUTUKUPIA","KUPIA"},{"GIARDINAGIN","FLURIKUTURA"},{"GINASTIKA","AKADIMIA"},{"GINAZIU","ISKULA"},{"GRANGIA","AVI"},{"IDITURA","LIVRARIA"},{"INFURMATIKA","KUNPUTADUR"},{"INGARAFADUR","BIBIDA"},{"INKURPURADUR","KUNSTRUKAU"},{"ISPRISU","TRANSPURTADUR"},{"ISTASIUNAMINTU","GARAGI"},{"ISTITIKA","AKADIMIA"},{"IZIRSITU","MILITAR"},{"KAFI","LANXUNITI"},{"KANTINA","RISTAURANTI"},{"KARGA","TRANSPURTADUR"},{"KARNI","AKUGI"},{"KLINIKA","USPITAU"},{"KUARTIU","MILITAR"},{"KULIGIU","ISKULA"},{"KUNFIKAU","MUDA"},{"KURSU","ISKULA"},{"KURTINA","DIKURAKAU"},{"LANXIRIA","LANXUNITI"},{"LUTIAMINTU","KUNSTRUKAU"},{"MAGAZINI","MUDA"},{"MARSINARIA","MUVIU"},{"MATIRNAU","KRIKI"},{"MITALURGIKA","AKU"},{"MITAU","AKU"},{"MUTIU","UTIU"},{"PAPILARIA","LIVRARIA"},{"PARUKIA","IGRIGIA"},{"PIZARIA","RISTAURANTI"},{"PULISIA","MILITAR"},{"PULISIAU","MILITAR"},{"RIFRIGIRANTI","BIBIDA"},{"RIGIMINTU","MILITAR"},{"RILUGIUARIA","GIUALIRIA"},{"SANTUARIU","IGRIGIA"},{"SIRIALISTA","SIRIAI"},{"SIRVIGIARIA","BIBIDA"},{"SUPLITIVU","ISKULA"},{"TAPIKARIA","DIKURAKAU"},{"TAPITI","DIKURAKAU"},{"TIPUGRAFIA","GRAFIKA"},{"UIAKAU","UNIBU"},{"UINIU","BIBIDA"},{"UISTUARIU","MUDA"},{"XAPA","AKU"},{"XIRUKUPIA","KUPIA"},{"PAU","PADARIA"},{"XURASKARIA","RISTAURANTI"}});
	
    /**
     * Fonetiza um termo parar a língua portuguesa
     * 
     * @param str
     * @since
     */
    public static String fonetizar(String str) {
        str = str.toUpperCase(); //converte tudo p/ maiuscula
        str = Remover.remove(str, prepositions); //remove preposições (da, de, do...)
        str = Remover.remove(str, titles); //remove os titulos (Sr, Sra, Dr...)
        str = CharReplacer.replaceChars(str, accentuationReplacements); //substitui acentos (Á->A, É->E)
        str = StrangeRemover.remove(str); //remove coisas não alfanuméricas
        str = StringReplacer.replaceStrings(str, letterReplacements); //substitui letras por extenso pela letra mesmo
        str = StringReplacer.replaceStrings(str, numberReplacements); //substitui numeros por extenso por digitos
        str = NumberReplacer.replaceNumbers(str); //soma sequencias de palavras feitas só de digitos
        str = PhonetizerBR.phonetize(str); //Fonetiza (a mágica fica aqui!)
        str = StringReplacer.replaceStrings(str, nameReplacements); //Substitui nomes parecidos
        str = StringReplacer.replaceStrings(str, synonimReplacements); //Substitui sinonimos foneticos
        return str;
    }
    
    public static String makePhoneticCode(String str){
    	str = fonetizar(str);
    	return CodeGenerator.randomize(str);
    }

    public static ArrayList<String> makeAllPhoneticCodes(String str){
    	str = fonetizar(str);
    	return MultipleCodeGenerator.generateCodes(str);
    }

}
