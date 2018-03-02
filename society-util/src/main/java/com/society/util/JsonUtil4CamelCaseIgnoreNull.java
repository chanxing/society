package com.society.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.society.util.exception.JsonConvertRuntimeException;

public final class JsonUtil4CamelCaseIgnoreNull {
	private JsonUtil4CamelCaseIgnoreNull() {
	}

	private static final ObjectMapper OM = new ObjectMapper();
	private static final TypeFactory TF = OM.getTypeFactory();

	static {
		OM.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		OM.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
		OM.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
		OM.configure(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS, false);
		OM.setSerializationInclusion(Include.NON_NULL); // ignore null
	}

	public static final JavaType Object = TF.constructParametricType(HashMap.class, String.class, Object.class);

	public static final JavaType ObjectList = TF.constructParametricType(List.class, Object);

	public static ObjectMapper getObjectMapper() {
		return OM;
	}

	public static final Map<String, Object> toMap(String json) {
		try {
			return OM.readValue(json, Object);
		} catch (IOException e) {
			throw new JsonConvertRuntimeException(e);
		}
	}

	public static final List<Map<String, Object>> toMapList(String json) {
		try {
			return OM.readValue(json, ObjectList);
		} catch (IOException e) {
			throw new JsonConvertRuntimeException(e);
		}
	}

	public static final <T> T toObject(String json, Class<T> type) {
		try {
			return OM.readValue(json, type);
		} catch (IOException e) {
			throw new JsonConvertRuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public static final <T> T toObject(String json, Class<?> parametrized, Class<?>... parameterClasses) {
		try {
			if (isNotEmpty(parameterClasses)) {
				int last = parameterClasses.length - 1;
				JavaType[] types = new JavaType[] { TF.constructType(parameterClasses[last]) };
				for (int i = last - 1; i >= 0; i--) {
					types[0] = TF.constructSimpleType(parameterClasses[i], types);
				}
				return OM.readValue(json, TF.constructSimpleType(parametrized, types));
			} else {
				return (T) OM.readValue(json, parametrized);
			}
		} catch (IOException e) {
			throw new JsonConvertRuntimeException(e);
		}
	}

	public static final Map<String, Object> toMap(byte[] json) {
		try {
			return OM.readValue(json, Object);
		} catch (IOException e) {
			throw new JsonConvertRuntimeException(e);
		}
	}

	public static final List<Map<String, Object>> toMapList(byte[] json) {
		try {
			return OM.readValue(json, ObjectList);
		} catch (IOException e) {
			throw new JsonConvertRuntimeException(e);
		}
	}

	public static final <T> T toObject(byte[] json, Class<T> type) {
		try {
			return OM.readValue(json, type);
		} catch (IOException e) {
			throw new JsonConvertRuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public static final <T> T toObject(byte[] json, Class<?> parametrized, Class<?>... parameterClasses) {
		try {
			if (isNotEmpty(parameterClasses)) {
				int last = parameterClasses.length - 1;
				JavaType[] types = new JavaType[] { TF.constructType(parameterClasses[last]) };
				for (int i = last - 1; i >= 0; i--) {
					types[0] = TF.constructSimpleType(parameterClasses[i], types);
				}
				return OM.readValue(json, TF.constructSimpleType(parametrized, types));
			} else {
				return (T) OM.readValue(json, parametrized);
			}
		} catch (IOException e) {
			throw new JsonConvertRuntimeException(e);
		}
	}

	public static final Map<String, Object> toMap(Reader in) {
		try {
			return OM.readValue(in, Object);
		} catch (IOException e) {
			throw new JsonConvertRuntimeException(e);
		}
	}

	public static final List<Map<String, Object>> toMapList(Reader in) {
		try {
			return OM.readValue(in, ObjectList);
		} catch (IOException e) {
			throw new JsonConvertRuntimeException(e);
		}
	}

	public static final <T> T toObject(Reader in, Class<T> type) {
		try {
			return OM.readValue(in, type);
		} catch (IOException e) {
			throw new JsonConvertRuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public static final <T> T toObject(Reader in, Class<?> parametrized, Class<?>... parameterClasses) {
		try {
			if (isNotEmpty(parameterClasses)) {
				int last = parameterClasses.length - 1;
				JavaType[] types = new JavaType[] { TF.constructType(parameterClasses[last]) };
				for (int i = last - 1; i >= 0; i--) {
					types[0] = TF.constructSimpleType(parameterClasses[i], types);
				}
				return OM.readValue(in, TF.constructSimpleType(parametrized, types));
			} else {
				return (T) OM.readValue(in, parametrized);
			}
		} catch (IOException e) {
			throw new JsonConvertRuntimeException(e);
		}
	}

	public static final List<Map<String, Object>> toMapList(InputStream in) {
		try {
			return OM.readValue(in, ObjectList);
		} catch (IOException e) {
			throw new JsonConvertRuntimeException(e);
		}
	}

	public static final <T> T toObject(InputStream in, Class<T> type) {
		try {
			return OM.readValue(in, type);
		} catch (IOException e) {
			throw new JsonConvertRuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public static final <T> T toObject(InputStream in, Class<?> parametrized, Class<?>... parameterClasses) {
		try {
			if (isNotEmpty(parameterClasses)) {
				int last = parameterClasses.length - 1;
				JavaType[] types = new JavaType[] { TF.constructType(parameterClasses[last]) };
				for (int i = last - 1; i >= 0; i--) {
					types[0] = TF.constructSimpleType(parameterClasses[i], types);
				}
				return OM.readValue(in, TF.constructSimpleType(parametrized, types));
			} else {
				return (T) OM.readValue(in, parametrized);
			}
		} catch (IOException e) {
			throw new JsonConvertRuntimeException(e);
		}
	}

	public static final String toJson(Object value) {
		try {
			return OM.writeValueAsString(value);
		} catch (IOException e) {
			throw new JsonConvertRuntimeException(e);
		}
	}

	public static final byte[] toJsonByte(Object value) {
		try {
			return OM.writeValueAsBytes(value);
		} catch (IOException e) {
			throw new JsonConvertRuntimeException(e);
		}
	}

	public static final void writeValue(Writer out, Object value) {
		try {
			OM.writeValue(out, value);
		} catch (IOException e) {
			throw new JsonConvertRuntimeException(e);
		}
	}

	public static final void writeValue(OutputStream out, Object value) {
		try {
			OM.writeValue(out, value);
		} catch (IOException e) {
			throw new JsonConvertRuntimeException(e);
		}
	}

	public static <T> boolean isNotEmpty(Class<?>... parameterClasses) {
		return parameterClasses != null && parameterClasses.length > 0;
	}

}
