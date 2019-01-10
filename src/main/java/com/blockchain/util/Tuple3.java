package com.blockchain.util;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class Tuple3<F, S, T> implements Serializable {

	private static final long serialVersionUID = 1L;
	private F first;
	private S second;
	private T three;

	public Tuple3() {
	}

	public Tuple3(F first, S second, T three) {
		this.first = first;
		this.second = second;
		this.three = three;
	}

	public static <F, S, T> Tuple3<F, S, T> of(F first, S second, T three) {
		return new Tuple3<>(first, second, three);
	}
}
