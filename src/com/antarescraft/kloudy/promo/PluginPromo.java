package com.antarescraft.kloudy.promo;

public enum PluginPromo
{
	STAFF_TIMESHEET("&lStaffTimesheet", "StaffTimesheet.png", 
			"https://www.spigotmc.org/resources/stafftimesheet.32719/", 
			new String[] { "&lA &9&lbeauiful&r&l & &c&lintuitive", "", "&lholographic staff management system" }),
	
	HOLO_SLOTS("&lHoloSlots", "HoloSlots.png", 
			"https://www.spigotmc.org/members/kloudy.42303/", new String[] { "&lAn &9&linnovative&r&l & &c&lfun", "", "&lholographic slot machine (Coming soon...)" }),
	
	STOPWATCH("&lStopwatch - &a&lExample HoloGUIApi Plugin", "Stopwatch.gif", 
			"https://www.spigotmc.org/resources/stopwatch-a-hologui-plugin.32595/", 
			new String[] { "&lA &9&lstylish&r&l & &c&lfunctional", "", "&lholographic stopwatch" });
	
	private String pluginName;
	private String imageName;
	private String link;
	private String[] description;
	
	PluginPromo(String pluginName, String imageName, String link, String[] description)
	{
		this.pluginName = pluginName;
		this.imageName = imageName;
		this.link = link;
		this.description = description;
	}
	
	public String pluginName()
	{
		return pluginName;
	}
	
	public String imageName()
	{
		return imageName;
	}
	
	public String link()
	{
		return link;
	}
	
	public String[] description()
	{
		return description;
	}
}