package net.scirave.psyche.data;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

public interface PsycheActor {

    @NotNull <T> T getData(PsycheEntityData.DataKey<T> key);
    <T> void setData(PsycheEntityData.DataKey<T> key, @NotNull T data);


}
