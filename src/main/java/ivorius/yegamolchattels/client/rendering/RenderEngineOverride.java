/***************************************************************************************************
 * Copyright (c) 2014, Lukas Tenbrink.
 * http://lukas.axxim.net
 **************************************************************************************************/

package ivorius.yegamolchattels.client.rendering;

import net.minecraft.client.renderer.texture.*;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;

/**
 * Created by lukas on 09.02.14.
 */
public class RenderEngineOverride extends TextureManager
{
    public TextureManager renderEngine;
    public ResourceLocation textureOverride;

    public RenderEngineOverride()
    {
        super(null);
    }

    @Override
    public void bindTexture(ResourceLocation par1ResourceLocation)
    {
        if (textureOverride == null)
            renderEngine.bindTexture(par1ResourceLocation);
        else if (par1ResourceLocation.getResourcePath().contains("armor"))
            renderEngine.bindTexture(par1ResourceLocation);
        else if (par1ResourceLocation.getResourcePath().contains("spider_eyes"))
            renderEngine.bindTexture(par1ResourceLocation);
        else if (par1ResourceLocation.getResourcePath().contains("enderman_eyes"))
            renderEngine.bindTexture(par1ResourceLocation);
        else if (par1ResourceLocation.getResourcePath().contains("dragon_eyes"))
            renderEngine.bindTexture(par1ResourceLocation);
        else if (par1ResourceLocation.equals(TextureMap.locationBlocksTexture))
            renderEngine.bindTexture(par1ResourceLocation);
        else
        {
            renderEngine.bindTexture(textureOverride);
        }
    }

    @Override
    public ResourceLocation getResourceLocation(int par1)
    {
        return renderEngine.getResourceLocation(par1);
    }

    @Override
    public boolean loadTextureMap(ResourceLocation par1ResourceLocation, TextureMap par2TextureMap)
    {
        return renderEngine.loadTextureMap(par1ResourceLocation, par2TextureMap);
    }

    @Override
    public boolean loadTickableTexture(ResourceLocation par1ResourceLocation, ITickableTextureObject par2TickableTextureObject)
    {
        return renderEngine.loadTickableTexture(par1ResourceLocation, par2TickableTextureObject);
    }

    @Override
    public boolean loadTexture(ResourceLocation par1ResourceLocation, final ITextureObject par2TextureObject)
    {
        return renderEngine.loadTexture(par1ResourceLocation, par2TextureObject);
    }

    @Override
    public ITextureObject getTexture(ResourceLocation par1ResourceLocation)
    {
        return renderEngine.getTexture(par1ResourceLocation);
    }

    @Override
    public ResourceLocation getDynamicTextureLocation(String par1Str, DynamicTexture par2DynamicTexture)
    {
        return renderEngine.getDynamicTextureLocation(par1Str, par2DynamicTexture);
    }

    @Override
    public void tick()
    {
        renderEngine.tick();
    }

    @Override
    public void deleteTexture(ResourceLocation p_147645_1_)
    {
        renderEngine.deleteTexture(p_147645_1_);
    }

    @Override
    public void onResourceManagerReload(IResourceManager par1ResourceManager)
    {
        renderEngine.onResourceManagerReload(par1ResourceManager);
    }
}
