/***************************************************************************************************
 * Copyright (c) 2014, Lukas Tenbrink.
 * http://lukas.axxim.net
 **************************************************************************************************/

// Date: 1-10-2013 16:12:53
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX

package ivorius.yegamolchattels.client.rendering;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelPedestalGold extends ModelBase
{
    //fields
    ModelRenderer base;
    ModelRenderer top;
    ModelRenderer pillar1;
    ModelRenderer pillar2;
    ModelRenderer pillar3;
    ModelRenderer pillar_4;
    ModelRenderer basetopthing;
    ModelRenderer arch1;
    ModelRenderer arch2;
    ModelRenderer arch3;
    ModelRenderer arch4;
    ModelRenderer archpiece1;
    ModelRenderer archpiece2;
    ModelRenderer archpiece3;
    ModelRenderer archpiece4;
    ModelRenderer archpiece5;
    ModelRenderer archpiece6;
    ModelRenderer archpiece7;
    ModelRenderer archpiece8;
    ModelRenderer floatingthingthatcanbeanimatedinanawesomeway;
    ModelRenderer ring1;
    ModelRenderer ring2;
    ModelRenderer ring3;
    ModelRenderer ring4;

    public ModelPedestalGold()
    {
        textureWidth = 128;
        textureHeight = 64;

        base = new ModelRenderer(this, 0, 0);
        base.addBox(-6F, 19F, -6F, 12, 2, 12);
        base.setRotationPoint(0F, 3F, 0F);
        base.setTextureSize(128, 64);
        base.mirror = true;
        setRotation(base, 0F, 0F, 0F);
        top = new ModelRenderer(this, 0, 15);
        top.addBox(-3F, 0F, -3F, 6, 5, 6);
        top.setRotationPoint(0F, 3F, 0F);
        top.setTextureSize(128, 64);
        top.mirror = true;
        setRotation(top, 0F, 0F, 0F);
        pillar1 = new ModelRenderer(this, 0, 27);
        pillar1.addBox(2F, 3F, -4F, 2, 16, 2);
        pillar1.setRotationPoint(0F, 3F, 0F);
        pillar1.setTextureSize(128, 64);
        pillar1.mirror = true;
        setRotation(pillar1, 0F, 0F, 0F);
        pillar2 = new ModelRenderer(this, 9, 27);
        pillar2.addBox(-4F, 3F, -4F, 2, 16, 2);
        pillar2.setRotationPoint(0F, 3F, 0F);
        pillar2.setTextureSize(128, 64);
        pillar2.mirror = true;
        setRotation(pillar2, 0F, 0F, 0F);
        pillar3 = new ModelRenderer(this, 18, 27);
        pillar3.addBox(2F, 3F, 2F, 2, 16, 2);
        pillar3.setRotationPoint(0F, 3F, 0F);
        pillar3.setTextureSize(128, 64);
        pillar3.mirror = true;
        setRotation(pillar3, 0F, 0F, 0F);
        pillar_4 = new ModelRenderer(this, 27, 27);
        pillar_4.addBox(-4F, 3F, 2F, 2, 16, 2);
        pillar_4.setRotationPoint(0F, 3F, 0F);
        pillar_4.setTextureSize(128, 64);
        pillar_4.mirror = true;
        setRotation(pillar_4, 0F, 0F, 0F);
        basetopthing = new ModelRenderer(this, 0, 46);
        basetopthing.addBox(-3F, 13F, -3F, 6, 6, 6);
        basetopthing.setRotationPoint(0F, 3F, 0F);
        basetopthing.setTextureSize(128, 64);
        basetopthing.mirror = true;
        setRotation(basetopthing, 0F, 0F, 0F);
        arch1 = new ModelRenderer(this, 50, 0);
        arch1.addBox(-2F, 5F, -4F, 4, 1, 2);
        arch1.setRotationPoint(0F, 3F, 0F);
        arch1.setTextureSize(128, 64);
        arch1.mirror = true;
        setRotation(arch1, 0F, 0F, 0F);
        arch2 = new ModelRenderer(this, 50, 4);
        arch2.addBox(-2F, 5F, 2F, 4, 1, 2);
        arch2.setRotationPoint(0F, 3F, 0F);
        arch2.setTextureSize(128, 64);
        arch2.mirror = true;
        setRotation(arch2, 0F, 0F, 0F);
        arch3 = new ModelRenderer(this, 50, 8);
        arch3.addBox(2F, 5F, -2F, 2, 1, 4);
        arch3.setRotationPoint(0F, 3F, 0F);
        arch3.setTextureSize(128, 64);
        arch3.mirror = true;
        setRotation(arch3, 0F, 0F, 0F);
        arch4 = new ModelRenderer(this, 50, 14);
        arch4.addBox(-4F, 5F, -2F, 2, 1, 4);
        arch4.setRotationPoint(0F, 3F, 0F);
        arch4.setTextureSize(128, 64);
        arch4.mirror = true;
        setRotation(arch4, 0F, 0F, 0F);
        archpiece1 = new ModelRenderer(this, 70, 0);
        archpiece1.addBox(1F, 6F, -4F, 1, 1, 2);
        archpiece1.setRotationPoint(0F, 3F, 0F);
        archpiece1.setTextureSize(128, 64);
        archpiece1.mirror = true;
        setRotation(archpiece1, 0F, 0F, 0F);
        archpiece2 = new ModelRenderer(this, 70, 4);
        archpiece2.addBox(-2F, 6F, -4F, 1, 1, 2);
        archpiece2.setRotationPoint(0F, 3F, 0F);
        archpiece2.setTextureSize(128, 64);
        archpiece2.mirror = true;
        setRotation(archpiece2, 0F, 0F, 0F);
        archpiece3 = new ModelRenderer(this, 70, 8);
        archpiece3.addBox(-2F, 6F, 2F, 1, 1, 2);
        archpiece3.setRotationPoint(0F, 3F, 0F);
        archpiece3.setTextureSize(128, 64);
        archpiece3.mirror = true;
        setRotation(archpiece3, 0F, 0F, 0F);
        archpiece4 = new ModelRenderer(this, 70, 12);
        archpiece4.addBox(1F, 6F, 2F, 1, 1, 2);
        archpiece4.setRotationPoint(0F, 3F, 0F);
        archpiece4.setTextureSize(128, 64);
        archpiece4.mirror = true;
        setRotation(archpiece4, 0F, 0F, 0F);
        archpiece5 = new ModelRenderer(this, 70, 16);
        archpiece5.addBox(2F, 6F, 1F, 2, 1, 1);
        archpiece5.setRotationPoint(0F, 3F, 0F);
        archpiece5.setTextureSize(128, 64);
        archpiece5.mirror = true;
        setRotation(archpiece5, 0F, 0F, 0F);
        archpiece6 = new ModelRenderer(this, 70, 19);
        archpiece6.addBox(2F, 6F, -2F, 2, 1, 1);
        archpiece6.setRotationPoint(0F, 3F, 0F);
        archpiece6.setTextureSize(128, 64);
        archpiece6.mirror = true;
        setRotation(archpiece6, 0F, 0F, 0F);
        archpiece7 = new ModelRenderer(this, 70, 22);
        archpiece7.addBox(-4F, 6F, 1F, 2, 1, 1);
        archpiece7.setRotationPoint(0F, 3F, 0F);
        archpiece7.setTextureSize(128, 64);
        archpiece7.mirror = true;
        setRotation(archpiece7, 0F, 0F, 0F);
        archpiece8 = new ModelRenderer(this, 70, 25);
        archpiece8.addBox(-4F, 6F, -2F, 2, 1, 1);
        archpiece8.setRotationPoint(0F, 3F, 0F);
        archpiece8.setTextureSize(128, 64);
        archpiece8.mirror = true;
        setRotation(archpiece8, 0F, 0F, 0F);
        floatingthingthatcanbeanimatedinanawesomeway = new ModelRenderer(this, 80, 0);
        floatingthingthatcanbeanimatedinanawesomeway.addBox(-1F, -1F, -1F, 2, 2, 2);
        floatingthingthatcanbeanimatedinanawesomeway.setRotationPoint(0F, 12.5F, 0F);
        floatingthingthatcanbeanimatedinanawesomeway.setTextureSize(128, 64);
        floatingthingthatcanbeanimatedinanawesomeway.mirror = true;
        setRotation(floatingthingthatcanbeanimatedinanawesomeway, 0F, 0F, 0F);
        ring1 = new ModelRenderer(this, 80, 5);
        ring1.addBox(0F, 0F, -1.5F, 3, 0, 3);
        ring1.setRotationPoint(3.5F, 18F, 3.5F);
        ring1.setTextureSize(128, 64);
        ring1.mirror = true;
        setRotation(ring1, 0F, -0.7853982F, 1.07818F);
        ring2 = new ModelRenderer(this, 80, 9);
        ring2.addBox(0F, 0F, -1.5F, 3, 0, 3);
        ring2.setRotationPoint(3.5F, 18F, -3.5F);
        ring2.setTextureSize(128, 64);
        ring2.mirror = true;
        setRotation(ring2, 0F, 0.7853982F, 1.078177F);
        ring3 = new ModelRenderer(this, 80, 13);
        ring3.addBox(-1.5F, 0F, 0F, 3, 0, 3);
        ring3.setRotationPoint(-3.5F, 18F, 3.5F);
        ring3.setTextureSize(128, 64);
        ring3.mirror = true;
        setRotation(ring3, -1.078177F, -0.7853982F, 0F);
        ring4 = new ModelRenderer(this, 80, 17);
        ring4.addBox(-1.5F, 0F, -3F, 3, 0, 3);
        ring4.setRotationPoint(-3.5F, 18F, -3.5F);
        ring4.setTextureSize(128, 64);
        ring4.mirror = true;
        setRotation(ring4, 1.078177F, 0.7853982F, 0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        base.render(f5);
        top.render(f5);
        pillar1.render(f5);
        pillar2.render(f5);
        pillar3.render(f5);
        pillar_4.render(f5);
        basetopthing.render(f5);
        arch1.render(f5);
        arch2.render(f5);
        arch3.render(f5);
        arch4.render(f5);
        archpiece1.render(f5);
        archpiece2.render(f5);
        archpiece3.render(f5);
        archpiece4.render(f5);
        archpiece5.render(f5);
        archpiece6.render(f5);
        archpiece7.render(f5);
        archpiece8.render(f5);
        floatingthingthatcanbeanimatedinanawesomeway.render(f5);
        ring1.render(f5);
        ring2.render(f5);
        ring3.render(f5);
        ring4.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}