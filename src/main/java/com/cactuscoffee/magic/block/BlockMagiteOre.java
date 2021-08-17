package com.cactuscoffee.magic.block;

import com.cactuscoffee.magic.data.Element;
import com.cactuscoffee.magic.ItemRegister;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.Random;

public class BlockMagiteOre extends ModBlock {
    private final Element variant;

    public BlockMagiteOre(Element variant) {
        super("magite_ore_" + variant.getName(), Material.ROCK, SoundType.STONE);
        this.setHardness(4F);
        this.setHarvestLevel("pickaxe", 1);

        this.variant = variant;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        switch (variant) {
            case RED:
                return ItemRegister.magiteRed;
            case YELLOW:
                return ItemRegister.magiteYellow;
            case GREEN:
                return ItemRegister.magiteGreen;
            case BLUE:
                return ItemRegister.magiteBlue;
            case BLACK:
                return ItemRegister.magiteBlack;
            case WHITE:
                return ItemRegister.magiteWhite;
            //Following should never happen
            default:
                return ItemRegister.manaCrystal;
        }
    }

    @Override
    public int quantityDropped(Random random) {
        return 1;
    }

    @Override
    public int quantityDroppedWithBonus(int fortune, @Nonnull Random random) {
        return quantityDropped(random) + random.nextInt(fortune + 1);
    }

    @Nonnull
    @Override
    public ItemStack getPickBlock(@Nonnull IBlockState state, RayTraceResult target,
                                  @Nonnull World world, @Nonnull BlockPos pos, EntityPlayer player) {
        return new ItemStack(getItemDropped(state, null, 0), 1);
    }

    @Override
    public int getExpDrop(IBlockState state, net.minecraft.world.IBlockAccess world, BlockPos pos, int fortune) {
        if (this.getItemDropped(state, RANDOM, fortune) != Item.getItemFromBlock(this)) {
            return 3 + RANDOM.nextInt(2);
        }
        return 0;
    }
}