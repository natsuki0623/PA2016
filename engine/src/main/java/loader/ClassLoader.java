//package loader;
//
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.security.SecureClassLoader;
//import java.util.ArrayList;
//import java.util.zip.ZipEntry;
//import java.util.zip.ZipInputStream;
//
//public class ClassLoader extends SecureClassLoader {
//	private String cheminDuPlugin;
//
//	public ClassLoader(String chemin) {
//		cheminDuPlugin = chemin;
//	}
//
//	@Override
//	protected Class<?> findClass(String name) throws ClassNotFoundException {
//		byte[] b = loadClassData(name);
//		System.out.println(name);
//		return super.defineClass(name, b, 0, b.length);
//	}
//
//	private byte[] loadClassData(String name) throws ClassNotFoundException {
//
//		File fichier = new File(cheminDuPlugin);
//
//		try {
//			return Files.readAllBytes(fichier.toPath());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		//
//		// // Si c'est un .jar ou un .zip
//		// if (file.getAbsolutePath().endsWith(".jar") ||
//		// file.getAbsolutePath().endsWith(".zip")) {
//		//
//		// ZipInputStream zip;
//		// try {
//		// zip = new ZipInputStream(new FileInputStream(file));
//		//
//		// ZipEntry entry;
//		// // Parcours de tous les éléments du jar
//		// while ((entry = zip.getNextEntry()) != null) {
//		// // Si on trouve le bon fichier
//		// if (entry.toString().equals(name)) {
//		//
//		// // Permet d'initialiser un tab sans lui donner la
//		// // taille
//		// ByteArrayOutputStream streamBuilder = new ByteArrayOutputStream();
//		// int bytesRead;
//		//
//		// // Buffer temporaire
//		// byte[] tempBuffer = new byte[8192 * 2];
//		// try {
//		// // On lit tant qu'il reste des bytes à lire
//		// while ((bytesRead = zip.read(tempBuffer)) != -1) {
//		// streamBuilder.write(tempBuffer, 0, bytesRead);
//		// }
//		// } catch (IOException e) {
//		// e.printStackTrace();
//		// }
//		// // On retourne ce qu'on a lu
//		// return streamBuilder.toByteArray();
//		// }
//		// }
//		// } catch (IOException e) {
//		// e.printStackTrace();
//		// }
//		// }
//		//
//		// // Si le chemin n'existe pas
//		// if (!fichier.exists() || fichier == null) {
//		// throw new ClassNotFoundException();
//		// }
//		//
//		// System.out.println("Le fichier existe : " +
//		// fichier.getAbsolutePath());
//		//
//		// byte[] tab = null;
//		// try {
//		// // On lit le contenu du .class
//		// tab = Files.readAllBytes(fichier.toPath());
//		// } catch (IOException e) {
//		// e.printStackTrace();
//		// }
//		//
//		// return tab;
//		return null;
//	}
//
//}
////import java.io.BufferedInputStream;
////import java.io.ByteArrayOutputStream;
////import java.io.File;
////import java.io.FileInputStream;
////import java.io.IOException;
////import java.nio.file.FileVisitResult;
////import java.nio.file.Files;
////import java.nio.file.Path;
////import java.nio.file.SimpleFileVisitor;
////import java.nio.file.attribute.BasicFileAttributes;
////import java.security.SecureClassLoader;
////import java.util.ArrayList;
////import java.util.Enumeration;
////import java.util.jar.JarEntry;
////import java.util.jar.JarFile;
////import java.util.zip.ZipEntry;
////import java.util.zip.ZipFile;
////
/////**
//// * Classe permettant de gerer le 
//// * chargement dynamique des plugins
//// */
////public class ClassLoader extends SecureClassLoader {
////
////	private ArrayList<File> path = new ArrayList<File>();
////
////	/**
////	 * Constructeur 
////	 * 
////	 * @param path
////	 */
////	public ClassLoader(ArrayList<File> path) {
////		this.path = path;
////	}
////	
////	@Override  
////	protected Class<?> findClass(String name) throws ClassNotFoundException {  
////		byte[] b = loadClassData(name);
////
////		if(b == null || b.length == 0) {
////			throw new ClassNotFoundException("Cannot find class named : '" + name + "'");
////		}
////
////		return super.defineClass(name, b, 0, b.length);
////	}
////	
////	/**
////	 * Methode permettant de trouver une classe
////	 * dans un repertoire 
////	 * 
////	 * @param name
////	 * @param arrayOutputStream
////	 * @param file
////	 */
////	void findClassInDirectory(final String name, final ByteArrayOutputStream arrayOutputStream, final File file) {
////		try {
////			Files.walkFileTree(file.toPath(), new SimpleFileVisitor<Path>() {
////				@Override
////				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
////					String possiblePath = name.replace('.', File.separatorChar);
////
////					if(file.endsWith(possiblePath + ".class")) {
////
////						BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file.toFile()));
////						int sRead;
////						byte[] data = new byte[16384];
////						while((sRead = bis.read(data, 0, data.length)) != -1) {
////							arrayOutputStream.write(data, 0, sRead);
////						}
////						arrayOutputStream.flush();
////						bis.close();
////						return FileVisitResult.TERMINATE;
////					}
////					return FileVisitResult.CONTINUE;
////				}
////			});
////		} catch (IOException e) {
////			e.printStackTrace();
////		}
////	}
////	
////	/**
////	 * Methode permettant de trouver une classe
////	 * dans un fichier jar 
////	 * 
////	 * @param name
////	 * @param arrayOutputStream
////	 * @param file
////	 */
////	private void findClassInJarFile(final String name, final ByteArrayOutputStream arrayOutputStream, File file) {
////		try {
////			JarFile jarFile = new JarFile(file);
////			Enumeration<JarEntry> entries = jarFile.entries();
////			JarEntry jarEntryToRead = null;
////			String pathToSearch = name.replace('.', File.separatorChar) + ".class";
////			
////			while (entries.hasMoreElements()) {
////			  JarEntry entry = entries.nextElement();
////			  if (entry.getName().endsWith(pathToSearch)) {
////				  jarEntryToRead = entry;
////			  }
////			}
////			
////			if(jarEntryToRead != null) {
////				BufferedInputStream bufferedInputStream = new BufferedInputStream(jarFile.getInputStream(jarEntryToRead));
////				int sRead;
////				byte[] data = new byte[16384];
////				while((sRead = bufferedInputStream.read(data, 0, data.length)) != -1) {
////					arrayOutputStream.write(data, 0, sRead);
////				}
////				arrayOutputStream.flush();
////			}
////			
////			jarFile.close();
////		} catch (IOException e) {
////			e.printStackTrace();
////		}
////	}
////	
////	/**
////	 * Methode permettant de trouver une classe
////	 * dans un fichier zip
////	 * 
////	 * @param name
////	 * @param arrayOutputStream
////	 * @param file
////	 */
////	private void findClassInZipFile(final String name, final ByteArrayOutputStream arrayOutputStream, File file) {
////		try {
////			ZipFile zipFile = new ZipFile(file);
////			Enumeration<? extends ZipEntry> entries = zipFile.entries();
////			ZipEntry zipEntryToRead = null;
////			String pathToSearch = name.replace('.', File.separatorChar) + ".class";
////			while (entries.hasMoreElements()) {
////			  ZipEntry entry = entries.nextElement();
////			  if (entry.getName().endsWith(pathToSearch)) {
////				  System.out.println("found class : " + entry.getName());
////				  zipEntryToRead = entry;
////			  }
////			}
////			
////			if(zipEntryToRead != null) {
////				BufferedInputStream bufferedInputStream = new BufferedInputStream(zipFile.getInputStream(zipEntryToRead));
////				int sRead;
////				byte[] data = new byte[16384];
////				while((sRead = bufferedInputStream.read(data, 0, data.length)) != -1) {
////					arrayOutputStream.write(data, 0, sRead);
////				}
////				arrayOutputStream.flush();
////			}
////			
////			zipFile.close();
////		} catch (IOException e) {
////			e.printStackTrace();
////		}
////	}
////
////	/**
////	 * Methode permettant de charger les donnees
////	 * d'une classe 
////	 * 
////	 * @param name
////	 * @return tableau de byte des donnees
////	 * @throws ClassNotFoundException
////	 */
////	private byte[] loadClassData(final String name) throws ClassNotFoundException {
////		final ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
////
////		for(int i = 0; i < path.size() && arrayOutputStream.size() == 0; i++) {
////			File file = path.get(i);
////			if(file.isDirectory()) {
////				findClassInDirectory(name, arrayOutputStream, file);
////			}
////			else if(file.isFile() && file.getName().endsWith(".zip")) {
////				findClassInZipFile(name, arrayOutputStream, file);
////			}
////			else if(file.isFile() && file.getName().endsWith(".jar")) {
////				findClassInJarFile(name, arrayOutputStream, file);
////			}
////		}
////		
////		if(arrayOutputStream.size() == 0) {
////			return new byte[0];
////		}
////		
////		return arrayOutputStream.toByteArray();
////	}
////}
//
//
package loader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.security.SecureClassLoader;
import java.util.ArrayList;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * ClassLoader qui charge une classe se trouvant dans une liste de paths.
 * @author Dragos
 *
 */
public class ClassLoader extends  SecureClassLoader {

	public String paths ;
	
	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		return super.loadClass(name);
	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		byte[] b = loadClassData(name);
		return super.defineClass(name, b, 0, b.length);
	}
	
	public void addPath(String path){
		paths = path;
	}
	
	/**
	 * Cette méthode permet de charger le contenu de la classe (dont le 
	 * nom est passé en paramètre) dans un tableau de byte. Selon l'endroit où
	 * se trouve cette classe, la façon de la charger diffère. 
	 * @param le nom de la classe à charger
	 * @return byte[] --> le bytecode de la classe à charger
	 * @throws ClassNotFoundException
	 */
	private byte[] loadClassData(String name) throws ClassNotFoundException {
		
		File fichier = new File(paths);

		try {
			return Files.readAllBytes(fichier.toPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

}