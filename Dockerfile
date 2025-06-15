FROM postgres:15

ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=postgres
ENV POSTGRES_DB=postgres


RUN apt-get update && \
    apt-get install -y postgresql-server-dev-15 build-essential git && \
    git clone https://github.com/pgvector/pgvector.git && \
    cd pgvector && \
    make && make install
