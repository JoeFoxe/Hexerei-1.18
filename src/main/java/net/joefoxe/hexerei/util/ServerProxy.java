package net.joefoxe.hexerei.util;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class ServerProxy implements SidedProxy {
    @Override
    public Player getPlayer() {
        return null;
    }

    @Override
    public Level getLevel() {
        return null;
    }

    @Override
    public void init() {
        //
    }

    @Override
    public void openCodexGui() {
        //
    }
}