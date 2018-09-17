package com.utn.meraki.service.impl;

import com.utn.meraki.service.GeneracionCodigo;
import org.springframework.stereotype.Service;

@Service("generacionCodigo")
public class GeneracionCodigoImpl implements GeneracionCodigo {

    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static String randomAlphaNumeric(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }

    @Override
    public String generarCodigo() {
        return randomAlphaNumeric(6);
    }
}
