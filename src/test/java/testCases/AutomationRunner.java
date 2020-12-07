package testCases;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;
import org.testng.TestNG;
import org.testng.collections.Lists;

public class AutomationRunner {

	static TestNG testNG;
	static String browser ="";
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String prefix = "test";
		List<ClassLoader> classLoadersList = new LinkedList<ClassLoader>();
	     classLoadersList.add(ClasspathHelper.contextClassLoader());
	     //classLoadersList.add(ClasspathHelper.staticClassLoader());
	     Reflections reflections = new Reflections(new ConfigurationBuilder()
	             .setScanners(new SubTypesScanner(false), new ResourcesScanner())
	             .setUrls(ClasspathHelper.forClassLoader(classLoadersList.toArray(new ClassLoader[0])))
	             .filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix(prefix))
	            		 .excludePackage("com.sun")
	            		 .excludePackage("org")
	            		 .excludePackage("com.microsoft")
	            		 .excludePackage("org.apache.tools.ant")
	            		 .excludePackage("junit.framework.TestCase")
	            		 .excludePackage("org.testng.TestNGAntTask")
	            		 ));
	     Set<Class<? extends Object>> classes = reflections.getSubTypesOf(Object.class);

	     Set<String> packageNameSet = new TreeSet<String>();
	     List<Class> classesList = Lists.newArrayList();
			for (Class<?> classInstance : classes) {
	         String packageName = classInstance.getPackage().getName();
	         // && classInstance.getName().contains("ComponentBuilder_test")
	         if (packageName.startsWith("testCases")) {
	             packageNameSet.add(packageName);
	             classesList.add(classInstance);
	         }
	     }
	     
	     	
	     //for(String t : packageNameSet){
	     //System.out.println(":::"+t);
	      
	      
	      String[] testClasses = args[0].split(",");
	      String testGroups="";
		if (args.length>1) {
			testGroups = args[1];
		}
		if (args.length>2) {
			browser=args[2];
			//setProperty("Config.properties", "browser",args[2]);
		}
	      List<Class> executeClassList = Lists.newArrayList();
			  for (String c : testClasses) {
				  
				  
				  for (Class<?> classInstance : classesList) {
				         String packageName = classInstance.getPackage().getName();
				         if (classInstance.getName().contains(c)) {
				             executeClassList.add(classInstance);
				         }
				     }
				  }
			testNG = new TestNG();
			testNG.setTestClasses(executeClassList.toArray(new Class[0]));
			testNG.setGroups(testGroups);
			testNG.run();
			
	     //}
	    
		/*testNG = new TestNG();
		testNG.setTestClasses(new Class[] {testClasses[i].class});
		testNG.setGroups("Formula Builder");
		testNG.run();*/

	}

}
