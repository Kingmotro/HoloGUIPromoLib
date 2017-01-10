package com.antarescraft.kloudy.hologuiapi.promo;

import java.io.StringReader;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.antarescraft.kloudy.hologuiapi.HoloGUIPlugin;
import com.antarescraft.kloudy.hologuiapi.guicomponents.GUIPage;
import com.antarescraft.kloudy.plugincore.io.AsyncTaskCompleteCallback;
import com.antarescraft.kloudy.plugincore.io.IOManager;

public class HoloGUIPromo 
{
	private static final String url = "https://dl.dropboxusercontent.com/u/49309383/HoloGUI/promo-page.yml";
	
	private static GUIPage promoPage;
	
	/**
	 * Fetches the promo page from dropbox
	 */
	public static void fetchPromoPage(HoloGUIPlugin plugin)
	{
		IOManager.fetchContentFromURL(plugin, url, new AsyncTaskCompleteCallback()
		{
			@Override
			public void call(String content)
			{
				YamlConfiguration yaml = YamlConfiguration.loadConfiguration(new StringReader(content));
				
			}
		});
	}
	
	public static void displayPromoPage(Player player)
	{
		
	}
}