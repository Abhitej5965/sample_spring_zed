package com.example.sample_spring.migration;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;

import java.sql.Statement;

public class V3__Add_Product_Timestamps extends BaseJavaMigration {

    @Override
    public void migrate(Context context) throws Exception {
        try (Statement statement = context.getConnection().createStatement()) {
            // Add updated_at column
            statement.execute(
                "ALTER TABLE products ADD COLUMN updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP"
            );

            // Update all existing products' updated_at to match their created_at
            statement.execute(
                "UPDATE products SET updated_at = created_at"
            );
        }
    }
}