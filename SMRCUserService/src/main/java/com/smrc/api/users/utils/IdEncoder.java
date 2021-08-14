package com.smrc.api.users.utils;

import java.nio.charset.Charset;

import org.apache.commons.codec.binary.Base64;
import org.springframework.util.StringUtils;

public class IdEncoder {
	
	public static String decodeId(String encodedId) {
		Base64 base = new Base64();
		String encToken = RequestDataHolder.getEncodingSecurityKey();
		String mergedId = new String(base.decode(encodedId.getBytes(Charset.forName("UTF-8"))));
		String rawId = mergedId.substring(0, mergedId.lastIndexOf(encToken));
		return rawId;
	}

	public static String encodeId(Long rawId) {
		Base64 base = new Base64();
		String encToken = RequestDataHolder.getEncodingSecurityKey();
		String mergedPwd = rawId + encToken;
		return new String(base.encode(mergedPwd.getBytes(Charset.forName("UTF-8"))));

	}
	
	public static String encodeId(String rawId, String encToken) {
		Base64 base = new Base64();
		String mergedPwd = rawId + encToken;
		return new String(base.encode(mergedPwd.getBytes(Charset.forName("UTF-8"))));

	}
	
	public static String decodeId(String encodedId, String encToken) {
		Base64 base = new Base64();
		String mergedId = new String(base.decode(encodedId.getBytes(Charset.forName("UTF-8"))));
		String rawId = mergedId.substring(0, mergedId.lastIndexOf(encToken));
		return rawId;
	}
	
	public static String decodeIds(String encodedIds, String delimeter) {
		
		if(!StringUtils.isEmpty(encodedIds) && !encodedIds.contains(StringConstant.COMMA)
				&& !encodedIds.equals(StringConstant.ALL)){
			return decodeId(encodedIds);
		}else 	if(!StringUtils.isEmpty(encodedIds) && encodedIds.equals(StringConstant.ALL)){
			return encodedIds;
		}else if(StringUtils.isEmpty(encodedIds)) {
			return null;
		}
		
		Base64 base = new Base64();
		String encToken = RequestDataHolder.getEncodingSecurityKey();
		StringBuilder decodedIds = new StringBuilder();
		String mergedId = null;
		for(String encodedId : encodedIds.split(delimeter)) {
			mergedId = new String(base.decode(encodedId.getBytes(Charset.forName("UTF-8"))));
			if(decodedIds.length() > 0) {
				decodedIds.append(",");
			}
			decodedIds.append( mergedId.substring(0, mergedId.lastIndexOf(encToken)));
		}
		return decodedIds.toString();
	}
	
	public static String encodeIds(String rawIds, String delimeter) {
		
		if(!StringUtils.isEmpty(rawIds) && !rawIds.contains(StringConstant.COMMA)){
			return encodeId(Long.valueOf(rawIds.trim()));
		}if(StringUtils.isEmpty(rawIds)) {
			return null;
		}
		
		Base64 base = new Base64();
		String encToken = RequestDataHolder.getEncodingSecurityKey();
		StringBuilder encodedIds = new StringBuilder();
		String mergedPwd = null;
		for(String rawId : rawIds.split(delimeter)) {
			mergedPwd = rawId + encToken;
			if(encodedIds.length() > 0) {
				encodedIds.append(",");
			}
			encodedIds.append(new String(base.encode(mergedPwd.getBytes(Charset.forName("UTF-8")))));
		}
		return encodedIds.toString();
	}

}
