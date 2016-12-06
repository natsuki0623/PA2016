////package plateau;
////
////import java.awt.Color;
////import java.awt.Component;
////import java.awt.GridLayout;
////import java.awt.Label;
////import java.awt.List;
////import java.io.File;
////import java.util.ArrayList;
////import loader.ClassLoader;
////
////import javax.swing.BorderFactory;
////import javax.swing.BoxLayout;
////import javax.swing.JButton;
////import javax.swing.JComboBox;
////import javax.swing.JFrame;
////import javax.swing.JPanel;
////import javax.swing.border.Border;
////
////public class PluginSelecter {
////	String[] listRobot = { "Manon", "Flo", "Antoine" };
////	String[] listAttack = { "Cac", "midRange", "longRange" };
////	String[] listMove = { "slow", "middle", "speed" };
////	String[] listGraphic = { "Yoda", "Pikachu", "Ornithorynque" };
////	private ArrayList<File> listPlugins;
////	private final String chemin = "C:\\Users\\antoi\\Desktop\\PA\\PA2016\\plugins\\";
////
////	private void remplirListePlugins(File file) {
////		// Liste des fichiers dans le répertoire :
////		File[] listFichiers = file.listFiles();
////
////		// On parcourt cette liste de fichiers :
////		for (File fichier : listFichiers) {
////			// Si le fichier est un répertoire :
////			if (fichier.isDirectory()) {
////				// On rappelle la méthode
////				remplirListePlugins(fichier);
////			} else
////			// Si le fichier est un .class
////			if (fichier.getAbsolutePath().endsWith(".class")) {
////				// On ajoute le fichier à la liste des plugins
////				listPlugins.add(fichier);
////			}
////		}
////
////	}
////
////	private void chargerClass(){
////	// On instantie le classeLoader
////	//ClassLoader cl = new ClassLoader(chemin);
////	ClassLoader c1 = new ClassLoader(listPlugins);
////		
////	File fichier = new File(chemin);
////
////	
////	// Nom du fichier
////	String name = "plugins.pluginMouvement." + fichier.getName().replace(".class", "");
////
////	// On charge la classe
////	//Class<?> pluginClass = c1.findClass(name);
////	}
////
////	public PluginSelecter() {
////		super();
////
////		JFrame t = new JFrame();
////
////		t.setSize(400, 700);
////		JPanel listPane = new JPanel();
////
////		listPane.setLayout(new BoxLayout(listPane, BoxLayout.Y_AXIS));
////		Border blackline = BorderFactory.createLineBorder(Color.black, 1);
////
////		JButton validate = new JButton();
////		validate.setText("OK");
////		validate.setSize(400, 30);
////
////		listPane.add(new Label("Selectionner robot"));
////		listPane.add(new JComboBox(listRobot));
////		listPane.add(new Label("Selectionner type d'attaque"));
////		listPane.add(new JComboBox(listAttack));
////		listPane.add(new Label("Selectionner type de mouvement"));
////		listPane.add(new JComboBox(listMove));
////		listPane.add(new Label("Selectionner type de graphisme"));
////		listPane.add(new JComboBox(listAttack));
////		listPane.add(validate);
////
////		listPane.setBorder(blackline);
////		t.add(listPane);
////		t.setVisible(true);
////		t.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
////	}
////
////	public static void main(String[] args) {
////		PluginSelecter p = new PluginSelecter();
////	}
////
////}
//
//package loader;
//
//import java.awt.Color;
//import java.awt.Label;
//import java.awt.LayoutManager;
//import java.io.File;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.util.ArrayList;
//
//import javax.swing.BorderFactory;
//import javax.swing.BoxLayout;
//import javax.swing.JButton;
//import javax.swing.JComboBox;
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.border.Border;
//
//import IPlugin.IAttack;
//import IPlugin.IDrawing;
//import IPlugin.IMovement;
//
///**
// * La classe qui permet de charger et d'utiliser les différents plugins
// * 
// * @author Karl
// *
// */
//public class PluginSelecter {
//
//	/** L'instance du plugin d'attaque */
//	IAttack pluginAttaque;
//	/** L'instance du plugin de déplacement */
//	IMovement pluginDeplacement;
//	/** La liste des instances des plugins graphismes */
//	ArrayList<IDrawing> listPluginsGraphisme;
//	ArrayList<IAttack> listPluginsAttack;
//	ArrayList<IMovement> listPluginsMove;
//	static File lefile = new File("C:\\\\Users\\antoi\\Desktop\\PA\\PA2016\\plugins\\target\\classes");
//
//	ArrayList<File> listPlugins;
//
//	String[] listRobot = { "Manon", "Flo", "Antoine" };
//	String[] listAttack = { "Cac", "midRange", "longRange" };
//	String[] listMove = { "slow", "middle", "speed" };
//	String[] listGraphic = { "Yoda", "Pikachu", "Ornithorynque" };
//
//	/**
//	 * Constructeur de la classe {@link PluginSelecter}
//	 */
//	public PluginSelecter() {
//		super();
//
//		JFrame t = new JFrame();
//
//		t.setSize(400, 700);
//		JPanel listPane = new JPanel();
//
//		listPane.setLayout((LayoutManager) new BoxLayout(listPane, BoxLayout.Y_AXIS));
//		Border blackline = BorderFactory.createLineBorder(Color.black, 1);
//
//		JButton validate = new JButton();
//		validate.setText("OK");
//		validate.setSize(400, 30);
//
//		listPluginsGraphisme = new ArrayList<IDrawing>();
//		listPlugins = new ArrayList<>();
//
//		this.remplirListePlugins(lefile);
//		this.chargerPlugin(lefile.getAbsolutePath(), TypePlugin.PluginAttack);
//		
////		listPane.add(new Label("Selectionner robot"));
////		listPane.add(new JComboBox(listRobot));
////		listPane.add(new Label("Selectionner type d'attaque"));
////		listPane.add(new JComboBox(listAttack));
////		listPane.add(new Label("Selectionner type de mouvement"));
////		listPane.add(new JComboBox(listMove));
////		listPane.add(new Label("Selectionner type de graphisme"));
////		listPane.add(new JComboBox(listAttack));
////		listPane.add(validate);
//
//		listPane.setBorder(blackline);
//		t.add(listPane);
//		t.setVisible(true);
//		t.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	}
//
//	public ArrayList<File> getListePluginsFromJar(File file) {
//		// Liste des plugins
//		listPlugins = new ArrayList<>();
//
//		remplirListePlugins(lefile);
//		//
//		// // Si c'est un .jar ou un .zip
//		// if (file.getAbsolutePath().endsWith(".jar") ||
//		// file.getAbsolutePath().endsWith(".zip")) {
//		//
//		// ZipInputStream zip;
//		//
//		// try {
//		// zip = new ZipInputStream(new FileInputStream(file));
//		//
//		// ZipEntry entry;
//		// // Parcours de tous les éléments du jar
//		// while ((entry = zip.getNextEntry()) != null) {
//		// // Si c'est un .class
//		// if (entry.getName().endsWith(".class")) {
//		// // On ajoute le nom du plugins à la liste
//		// listPlugins.add(entry.getName());
//		// }
//		// }
//		// } catch (FileNotFoundException e) {
//		// // TODO Auto-generated catch block
//		// e.printStackTrace();
//		// } catch (IOException e) {
//		// // TODO Auto-generated catch block
//		// e.printStackTrace();
//		// }
//
//		// }
//		return listPlugins;
//
//	}
//
//	private void remplirListePlugins(File file) {
//		// Liste des fichiers dans le répertoire :
//		File[] listFichiers = file.listFiles();
//
//		// On parcourt cette liste de fichiers :
//		for (File fichier : listFichiers) {
//			// Si le fichier est un répertoire :
//			if (fichier.isDirectory()) {
//				// On rappelle la méthode
//				remplirListePlugins(fichier);
//			} else
//			// Si le fichier est un .class
//			if (fichier.getAbsolutePath().endsWith(".class")) {
//				// On ajoute le fichier à la liste des plugins
//				listPlugins.add(fichier);
//			}
//		}
//
//	}
//
//	/**
//	 * Méthode qui charge un plugin
//	 * 
//	 * @param chemin
//	 *            nom du plugin
//	 * @param typePlugin
//	 *            type du plugin
//	 */
//	public boolean chargerPlugin(String chemin, TypePlugin typePlugin) {
//		try {
//			
//			// On instantie le classeLoader
//			ClassLoader cl = new ClassLoader(chemin);
//
//			
//			//
//			File fichier = new File(chemin);
//
//			// Nom du fichier
//			String name = "plugins." + typePlugin.toString().toLowerCase() + "."
//					+ fichier.getName().replace(".class", "");
//
//			// On charge la classe
//			Class<?> pluginClass = cl.findClass(name);
//
//			// Si c'est un plugin d'attaque :
//			if (typePlugin == TypePlugin.PluginAttack) {
//				// On stocke le plugin d'attaque
//				pluginAttaque = (IAttack) pluginClass.newInstance();
//				
//			}
//
//			// Si c'est un plugin de déplacement :
//			if (typePlugin == TypePlugin.PluginMovement) {
//				// On stocke le plugin de déplacement
//				pluginDeplacement = (IMovement) pluginClass.newInstance();
//			}
//
//			// Si c'est un plugin de graphisme :
//			if (typePlugin == TypePlugin.PluginDrawing) {
//				// On stocke le plugin Graphisme avec les autres
//				IDrawing plugin = (IDrawing) pluginClass.newInstance();
//
//				// On l'ajoute dans la liste des plugins Graphisme
//				listPluginsGraphisme.add(plugin);
//				
//			}
//			return true;
//
//		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
//			System.out.println("Flingué barnini");
//			e.printStackTrace();
//			return false;
//		}
//
//	}
//
//	/**
//	 * Méthode qui retourne une couleur
//	 * 
//	 * @return
//	 */
//	public Color getCouleurRobot() {
//
//		for (IDrawing plugin : listPluginsGraphisme) {
//			// La méthode du plugin qui permet de choisir la couleur du robot
//			try {
//				// On r
//				Method[] methods = listPluginsGraphisme.get(0).getClass().getMethods();
//
//				for (Method m : methods) {
//					if (m.getName().equals("getCouleur")) {
//						Color couleur = (Color) m.invoke(listPluginsGraphisme.get(0));
//						return couleur;
//					}
//				}
//
//			} catch (SecurityException e) {
//				e.printStackTrace();
//			} catch (IllegalAccessException e) {
//				e.printStackTrace();
//			} catch (IllegalArgumentException e) {
//				e.printStackTrace();
//			} catch (InvocationTargetException e) {
//				e.printStackTrace();
//			}
//
//		}
//
//		return Color.WHITE;
//	}
//
//	/**
//	 * Méthode qui permet au robot d'attaquer un autre robot
//	 * 
//	 * @param grille
//	 */
//	// public Robot attaquer(Grille grille, Robot robot) {
//	// try {
//	// // La méthode du plugin qui permet de choisir une cible
//	// Method m = pluginAttaque.getClass().getMethod("choisirCible",
//	// IGrille.class, IRobot.class);
//	//
//	// // Cette méthode retourne un robot "cible"
//	// Robot robotCible = (Robot) m.invoke(pluginAttaque, grille, robot);
//	//
//	// // On retourne le robot choisit (null si aucun)
//	// return robotCible;
//	//
//	// } catch (IllegalAccessException | IllegalArgumentException |
//	// InvocationTargetException | NoSuchMethodException
//	// | SecurityException e) {
//	// e.printStackTrace();
//	// }
//	// // Le robot n'attaque pas
//	// return null;
//	// }
//	//
//	// /**
//	// * Méthode qui permet au robot de se déplacer sur la grille
//	// *
//	// * @param grille
//	// */
//	// public Point seDeplacer(Grille grille, Robot robot) {
//	// try {
//	// // La méthode du plugin qui permet de choisir un déplacement
//	// Method m = pluginDeplacement.getClass().getMethod("choisirDeplacement",
//	// IGrille.class, IRobot.class);
//	//
//	// // Point choisie par le plugin
//	// Point posChoisie = (java.awt.Point) m.invoke(pluginDeplacement, grille,
//	// robot);
//	//
//	// return posChoisie;
//	// } catch (NoSuchMethodException | SecurityException |
//	// IllegalAccessException | IllegalArgumentException
//	// | InvocationTargetException e) {
//	// e.printStackTrace();
//	// }
//	// // On retourne sa position initiale
//	// return robot.getPosition();
//	// }
//	//
//	// /**
//	// * Méthode qui permet de dessiner le robot
//	// *
//	// * @param g
//	// * @param caseRobot
//	// */
//	// public void dessiner(Graphics g, Case caseRobot) {
//	// try {
//	// for (IPluginGraphisme plugin : listPluginsGraphisme) {
//	// Method m = plugin.getClass().getMethod("paint", Graphics.class,
//	// ICase.class);
//	//
//	// // Méthode qui va décider elle même le robot
//	// m.invoke(plugin, g, caseRobot);
//	// }
//	// } catch (NoSuchMethodException | SecurityException |
//	// IllegalAccessException | IllegalArgumentException
//	// | InvocationTargetException e) {
//	// e.printStackTrace();
//	// } catch (NullPointerException e) {
//	// e.printStackTrace();
//	// }
//	//
//	// }
//
//	public void setListPlugins(ArrayList<File> listPluginsChoisis) {
//		listPlugins = listPluginsChoisis;
//
//	}
//
//	public ArrayList<File> getListPlugins() {
//		return listPlugins;
//
//	}
//	
//	public static void main(String[] args) {
//		
//		PluginSelecter p = new PluginSelecter();
//		p.remplirListePlugins(lefile);
//		
//	}
//
//}

/**
 * j'appel la methode load du plugin loader
 * load appel init
 * 
 */

package loader;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.swing.JColorChooser;
import javax.swing.JFileChooser;

import IPlugin.IPlugin;

/**
 * PluginsLoader est le moteur de chargement des plugins. A partir d'un fichier
 * 'base', il va chercher les classes dont au moins une des interfaces
 * correspond aux interfaces des plugins.
 * 
 * @author Dragos
 *
 */
public class PluginsLoader {

	private ArrayList<Class<?>> plugins;
	private Class<?> typePlugin;

	public PluginsLoader() {
		this.plugins = new ArrayList<Class<?>>();
		this.typePlugin = IPlugin.class;
	}

	public PluginsLoader(Class<?> typePlugin) {
		this.plugins = new ArrayList<Class<?>>();
		this.typePlugin = typePlugin;
	}

	public ArrayList<Class<?>> getPlugins() {
		return this.plugins;
	}

	public Class<?> getTypePlugin() {
		return this.typePlugin;
	}

	public void setTypePlugin(Class<?> typePlugin) {
		this.typePlugin = typePlugin;
	}

	/**
	 * Cette méthode permet de créer des instances des plugins.
	 * 
	 * @param fichier
	 *            f (l'endroit où les classes des plugins se trouvent)
	 * @return un tableau de plugins (instances des classes des plugins)
	 * @throws ClassNotFoundException
	 */
	public IPlugin[] load(File f) throws ClassNotFoundException {
		this.init(f);

		IPlugin[] tmpPlugins = new IPlugin[plugins.size()];
		try {
			for (int i = 0; i < tmpPlugins.length; i++) {
				tmpPlugins[i] = (IPlugin) ((Class) plugins.get(i)).newInstance();
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return tmpPlugins;

	}

	/**
     * Chargement des plugins se trouvant dans f. Cette méthode est appelée lors
     * du des plugins.
     * 
     * @param f
     * @throws ClassNotFoundException
     */
    public void init(File f) throws ClassNotFoundException {
	// On crée une instance du GeneralClassLoader et on ajoute f à sa liste
	// de paths
	ClassLoader cl = new ClassLoader();
	
	// Initialisation des variables
	String className = "";
	int startIndex = f.toString().length() + 1;
	int endIndex;

	// Si l'on cherche les plugins dans un répertoire
	if (f.isDirectory()) {
	    /*
	     * A l'aide du SimpleFileVisitor on cherche toutes les fichiers
	     * .class se retrouvant dans l'arborescence du f.
	     */
	    final List<File> files = new ArrayList<File>();
	    try {
		Files.walkFileTree(f.toPath(), new SimpleFileVisitor<Path>() {
		    @Override
		    public FileVisitResult visitFile(Path file,
			    BasicFileAttributes attrs) throws IOException {
			if (file.toString().endsWith(".class")) {

			    files.add(file.toFile());
			}
			return FileVisitResult.CONTINUE;
		    }

		});
	    } catch (IOException e) {
		e.printStackTrace();
	    }

	    // On parcourt la liste de classes
	    for (File file : files) {
		endIndex = file.toString().length() - 6; // Cela sert à enlever
							 // le '.class'
		className = file.toString().substring(startIndex, endIndex)
			.replace("\\", ".");
		
		cl.addPath(f.getPath()+"/"+className.replace(".", "\\")+".class");
		String [] test =f.getAbsolutePath().split("\\\\");
		String type = test[test.length-1];
		
		className = "pluginAttack."+className;
		
		Class<?> tmpClass = cl.findClass(className);
		// On regarde si la classe a des interfaces, sinon, ce n'est
		// même pas la peine d'y regarder
 		if (tmpClass.getInterfaces().length != 0) {
		    // On parcourt les interfaces
		    for (int i = 0; i < tmpClass.getInterfaces().length; i++) {
			if (typePlugin.isAssignableFrom(tmpClass
				.getInterfaces()[i])) {
			    plugins.add(cl.loadClass(className));
			}
		    }
		}
	    }
	}
	// Si l'on cherche les plugins dans une archive .jar
	else if (f.isFile() && f.getName().endsWith(".jar")) {
	    try {
		JarFile jar = new JarFile(f);
		Enumeration<? extends JarEntry> jarEntries = jar.entries();
		// On parcourt les entrées du fichier jar
		while (jarEntries.hasMoreElements()) {
		    JarEntry jarEntry = jarEntries.nextElement();
		    if (jarEntry.toString().endsWith(".class")) {
			endIndex = jarEntry.toString().length() - 6; // Cela
								     // sert à
								     // enlever
								     // le
								     // '.class'
			className = jarEntry.toString().substring(0, endIndex)
				.replace("/", ".");
			Class<?> tmpClass = Class.forName(className, true, cl);
			// on regarde si la classe a des interfaces, sinon, ce
			// n'est même pas la peine d'y regarder
			if (tmpClass.getInterfaces().length != 0) {
			    // On parcourt les interfaces
			    for (int i = 0; i < tmpClass.getInterfaces().length; i++) {
				if (typePlugin.isAssignableFrom(tmpClass
					.getInterfaces()[i]))
				    plugins.add(cl.loadClass(className));
			    }
			}
		    }
		}
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}
	// Si l'on cherche les plugins dans une archive .zip
	else if (f.isFile() && f.getName().endsWith(".zip")) {
	    try {
		ZipFile zip = new ZipFile(f);
		Enumeration<? extends ZipEntry> zipEntries = zip.entries();

		// On parcourt les entrées du fichier zip
		while (zipEntries.hasMoreElements()) {
		    ZipEntry zipEntry = zipEntries.nextElement();
		    if (zipEntry.toString().endsWith(".class")) {
			endIndex = zipEntry.toString().length() - 6; 
								// Cela
								     // sert à
								     // enlever
								     // le
								     // '.class'
			className = zipEntry.toString().substring(0, endIndex)
				.replace("/", ".");
			Class<?> tmpClass = Class.forName(className, true, cl);
			// On regarde si la classe a des interfaces, sinon, ce
			// n'est même pas la peine d'y regarder
			if (tmpClass.getInterfaces().length != 0) {
			    // on parcourt les interfaces
			    for (int i = 0; i < tmpClass.getInterfaces().length; i++) {
				// On regarde si l'interface est
				if (typePlugin.isAssignableFrom(tmpClass
					.getInterfaces()[i]))
				    plugins.add(cl.loadClass(className));
			    }
			}
		    }
		}
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}
    }

	public static void main(String[] args) throws ClassNotFoundException {
		JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(new java.io.File("."));
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fc.showOpenDialog(null);
		File file1 = fc.getSelectedFile();
		System.out.println(file1.getAbsolutePath());
		PluginsLoader pl = new PluginsLoader();
		pl.init(file1);
		System.out.println(pl.getPlugins());
	}
}
