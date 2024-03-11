create user "admin";
alter user "admin" with PASSWORD 'admin';
create schema "nat_geo";
alter schema "nat_geo" owner to "admin";