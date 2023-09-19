package net.scirave.psyche.mixins;

import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.NbtCompound;
import net.scirave.psyche.Psyche;
import net.scirave.psyche.data.PsycheActor;
import net.scirave.psyche.data.PsycheEntityData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashMap;
import java.util.Map;

@Mixin(LivingEntity.class)
public class MixinEntityDataExtension implements PsycheActor {

    private final Map<String, Object> __data = new HashMap<>();


    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    public void psyche$extendEntityData$add(NbtCompound nbt, CallbackInfo ci) {
        for (PsycheEntityData.DataKey<?> key : PsycheEntityData.__KEYS) {
            @Nullable Object currentValue = __data.get(key.key);

            if (currentValue != null) {
                psyche$addToNbt(nbt, key, currentValue);
            }
        }
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
    public void psyche$extendEntityData$retrieve(NbtCompound nbt, CallbackInfo ci) {
        for (PsycheEntityData.DataKey<?> key : PsycheEntityData.__KEYS) {
            @Nullable Object storedValue = psyche$getFromNbt(nbt, key);

            if (storedValue != null) {
                __data.put(key.key, storedValue);
            }
        }
    }

    @Unique
    private static void psyche$addToNbt(NbtCompound nbt, PsycheEntityData.DataKey<?> key, @NotNull Object value) {
        if (key.type == Boolean.class) {
            //nbt.putBoolean(key.key, (Boolean) value);
        } else if (key.type == Integer.class) {
            nbt.putInt(key.key, (Integer) value);
        } else if (key.type == Float.class) {
            nbt.putFloat(key.key, (Float) value);
        } else {
            Psyche.LOGGER.error("Could not serialize entity data extension '" + key.key + "' of type '" + key.type + "'");
        }
    }

    @Unique
    private static @Nullable Object psyche$getFromNbt(NbtCompound nbt, PsycheEntityData.DataKey<?> key) {
        if (key.type == Boolean.class) {
            return nbt.getBoolean(key.key);
        } else if (key.type == Integer.class) {
            return nbt.getInt(key.key);
        } else if (key.type == Float.class) {
            return nbt.getFloat(key.key);
        } else {
            Psyche.LOGGER.error("Could not deserialize entity data extension '" + key.key + "' of type '" + key.type + "'");
        }

        return null;
    }

    @SuppressWarnings("unchecked cast")
    @Override
    public <T> T getData(PsycheEntityData.DataKey<T> key) {
        @Nullable T data = (T)  __data.get(key.key);
        return data != null ? data : key.defaultValue;
    }

    @Override
    public <T> void setData(PsycheEntityData.DataKey<T> key, @NotNull T data) {
        __data.put(key.key, data);
    }
}
