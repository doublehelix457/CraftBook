package com.sk89q.craftbook.mechanics.area;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.regions.CuboidRegion;
import com.sk89q.worldedit.world.DataException;
import com.sk89q.worldedit.world.block.BlockTypes;
import org.bukkit.World;

import java.io.File;
import java.io.IOException;

public class MCEditCuboidCopy extends CuboidCopy {

    private Clipboard clipboard;

    public MCEditCuboidCopy(Vector origin, Vector size, World world) {

        super(origin, size, world);
//        clipboard = new Clipboard(size, origin);
    }

    protected MCEditCuboidCopy(World world) {

        // for loading from file
        this.world = world;
    }

    @Override
    public void save(File file) throws IOException, DataException {
//        SchematicFormat.MCEDIT.save(clipboard, file);
    }

    @Override
    protected void loadFromFile(File file) throws IOException, DataException {

//        clipboard = BuiltInClipboardFormat.MCEDIT_SCHEMATIC.getReader(file);
        origin = clipboard.getOrigin();
        size = clipboard.getDimensions();
        width = size.getBlockX();
        height = size.getBlockY();
        length = size.getBlockZ();
    }

    @Override
    public void paste() {

//        try {
            EditSession editSession = WorldEdit.getInstance().getEditSessionFactory().getEditSession(new BukkitWorld(world), -1);
            editSession.enableQueue();
//            clipboard.place(editSession, origin, false);
            editSession.flushQueue();
//        } catch (MaxChangedBlocksException e) {
            // is never thrown because we are on infinite mode
//        }
    }

    @Override
    public void clear() {

        try {
            CuboidRegion region = new CuboidRegion(origin, origin.add(size.getX() - 1, size.getY() - 1,
                    size.getZ() - 1));
            EditSession editSession = WorldEdit.getInstance().getEditSessionFactory().getEditSession(new BukkitWorld(world), -1);
            editSession.enableQueue();
            editSession.setBlocks(region, BlockTypes.AIR.getDefaultState());
            editSession.flushQueue();
        } catch (MaxChangedBlocksException e) {
            // is never thrown
        }
    }

    @Override
    public void copy() {

        EditSession editSession = WorldEdit.getInstance().getEditSessionFactory().getEditSession(new BukkitWorld(world), -1);
        editSession.enableQueue();
        // -1 means no block limit
//        clipboard.copy(editSession);
        editSession.flushQueue();
    }
}