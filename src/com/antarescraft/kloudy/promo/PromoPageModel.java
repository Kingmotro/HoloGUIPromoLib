package com.antarescraft.kloudy.promo;

import org.bukkit.entity.Player;

import com.antarescraft.kloudy.hologuiapi.HoloGUIPlugin;
import com.antarescraft.kloudy.hologuiapi.guicomponents.ButtonComponent;
import com.antarescraft.kloudy.hologuiapi.guicomponents.GUIPage;
import com.antarescraft.kloudy.hologuiapi.handlers.ClickHandler;
import com.antarescraft.kloudy.hologuiapi.playerguicomponents.PlayerGUIPageModel;

public class PromoPageModel extends PlayerGUIPageModel
{
	private ButtonComponent continueBtn;
	private ButtonComponent nextBtn;
	private ButtonComponent backBtn;
	
	private int page = 0;
	
	public PromoPageModel(final HoloGUIPlugin plugin, GUIPage guiPage, final Player player, final GUIPage redirectGUIPage, final PlayerGUIPageModel redirectModel)
	{
		super(plugin, guiPage, player);
		
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
	}
}