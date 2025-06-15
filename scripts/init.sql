CREATE EXTENSION IF NOT EXISTS vector;
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- DROP TABLE IF EXISTS vector_store;

CREATE TABLE IF NOT EXISTS vector_store
(
    embedding vector(1536) NOT NULL,
    metadata  jsonb,
    content   text,
    id        uuid default uuid_generate_v4() PRIMARY KEY
);
