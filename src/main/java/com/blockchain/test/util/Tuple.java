package com.blockchain.test.util;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class Tuple<F, S> implements Serializable {

	private static final long serialVersionUID = 1L;
	private F first;
	private S second;

	public Tuple() {
	}

	public Tuple(F first, S second) {
		this.first = first;
		this.second = second;
	}

	public static <F, S> Tuple<F, S> of(F first, S second) {
		return new Tuple<>(first, second);
	}

}
