package com.blockchain.test.demo.thread.proxy.example;

import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import javax.lang.model.element.Modifier;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class Proxy {
	public static Object newProxyInstance(Class inf, InvocationHandler handler) throws Exception {
		TypeSpec.Builder typeSpecBuilder = TypeSpec.classBuilder("TimeProxy")
				.addSuperinterface(inf);

		FieldSpec fieldSpec = FieldSpec.builder(handler.getClass(), "handler", Modifier.PRIVATE).build();
		typeSpecBuilder.addField(fieldSpec);

		MethodSpec constructorMethodSpec = MethodSpec.constructorBuilder()
				.addModifiers(Modifier.PUBLIC)
				.addParameter(handler.getClass(), "handler")
				.addStatement("this.handler = handler")
				.build();
		typeSpecBuilder.addMethod(constructorMethodSpec);

		Method[] methods = inf.getDeclaredMethods();
		for (Method method : methods) {
			MethodSpec methodSpec = MethodSpec.methodBuilder(method.getName())
					.addModifiers(Modifier.PUBLIC)
					.addAnnotation(Override.class)
					.returns(method.getReturnType())
					.addCode("try {\n")
					.addStatement("\t$T method = " + inf.getName() + ".class.getMethod(\"" + method.getName() + "\")", Method.class)
					// 为了简单起见，这里参数直接写死为空
					.addStatement("\tthis.handler.invoke(this, method, null)")
					.addCode("} catch(Exception e) {\n")
					.addCode("\te.printStackTrace();\n")
					.addCode("}\n")
					.build();

			typeSpecBuilder.addMethod(methodSpec);
		}

		JavaFile javaFile = JavaFile.builder("com.proxy", typeSpecBuilder.build()).build();
		// 为了看的更清楚，我将源码文件生成到桌面
		String sourcePath = "C:/Users/83982/Desktop/";
		javaFile.writeTo(new File(sourcePath));

		// 编译
		JavaCompiler.compile(new File(sourcePath + "/com/proxy/TimeProxy.java"));

		URL[] urls = new URL[] {new URL("file:" + sourcePath)};
		URLClassLoader classLoader = new URLClassLoader(urls);
		Class clazz = classLoader.loadClass("com.proxy.TimeProxy");
		Constructor constructor = clazz.getConstructor(handler.getClass());
		Object object = constructor.newInstance(handler);
		return object;
		//return null;
	}

	public static void main(String[] args) {
		try {
			MyInvocationHandler handler = new MyInvocationHandler(new Birds());
			newProxyInstance(Flyable.class,handler);
		}catch (Exception e) {
			System.out.println(e);
		}
	}
}
