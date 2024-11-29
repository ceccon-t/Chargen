package dev.ceccon.client;

public class LLMSanitizer {

    public static String sanitizeLLMSpecialTokens(String raw) {
        String sanitized = raw.replace("</s>", "");
        sanitized = sanitized.replace("<|eot_id|>", "");

        return sanitized;
    }

    public static String sanitizeForChat(String raw) {
        String sanitized = sanitizeLLMSpecialTokens(raw);

        sanitized = sanitized.trim();

        sanitized = sanitized.replaceAll("á", "a");
        sanitized = sanitized.replaceAll("à", "a");
        sanitized = sanitized.replaceAll("â", "a");
        sanitized = sanitized.replaceAll("ä", "a");
        sanitized = sanitized.replaceAll("ã", "a");
        sanitized = sanitized.replaceAll("Á", "A");
        sanitized = sanitized.replaceAll("À", "A");
        sanitized = sanitized.replaceAll("Â", "A");
        sanitized = sanitized.replaceAll("Ã", "A");

        sanitized = sanitized.replaceAll("é", "e");
        sanitized = sanitized.replaceAll("è", "e");
        sanitized = sanitized.replaceAll("ê", "e");
        sanitized = sanitized.replaceAll("ë", "e");
        sanitized = sanitized.replaceAll("ẽ", "e");
        sanitized = sanitized.replaceAll("É", "E");
        sanitized = sanitized.replaceAll("È", "E");
        sanitized = sanitized.replaceAll("Ê", "E");
        sanitized = sanitized.replaceAll("Ë", "E");
        sanitized = sanitized.replaceAll("Ẽ", "E");

        sanitized = sanitized.replaceAll("í", "i");
        sanitized = sanitized.replaceAll("ì", "i");
        sanitized = sanitized.replaceAll("î", "i");
        sanitized = sanitized.replaceAll("ï", "i");
        sanitized = sanitized.replaceAll("ĩ", "i");
        sanitized = sanitized.replaceAll("Í", "I");
        sanitized = sanitized.replaceAll("Ì", "I");
        sanitized = sanitized.replaceAll("Î", "I");
        sanitized = sanitized.replaceAll("Ï", "I");
        sanitized = sanitized.replaceAll("Ĩ", "I");

        sanitized = sanitized.replaceAll("ó", "o");
        sanitized = sanitized.replaceAll("ò", "o");
        sanitized = sanitized.replaceAll("ô", "o");
        sanitized = sanitized.replaceAll("ö", "o");
        sanitized = sanitized.replaceAll("õ", "o");
        sanitized = sanitized.replaceAll("Ó", "O");
        sanitized = sanitized.replaceAll("Ò", "O");
        sanitized = sanitized.replaceAll("Ô", "O");
        sanitized = sanitized.replaceAll("Ö", "O");
        sanitized = sanitized.replaceAll("Õ", "O");

        sanitized = sanitized.replaceAll("ú", "u");
        sanitized = sanitized.replaceAll("ù", "u");
        sanitized = sanitized.replaceAll("û", "u");
        sanitized = sanitized.replaceAll("ü", "u");
        sanitized = sanitized.replaceAll("ũ", "u");
        sanitized = sanitized.replaceAll("Ù", "U");
        sanitized = sanitized.replaceAll("Ù", "U");
        sanitized = sanitized.replaceAll("Û", "U");
        sanitized = sanitized.replaceAll("Ü", "U");
        sanitized = sanitized.replaceAll("Ũ", "U");

        sanitized = sanitized.replaceAll("ñ", "n");
        sanitized = sanitized.replaceAll("Ñ", "N");

        sanitized = sanitized.replaceAll("ç", "c");
        sanitized = sanitized.replaceAll("Ç", "C");

        sanitized = sanitized.replaceAll("ß", "ss");
        sanitized = sanitized.replaceAll("¿", "");
        sanitized = sanitized.replaceAll("¡", "");

        sanitized = sanitized.replaceAll("–", "-");

        return sanitized;
    }
}
