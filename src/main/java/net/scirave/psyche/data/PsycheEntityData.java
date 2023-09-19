package net.scirave.psyche.data;

import org.jetbrains.annotations.NotNull;

public final class PsycheEntityData {

    // Data keys
    public static final DataKey<Boolean> TERRIFIED = new DataKey<>(Boolean.class, "terrified", false);

    // Array of data keys.
    public static final DataKey<?>[] __KEYS = {
            TERRIFIED
    };

    public static final class DataKey<T> {

        public final @NotNull String key;
        public final @NotNull Class<T> type;
        public final @NotNull T defaultValue;

        public DataKey(@NotNull Class<T> type, @NotNull String key, @NotNull T defaultValue) {
            this.key = key;
            this.type = type;
            this.defaultValue = defaultValue;
        }

    }


}
