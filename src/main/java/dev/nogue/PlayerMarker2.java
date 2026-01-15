package dev.nogue;

import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import com.hypixel.hytale.server.core.universe.world.events.AddWorldEvent;
import dev.nogue.modules.MarkerModule;

import javax.annotation.Nonnull;

public class PlayerMarker2 extends JavaPlugin {
    public PlayerMarker2(@Nonnull JavaPluginInit init) {
        super(init);
    }

    @Override
    protected void setup() {
        this.getEventRegistry().registerGlobal(AddWorldEvent.class, event -> event.getWorld().getWorldMapManager().addMarkerProvider("playerIcons", MarkerModule.INSTANCE));
    }

}
