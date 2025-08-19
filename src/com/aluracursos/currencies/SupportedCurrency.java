package com.aluracursos.currencies;

public enum SupportedCurrency {
    USD, EUR, CAD, BRL, CLP, JPY;

    public static boolean isSupported(String input) {
        try {
            SupportedCurrency.valueOf(input.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
