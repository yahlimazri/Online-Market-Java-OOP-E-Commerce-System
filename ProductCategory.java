package ShaharAndYahli;

public enum ProductCategory {
    KIDS, ELECTRICITY, OFFICE, CLOTHING;

    public static ProductCategory fromString(String category) throws IllegalArgumentException {
        try {
            return ProductCategory.valueOf(category.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid product category: " + category);
        }
    }
}
