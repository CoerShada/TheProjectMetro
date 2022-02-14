package serb.tp.metro.client.render.loaders.smd;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public abstract class ObjLoader extends Loader{

	@SuppressWarnings("resource")
	public static ObjModel loadToVAO(ResourceLocation resource) {
		ArrayList<Float> vertices = new ArrayList<Float>();
		ArrayList<Float> normals = new ArrayList<Float>();
		ArrayList<Float> textures = new ArrayList<Float>();
		Scanner reader;
		try {
			InputStream stream = Minecraft.getMinecraft().getResourceManager().getResource(resource).getInputStream();
			reader = new Scanner(stream);
			
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		int vaoID = createVAO(); //Создание VAO
		
		while (reader.hasNext()) {
			String currentLine = reader.nextLine();
			if (currentLine == null || currentLine.equals("") || currentLine.isEmpty()|| currentLine.startsWith("#")) continue;
			String[] arrLines = currentLine.split(" ");
			switch (arrLines[0]) {
				case "v":
					vertices.add(Float.parseFloat(arrLines[1])); 	//Вершина Х
					vertices.add(Float.parseFloat(arrLines[2]));	//Вершина Y
					vertices.add(Float.parseFloat(arrLines[3]));	//Вершина Z
					continue;
				case "vn":
					normals.add(Float.parseFloat(arrLines[1])); 	//Нормаль Х
					normals.add(Float.parseFloat(arrLines[2]));		//Нормаль Y
					normals.add(Float.parseFloat(arrLines[3]));		//Нормаль Z
					continue;
				case "vt":
					textures.add(Float.parseFloat(arrLines[1])); 	//Текстура U
					textures.add(Float.parseFloat(arrLines[2]));	//Текстура V
					continue;
				case "f":
				default:
			}
			
		}
		int i = 0;
		storeFloatDataInAttributeList(++i, vertices, 3); 	//Сохранить данные вершин в VBO
		storeFloatDataInAttributeList(++i, normals, 3);		//Сохранить данные нормалей в VBO
		storeFloatDataInAttributeList(++i, textures, 2);	//Сохранить данные текстур в VBO
		unbindVAO(); //Отвязать VAO
		
			
		return new ObjModel(vaoID, 0);

	}
	
}
