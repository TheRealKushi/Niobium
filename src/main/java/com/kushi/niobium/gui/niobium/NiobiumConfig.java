package com.kushi.niobium.gui.niobium;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name = "niobium")
public class NiobiumConfig implements ConfigData {
    public boolean enableCustomWorldGen = true;
    public boolean enableSpecialBlocks = true;
}
