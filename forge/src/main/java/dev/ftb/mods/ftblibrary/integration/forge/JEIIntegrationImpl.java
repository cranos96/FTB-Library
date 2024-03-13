package dev.ftb.mods.ftblibrary.integration.forge;

import dev.ftb.mods.ftblibrary.integration.JEIIntegration;
import dev.ftb.mods.ftblibrary.util.client.PositionedIngredient;
import mezz.jei.api.forge.ForgeTypes;
import mezz.jei.api.ingredients.ITypedIngredient;
import mezz.jei.api.runtime.IClickableIngredient;
import mezz.jei.api.runtime.IJeiRuntime;
import net.minecraftforge.fluids.FluidStack;

import java.util.Optional;

public class JEIIntegrationImpl {
    public static Optional<IClickableIngredient<?>> handleExtraIngredientTypes(IJeiRuntime runtime, PositionedIngredient underMouse) {
        if (underMouse.ingredient() instanceof FluidStack stack) {
            Optional<ITypedIngredient<FluidStack>> typed = runtime.getIngredientManager().createTypedIngredient(ForgeTypes.FLUID_STACK, stack);
            if (typed.isPresent()) {
                return Optional.of(new JEIIntegration.ClickableIngredient<>(typed.get(), underMouse.area()));
            }
        }

        return Optional.empty();
    }
}
