-- ========== CATEGORIES ==========
INSERT INTO categories (id, name, description)
VALUES (1, 'Lighting', 'Different lighting equipment for stage and photography'),
       (2, 'Cables', 'Various types of power and connection cables'),
       (3, 'Control Panels', 'Mixing and control panels for lights and sound');

-- ========== PRODUCTS ==========
INSERT INTO products (id, name, description, available, category_id)
VALUES (1, 'LED Spotlight', 'High power LED spotlight with adjustable brightness', true, 1),
       (2, 'Softbox Light', 'Studio softbox for photography lighting', true, 1),
       (3, 'HDMI Cable', '2 meter HDMI cable for video connection', true, 2),
       (4, 'Extension Cable', '10 meter power extension cable', true, 2),
       (5, 'Light Controller', 'Controller for stage light effects', true, 3),
       (6, 'Sound Mixer', 'Professional audio mixing panel', true, 3);

-- ========== USERS ==========
INSERT INTO users (id, name, email, password, role)
VALUES (1, 'Admin User', 'admin@example.com', 'password123', 'ADMIN');
