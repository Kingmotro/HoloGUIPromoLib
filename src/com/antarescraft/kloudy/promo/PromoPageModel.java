package com.antarescraft.kloudy.promo;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

import com.antarescraft.kloudy.hologuiapi.HoloGUIPlugin;
import com.antarescraft.kloudy.hologuiapi.guicomponentproperties.ButtonComponentProperties;
import com.antarescraft.kloudy.hologuiapi.guicomponentproperties.LabelComponentProperties;
import com.antarescraft.kloudy.hologuiapi.guicomponents.ButtonComponent;
import com.antarescraft.kloudy.hologuiapi.guicomponents.ComponentPosition;
import com.antarescraft.kloudy.hologuiapi.guicomponents.GUIComponentFactory;
import com.antarescraft.kloudy.hologuiapi.guicomponents.GUIPage;
import com.antarescraft.kloudy.hologuiapi.guicomponents.LabelComponent;
import com.antarescraft.kloudy.hologuiapi.handlers.ClickHandler;
import com.antarescraft.kloudy.hologuiapi.handlers.GUIPageLoadHandler;
import com.antarescraft.kloudy.hologuiapi.playerguicomponents.PlayerGUIPage;
import com.antarescraft.kloudy.hologuiapi.playerguicomponents.PlayerGUIPageModel;

public class PromoPageModel extends PlayerGUIPageModel
{
	private PlayerGUIPage playerGUIPage;
		
	private ButtonComponent continueBtn;
	private ButtonComponent nextBtn;
	private ButtonComponent backBtn;
	private ButtonComponent pluginPromoButton;
	private LabelComponent pluginPromoDescription;
	
	private static HashMap<String, String[][]> imageLines = null;
	
	private int page = 0;
	
	public PromoPageModel(final HoloGUIPlugin plugin, GUIPage guiPage, final Player player, final GUIPage redirectGUIPage, final PlayerGUIPageModel redirectModel)
	{
		super(plugin, guiPage, player);
		
		// Load up the plugin images if we haven't already.
		if(imageLines == null)
		{
			imageLines = new HashMap<String, String[][]>();
			
			for(PluginPromo promo : PluginPromo.values())
			{
				imageLines.put(promo.pluginName(), plugin.loadImage(promo.imageName(), 18, 18, true));
			}
		}
		
		guiPage.registerPageLoadHandler(player, new GUIPageLoadHandler()
		{
			@Override
			public void onPageLoad(PlayerGUIPage _playerGUIPage)
			{
				playerGUIPage = _playerGUIPage;
				
				// Render the first plugin promo on page load
				PluginPromo promo = PluginPromo.values()[0];
				
				ButtonComponentProperties properties = new ButtonComponentProperties();
				properties.setId("plugin-promo-btn");
				properties.setIcon(promo.imageName());
				properties.setSymmetrical(true);
				properties.setPosition(new ComponentPosition(0, 0));
				properties.setLabelDistance(8);
				properties.setLabelZoomDistance(2);
				properties.setAlwaysShowLabel(true);
				properties.setLabel(promo.pluginName());
				properties.setExecuteCommandAsConsole(true);
				properties.setOnclickSound(Sound.UI_BUTTON_CLICK);
				properties.setOnclick(String.format("tellraw %s {\"text\":\"\",\"extra\":[{\"text\":\"Click Here: \",\"color\":\"bold\"},{\"text\":\"%s\",\"color\":\"underline\",\"clickEvent\":{\"action\":\"open_url\",\"value\":\"%s\"}}]}", 
						player.getName(), promo.pluginName(), promo.link()));
				
				pluginPromoButton = GUIComponentFactory.createButtonComponent(plugin, properties);
				
				playerGUIPage.renderComponent(pluginPromoButton);
				
				// Render the plugin description label
				LabelComponentProperties labelProperties = new LabelComponentProperties();
				labelProperties.setId("plugin-promo-description");
				labelProperties.setLabelDistance(8);
				labelProperties.setPosition(new ComponentPosition(0, -0.58));
				labelProperties.setLines(new ArrayList<String>());
				for(String line : promo.description())
				{
					labelProperties.getLines().add(line);
				}
				
				pluginPromoDescription = GUIComponentFactory.createLabelComponent(plugin, labelProperties);
				
				playerGUIPage.renderComponent(pluginPromoDescription);
			}
		});
		
		continueBtn = (ButtonComponent)guiPage.getComponent("continue-btn");
		nextBtn = (ButtonComponent)guiPage.getComponent("next-btn");
		backBtn = (ButtonComponent)guiPage.getComponent("back-btn");
		
		continueBtn.registerClickHandler(player, new ClickHandler()
		{
			@Override
			public void onClick()
			{
				// Redirect guiPage was passed in
				if(redirectGUIPage != null)
				{
					plugin.getHoloGUIApi().openGUIPage(plugin, player, redirectGUIPage.getId());
				}
				// Redirect model was passed in
				else if(redirectModel != null)
				{
					plugin.getHoloGUIApi().openGUIPage(plugin, player, redirectModel);
				}
			}
		});
		
		nextBtn.registerClickHandler(player, new ClickHandler()
		{
			@Override
			public void onClick()
			{
				page++;
				
				playerGUIPage.renderComponent(backBtn);
				
				// Reached the end of the plugin promos, remove the next button
				if(page >= PluginPromo.values().length - 1)
				{
					playerGUIPage.removeComponent("next-btn");
				}
				
				renderPluginPromo();
			}
		});
		
		backBtn.registerClickHandler(player, new ClickHandler()
		{
			@Override
			public void onClick()
			{
				playerGUIPage.renderComponent(nextBtn);
				
				page--;
				
				// Reached the first plugin promo, remove the back button
				if(page == 0)
				{
					playerGUIPage.removeComponent("back-btn");
				}
				
				renderPluginPromo();
			}
		});
	}
	
	private void renderPluginPromo()
	{
		PluginPromo promo = PluginPromo.values()[page];
		
		playerGUIPage.removeComponent("plugin-promo-btn");
		playerGUIPage.removeComponent("plugin-promo-description");
		
		pluginPromoButton.setLines(imageLines.get(promo.pluginName()));
		pluginPromoButton.getProperties().setLabel(promo.pluginName());
		pluginPromoButton.getProperties().setOnclick(String.format("tellraw %s {\"text\":\"\",\"extra\":[{\"text\":\"Click Here: \",\"color\":\"bold\"},{\"text\":\"%s\",\"color\":\"underline\",\"clickEvent\":{\"action\":\"open_url\",\"value\":\"%s\"}}]}", 
				player.getName(), promo.pluginName(), promo.link()));
		
		pluginPromoDescription.getProperties().setLines(new ArrayList<String>());
		for(String line : promo.description())
		{
			pluginPromoDescription.getProperties().getLines().add(line);
		}
		
		playerGUIPage.renderComponent(pluginPromoButton);
		playerGUIPage.renderComponent(pluginPromoDescription);
	}
	
	public String pluginName()
	{
		return plugin.getName();
	}
}