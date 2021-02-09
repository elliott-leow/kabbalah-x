package com.example.examplemod.module.modules.offense;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import com.example.examplemod.module.Category;
import com.example.examplemod.module.Module;
import com.example.examplemod.settings.Setting;
import com.example.examplemod.util.Reference;

import io.github.elliottleow.kabbalah.ExampleMod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockClay;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import scala.reflect.internal.Trees.This;

public class LowBlockWarning extends Module {
	
	
	public LowBlockWarning() {
		super("Low Block Warning", "Tells you when you are low on blocks", Category.OFFENSE);
		this.setKey(Keyboard.KEY_L);
		ExampleMod.settingsManager.rSetting(new Setting("Width", this, 50, 1, 100, true));
		ExampleMod.settingsManager.rSetting(new Setting("Height", this, 60, 1, 100, true));
		ExampleMod.settingsManager.rSetting(new Setting("Only Clay", this, true));
		ExampleMod.settingsManager.rSetting(new Setting("Min Blocks", this, 16, 1, 64, true));
		
		
	}
	boolean toggled = false;
	
	@Override
	public void onEnable() {
		super.onEnable();
		toggled = true;
	
	}
	
	@Override
	public void onDisable() {
		super.onDisable();
		toggled = false;
	}
	
	@SubscribeEvent
	public void renderOverlay(RenderGameOverlayEvent.Text event) {
		
	
	if(event.type == RenderGameOverlayEvent.ElementType.TEXT && Minecraft.getMinecraft().thePlayer.getCurrentEquippedItem() != null && Minecraft.getMinecraft().thePlayer.getCurrentEquippedItem().getItem() instanceof ItemBlock && Minecraft.getMinecraft().thePlayer.getCurrentEquippedItem().stackSize < (int)ExampleMod.settingsManager.getSettingByName(this, "Min Blocks").getValDouble()) {
		
		ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
		FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
		if (ExampleMod.settingsManager.getSettingByName(this, "Only Clay").getValBoolean() && Minecraft.getMinecraft().thePlayer.getCurrentEquippedItem().getItem() == Item.getItemFromBlock(Blocks.stained_hardened_clay)) {
			GL11.glPushMatrix();
			fr.drawStringWithShadow("You are almost out of blocks", (int) (sr.getScaledWidth()*(ExampleMod.settingsManager.getSettingByName(this, "Width").getValDouble()/100) - 70), (int) (sr.getScaledHeight()* (ExampleMod.settingsManager.getSettingByName(this, "Height").getValDouble()/100)), 0xffC42300);
			GL11.glPopMatrix();
		}
		if (ExampleMod.settingsManager.getSettingByName(this, "Only Clay").getValBoolean() == false) {
			GL11.glPushMatrix();
			fr.drawStringWithShadow("You are almost out of blocks", (int) (sr.getScaledWidth()*(ExampleMod.settingsManager.getSettingByName(this, "Width").getValDouble()/100) - 70), (int) (sr.getScaledHeight()* (ExampleMod.settingsManager.getSettingByName(this, "Height").getValDouble()/100)), 0xffC42300);
			//fr.drawStringWithShadow("", 0, 0, 0x00000000);
			//fr.drawStringWithShadow("", 0, 0, 0xffffffff);
			GL11.glPopMatrix();
	}
	
		
		}
}
}

