/***************************************************************************************************
 * Copyright (c) 2014, Lukas Tenbrink.
 * http://lukas.axxim.net
 **************************************************************************************************/

package ivorius.yegamolchattels.blocks;

import ivorius.ivtoolkit.blocks.IvBlockMultiblock;
import ivorius.ivtoolkit.blocks.IvTileEntityMultiBlock;
import ivorius.yegamolchattels.YeGamolChattels;
import ivorius.yegamolchattels.achievements.YGCAchievementList;
import ivorius.yegamolchattels.items.ItemStatue;
import ivorius.yegamolchattels.materials.YGCMaterials;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.particle.EntityDiggingFX;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockStatue extends IvBlockMultiblock
{
    public BlockStatue()
    {
        super(YGCMaterials.mixed);
    }

    @Override
    public void parentBlockHarvestItem(World world, IvTileEntityMultiBlock tileEntity, BlockPos pos, IBlockState state)
    {
        if (tileEntity instanceof TileEntityStatue)
        {
            Statue statue = ((TileEntityStatue) tileEntity).getStatue();

            if (statue != null)
            {
                ItemStack stack = new ItemStack(Item.getItemFromBlock(YGCBlocks.statue));
                ItemStatue.setStatue(stack, statue);

                spawnAsEntity(world, pos, stack);
            }
        }
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public boolean isFullCube()
    {
        return false;
    }

    @Override
    public int getRenderType()
    {
        return -1;
    }

    @Override
    public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block neighborBlock)
    {
        super.onNeighborBlockChange(world, pos, state, neighborBlock);

        if (world.isBlockIndirectlyGettingPowered(pos) > 0)
        {
            IvTileEntityMultiBlock parent = getValidatedTotalParent(this, world, pos);

            if (parent instanceof TileEntityStatue)
            {
                if (((TileEntityStatue) parent).letStatueComeAlive())
                    world.setBlockToAir(pos);
            }
        }
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float par7, float par8, float par9)
    {
        IvTileEntityMultiBlock parent = getValidatedTotalParent(this, world, pos);

        if (parent instanceof TileEntityStatue)
        {
            TileEntityStatue tileEntityStatue = (TileEntityStatue) parent;
            if (tileEntityStatue.getStatue() != null && tileEntityStatue.tryEquipping(player.getHeldItem()))
            {
                Statue statue = tileEntityStatue.getStatue();

                ItemStack[] statueEquip = statue.getEntity().getInventory();
                if (statue.getMaterial().getBlock() == Blocks.gold_block
                        && isDiamond(statueEquip[1]) && isDiamond(statueEquip[2]) && isDiamond(statueEquip[3]) && isDiamond(statueEquip[4]))
                {
                    player.triggerAchievement(YGCAchievementList.superExpensiveStatue);
                }

                return true;
            }
            else
            {
                tileEntityStatue.addEquipmentToInventory(player);
//                tileEntityStatue.dropEquipment();

                return true;
            }
        }

        return super.onBlockActivated(world, pos, state, player, side, par7, par8, par9);
    }

    private boolean isDiamond(ItemStack stack)
    {
        return stack != null && stack.getItem() instanceof ItemArmor && ((ItemArmor) stack.getItem()).getArmorMaterial() == ItemArmor.ArmorMaterial.DIAMOND;
    }

    // TODO
//    @Override
//    public Icon getIcon(IBlockAccess blockAccess, BlockPos pos, EnumFacing facing)
//    {
//        IvTileEntityMultiBlock parent = getValidatedTotalParent(this, blockAccess, pos);
//
//        if (parent instanceof TileEntityStatue && ((TileEntityStatue) parent).getStatue() != null)
//        {
//            TileEntityStatue tileEntityStatue = (TileEntityStatue) parent;
//            Statue.BlockFragment fragment = tileEntityStatue.getStatue().getMaterial();
//            return fragment.getBlock().getIcon(side, fragment.getMetadata());
//        }
//
//        return super.getIcon(blockAccess, pos, side);
//    }

    // TODO
//    @Override
//    public boolean addHitEffects(World worldObj, MovingObjectPosition target, EffectRenderer effectRenderer)
//    {
//        IvTileEntityMultiBlock parent = getValidatedTotalParent(this, worldObj, target.getBlockPos());
//
//        if (parent instanceof TileEntityStatue && ((TileEntityStatue) parent).getStatue() != null)
//        {
//            TileEntityStatue tileEntityStatue = (TileEntityStatue) parent;
//            Statue.BlockFragment fragment = tileEntityStatue.getStatue().getMaterial();
//
//            float f = 0.1F;
//            double d0 = (double) target.blockX + worldObj.rand.nextDouble() * (getBlockBoundsMaxX() - getBlockBoundsMinX() - (double) (f * 2.0F)) + (double) f + getBlockBoundsMinX();
//            double d1 = (double) target.blockY + worldObj.rand.nextDouble() * (getBlockBoundsMaxY() - getBlockBoundsMinY() - (double) (f * 2.0F)) + (double) f + getBlockBoundsMinY();
//            double d2 = (double) target.blockZ + worldObj.rand.nextDouble() * (getBlockBoundsMaxZ() - getBlockBoundsMinZ() - (double) (f * 2.0F)) + (double) f + getBlockBoundsMinZ();
//
//            if (target.sideHit == 0)
//            {
//                d1 = (double) target.blockY + getBlockBoundsMinY() - (double) f;
//            }
//
//            if (target.sideHit == 1)
//            {
//                d1 = (double) target.blockY + getBlockBoundsMaxY() + (double) f;
//            }
//
//            if (target.sideHit == 2)
//            {
//                d2 = (double) target.blockZ + getBlockBoundsMinZ() - (double) f;
//            }
//
//            if (target.sideHit == 3)
//            {
//                d2 = (double) target.blockZ + getBlockBoundsMaxZ() + (double) f;
//            }
//
//            if (target.sideHit == 4)
//            {
//                d0 = (double) target.blockX + getBlockBoundsMinX() - (double) f;
//            }
//
//            if (target.sideHit == 5)
//            {
//                d0 = (double) target.blockX + getBlockBoundsMaxX() + (double) f;
//            }
//
//            EntityFX diggingFX = new EntityDiggingFX(worldObj, d0, d1, d2, 0.0D, 0.0D, 0.0D, fragment.getBlock(), fragment.getMetadata()).applyColourMultiplier(target.blockX, target.blockY, target.blockZ).multiplyVelocity(0.2F).multipleParticleScaleBy(0.6F);
//            effectRenderer.addEffect(diggingFX);
//
//            return true;
//        }
//
//        return false;
//    }

//    @Override
//    public Icon getIcon(EnumFacing facing, int meta)
//    {
//        return Blocks.stone.getIcon(0, 0);
//    }
//
//    @Override
//    public void registerBlockIcons(IconRegister par1IconRegister)
//    {
//
//    }
//
//    @Override
//    public String getItemIconName()
//    {
//        return YeGamolChattels.textureBase + getTextureName();
//    }

    @Override
    public TileEntity createNewTileEntity(World var1, int i)
    {
        return new TileEntityStatue();
    }
}
