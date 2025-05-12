-- Insert sample users
INSERT INTO users (username, email, password, first_name, last_name, address, phone_number)
VALUES 
('john_doe', 'john@example.com', '$2a$10$xn3LI/AjqicFYZFruSwve.681477XaVNaUQbr1gioaWPn4t1KsnmG', 'John', 'Doe', '123 Main St, City', '1234567890'),
('jane_smith', 'jane@example.com', '$2a$10$xn3LI/AjqicFYZFruSwve.681477XaVNaUQbr1gioaWPn4t1KsnmG', 'Jane', 'Smith', '456 Oak St, Town', '9876543210');

-- Insert sample products
INSERT INTO products (name, description, price, stock_quantity, category)
VALUES 
('Laptop', 'High-performance laptop with latest specs', 999.99, 50, 'Electronics'),
('Smartphone', 'Latest model with advanced features', 699.99, 100, 'Electronics'),
('Coffee Maker', 'Automatic drip coffee maker', 49.99, 30, 'Appliances'),
('Desk Chair', 'Ergonomic office chair', 199.99, 25, 'Furniture');

-- Insert sample orders
INSERT INTO orders (user_id, total_amount, status, shipping_address, payment_method)
VALUES 
(1, 1699.98, 'COMPLETED', '123 Main St, City', 'CREDIT_CARD'),
(2, 249.98, 'PROCESSING', '456 Oak St, Town', 'PAYPAL');

-- Insert order items
INSERT INTO order_products (order_id, product_id, quantity, price_at_time)
VALUES 
(1, 1, 1, 999.99),
(1, 2, 1, 699.99),
(2, 3, 1, 49.99),
(2, 4, 1, 199.99);