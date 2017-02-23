package com.antarescraft.kloudy.promo;

import org.bukkit.entity.Player;

import com.antarescraft.kloudy.hologuiapi.HoloGUIPlugin;
import com.antarescraft.kloudy.hologuiapi.guicomponents.GUIPage;
import com.antarescraft.kloudy.hologuiapi.playerguicomponents.PlayerGUIPageModel;

public class HoloGUIPromo 
{
	public static void openPromoPage(HoloGUIPlugin plugin, Player player, PlayerGUIPageModel model)
	{
		openPromoPage(plugin, player, null, model);
	}
	
	public static void openPromoPage(HoloGUIPlugin plugin, Player player, GUIPage guiPage)
	{
		openPromoPage(plugin, player, guiPage, null);
	}
	
	private static void openPromoPage(HoloGUIPlugin plugin, Player player, GUIPage guiPage, PlayerGUIPageModel model)
	{
		PromoPageModel promoModel = new PromoPageModel(plugin, plugin.getGUIPage("promo-page"), player, guiPage, model);
		plugin.getHoloGUIApi().openGUIPage(plugin, player, promoModel);
	}
}