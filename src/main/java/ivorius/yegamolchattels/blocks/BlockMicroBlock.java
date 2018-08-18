/*
 *  Copyright (c) 2014, Lukas Tenbrink.
 *  * http://lukas.axxim.net
 */

package ivorius.yegamolchattels.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ivorius.ivtoolkit.blocks.BlockCoord;
import ivorius.ivtoolkit.blocks.IvBlockCollection;
import ivorius.ivtoolkit.math.AxisAlignedTransform2D;
import ivorius.yegamolchattels.items.*;
import ivorius.yegamolchattels.materials.YGCMaterials;
import ivorius.yegamolchattels.utils.IvBlockCollections;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.List;
import java.util.Random;

/**
 * Created by lukas on 11.07.14.
 */
public class BlockMicroBlock extends Block
{
    @SideOnly(Side.CLIENT)
    public ForgeDirection renderSide;
    @SideOnly(Side.CLIENT)
    public IIcon renderIcon;

    public BlockMicroBlock()
    {
        super(YGCMaterials.mixed);
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess blockAccess, int x, int y, int z)
    {
        setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
    }

    @Override
    public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side)
    {
        if (world.getTileEntity(x, y, z) != null) {
            if (side != null) {
                return ((TileEntityMicroBlock) world.getTileEntity(x, y, z)).isSideOpaque(side);
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int meta)
    {
        if (!world.isRemote)
        {
            TileEntityMicroBlock microBlock = (TileEntityMicroBlock) world.getTileEntity(x, y, z);

            if (microBlock.shouldDropAsItem())
            {
                ItemStack stack = new ItemStack(Item.getItemFromBlock(this));
                ItemMicroBlock.setMicroBlock(stack, microBlock.getBlockCollection());
                world.spawnEntityInWorld(new EntityItem(world, x + 0.5, y + 0.5, z + 0.5, stack));
            }
        }

        super.breakBlock(world, x, y, z, block, meta);
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
    {
        TileEntityMicroBlock microBlock = (TileEntityMicroBlock) world.getTileEntity(x, y, z);

        ItemStack stack = new ItemStack(Item.getItemFromBlock(this));
        ItemMicroBlock.setMicroBlock(stack, microBlock.getBlockCollection());
        return stack;
    }

    @Override
    public void onBlockHarvested(World world, int x, int y, int z, int side, EntityPlayer player)
    {
        super.onBlockHarvested(world, x, y, z, side, player);

        ItemStack heldItem = player.getHeldItem();
        if (heldItem != null && heldItem.getItem() == YGCItems.clubHammer)
        {
            TileEntity tileEntity = world.getTileEntity(x, y, z);
            if (tileEntity instanceof TileEntityMicroBlock)
                dropAllMicroblockFragments((TileEntityMicroBlock) tileEntity, ItemClubHammer.FRAGMENT_DROP_CHANCE);
        }
    }

    public static void dropAllMicroblockFragments(TileEntityMicroBlock tileEntityMicroBlock, float dropChance)
    {
        World world = tileEntityMicroBlock.getWorldObj();
        int x = tileEntityMicroBlock.xCoord;
        int y = tileEntityMicroBlock.yCoord;
        int z = tileEntityMicroBlock.zCoord;
        IvBlockCollection collection = tileEntityMicroBlock.getBlockCollection();

        if (!world.isRemote)
        {
            for (BlockCoord coord : collection)
            {
                Block block = collection.getBlock(coord);
                if (block.getMaterial() != Material.air && world.rand.nextFloat() < dropChance)
                {
                    ItemStack drop = new ItemStack(YGCItems.blockFragment);
                    ItemBlockFragment.setFragment(drop, new ItemChisel.BlockData(block, collection.getMetadata(coord)));
                    EntityItem entityItem = new EntityItem(world, x + 0.5, y + 0.5, z + 0.5, drop);
                    world.spawnEntityInWorld(entityItem);
                }
            }
        }

        tileEntityMicroBlock.setShouldDropAsItem(false);
        world.setBlockToAir(x, y, z);
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLivingBase, ItemStack stack)
    {
        super.onBlockPlacedBy(world, x, y, z, entityLivingBase, stack);

        if (!world.isRemote)
        {
            IvBlockCollection collection = ItemMicroBlock.containedMicroBlock(stack);

            if (collection != null)
                ((TileEntityMicroBlock) world.getTileEntity(x, y, z)).setBlockCollection(collection);
        }
    }

    @Override
    public MovingObjectPosition collisionRayTrace(World world, int x, int y, int z, Vec3 start, Vec3 end)
    {
        ItemChisel.MicroBlockFragment hitFragment = ItemChisel.getHoveredFragment(world, x, y, z, start, Vec3.createVectorHelper(end.xCoord - start.xCoord, end.yCoord - start.yCoord, end.zCoord - start.zCoord));

        return hitFragment != null ? new MovingObjectPosition(x, y, z, hitFragment.getInternalSide().ordinal(), hitFragment.getHitPoint()) : null;
    }

    @Override
    public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB box, List list, Entity entity)
    {
        AxisAlignedBB thisBox = this.getCollisionBoundingBoxFromPool(world, x, y, z);

        if (thisBox != null && box.intersectsWith(thisBox))
        {
            TileEntityMicroBlock microBlock = (TileEntityMicroBlock) world.getTileEntity(x, y, z);
            IvBlockCollection collection = microBlock.getBlockCollection();

            double oneX = 1.0 / collection.width;
            double oneY = 1.0 / collection.height;
            double oneZ = 1.0 / collection.length;
            for (BlockCoord coord : collection)
            {
                if (collection.getBlock(coord).getMaterial() != Material.air)
                {
                    AxisAlignedBB bb = AxisAlignedBB.getBoundingBox(coord.x * oneX + x, coord.y * oneY + y, coord.z * oneZ + z, (coord.x + 1) * oneX + x, (coord.y + 1) * oneY + y, (coord.z + 1) * oneZ + z);
                    if (bb.intersectsWith(box))
                        list.add(bb);
                }
            }
        }
    }

    @Override
    public Item getItemDropped(int meta, Random rand, int fortune)
    {
        return null;
    }

    @Override
    public int getRenderType()
    {
        return YGCBlocks.blockMicroBlockRenderType;
    }

    @Override
    public IIcon getIcon(int side, int meta)
    {
        return renderIcon;
    }

    @Override
    public boolean shouldSideBeRendered(IBlockAccess blockAccess, int x, int y, int z, int side)
    {
        return side == renderSide.ordinal();
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister)
    {

    }

    @Override
    public boolean rotateBlock(World worldObj, int x, int y, int z, ForgeDirection axis)
    {
        TileEntityMicroBlock microBlock = (TileEntityMicroBlock) worldObj.getTileEntity(x, y, z);

        microBlock.setBlockCollection(IvBlockCollections.transform(microBlock.getBlockCollection(), AxisAlignedTransform2D.transform(1, false)));
        microBlock.markCacheInvalid();

        return super.rotateBlock(worldObj, x, y, z, axis);
    }

    @Override
    public boolean hasTileEntity(int metadata)
    {
        return true;
    }

    @Override
    public TileEntity createTileEntity(World var1, int var2)
    {
        return new TileEntityMicroBlock();
    }
}
