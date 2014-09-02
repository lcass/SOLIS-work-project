package com.Core.Util;

import com.Core.graphics.vertex2d;

public class Translationhelper {
	private float width, height;

	public void setsize(float width, float height) {
		this.width = width;
		this.height = height;

	}

	public vertex2d transform(float x, float y) {
		return new vertex2d((x / (width)), y / height);
	}

	public vertex2d rotate(float rot, vertex2d position, vertex2d point) {// will
																			// be
																			// moved
																			// to
																			// shader
																			// soon
		vertex2d prerot = new vertex2d(position.getx() + point.getx(),
				position.gety() + point.gety());
		vertex2d posrot = new vertex2d(
				(float) ((prerot.getx() * Math.cos(rot)) - (prerot.gety() * Math
						.sin(rot))),
				(float) ((prerot.getx() * Math.cos(rot)) + (prerot.gety() * Math
						.sin(rot))));
		posrot.addtovertcoords(-point.getx(), -point.gety());
		return posrot;
	}

}
