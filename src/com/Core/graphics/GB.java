package com.Core.graphics;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;

import com.Core.Gamecore;
import com.Core.Util.Translationhelper;
import com.Core.graphics.shader.Shaderclass;
import com.Core.graphics.texture.Texture;

public class GB {// Sepeartely used object only.
	private FloatBuffer verticies;
	private FloatBuffer texcoord;
	private int vertid, texid, vertidcustom, texidcustom;// one spritesheet per
															// VBO
	public vertex2d translation;
	public Texture t;
	private int transformid, scalarid, vertcount;
	private float scalar;
	private vertex2d[] coordinates;
	private Translationhelper th;
	private Gamecore gc;
	private Shaderclass shader;
	private int objectlength = 0;

	public GB(Gamecore gc) {
		shader = new Shaderclass();
		shader.attachVertexShader("com/Core/graphics/shader/shaders/vertshader.vert");
		shader.attachFragmentShader("com/Core/graphics/shader/shaders/fragshader.frag");
		shader.link();
		transformid = GL20.glGetUniformLocation(shader.programID, "transform");
		scalarid = GL20.glGetUniformLocation(shader.programID, "scalar");
		scalar = gc.verticalaspect;
		th = new Translationhelper();
		th.setsize(gc.width, gc.height);
		this.gc = gc;
	}

	public void setupnewprogressiveobject(int objectsize) {// I know the name is
															// painful but its a
															// useful function
		this.verticies = BufferUtils.createFloatBuffer(objectsize * 2);
		this.texcoord = BufferUtils.createFloatBuffer(objectsize * 2);// only
																		// supports
																		// 2d
																		// because
																		// its a
																		// 2d
																		// engine
																		// duh
		coordinates = new vertex2d[objectsize];
		this.objectlength = objectsize;
		translation = new vertex2d(0, 0);

	}

	public void progressiveobject(vertex2d[] object) {
		vertcount += object.length * 2;
		if (vertcount / 2 <= objectlength) {
			for (int i = 0; i < object.length; i++) {
				coordinates[i + ((vertcount / 2) - object.length)] = object[i];
				verticies.put(object[i].getx());
				verticies.put(object[i].gety());
				texcoord.put(object[i].gets());
				texcoord.put(object[i].gett());
			}
		} else {
			gc.logger.log("Failed to add to VBO over max size limit! "
					+ objectlength);
		}

	}

	public void editVBO(int index, vertex2d[] data){//only usable for manually created VBOS
		for(int i = 0; i < data.length;i++){
			verticies.put(index + i,data[i].getx());
			verticies.put(index + i + 1,data[i].gety());
			texcoord.put(index + i, data[i].gets());
			texcoord.put(index + i + 1, data[i].gett());
			coordinates[i + index] = data[i];
			
			
		}
		verticies.rewind();
		texcoord.rewind();
		
	}

	public void appendVBO(vertex2d[] object) {// add data to the end of a vbo
		if (object.length + (vertcount / 2) < objectlength) {
			for (int i = 0; i < object.length; i++) {
				verticies.put(object[i].getx());
				verticies.put(object[i].gety());
				texcoord.put(object[i].gets());
				texcoord.put(object[i].gett());
				coordinates[i + ((vertcount / 2) - object.length)] = object[i];
			}
		} else {
			gc.logger
					.log("Warning , appending failed due to maximum length being reached");
			return;
		}
	}

	public void settex(Texture t) {
		this.t = t;
	}

	public void manualVBOsetup() {
		if (t == null) {
			gc.logger.log("Unable to setup VBO null texture");
			return;
		} else if (coordinates == null) {
			gc.logger.log("Unable to setup VBO no vertexs added");
			return;
		} else {
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			verticies.rewind();
			texcoord.rewind();
			vertidcustom = GL15.glGenBuffers();
			GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vertidcustom);
			GL15.glBufferData(GL15.GL_ARRAY_BUFFER, verticies,
					GL15.GL_DYNAMIC_DRAW);
			GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);

			texidcustom = GL15.glGenBuffers();
			GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, texidcustom);
			GL15.glBufferData(GL15.GL_ARRAY_BUFFER, texcoord,
					GL15.GL_DYNAMIC_DRAW);
			GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glDisable(GL11.GL_BLEND);
		}
	}

	public void createobject(vertex2d[] object, Texture t) {// for static object
															// creation that
															// requires constant
															// editing such as
															// ones used in the
															// editor
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		translation = new vertex2d(0, 0);
		verticies = BufferUtils.createFloatBuffer(2 * object.length);
		texcoord = BufferUtils.createFloatBuffer(2 * object.length);
		for (int i = 0; i < object.length; i++) {
			verticies.put(object[i].getx());
			verticies.put(object[i].gety());
			texcoord.put(object[i].gets());
			texcoord.put(object[i].gett());
		}
		verticies.rewind();
		texcoord.rewind();
		vertid = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vertid);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, verticies, GL15.GL_DYNAMIC_DRAW);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		vertcount = object.length;
		texid = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, texid);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, texcoord, GL15.GL_DYNAMIC_DRAW);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		this.t = t;
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_BLEND);
	}

	public void swaptexture(Texture t) {
		this.t = t;
	}

	public void translate(float f, float g) {
		vertex2d transformed = th.transform((f * 2) - (gc.width), (g * 2)
				+ (gc.height));
		translation.setcoordinates(transformed.getx(), transformed.gety());
	}

	public void forcetranslate(float f, float g) {
		translation.setcoordinates(f, g);
	}

	public void render() {

		// Bind the vertex buffer
		// t.bindtex(t.id, t.width, t.height, t.buffer);
		if (t != null) {
			shader.bind();
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, t.id);
			GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vertid);
			GL20.glEnableVertexAttribArray(0);
			GL20.glEnableVertexAttribArray(1);
			GL20.glVertexAttribPointer(0, 2, GL11.GL_FLOAT, false, 0, 0);
			GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, texid);
			GL20.glVertexAttribPointer(1, 2, GL11.GL_FLOAT, false, 0, 0);
			GL20.glUniform2f(transformid, translation.getx(),
					translation.gety());
			GL20.glUniform1f(scalarid, scalar);
			// Draw the textured rectangle
			GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, vertcount);

			GL20.glDisableVertexAttribArray(0);
			GL20.glDisableVertexAttribArray(1);
			shader.unbind();
		}

		else {
			System.out.println("failure");
		}
	}

	public void renderprogressive() {
		shader.bind();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, t.id);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vertidcustom);
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		GL20.glVertexAttribPointer(0, 2, GL11.GL_FLOAT, false, 0, 0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, texidcustom);
		GL20.glVertexAttribPointer(1, 2, GL11.GL_FLOAT, false, 0, 0);
		GL20.glUniform2f(transformid, translation.getx(), translation.gety());
		GL20.glUniform1f(scalarid, scalar);
		// Draw the textured rectangle
		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, vertcount);

		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		shader.unbind();
	}

	public void destroy() {
		if (Display.isActive()) {
			GL15.glDeleteBuffers(vertid);
			GL15.glDeleteBuffers(texid);
			GL15.glDeleteBuffers(vertidcustom);
			GL15.glDeleteBuffers(texidcustom);
		}
		shader.dispose();
	}

	/*
	 * public GB(Gamecore gc) { //initialize create VAO verticies =
	 * BufferUtils.createFloatBuffer(vertnum * 2); color =
	 * BufferUtils.createFloatBuffer(vertnum * 4); this.gc = gc; }
	 * 
	 * public void render() { //enable states
	 * GL11.glEnableClientState(GL11.GL_VERTEX_ARRAY);
	 * GL11.glEnableClientState(GL11.GL_COLOR_ARRAY); //setup pointers
	 * GL11.glColorPointer(vertcount,4,color);
	 * 
	 * GL11.glVertexPointer(vertcount, 2, verticies); //draw data
	 * GL11.glDrawArrays(GL11.GL_TRIANGLES,0, vertcount); //clean up data
	 * GL11.glDisableClientState(GL11.GL_COLOR_ARRAY);
	 * GL11.glDisableClientState(GL11.GL_VERTEX_ARRAY); } public void
	 * addvertex(vertex2d v){ verticies.put(v.getx()).put(v.gety());
	 * color.put(new float[]{1,1,1,1}); vertcount++; } public void bind(){
	 * 
	 * } //not called yet public void update() { verticies.flip(); color.flip();
	 * render(); vertcount = 0; verticies.clear(); color.clear(); } public void
	 * destroy(){
	 * 
	 * }
	 */// Unsuccessfull renderer
}
