package com.society.util;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.society.util.exception.JsonConvertRuntimeException;

@SuppressWarnings("deprecation")
public class JsonUtil4LowerCase {

	private static final ObjectMapper mapper = new ObjectMapper();
	static {
		mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
		mapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
		mapper.configure(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS, false);
	}

	/**
	 * 将对象序列化成json对象
	 * 
	 * @param obj
	 * @return
	 */
	public static String toJson(Object obj) {

		String json = "";
		try {
			json = mapper.writeValueAsString(obj);
		} catch (Exception e) {
			throw new JsonConvertRuntimeException("obj[" + obj + "]转换出错 ", e);
		}
		return json;
	}

	/**
	 * 将json对象反序列化成对象
	 * 
	 * @param json
	 * @param cls
	 * @return
	 */
	public static <T> T toObject(String json, Class<T> cls) {

		if (EmptyUtil.isStringEmpty(json)) {
			return null;
		}
		T result = null;
		try {
			result = mapper.readValue(json, cls);
		} catch (Exception e) {
			throw new JsonConvertRuntimeException("toObject:json[" + json + "]转换出错 class[" + cls + "]", e);
		}
		return result;
	}

	public static <T> T toObject(InputStream in, Class<T> cls) {
		T result = null;
		try {
			if (in != null) {
				result = mapper.readValue(in, cls);
			}
		} catch (Exception e) {
			throw new JsonConvertRuntimeException("转换出错 class[" + cls + "]", e);
		}
		return result;
	}

	public static void write(OutputStream out, Object value) {
		try {
			if (out != null && value != null) {
				mapper.writeValue(out, value);
			}
		} catch (Exception e) {
			throw new JsonConvertRuntimeException("转换出错 value[" + value + "]", e);
		}
	}

	public static <T> T toObject(String json, TypeReference<T> typeReference) {

		if (EmptyUtil.isStringEmpty(json)) {
			return null;
		}
		T result = null;
		try {
			result = mapper.readValue(json, typeReference);
		} catch (Exception e) {
			e.printStackTrace();
			throw new JsonConvertRuntimeException("toObject:json[" + json + "]转换出错 typeReference[" + typeReference + "]", e);
		}
		return result;
	}

	/**
	 * 将json对象反序列化成对象列表
	 * 
	 * @param json
	 * @param cls
	 * @return
	 */
	public static <T> List<T> toListObject(String json, Class<T> cls) {

		if (EmptyUtil.isStringEmpty(json)) {
			return null;
		}
		List<T> result = null;

		try {
			result = mapper.readValue(json, TypeFactory.defaultInstance().constructCollectionType(List.class, cls));
		} catch (Exception e) {
			throw new JsonConvertRuntimeException("toListObject:json[" + json + "]转换出错 class[" + cls + "]", e);
		}
		return result;
	}

	/**
	 * 将json对象反序列化成map
	 * 
	 * @param json
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Map toMap(String json) {

		if (EmptyUtil.isStringEmpty(json)) {
			return null;
		}
		Map result = null;

		try {
			result = mapper.readValue(json, Map.class);
		} catch (Exception e) {
			throw new JsonConvertRuntimeException("toMap:json[" + json + "]转换出错", e);
		}
		return result;
	}

	/**
	 * 将json对象反序列化成map
	 * 
	 * @param <KEY>
	 * @param <VAL>
	 * 
	 * @param json
	 * @return
	 */
	public static <KEY, VAL> Map<KEY, VAL> toMapObject(String json, Class<KEY> key, Class<VAL> value) {

		if (EmptyUtil.isStringEmpty(json)) {
			return null;
		}
		Map<KEY, VAL> result = null;

		try {
			result = mapper.readValue(json, TypeFactory.defaultInstance().constructMapType(Map.class, key, value));
		} catch (Exception e) {
			throw new JsonConvertRuntimeException("toMap:json[" + json + "]转换出错", e);
		}
		return result;
	}

	/**
	 * 将json对象反序列化成对象map数组
	 * 
	 * @param json
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object>[] toArray(String json) {

		Map<String, Object>[] result = null;

		try {
			result = mapper.readValue(json, Map[].class);
		} catch (Exception e) {
			throw new JsonConvertRuntimeException("toArray:json[" + json + "]转换出错", e);
		}
		return result;
	}

}
