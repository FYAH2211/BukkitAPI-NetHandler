package com.lunarclient.bukkitapi.nethandler.client.obj;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class ServerRule {

    public static final ServerRule DEFAULT_RULE = new ServerRule();

    private String minimapStatus = "FORCED_OFF";
    private boolean serverHandlesWaypoints = false;
    private boolean competitiveGame = false;
    private boolean shadersDisabled = false;
    private boolean voiceEnabled = false;

    private Map<String, ModSetting> modSettings = new HashMap<>();


    public Map<String, ModSetting> getModSettings() {
        return modSettings;
    }

    public String getMinimapStatus() {
        return minimapStatus;
    }

    public boolean isCompetitiveGame() {
        return competitiveGame;
    }

    public boolean isShadersDisabled() {
        return shadersDisabled;
    }

    public boolean isVoiceEnabled() {
        return voiceEnabled;
    }

    public boolean doesServerHandlesWaypoints() {
        return serverHandlesWaypoints;
    }

    public ServerRule setMinimapStatus(String minimapStatus) {
        this.minimapStatus = minimapStatus;
        return this;
    }

    public ServerRule setCompetitiveGame(boolean competitiveGame) {
        this.competitiveGame = competitiveGame;
        return this;
    }

    public ServerRule setShadersDisabled(boolean shadersDisabled) {
        this.shadersDisabled = shadersDisabled;
        return this;
    }

    public ServerRule setVoiceEnabled(boolean voiceEnabled) {
        this.voiceEnabled = voiceEnabled;
        return this;
    }

    public ServerRule setServerHandlesWaypoints(boolean serverHandlesWaypoints) {
        this.serverHandlesWaypoints = serverHandlesWaypoints;
        return this;
    }

    public ServerRule addSetting(String mod, ModSetting modSetting) {
        this.modSettings.put(mod, modSetting);
        return this;
    }


    public static class ModSetting {
        private boolean enabled;
        private Map<String, Object> properties;

        public ModSetting() {
        } // for serialization

        public ModSetting(boolean enabled, Map<String, Object> properties) {
            this.enabled = enabled;
            this.properties = properties;
        }

        public boolean isEnabled() {
            return enabled;
        }

        public Map<String, Object> getProperties() {
            return properties;
        }

        @Override
        public String toString() {
            return "ModSetting{" +
                    "enabled=" + enabled +
                    ", properties=" + properties +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ModSetting that = (ModSetting) o;
            return enabled == that.enabled &&
                    Objects.equals(properties, that.properties);
        }

        @Override
        public int hashCode() {
            return Objects.hash(enabled, properties);
        }
    }

    @Override
    public String toString() {
        return "ServerRule{" +
                "minimapStatus='" + minimapStatus + '\'' +
                ", serverHandlesWaypoints=" + serverHandlesWaypoints +
                ", competitiveGame=" + competitiveGame +
                ", shadersDisabled=" + shadersDisabled +
                ", voiceEnabled=" + voiceEnabled +
                ", modSettings=" + modSettings +
                '}';
    }
}
