package com.abyiyi.tools.javaassist;

import javassist.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;


public final class ClassCreator {

	private static final Logger logger = LoggerFactory.getLogger(ClassCreator.class);

	

	private static final String outputDir = System.getProperty("codegen.dir");
	private static final ConcurrentHashMap<String, Class> clazz_cache = new ConcurrentHashMap<String, Class>();
	private static final String VERIF_CLASS_NAME = "TempCompile_";
	private static final AtomicLong verif_class_count = new AtomicLong(0L); 

	private int mod;
	private String name;
	private String verif_name;
	private String super_class_name;
	private String[] imports;
	private String[] infs;
	private String[] methods;
	private String[] fields;
	private String[] constructors;

	public void setModifier(int mod) {
		this.mod = mod;
	}

	public void addModifier(int mod) {
		this.mod |= mod;
	}

	public void setClassName(String name) {
		this.name = name;
	}

	public void setSuperClass(String super_class_name) {
		this.super_class_name = super_class_name;
	}

	public void setSuperClass(Class super_class) {
		this.super_class_name = super_class.getName();
	}

	public void setImports(String[] imports) {
		this.imports = imports;
	}

	public void addImport(String imp) {
		imports = CommonUtil.addString(imports, imp);
	}

	public void addImport(Class cls) {
		imports = CommonUtil.addString(imports, CommonUtil.getPackageName(cls));
	}

	public void addInterface(String inf) {
		infs = CommonUtil.addString(infs, inf);
	}

	public void addInterface(Class inf) {
		infs = CommonUtil.addString(infs, inf.getName());
	}

	public void setInterfaces(String[] infs) {
		this.infs = infs;
	}

	public void addMethod(String method) {
		methods = CommonUtil.addString(methods, method);
	}

	public void addMethods(String[] method) {
		methods = CommonUtil.addString(methods, method);
	}

	public void setMethods(String[] methods) {
		this.methods = methods;
	}

	public void addField(String field) {
		fields = CommonUtil.addString(fields, field);
	}

	public void addFields(String[] field) {
		fields = CommonUtil.addString(fields, field);
	}

	public void setFields(String[] fields) {
		this.fields = fields;
	}

	public void addDefaultConstructor() {
		constructors = CommonUtil.addString(constructors, "public " + CommonUtil.lastPart(name) + "(){}");
	}

	public void addConstructor(String cons) {
		constructors = CommonUtil.addString(constructors, cons);
	}

	public void addConstructors(String[] cons) {
		constructors = CommonUtil.addString(constructors, cons);
	}

	public void setConstructors(String[] cons) {
		this.constructors = cons;
	}

	private CtClass createCtClass(ClassPool cp, ClassLoader cl) {
		CtClass ctc = cp.makeClass(name);
		if (mod != 0) {
			ctc.setModifiers(mod);
		}

		setSuperClass(cp, ctc);
		addInterfaces(cp, ctc);
		addFields(cp, ctc);
		addConstructors(cp, ctc);
		addMethods(cp, ctc);
		return ctc;
	}

	public Class makeClass() throws CannotCompileException {
		return makeClass((ClassLoader)null);
	}

	public Class makeClass(Class cls) throws CannotCompileException {
		ClassLoader cl = null;
		if (cls != null) {
			cl = cls.getClassLoader();
			if (cl == null) {
				cl = ClassCreator.class.getClassLoader();
			}
		}

		return makeClass(cl);
	}

	private void resetNameForVerify() {
		this.verif_name = name;
		this.name = VERIF_CLASS_NAME + verif_class_count.incrementAndGet();
	}

	private void resumeName() {
		this.name = verif_name;
		this.verif_name = null;
	}

	public Class compileAndMakeClass() throws CannotCompileException {
		resetNameForVerify();
		compileClass();
		resumeName();
		return makeClass();
	}

	public Class compileAndMakeClass(Class cls) throws CannotCompileException {
		resetNameForVerify();
		compileClass(cls);
		resumeName();
		return makeClass(cls);
	}

	public void compileClass() throws CannotCompileException {
		resetNameForVerify();
		compileClass((ClassLoader)null);
	}

	public void compileClass(Class cls) throws CannotCompileException {
		ClassLoader cl = null;
		if (cls != null) {
			cl = cls.getClassLoader();
			if (cl == null) {
				cl = ClassCreator.class.getClassLoader();
			}
		}

		resetNameForVerify();
		compileClass(cl);
	}

	private static synchronized void addClass(ClassPool cp, CtClass ctc) {
		try {
			cp.insertClassPath(new ByteArrayClassPath(ctc.getName(), ctc.toBytecode()));
		} catch (Exception e) {
		}
	}

	public Class makeClass(ClassLoader cl) throws CannotCompileException {
		Class clazz = getFromCache(name);

		if (clazz == null) {
			if (cl == null) {
				cl = getClassLoader();
			}

			JavassistUtil.addClassLoader(cl);
			ClassPool rootcp = JavassistUtil.getClassPool();

			synchronized(rootcp) {
				JavassistUtil.addImports(imports);
			}

			logger.debug("Create class [{}]", name);
			if (super_class_name != null) {
				logger.debug( "Super class [{}]", super_class_name);
			}

			if (infs != null) {
				for (int i = 0; i < infs.length; i++) {
					logger.debug( "Implements Interface[" + (i + 1) + "]=" + infs[i]);
				}
			}

			if (fields != null) {
				for (int i = 0; i < fields.length; i++) {
					logger.debug( "Field[" + (i + 1) + "]=" + fields[i]);
				}
			}

			if (constructors != null) {
				for (int i = 0; i < constructors.length; i++) {
					logger.debug( "Constructor[" + (i + 1) + "]=\n" + constructors[i]);
				}
			}

			if (methods != null) {
				for (int i = 0; i < methods.length; i++) {
					logger.debug( "Method[" + (i + 1) + "]=\n" + methods[i]);
				}
			}

			CtClass ctc = null;
			synchronized(rootcp) {
				clazz = getFromCache(name);
				if (clazz == null) {
					ctc = createCtClass(rootcp, cl);
					clazz = ctc.toClass(cl, null);
					clazz_cache.put(name, clazz);
					if (outputDir != null && !outputDir.equals("")) {
						try {
							ctc.writeFile(outputDir);
						} catch (java.io.IOException ioe) {
							ioe.printStackTrace();
						}
					}
				}
			}
			
		}
		return clazz;
	}

	public void compileClass(ClassLoader cl) throws CannotCompileException {
		if (cl == null) {
			cl = getClassLoader();
		}

		JavassistUtil.addClassLoader(cl);
		ClassPool rootcp = JavassistUtil.getClassPool();

		synchronized(rootcp) {
			JavassistUtil.addImports(imports);
		}

		logger.debug( "Create class [{}]", name);
		if (super_class_name != null) {
			logger.debug( "Super class [{}]", super_class_name);
		}

		if (infs != null) {
			for (int i = 0; i < infs.length; i++) {
				logger.debug( "Implements Interface[" + (i + 1) + "]=" + infs[i]);
			}
		}

		if (fields != null) {
			for (int i = 0; i < fields.length; i++) {
				logger.debug( "Field[" + (i + 1) + "]=" + fields[i]);
			}
		}

		if (constructors != null) {
			for (int i = 0; i < constructors.length; i++) {
				logger.debug( "Constructor[" + (i + 1) + "]=\n" + constructors[i]);
			}
		}

		if (methods != null) {
			for (int i = 0; i < methods.length; i++) {
				logger.debug( "Method[" + (i + 1) + "]=\n" + methods[i]);
			}
		}

		synchronized(rootcp) {
			createCtClass(rootcp, cl);
		}
	}

	private void addMethods(ClassPool cp, CtClass ctc) {
		if (methods != null) {
			for (int i = 0; i < methods.length; i++) {
				try {
					CtMethod md = CtMethod.make(methods[i], ctc);
					ctc.addMethod(md);
				} catch (CannotCompileException ex) {
					logger.error("compile methods error:[{}]", ex);
					throw new RuntimeException("SYS_CLASS_GEN_COMPILE_ERROR", ex);
				}
			}
		}
	}

	private void addFields(ClassPool cp, CtClass ctc) {
		if (fields != null) {
			for (int i = 0; i < fields.length; i++) {
				try {
					CtField fld = CtField.make(fields[i] + ';', ctc);
					ctc.addField(fld);
				} catch (CannotCompileException ex) {
					logger.error( "compile fields error:[{}]", ex);
					throw new RuntimeException("SYS_CLASS_GEN_COMPILE_ERROR", ex);
				}
			}
		}
	}

	private void addConstructors(ClassPool cp, CtClass ctc) {
		if (constructors != null) {
			for (int i = 0; i < constructors.length; i++) {
				try {
					CtConstructor cons = CtNewConstructor.make(constructors[i], ctc);
					ctc.addConstructor(cons);
				} catch (CannotCompileException ex) {
					logger.error( "compile constructors error:[{}]", ex);
					throw new RuntimeException("SYS_CLASS_GEN_COMPILE_ERROR", ex);
				}
			}
		}
	}

	private void addInterfaces(ClassPool cp, CtClass ctc) {
		if (infs != null) {
			for (int i = 0; i < infs.length; i++) {
				CtClass clz = safeGet(cp, infs[i]);
				if (clz != null) {
					ctc.addInterface(clz);
				}
			}
		}
	}

	private void setSuperClass(ClassPool cp, CtClass ctc) {
		if (super_class_name != null) {
			CtClass clz = safeGet(cp, super_class_name);
			if (clz != null) {
				try {
					ctc.setSuperclass(clz);
				} catch (CannotCompileException ex) {
					logger.error( "compile superclass error:[{}]", ex);
					throw new RuntimeException("SYS_CLASS_GEN_COMPILE_ERROR", ex);
				}
			}
		}
	}

	private static ClassLoader getClassLoader() {
		return ClassUtil.getClassLoader(ClassCreator.class);
	}

	private static Class getFromCache(String clsname) {
		return clazz_cache.get(clsname);
	}

	private static CtClass safeGet(ClassPool cp, String clsname) {
		try {
			return cp.get(clsname);
		} catch (NotFoundException e) {
			return null;
		}
	}


}
