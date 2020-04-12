package com.moonsworth.client.nethandler.obj;

import lombok.Getter;

public enum ServerRule {

    /**
     * Whether or not voice will be enabled on this server
     */
    VOICE_ENABLED("voiceEnabled", Boolean.class),

    /**
     * Whether or not minimap is allowed
     * Expected value: (String) NEUTRAL or FORCED_OFF
     */
    MINIMAP_STATUS("minimapStatus", String.class),


    /**
     * Whether or not the server will store waypoints, instead of the client
     */
    SERVER_HANDLES_WAYPOINTS("serverHandlesWaypoints", Boolean.class),

    /**
     * A warning message will be shown when attempting to disconnect if the current
     * game is competitive.
     */
    COMPETITIVE_GAMEMODE("competitiveGame", Boolean.class),

    /**
     * Don't let Lunar Client players use shaders
     */
    SHADERS_DISABLED("shadersDisabled", Boolean.class);

    @Getter String rule;

    @Getter Class value;

    ServerRule(String rule, Class clazz) {
        this.rule = rule;
        this.value = clazz;
    }

    public static ServerRule getRule(String s) {
        ServerRule rule = null;

        for (ServerRule sr : ServerRule.values()) {
            if (sr.getRule().equals(s)) {
                rule = sr;
            }
        }

        return rule;
    }
}
