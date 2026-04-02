CREATE TABLE short_links (
  id SERIAL PRIMARY KEY,
  code VARCHAR(20) UNIQUE NOT NULL,
  url VARCHAR(2048) UNIQUE NOT NULL, 
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_short_links_code ON short_links(code);
CREATE INDEX idx_short_links_url ON short_links(url);
