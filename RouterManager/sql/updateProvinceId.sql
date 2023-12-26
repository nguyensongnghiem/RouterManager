use routermanager;
UPDATE routers 
SET 
    provinceId = substring(siteId,1,2)
WHERE
    name is not null;
