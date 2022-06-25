package fr.tangv.nestmc.game.controller;

import org.bukkit.entity.Player;

/**
 * @author tangv
 * Represente un siege pour un playerController
 */
public interface SofaController {

	/**
	 * Permet de récupéré le propriétaire du siège
	 * @return
	 */
	public PlayerController getOwner();
	
	/**
	 * Permet de créé le siège
	 * @param first
	 */
	public void create(boolean first);
	
	/**
	 * Permet d'afficher le siège a un joueur
	 * @param player le joueur a qui on affiche le siège
	 */
	public void show(Player player);
	
	/**
	 * Permet de cacher le siège a un joueur
	 * @param player le joueur a qui on cache le siège
	 */
	public void hide(Player player);
	
	/**
	 * Permet de détruire le siège
	 * @param first
	 */
	public void destruct();
	
}
