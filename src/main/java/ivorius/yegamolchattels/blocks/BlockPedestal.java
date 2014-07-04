/***************************************************************************************************
 * Copyright (c) 2014, Lukas Tenbrink.
 * http://lukas.axxim.net
 **************************************************************************************************/

package ivorius.yegamolchattels.blocks;

import ivorius.ivtoolkit.blocks.IvBlockMultiblock;
import ivorius.ivtoolkit.blocks.IvTileEntityMultiBlock;
import ivorius.yegamolchattels.YeGamolChattels;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockPedestal extends IvBlockMultiblock
{
    public IIcon[] icons;

    public BlockPedestal(Material par2Material)
    {
        super(par2Material);

        setCreativeTab(CreativeTabs.tabDecorations);
    }

    @Override
    public int getRenderType()
    {
        return -1;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    public IIcon getIcon(int par1, int par2)
    {
        return this.icons.length > par2 ? this.icons[par2] : Blocks.planks.getIcon(0, 0);
    }

    @Override
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.icons = new IIcon[EnumPedestalEntry.getNumberOfEntries()];

        for (int i = 0; i < this.icons.length; i++)
            this.icons[i] = par1IconRegister.registerIcon(YeGamolChattels.textureBase + "pedestal" + i);
    }

    @Override
    public TileEntity createNewTileEntity(World var1, int i)
    {
        return new TileEntityPedestal();
    }

    @Override
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
        TileEntity tileEntity = par1World.getTileEntity(par2, par3, par4);

        if (tileEntity instanceof TileEntityPedestal)
        {
            if (((TileEntityPedestal) tileEntity).tryStoringItem(par5EntityPlayer.getHeldItem()))
            {
                par5EntityPlayer.getHeldItem().stackSize--;
            }
            else
            {
                ((TileEntityPedestal) tileEntity).startDroppingItem(par5EntityPlayer);
            }
        }

        return true;
    }

    @Override
    public void parentBlockDropItemContents(World par1World, IvTileEntityMultiBlock tileEntity, int parentX, int parentY, int parentZ, Block block, int blockMeta)
    {
        ((TileEntityPedestal) tileEntity).dropItem();
    }

    @Override
    public void parentBlockHarvestItem(World par1World, IvTileEntityMultiBlock tileEntity, int parentX, int parentY, int parentZ, Block block, int blockMeta)
    {
        this.dropBlockAsItem(par1World, parentX, parentY, parentZ, new ItemStack(this, 1, ((TileEntityPedestal) tileEntity).pedestalIdentifier));
    }
}