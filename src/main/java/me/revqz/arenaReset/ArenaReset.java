package me.revqz.arenaReset;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class ArenaReset extends JavaPlugin {


    // singelton
    private static ArenaReset instance;

    public static ArenaReset getInstance() {
        return instance;
    }

    // managers
    private SchematicManager schematicManager;

    public SchematicManager getSchematicManager() {
        return schematicManager;
    }


    // lifecycle
    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();

        // schems
        File schematicsDir = new File(getDataFolder(), "schematics");
        if (!schematicsDir.exists() && schematicsDir.mkdirs()) {
            getLogger().info("Created schematics directory.");
        }

        schematicManager = new SchematicManager(this);

        // commads
        ArenaResetCommand cmd = new ArenaResetCommand(this);
        getCommand("arena").setExecutor(cmd);
        getCommand("arena").setTabCompleter(cmd);

        getLogger().info("ArenaReset enabled.");
    }

    @Override
    public void onDisable() {
        getLogger().info("ArenaReset disabled.");
    }
}
