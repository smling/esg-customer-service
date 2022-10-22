CREATE TABLE Customer
(
    CustomerRef     VARCHAR(255)    PRIMARY KEY,
    CustomerName    VARCHAR(1000),
    AddressLine1    VARCHAR(200),
    AddressLine2    VARCHAR(200),
    Town            VARCHAR(200),
    County          VARCHAR(200),
    Country         VARCHAR(200),
    PostCode        VARCHAR(6)
);