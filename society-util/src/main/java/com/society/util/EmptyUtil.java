package com.society.util;

import java.util.Collection;
import java.util.Map;

public class EmptyUtil {
	public static <T> boolean isCollectionEmpty(Collection<T> collection) {
		return ((collection == null) || (collection.isEmpty()));
	}

	public static <T, S> boolean isMapEmpty(Map<T, S> map) {
		return ((map == null) || (map.isEmpty()));
	}

	public static <T> boolean isArrayEmpty(T[] array) {
		return ((array == null) || (array.length == 0));
	}

	public static <T> boolean isStringEmpty(String s) {
		return ((s == null) || (s.isEmpty()));
	}
}
