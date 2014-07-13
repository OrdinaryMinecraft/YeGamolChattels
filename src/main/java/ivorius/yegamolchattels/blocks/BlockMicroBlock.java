/*
 *  Copyright (c) 2014, Lukas Tenbrink.
 *  * http://lukas.axxim.net
 */

package ivorius.yegamolchattels.blocks;

import ivorius.ivtoolkit.blocks.BlockCoord;
import ivorius.ivtoolkit.blocks.IvBlockCollection;
import ivorius.yegamolchattels.items.ItemChisel;
import ivorius.yegamolchattels.items.ItemMicroBlock;
import ivorius.yegamolchattels.items.YGCItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
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
public class BlockMicroBlock extends BlockContainer
{
    public boolean[] renderSideCache = new boolean[6];
    public Block renderBlockCache;
    public int renderBlockMetaCache;

    public BlockMicroBlock()
    {
        super(Material.wood);
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess blockAccess, int x, int y, int z)
    {
        setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
    }

    @Override
    public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side)
    {
        return ((TileEntityMicroBlock) world.getTileEntity(x, y, z)).isSideOpaque(side);
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

            if (!microBlock.isDestroyByConverting())
            {
                ItemStack stack = new ItemStack(Item.getItemFromBlock(this));
                ItemMicroBlock.setMicroBlock(stack, microBlock.getBlockCollection());
                world.spawnEntityInWorld(new EntityItem(world, x + 0.5, y + 0.5, z + 0.5, stack));
            }
        }

        super.breakBlock(world, x, y, z, block, meta);
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLivingBase, ItemStack stack)
    {
        super.onBlockPlacedBy(world, x, y, z, entityLivingBase, stack);

        if (!world.isRemote)
        {
            IvBlockCollection collection = ItemMicroBlock.containedMicroBlock(stack);
            if (collection != null)
            {
                ((TileEntityMicroBlock) world.getTileEntity(x, y, z)).setBlockCollection(collection);
            }
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
        return renderBlockCache != null ? renderBlockCache.getIcon(side, renderBlockMetaCache) : Blocks.planks.getIcon(0, 0);
    }

    @Override
    public boolean shouldSideBeRendered(IBlockAccess blockAccess, int x, int y, int z, int side)
    {
        return renderSideCache[side];
    }

    @Override
    public TileEntity createNewTileEntity(World var1, int var2)
    {
        return new TileEntityMicroBlock();
    }
}
