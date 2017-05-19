package classloade;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MyClassLoader extends ClassLoader {
	String path = "E:/Users/Administrator/workspace/nioProject/src/";

	public byte[] loadClassData(String name) throws IOException {
		FileInputStream in = new FileInputStream(new File(path + name + ".class"));
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		int d;
		while ((d = in.read()) != -1) {
			outputStream.write(d);
		}
		in.close();
		outputStream.close();
		byte[] byteArray = outputStream.toByteArray();
		return byteArray;
	}

	@Override
	public Class<?> findClass(String name) {
		try {
			byte[] byteArray = loadClassData(name);
			return this.defineClass("test." + name, byteArray, 0, byteArray.length);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		MyClassLoader loader = new MyClassLoader();
		try {
			Class<?> c = loader.loadClass("transfer");
			System.out.println(c.getClassLoader().toString());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
