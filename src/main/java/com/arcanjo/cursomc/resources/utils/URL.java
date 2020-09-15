package com.arcanjo.cursomc.resources.utils;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class URL {
	
	public static List<Integer> decodeIntList(String categorias) {
		
		return Arrays.asList(categorias.split(",")).stream().map(x -> Integer.parseInt(x)).collect(Collectors.toList());
	}
	
	public static String decodeParam(String param) {
		try {
			return URLDecoder.decode(param, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			return "";
		}
	}
}
