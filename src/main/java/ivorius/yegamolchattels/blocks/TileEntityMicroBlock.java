/*
 *  Copyright (c) 2014, Lukas Tenbrink.
 *  * http://lukas.axxim.net
 */

package ivorius.yegamolchattels.blocks;

import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import ivorius.ivtoolkit.blocks.BlockArea;
import ivorius.ivtoolkit.blocks.BlockCoord;
import ivorius.ivtoolkit.blocks.IvBlockCollection;
import ivorius.ivtoolkit.blocks.IvTileEntityHelper;
import ivorius.ivtoolkit.network.IvNetworkHelperServer;
import ivorius.ivtoolkit.network.PartialUpdateHandler;
import ivorius.ivtoolkit.tools.MCRegistryDefault;
import ivorius.yegamolchattels.YeGamolChattels;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Created by lukas on 11.07.14.
 */
public class TileEntityMicroBlock extends TileEntity implements PartialUpdateHandler
{
    public static final int MICROBLOCKS_PER_BLOCK_X = 8;
    public static final int MICROBLOCKS_PER_BLOCK_Y = 8;
    public static final int MICROBLOCKS_PER_BLOCK_Z = 8;
    private IvBlockCollection blockCollection;

    private boolean[] isSideOpaque = new boolean[6];

    private boolean destroyByConverting;

    public TileEntityMicroBlock()
    {
        this.blockCollection = new IvBlockCollection(MICROBLOCKS_PER_BLOCK_X, MICROBLOCKS_PER_BLOCK_Y, MICROBLOCKS_PER_BLOCK_Z);
    }

    public IvBlockCollection getBlockCollection()
    {
        return blockCollection;
    }

    public void setBlockCollection(IvBlockCollection blockCollection)
    {
        this.blockCollection = blockCollection;
    }

    public boolean isSideOpaque(ForgeDirection direction)
    {
        return isSideOpaque[direction.ordinal()];
    }

    public void markCacheInvalid()
    {
        markCacheInvalid(false);
    }

    private void markCacheInvalid(boolean fromNBT)
    {
        int[] sizeX = new int[]{1, blockCollection.height, blockCollection.length};
        int[] sizeY = new int[]{blockCollection.width, 1, blockCollection.length};
        int[] sizeZ = new int[]{blockCollection.width, blockCollection.height, 1};
        BlockCoord zeroCoord = new BlockCoord(0, 0, 0);

        isSideOpaque[ForgeDirection.DOWN.ordinal()] = areAllOpaque(BlockArea.areaFromSize(zeroCoord, sizeY));
        isSideOpaque[ForgeDirection.UP.ordinal()] = areAllOpaque(BlockArea.areaFromSize(new BlockCoord(0, blockCollection.height - 1, 0), sizeY));
        isSideOpaque[ForgeDirection.NORTH.ordinal()] = areAllOpaque(BlockArea.areaFromSize(zeroCoord, sizeZ));
        isSideOpaque[ForgeDirection.EAST.ordinal()] = areAllOpaque(BlockArea.areaFromSize(new BlockCoord(blockCollection.width - 1, 0, 0), sizeX));
        isSideOpaque[ForgeDirection.SOUTH.ordinal()] = areAllOpaque(BlockArea.areaFromSize(new BlockCoord(0, 0, blockCollection.length - 1), sizeZ));
        isSideOpaque[ForgeDirection.WEST.ordinal()] = areAllOpaque(BlockArea.areaFromSize(zeroCoord, sizeX));

        if (worldObj != null)
        {
            if (!worldObj.isRemote && !fromNBT)
            {
                IvNetworkHelperServer.sendTileEntityUpdatePacket(this, "microBlocks", YeGamolChattels.network);
            }
            else if (worldObj.isRemote)
            {
                worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
            }
        }
    }

    public boolean validateBeingMicroblock()
    {
        boolean allSame = true;
        Block curBlock = blockCollection.getBlock(new BlockCoord(0, 0, 0));
        int curMeta = blockCollection.getMetadata(new BlockCoord(0, 0, 0));
        for (BlockCoord coord : blockCollection)
        {
            if (blockCollection.getBlock(coord) != curBlock || blockCollection.getMetadata(coord) != curMeta)
            {
                allSame = false;
                break;
            }
        }

        if (allSame)
        {
            destroyByConverting = true;
            worldObj.setBlock(xCoord, yCoord, zCoord, curBlock, curMeta, 3);
        }

        return !allSame;
    }

    private boolean areAllOpaque(BlockArea area)
    {
        for (BlockCoord coord : area)
        {
            if (!blockCollection.getBlock(coord).isOpaqueCube())
                return false;
        }

        return true;
    }

    public boolean isDestroyByConverting()
    {
        return destroyByConverting;
    }

    @Override
    public void writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);

        writeSyncToNBT(compound);
    }

    protected void writeSyncToNBT(NBTTagCompound compound)
    {
        compound.setTag("microblocks", blockCollection.createTagCompound());
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);

        readSyncFromNBT(compound);
    }

    protected void readSyncFromNBT(NBTTagCompound compound)
    {
        blockCollection = new IvBlockCollection(compound.getCompoundTag("microblocks"), MCRegistryDefault.INSTANCE);
        markCacheInvalid(true);
    }

    @Override
    public Packet getDescriptionPacket()
    {
        NBTTagCompound compound = new NBTTagCompound();
        writeSyncToNBT(compound);
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, compound);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
    {
        readSyncFromNBT(pkt.func_148857_g());
    }

    @Override
    public void writeUpdateData(ByteBuf buffer, String context)
    {
        if ("microBlocks".equals(context))
        {
            ByteBufUtils.writeTag(buffer, blockCollection.createTagCompound());
        }
    }

    @Override
    public void readUpdateData(ByteBuf buffer, String context)
    {
        if ("microBlocks".equals(context))
        {
            blockCollection = new IvBlockCollection(ByteBufUtils.readTag(buffer), MCRegistryDefault.INSTANCE);
            markCacheInvalid(false);
        }
    }
}
