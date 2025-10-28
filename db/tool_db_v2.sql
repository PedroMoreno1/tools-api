-- ===========================================================
-- 💾 Estructura de base de datos: tools_db_v2
-- Descripción: Script SQL para generar las tablas del sistema
-- Autor: Pedro Moreno
-- Fecha: 2025-10-28
-- ===========================================================

CREATE DATABASE IF NOT EXISTS tools_db_v2
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

USE tools_db_v2;

-- ===========================================================
-- Tabla: category
-- ===========================================================
CREATE TABLE category (
  id_category BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id_category),
  UNIQUE KEY name (name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ===========================================================
-- Tabla: customer
-- ===========================================================
CREATE TABLE customer (
  id_customer BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  dni VARCHAR(8) NOT NULL,
  full_name VARCHAR(100) NOT NULL,
  phone VARCHAR(15) NOT NULL,
  email VARCHAR(100) NOT NULL,
  address VARCHAR(100) NOT NULL,
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id_customer),
  UNIQUE KEY dni (dni),
  UNIQUE KEY email (email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ===========================================================
-- Tabla: section
-- ===========================================================
CREATE TABLE section (
  id_section BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  description TEXT,
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id_section),
  UNIQUE KEY name (name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ===========================================================
-- Tabla: tool
-- ===========================================================
CREATE TABLE tool (
  id_tool BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
  description TEXT,
  image_url VARCHAR(255) DEFAULT NULL,
  section_id BIGINT UNSIGNED NOT NULL,
  category_id BIGINT UNSIGNED NOT NULL,
  total_quantity INT NOT NULL DEFAULT 0,
  available_quantity INT NOT NULL DEFAULT 0,
  rental_price DECIMAL(10,2) NOT NULL DEFAULT 0.00,
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  active TINYINT(1) DEFAULT 1,
  PRIMARY KEY (id_tool),
  UNIQUE KEY name (name),
  CONSTRAINT fk_tool_category FOREIGN KEY (category_id) REFERENCES category (id_category),
  CONSTRAINT fk_tool_section FOREIGN KEY (section_id) REFERENCES section (id_section)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ===========================================================
-- Tabla: ticket
-- ===========================================================
CREATE TABLE ticket (
  id_ticket BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  ticket_code VARCHAR(255) NOT NULL,
  customer_id BIGINT UNSIGNED DEFAULT NULL,
  rented_date DATETIME NOT NULL,
  returned_date DATETIME DEFAULT NULL,
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  is_active TINYINT(1) DEFAULT 1,
  PRIMARY KEY (id_ticket),
  UNIQUE KEY ticket_code (ticket_code),
  CONSTRAINT fk_ticket_person FOREIGN KEY (customer_id) REFERENCES customer (id_customer)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ===========================================================
-- Tabla: ticket_tool (relación muchos a muchos)
-- ===========================================================
CREATE TABLE ticket_tool (
  ticket_id BIGINT UNSIGNED NOT NULL,
  tool_id BIGINT UNSIGNED NOT NULL,
  quantity INT NOT NULL DEFAULT 1,
  rental_cost_unit DECIMAL(10,2) NOT NULL,
  total_cost_rent DECIMAL(10,2) NOT NULL,
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (ticket_id, tool_id),
  CONSTRAINT fk_tt_ticket FOREIGN KEY (ticket_id) REFERENCES ticket (id_ticket),
  CONSTRAINT fk_tt_tool FOREIGN KEY (tool_id) REFERENCES tool (id_tool)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ===========================================================
-- Tabla: return_details
-- ===========================================================
CREATE TABLE return_details (
  id_details BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  note VARCHAR(150) DEFAULT NULL,
  returned_by_name VARCHAR(100) DEFAULT NULL,
  returned_by_dni VARCHAR(8) DEFAULT NULL,
  ticket_id BIGINT UNSIGNED NOT NULL,
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id_details),
  UNIQUE KEY ticket_id (ticket_id),
  CONSTRAINT fk_return_ticket FOREIGN KEY (ticket_id) REFERENCES ticket (id_ticket)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ===========================================================
-- ✅ Fin del script
-- ===========================================================