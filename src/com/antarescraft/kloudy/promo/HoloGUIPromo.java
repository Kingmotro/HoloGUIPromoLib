package com.antarescraft.kloudy.promo;

import org.bukkit.entity.Player;

import com.antarescraft.kloudy.hologuiapi.HoloGUIPlugin;

public class HoloGUIPromo 
{
	public static void openPromoPage(HoloGUIPlugin plugin, Player player)
	{
		PromoPageModel model = new PromoPageModel(plugin, plugin.getGUIPage("promo-page"), player);
		plugin.getHoloGUIApi().openGUIPage(plugin, player, model);
	}
}