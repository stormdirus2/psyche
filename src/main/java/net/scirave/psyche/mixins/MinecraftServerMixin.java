/*
 * -------------------------------------------------------------------
 * Pscyhe
 * Copyright (c) 2023 SciRave
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 * Credit to Fallen_Breath and contributors for the template.
 *
 * --------
 * Alternatively, the contents of this file may be used under the terms
 * of the GNU Lesser General Public License Version 3.0, as described below:
 *
 * This file is free software: you may copy, redistribute and/or modify
 * it under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3.0 of the License, or (at your
 * option) any later version.
 *
 * This file is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General
 * Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program. If not, see http://www.gnu.org/licenses/.
 * --------
 * -------------------------------------------------------------------
 */

package net.scirave.psyche.mixins;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.logging.Logger;

@Mixin(MinecraftServer.class)
public abstract class MinecraftServerMixin
{
	@Inject(
			method = "runServer",
			at = @At("HEAD")
	)
	private void onRun(CallbackInfo ci)
	{
		Logger.getLogger("TEST").info("WOW. IT'S WORKING!");

		NbtCompound nbt = new NbtCompound();
		nbt.putString("key", "value");
		System.err.println("nbt: " + nbt);
	}
}
