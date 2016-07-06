/***
 * Copyright (c) 2009 Caelum - www.caelum.com.br/opensource
 * All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 * 
 * 	http://www.apache.org/licenses/LICENSE-2.0 
 * 
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License. 
 */
package br.com.fonetizador.web;

import java.io.UnsupportedEncodingException;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.p2d.phonetizer.FonetizacaoBR;

@Resource
public class IndexController {

	Result result;
	
	
	
	public IndexController(Result result) {
		super();
		this.result = result;
	}



	@Path("/")
	public void index() {
		
	}
	

	
	@Get
	@Path("/fonetizar/{str}")
	public void fonetizar(String str) {
		String str2 = str;
		try {
			str2 = java.net.URLDecoder.decode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 
		
		result.include("strFonetizada", FonetizacaoBR.fonetizar(str2));
		result.include("strOriginal", str2);
	}


	@Get
	@Path("/codificar/{str}")
	public void codificar(String str) {
		String str2 = str;
		try {
			str2 = java.net.URLDecoder.decode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 
		result.include("strCodificada", FonetizacaoBR.makePhoneticCode(str2));
		result.include("strOriginal", str2);

	}
	
	
	@Get
	@Path("/contaLetras/{str}")
	public void contaLetras(String str) {

		result.include("strOriginal", str);
		result.include("strQtdLetras", str.toCharArray().length);
		result.include("arrLetras", "{" + str.toCharArray().toString() + "}");

	}

}
