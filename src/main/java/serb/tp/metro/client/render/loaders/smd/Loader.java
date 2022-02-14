package serb.tp.metro.client.render.loaders.smd;

import java.nio.FloatBuffer;
import java.util.ArrayList;

import org.apache.commons.lang3.ArrayUtils;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

abstract class Loader {

	protected static final int createVAO() {
		int vaoID = GL30.glGenVertexArrays();
		GL30.glBindVertexArray(vaoID);
		return vaoID;
	}
	
	protected static void storeFloatDataInAttributeList(int attributeData, ArrayList<Float> data, int sizePoints) {
		int vboID = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
		FloatBuffer fb = arrayListToFloatBuffer(data);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, fb, GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(attributeData, sizePoints, GL11.GL_FLOAT, false, 0, 0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		
	}
	
	protected static final void unbindVAO() {
		GL30.glBindVertexArray(0);
	}
	
	private static FloatBuffer arrayListToFloatBuffer(ArrayList<Float> data) {
		float[] dataArray = ArrayUtils.toPrimitive(data.toArray(new Float[0]), 0.0F);
		FloatBuffer fb = BufferUtils.createFloatBuffer(data.size());
		fb.put(dataArray);
		fb.flip();
		return fb;
		
	}
}
