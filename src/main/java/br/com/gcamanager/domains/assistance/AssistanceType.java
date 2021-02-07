package br.com.gcamanager.domains.assistance;

import java.util.HashMap;
import java.util.Map;

public enum AssistanceType {
    CONSULTANCY(1, "Consultoria"),
    ADVISORY(2, "Assessoria"),
    DEFAULT(3, "Não Informado");

    private static final Map<Integer, AssistanceType> map = new HashMap<>();

    static {
        for (AssistanceType type : values()) {
            map.put(type.code, type);
        }
    }

    private final int code;
    private final String description;

    AssistanceType(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public static AssistanceType valueOfCode(int code) {
        return map.get(code);
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
