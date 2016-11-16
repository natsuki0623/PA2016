package IPlugin;

/*
 * Interface permettant de gérer les plugins d'attaques
 */

public interface IAttack {
	public String toString();
	public int attaque();
	public int range();
}
