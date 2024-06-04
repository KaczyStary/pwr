package com.convista.pwr.bc;

public class Tea {
    private TeaType type;

    public Tea(TeaType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type.name();
    }
}
