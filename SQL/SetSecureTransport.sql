SET PERSIST require_secure_transport=ON;
SHOW GLOBAL VARIABLES LIKE 'tls_version';
SHOW SESSION STATUS LIKE 'Ssl_cipher_list';
