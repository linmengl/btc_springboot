package com.blockchain.test.designMode.factory.abstractF;

import org.apache.commons.lang3.StringUtils;

public class ShapeFactory extends AbstractFactory {
	@Override
	public Color getColor(String color) {
		return null;
	}

	@Override
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
