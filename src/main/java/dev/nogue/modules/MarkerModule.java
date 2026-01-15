package dev.nogue.modules;

import com.hypixel.hytale.math.vector.Transform;
import com.hypixel.hytale.math.vector.Vector3d;
import com.hypixel.hytale.protocol.packets.worldmap.MapMarker;
import com.hypixel.hytale.server.core.asset.type.gameplay.GameplayConfig;
import com.hypixel.hytale.server.core.asset.type.gameplay.WorldMapConfig;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.WorldMapTracker;
import com.hypixel.hytale.server.core.universe.world.worldmap.WorldMapManager;
import com.hypixel.hytale.server.core.util.PositionUtil;
import java.util.function.Predicate;
import javax.annotation.Nonnull;

public class MarkerModule implements WorldMapManager.MarkerProvider {
    public static final MarkerModule INSTANCE = new MarkerModule();

    private MarkerModule() {
    }

    @Override
    public void update(@Nonnull World world, @Nonnull GameplayConfig gameplayConfig, @Nonnull WorldMapTracker tracker, int chunkViewRadius, int playerChunkX, int playerChunkZ) {
        WorldMapConfig worldMapConfig = gameplayConfig.getWorldMapConfig();
        if (!worldMapConfig.isDisplayPlayers()) {
            return;
        }
        if (!tracker.shouldUpdatePlayerMarkers()) {
            return;
        }
        Player player = tracker.getPlayer();
        int chunkViewRadiusSq = chunkViewRadius * chunkViewRadius;
        Predicate<PlayerRef> playerMapFilter = tracker.getPlayerMapFilter();

        /*
         * TODO (Future Implementation):
         *
         * Clan & Alliance System
         *
         * In the future, player marker rendering logic will be handled here.
         * Instead of completely hiding all player markers, this loop will:
         *
         * - Check if the other player belongs to the same clan
         * - Check if the other player is an allied clan member
         * - Allow map markers ONLY for allied players
         *
         * This will enable tactical group play while keeping PvP, anarchy
         * and hardcore servers free from global player tracking.
         *
         * Current behavior intentionally disables all other player markers.
         */

//        for (PlayerRef otherPlayer : world.getPlayerRefs()) {
//            if (otherPlayer.getUuid().equals(player.getUuid())) continue;
//            Transform otherPlayerTransform = otherPlayer.getTransform();
//            Vector3d otherPos = otherPlayerTransform.getPosition();
//            int otherChunkX = (int)otherPos.x >> 5;
//            int chunkDiffX = otherChunkX - playerChunkX;
//            int otherChunkZ = (int)otherPos.z >> 5;
//            int chunkDiffZ = otherChunkZ - playerChunkZ;
//            int chunkDistSq = chunkDiffX * chunkDiffX + chunkDiffZ * chunkDiffZ;
//            if (chunkDistSq > chunkViewRadiusSq || playerMapFilter != null && playerMapFilter.test(otherPlayer)) continue;
//            tracker.trySendMarker(chunkViewRadius, playerChunkX, playerChunkZ, otherPos, otherPlayer.getHeadRotation().getYaw(), "Player-" + String.valueOf(otherPlayer.getUuid()), "Player: " + otherPlayer.getUsername(), otherPlayer, (id, name, op) -> new MapMarker((String)id, (String)name, "Player.png", PositionUtil.toTransformPacket(op.getTransform()), null));
//        }

        tracker.resetPlayerMarkersUpdateTimer();
    }
}
