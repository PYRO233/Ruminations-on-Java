package com.github.pyro233.ood.ricksGuitars;

/**
 * @Author: tao.zhou
 * @Date: 2023/5/2 17:43
 */
public enum Wood {

    INDIAN_ROSEWOOD,
    BRAZILIAN_ROSEWOOD,
    MAHOGANY,
    MAPLE,
    COCOBOLO,
    CEDAR,
    ADIRONDACK,
    ALDER,
    SITKA,
    ;

    public String toString() {
        return switch (this) {
            case INDIAN_ROSEWOOD -> "Indian Rosewood";
            case BRAZILIAN_ROSEWOOD -> "Brazilian Rosewood";
            case MAHOGANY -> "Mahogany";
            case MAPLE -> "Maple";
            case COCOBOLO -> "Cocobolo";
            case CEDAR -> "Cedar";
            case ADIRONDACK -> "Adirondack";
            case ALDER -> "Alder";
            case SITKA -> "Sitka";
        };
    }

}
