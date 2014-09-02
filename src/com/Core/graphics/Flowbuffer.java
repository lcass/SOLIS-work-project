package com.Core.graphics;

import com.Core.Gamecore;
import com.Core.Util.internalcodes;
import com.Core.graphics.texture.spritecomponent;

public class Flowbuffer {// continually adds to a vertex buffer
	private shapes shape;
	private Gamecore core;

	public Flowbuffer(Gamecore core) {
		this.core = core;
		shape = new shapes(core);
	}

	public vertex2d[] getflowbuffer(int maxlength, float increment, int height,
			spritecomponent[] sprites, float space) {// maxlength needs to be
														// even in order
		// to generate the objects.
		vertex2d[] data = new vertex2d[maxlength * 6];
		if (sprites.length < maxlength) {
			core.logger
					.log("WARNING CANNOT CREATE FLOWBUFFER ,SPRITECOMPONENT TO SHORT");
			core.logger.log(internalcodes.EMPTYSLOT);
			core.kill();
		}
		for (int i = 0; i < maxlength; i++) {
			vertex2d[] temp = shape.square(increment, sprites[i]);
			for (int ii = 0; ii < 6; ii++) {
				temp[ii].addtovertcoords(
						(float) ((1 / core.width) * (i * (increment + space))),
						0);
				data[ii + (i * 6)] = new vertex2d(temp[ii].getx(),
						temp[ii].gety());
				data[ii + (i * 6)].settexcoords(temp[ii].gets(),
						temp[ii].gett());
			}
		}

		return data;
	}

}
