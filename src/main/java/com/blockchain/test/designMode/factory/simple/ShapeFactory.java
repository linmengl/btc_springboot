package com.blockchain.test.designMode.factory.simple;


import org.apache.commons.lang3.StringUtils;

public class ShapeFactory {

	public Shape getShape(String shape) {
		if (StringUtils.isBlank(shape)) {
			return null;
		}
		if (shape.equalsIgnoreCase("CIRCLE")) {
			return new Circle();
		} else if (shape.equalsIgnoreCase("RECTANGLE")) {
			return new Rectangle();
		} else if (shape.equalsIgnoreCase("SQUARE")) {
			return new Square();
		}
		return null;
	}
}
