package io.github.filippobuletto.microprofileconfigdemo.rest;

import java.util.function.Predicate;

import io.vavr.control.Try;

public final class Predicates {
	
	public static Predicate<String> isInteger() {
		return s -> tryParseInteger(s) != null;
	}
	
	private static Integer tryParseInteger(String s) {
		return Try.of(() -> Integer.parseInt(s)).getOrElse((Integer)null);
	}

	public static Predicate<String> isNotEmpty() {
		return s -> !(s == null || s.isEmpty());
	}

}
