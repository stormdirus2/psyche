package net.scirave.psyche.mixins;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.CreeperEntity;
import net.scirave.psyche.data.PsycheActor;
import net.scirave.psyche.data.PsycheEntityData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CreeperEntity.class)
public class MixinCreeperTest {
    @Inject(method = "explode", at = @At("HEAD"), cancellable = true)
    public void psyche$preventExplosionWhenTerrifiedTest(CallbackInfo ci) {
        if (!(this instanceof PsycheActor)) return;
        PsycheActor actor = (PsycheActor) this;

        if (actor.getData(PsycheEntityData.TERRIFIED)) {
            ci.cancel();
        }

    }

    /*
    public void psyche$livingDamaged(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (!(this instanceof PsycheActor)) return;
        PsycheActor actor = (PsycheActor) this;

        if (amount > 7) {
            actor.setData(PsycheEntityData.TERRIFIED, true);
        }
    }
     */

}
