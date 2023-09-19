package net.scirave.psyche.mixins;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.scirave.psyche.Psyche;
import net.scirave.psyche.data.PsycheActor;
import net.scirave.psyche.data.PsycheEntityData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class MixinLivingHurt {

    @Shadow public abstract float getHealth();

    @Unique
    private float psyche$lastHealth = 0.0F;


    @Inject(method = "applyDamage", at = @At("HEAD"))
    public void psyche$livingDamaged$logic$head(DamageSource source, float amount, CallbackInfo ci) {
        this.psyche$lastHealth = this.getHealth();
    }

    @Inject(method = "applyDamage", at = @At("TAIL"))
    public void psyche$livingDamaged$logic$tail(DamageSource source, float amount, CallbackInfo ci) {
        this.psyche$livingDamaged(source, psyche$lastHealth - this.getHealth());
    }

    public void psyche$livingDamaged(DamageSource source, float amount) {
        // Trauma!
        psyche$testTrauma((LivingEntity) (Object) this, amount);
    }


    public void psyche$testTrauma(LivingEntity entity, float amount) {
        PsycheActor actor = (PsycheActor) entity;

        boolean fatalDamage = amount >= entity.getHealth();
        boolean healthCritical = entity.getHealth()/entity.getMaxHealth() <= Psyche.CONFIG.TRAUMA_LOW_HEALTH;

        if (!healthCritical && !(Psyche.CONFIG.TRAUMA_SEVERE_WOUND && fatalDamage)) return; // Not traumatic!

        actor.setData(PsycheEntityData.TERRIFIED, true);
    }

}
