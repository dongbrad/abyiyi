package com.abyiyi.dynamic.demo;
 
import java.io.IOException;
 
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMember;
import javassist.CtMethod;
import javassist.CtConstructor;
import javassist.CtNewMethod;
import javassist.NotFoundException;
 
/**
 * 测试使用javassist生成一个新的类
 * 
 * @author lenovo
 *
 */
public class TestJavassit {
 
	public static void main(String[] args) throws CannotCompileException, NotFoundException, IOException {
		ClassPool pool = ClassPool.getDefault(); //获得类池
		CtClass cc = pool.makeClass("Javassist.emp");
		
		//创建类
		CtField f1 = CtField.make("private int empno;", cc);
		CtField f2 = CtField.make("private String ename;", cc);
		cc.addField(f1);
		cc.addField(f2);
		
		//创建方法
		CtMethod m1 = CtNewMethod.make("public int getempno(){return empno;}", cc );
		CtMethod m2 = CtMethod.make("public void setempno(int empno){this.empno=empno;}", cc);
		cc.addMethod(m1);
		cc.addMethod(m2);
		
		//添加构造器
		CtConstructor constructor = new CtConstructor(new CtClass[] {CtClass.intType,pool.get("java.lang.String")}, cc);
		constructor.setBody("{this.empno = empno; this.ename = ename;}"); 
		cc.addConstructor(constructor);
		
		cc.writeFile("E:\\JavaSE");//将上面构造好的类写入到E:\JavaSE\javassist中
		System.out.println("生成类成功！");
	}
 
}